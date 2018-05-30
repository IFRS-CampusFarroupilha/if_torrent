package iftorrent.conexao;

import static iftorrent.arquivos.torrent.ConstantesTorrent.CHAVE_MAPA_ERRO_DHT;
import static iftorrent.arquivos.torrent.ConstantesTorrent.CHAVE_MAPA_RESULTADO_DHT;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import the8472.bencode.BDecoder;
import the8472.bencode.BEncoder;
import the8472.mldht.cli.Server;
import static the8472.utils.Functional.tap;

/**
 *
 * @author Garren Souza
 */
public class ClienteDHT {

    private SocketChannel conexao_servidor;
    private boolean tarefa_concluida;
    private String diretorio_padrao;
    private long nascimento;
    private long ultima_parada = -1;
    private boolean pausado;

    public ClienteDHT() throws IOException {
        this.conexao_servidor = SocketChannel.open(new InetSocketAddress(InetAddress.getLoopbackAddress(), Server.SERVER_PORT));
        this.conexao_servidor.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
        this.conexao_servidor.socket().setSoTimeout(0);
        this.diretorio_padrao = Paths.get("").toAbsolutePath().normalize().toString();
        this.pausado = false;
    }

    public HashMap executa_comando(String[] args) throws Exception {
        if (cliente_ativo()) {
            List<byte[]> argumentos = Arrays.asList(args).stream().map(s -> s.getBytes(StandardCharsets.UTF_8)).collect(Collectors.toList());
            Map<String, Object> comando = new HashMap<>();
            HashMap<String, String> resultado = new HashMap<>();
            comando.put("arguments", argumentos);
            comando.put("cwd", diretorio_padrao.getBytes(StandardCharsets.UTF_8));

            BEncoder codificador = new BEncoder();

            ByteBuffer buffer = codificador.encode(comando, 65535);

            this.conexao_servidor.write(tap(ByteBuffer.allocate(4), b -> b.putInt(0, buffer.limit())));
            this.conexao_servidor.write(buffer);
            //this.serverConnection.shutdownOutput();

            ByteBuffer cabecalho = ByteBuffer.allocate(4);
            ByteBuffer mensagem;
            this.tarefa_concluida = false;

            while (this.conexao_servidor.isOpen() && !this.tarefa_concluida) {
                cabecalho.clear();
//                long time = System.currentTimeMillis();
                this.conexao_servidor.read(cabecalho);
//                System.out.println("[NEW \"PACKET\"] \nheader read: "+(System.currentTimeMillis() - time)+"ms");
//                System.out.println("Header: "+cabecalho.toString());
//                time = System.currentTimeMillis();
                cabecalho.flip();
                int tam = cabecalho.getInt(0);
                mensagem = ByteBuffer.allocate(tam);
                this.conexao_servidor.read(mensagem);
//                System.out.println("Message read: "+(System.currentTimeMillis() - time)+"ms");
//                System.out.println("Message: "+mensagem.toString());
                mensagem.flip();
                BDecoder decodificador = new BDecoder();
//                time = System.currentTimeMillis();
                Map<String, Object> mapa_resposta = decodificador.decode(mensagem);
//                System.out.println("Map decode: "+(System.currentTimeMillis() - time)+"ms");
//                System.out.println();
                String acao = new String((byte[]) mapa_resposta.get("action"), StandardCharsets.ISO_8859_1);

                switch (acao) {
                    case "sysout":
                        adiciona_item(new String((byte[]) mapa_resposta.get("payload"), StandardCharsets.UTF_8), CHAVE_MAPA_RESULTADO_DHT, resultado);
                        break;
                    case "syserr":
                        adiciona_item(new String((byte[]) mapa_resposta.get("payload"), StandardCharsets.UTF_8), CHAVE_MAPA_ERRO_DHT, resultado);
                        break;
                    case "exit":
                        this.tarefa_concluida = true;
                        adiciona_item(mapa_resposta.get("exitCode"), CHAVE_MAPA_ERRO_DHT, resultado);
                        break;
                    default:
                        throw new IllegalStateException("unexpected action " + acao);
                }
            }
            return resultado;
        }
        return null;
    }

    public long finaliza_cliente() throws IOException, InterruptedException {
        if (this.conexao_servidor.isOpen()) {
            while (this.ocupado()) {
                Thread.sleep(125);
            }
            this.conexao_servidor.close();
        }
        return get_tempo_de_atividade();
    }

    private void reinicia_socket() throws IOException {
        this.conexao_servidor = SocketChannel.open(new InetSocketAddress(InetAddress.getLoopbackAddress(), Server.SERVER_PORT));
        this.conexao_servidor.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
        this.conexao_servidor.socket().setSoTimeout(0);
    }

    public boolean reativar_cliente() throws IOException {
        if (this.pausado) {
            if (!this.conexao_servidor.isOpen()) {
                this.reinicia_socket();
            }
            this.pausado = false;
            return true;
        }
        return false;
    }

    public void pausa_cliente() {
        this.ultima_parada = System.currentTimeMillis();
        this.pausado = true;
    }

    public long getUltima_parada() {
        return ultima_parada;
    }

    private void adiciona_item(Object item, Object chave, HashMap mapa) {
        if (item == null
                || chave == null
                || mapa == null) {
            return;
        }
        if (mapa.containsKey(chave)) {
            ((ArrayList) mapa.get(chave)).add(item);
        } else {
            ArrayList lista = new ArrayList();
            lista.add(item);
            mapa.put(chave, lista);
        }
    }

    public boolean cliente_ativo() {
        return this.conexao_servidor.isOpen() && !this.pausado;
    }

    public void set_diretorio_padrao(String caminho) {
        this.diretorio_padrao = caminho;
    }

    public String get_diretorio_padrao() {
        return diretorio_padrao;
    }

    public long get_tempo_de_atividade() {
        return System.currentTimeMillis() - this.nascimento;
    }

    public boolean ocupado() {
        return !this.tarefa_concluida;
    }
}
