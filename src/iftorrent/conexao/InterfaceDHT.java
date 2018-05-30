package iftorrent.conexao;

import com.turn.ttorrent.common.Peer;
import iftorrent.ConstantesGerais;
import iftorrent.arquivos.torrent.ConstantesTorrent;
import static iftorrent.arquivos.torrent.ConstantesTorrent.ARGUMENTO_COMANDO_OBTER_PEERS_RAPIDO;
import static iftorrent.arquivos.torrent.ConstantesTorrent.CHAVE_MAPA_RESULTADO_DHT;
import static iftorrent.arquivos.torrent.ConstantesTorrent.COMANDO_OBTER_PEERS_DHT;
import static iftorrent.arquivos.torrent.ConstantesTorrent.COMANDO_OBTER_TORRENT_DHT;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import the8472.mldht.cli.Server;

/**
 *
 * @author Garren Souza
 */
public class InterfaceDHT {

    private long tempo_de_atividade;
    private ClienteDHT cliente_DHT;

    /**
     * Método estático que retorna uma nova instância de ClienteDHT
     *
     * @return
     * @throws Exception
     * @see ClienteDHT
     */
    private ClienteDHT get_Cliente() throws Exception {
        return new ClienteDHT();
    }

    /**
     * Construtor da classe Interface_DHT
     *
     * @throws Exception
     */
    public InterfaceDHT() throws Exception {
        this.tempo_de_atividade = System.currentTimeMillis();
        this.cliente_DHT = get_Cliente();
        this.cliente_DHT.set_diretorio_padrao(ConstantesGerais.PASTA_TORRENTS);
    }

    /**
     * Método que busca um arquivo torrent à partir de seu info_hash, que é
     * recebido como parâmetro de entrada na forma de uma instância
     *
     * @param info_hash - String
     * @throws Exception
     */
    public boolean get_Torrent(String info_hash) throws Exception {
        if (InterfaceDHT.confere_info_hash(info_hash)) {
            String[] parametros = {COMANDO_OBTER_TORRENT_DHT, info_hash};
            HashMap mapa = (HashMap) this.cliente_DHT.executa_comando(parametros);
            ArrayList<String> resultado = (ArrayList<String>) mapa.get(CHAVE_MAPA_RESULTADO_DHT);
            if (resultado.size() == 3) {
                String caminho_arquivo = (resultado.get(1).split(" "))[3];
                caminho_arquivo = caminho_arquivo.substring(0, caminho_arquivo.length() - 1);
                Path source = Paths.get(caminho_arquivo);
                String novo_nome = resultado.get(0).split(" ")[2];
                novo_nome = novo_nome.substring(0, novo_nome.length() - 1);
                Files.move(source, source.resolveSibling(novo_nome + ConstantesGerais.EXTENSAO_TORRENTS));
                return true;
            }
        }
        return false;
    }

    /**
     * Método que confere validade à um hash gerado à partir do algoritmo
     * SHA1(160 bits) representado atraves de uma cadeia de caracteres em base
     * hexadecimal(160/4 = 40 caracteres).
     *
     * @param info_hash
     * @return boolean - Retorna verdadeiro se e apenas se o hash recebido
     * apresentar-se válido
     */
    public static boolean confere_info_hash(String info_hash) {
        Objects.requireNonNull(info_hash);
        if (info_hash.length() < 40) {
            return false;
        }
        char[] caracteres_hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D', 'E', 'F'};
        for (char caractere : info_hash.toCharArray()) {
            boolean encontrado = false;
            for (char caractere_hex : caracteres_hex) {
                if (caractere == caractere_hex) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                return false;
            }
        }
        return true;
    }

    /**
     * Método que confere se o nodo servidor (serviço de DHT) se encontra ativo
     *
     * @return boolean - Retorna verdadeiro apenas se o nodo servidor estiver em
     * atividade no momento da chamada
     */
    public static boolean nodo_servidor_online() {
        try {
            SocketChannel socket_teste;
            socket_teste = SocketChannel.open();
            socket_teste.socket().setSoTimeout(0);
            socket_teste.connect(new InetSocketAddress(InetAddress.getLoopbackAddress(), Server.SERVER_PORT));
            socket_teste.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public List<Peer> get_Peers(String info_hash) throws Exception {
        if (InterfaceDHT.confere_info_hash(info_hash)) {
            String[] parametros = {COMANDO_OBTER_PEERS_DHT, ARGUMENTO_COMANDO_OBTER_PEERS_RAPIDO, info_hash};
            ArrayList<Peer> lista_pares = new ArrayList();
            HashMap mapa = (HashMap) this.cliente_DHT.executa_comando(parametros);
            if (mapa != null) {
                ArrayList<String> resultado = (ArrayList) mapa.get(CHAVE_MAPA_RESULTADO_DHT);
                for (String endereco : resultado) {
                    String transicao = (endereco.split(" "))[1];
                    transicao = transicao.substring(6, transicao.length());
                    String[] endereco_final = transicao.split(":");
                    Peer par = new Peer(endereco_final[0], Integer.parseInt(endereco_final[1]));
                    lista_pares.add(par);
                }
                return lista_pares;
            }
        }
        return null;
    }

    /**
     * Método que retorna o caminho atribuído aos arquivos descarregados através
     * desta instância de <b>Client</b>
     *
     * @return String - Instância de String que contem o caminho em questão
     */
    public String get_caminho_download() {
        return this.cliente_DHT.get_diretorio_padrao();
    }

    /**
     * Método que insere um caminho que será atribuído oas arquivos
     * descarregados através desta instância de <b>Client</b>
     *
     * @param novo_caminho - Instância de String que contém o novo caminho
     * @return boolean - Retorna verdadeiro apenas se houver modificação, no
     * caso de tratar-se do mesmo caminho, retorna-se false
     */
    public boolean set_caminho_dowload(String novo_caminho) {
        if (this.cliente_DHT.get_diretorio_padrao().equals(novo_caminho)) {
            this.cliente_DHT.set_diretorio_padrao(novo_caminho);
            return true;
        }
        return false;
    }
}
