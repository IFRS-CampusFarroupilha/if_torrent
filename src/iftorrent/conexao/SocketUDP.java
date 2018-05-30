package iftorrent.conexao;

import iftorrent.arquivos.ConstantesLogger;
import iftorrent.arquivos.Logger;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ArrayList;
import java.util.List;

/** 
 * Classe que representa uma "interface" de envio de pacotes através do
 * protocolo UDP de comunicacao
 * 
 *
 * @author Garren Souza
 */
public class SocketUDP implements Runnable {

    private List<ByteBuffer> lista_envio;
    private final int tamanho_lista;
    private long bytes_enviados;
    private boolean rodando;
    private boolean enviando;
    private DatagramChannel socket;
    private final InetSocketAddress endereco;
    private final long birth;
    boolean bloqueio;
    boolean adicionado;

    /**
     * Construtor da classe que representa um socket dedicado ao envio de dados
     * através do protocolo UDP
     *
     * @param endereco_remoto - String Endereco remoto no qual o socket será
     * travado
     * @param porta - int Porta remota na qual o socket será travado
     * @param tamanho_lista
     * @throws IOException - Lançada no caso de ocorrer um erro na conexão do
     * socket (travamento do socket)
     * @see DatagramChannel
     */
    SocketUDP(String endereco_remoto, int porta, int tamanho_lista) throws IOException {
        this.socket = DatagramChannel.open();
        this.socket.configureBlocking(false);
        this.endereco = new InetSocketAddress(endereco_remoto, porta);
        this.socket.connect(endereco);
        this.rodando = true;
        this.bloqueio = false;
        this.birth = System.currentTimeMillis();
        this.lista_envio = new ArrayList();
        this.tamanho_lista = tamanho_lista;
    }

    /**
     * Método que adiciona o ByteBuffer recebido como parâmetro à lista de
     * pacotes que serão enviados
     *
     * @param pacote ByteBuffer - Objeto do tipo ByteBuffer contendo os dados
     * que serão enviados
     * @throws InterruptedException - Lançada quando a thread é interrompida
     * enqunto "dorme"
     * @see ByteBuffer
     */
    public void envia(ByteBuffer pacote) throws InterruptedException {
        adicionado = false;
        while (!adicionado) {
            if (lista_envio.size() < tamanho_lista) {
                lista_envio.add(pacote);
                adicionado = true;
            } else {
                Thread.sleep(10);
            }
        }
    }

    /**
     * Método que envia os dados presentes na lista um (cada ByteBuffer) a um
     * através do socket da classe alterando o valor da variável enviando antes
     * e depois do processo de envio (socket_out.write()).
     *
     * @param pacote ByteBuffer - Objeto que contém os dados que serão enviados
     * @throws IOException - Lançada quando ocorre algum erro de E/S no método
     * de envio do socket (socket.send())
     */
    private void escreve(ByteBuffer pacote) throws IOException {
        if (!bloqueio) {
            while (pacote.hasRemaining()) {
                enviando = true;
                socket.send(pacote, endereco);
                bytes_enviados += pacote.array().length;
                enviando = false;
            }
        }
        /*
         *     Limitação de upload (bytes/ms) por par ainda será implementada
         */
    }

    /**
     * Método implementado de Runnable que contem o ciclo de funcionamento da
     * classe
     *
     */
    @Override
    public void run() {
        while (rodando) {
            for (int i = 0; i < lista_envio.size(); i++) {
                try {
                    escreve(lista_envio.get(i));
                    lista_envio.remove(i);
                } catch (IOException ex) {
                    Logger.escrever(ConstantesLogger.ERRO, SocketUDP.class.getName());
                }
            }
        }
    }

    /**
     * Método que finaliza a execução do socket à partir do envio de uma
     * determinada quantidade de ByteBufers presentes na lista, quantidade esta
     * que é especificada à partir do parâmetro <b>limite</b>
     *
     * @param limite int - Quantidade de elementos da lista de envio que serão
     * enviados antes do fechamento do socket
     * @throws IOException - Lançada no caso de ocorrer algum erro relacionado à
     * escrita dos dados no SocketChannel da classe ou ao fechamento do socket
     * @see TiposFinalizacao
     * @see ByteBuffer
     */
    public void envio_final(int limite) throws IOException {
        if (limite > 0) {
            int cont = 0;
            while (cont < limite) {
                escreve(lista_envio.get(0));
                lista_envio.remove(lista_envio.get(0));
                cont++;
            }
        }
        lista_envio.clear();
        socket.socket().close();
        socket.close();
        socket = null;
        lista_envio = null;
    }

    /**
     * Método que finaliza a execução da classe através da entrada de um tipo de
     * finalização: <b>contem três tipos de finalização, o de finalização
     * imediata (DIE), o de finalização após envio de todo o conteúdo presente
     * na lista (FIRST_FINISH_THAT), finalização após o envio de 2/3 do conteúdo
     * total da lista (GO_TO_BED), e finalização após o envio de 1/3 do conteúdo
     * da lista (ONE_BREATHE).</b>
     *
     * @param tipo TiposFinalizacao tipo de finalização escolhido
     * @throws IOException - Lançada no caso de ocorrer algum erro relacionado à
     * escrita dos dados no SocketChannel da classe ou ao fechamento do socket
     * @see TiposFinalizacao
     * @see DatagramChannel
     */
    public void encerra(TiposFinalizacao tipo) throws IOException {
        rodando = false;
        if (!lista_envio.isEmpty()) {
            switch (tipo) {
                case DIE://Termina o processo sem enviar nada
                    envio_final(0);
                    break;
                case FIRST_FINISH_THAT://permite o envio de TUDO o que estiver na lista de envio
                    envio_final(lista_envio.size());
                    break;
                case GO_TO_BED://permite o envio de 2/3 do que houver dentro da lista
                    envio_final(lista_envio.size() * (2 / 3));
                    break;
                case ONE_BREATHE://permite o envio de uma pequena parte da lista (1/3)
                    envio_final(lista_envio.size() * (1 / 3));
                    lista_envio.clear();
                    break;
            }
        }
    }

    /**
     * Retorna a quantidade de bytes enviados através deste socket.
     *
     * @return long - Quantidade de bytes enviados através deste socket.
     */
    public long obter_enviado() {
        return bytes_enviados;
    }

    /**
     * Método que retorna o tempo de atividade da classe
     *
     * @return - long tempo de atividade da classe (contado à partir do momento
     * em que a mesma é construída)
     */
    public long obter_uptime() {
        return (System.currentTimeMillis() - birth) / 1000;
    }

    /**
     * Método que retorna verdadeiro se o método run esta em funcionamento
     *
     * @return boolean - true se e apenas se o método run estiver funcionando
     */
    public boolean esta_rodando() {
        return rodando;
    }

    /**
     * Método que retorna verdadeiro se no momento da requisição estiver sendo
     * feita uma operação de escrita no socket da classe (envio de dados em
     * andamento).
     *
     * @return boolean - true se e apenas se estiver sendo realizada alguma
     * operação de escrita no socket da classe (envio de dados).
     */
    public boolean esta_enviando() {
        return enviando;
    }

    /**
     * Método que retorna o endereço inserido na construção da classe
     *
     * @return String - endereço remoto
     */
    public String obter_endereco_remoto() {
        return socket.socket().getInetAddress().getHostAddress();
    }

    /**
     * Método que retorna a porta inserida na construção da classe
     *
     * @return int - porta remota
     */
    public int obter_porta() {
        return socket.socket().getPort();
    }

    /**
     * Método que retorna a quantidade de espaços livres que existem na lista de
     * envio
     *
     * @return
     */
    public int obter_espaco_livre() {
        return tamanho_lista - lista_envio.size();
    }

    /**
     * Método que checa se a lista de envio se encontra vazia
     *
     * @return - true se e apenas se a lista não contiver nenhum objeto do tipo
     * BiteBuffer
     */
    public boolean lista_esta_vazia() {
        return lista_envio.isEmpty();
    }

    /**
     * Método que esvazia (limpa) a lista de envio
     *
     */
    public void limpa_lista() {
        this.bloqueio = true;
        lista_envio.clear();
        this.bloqueio = false;
    }
}
