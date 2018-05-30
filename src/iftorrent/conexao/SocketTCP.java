package iftorrent.conexao;

import iftorrent.arquivos.ConstantesLogger;
import iftorrent.arquivos.Logger;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/** 
 * Classe que representa uma "interface" de envio de pacotes através do
 * protocolo TCP de comunicacao
 * 
 *
 * @author Garren Souza
 */
public class SocketTCP implements Runnable {

    private SocketChannel socket;
    private ByteBuffer transicao;
    private List<ByteBuffer> lista_envio;
    private final int tamanho_lista;
    private long enviado;
    private final long birth;
    private boolean rodando;
    public boolean entrando;
    private boolean enviando;
    private boolean adicionado;

    /**
     * Método construtor da classe Socket_TCP
     *
     * @param porta_remota - Porta que será acessada no dispositivo remoto
     * @param endereco_remoto - Endereço que será acessado no dispositivo remoto
     * @param tamanho_lista - Tamanho máximo da lista de pacotes
     * @throws IOException - A exceção pode ser lançada no caso do objeto
     * <b>InetSocketAddress</b> encontrar irregularidades em sua construção,
     * assim como pode ser lançada se ocorrer um erro na abertura do socket
     * (SocketChannel) da classe
     * @see InetSocketAddress
     * @see SocketChannel
     */
    public SocketTCP(int porta_remota, String endereco_remoto, int tamanho_lista) throws IOException {
        this.socket = SocketChannel.open();
        this.socket.connect(new InetSocketAddress(endereco_remoto, porta_remota));
        this.tamanho_lista = tamanho_lista;
        this.lista_envio = new ArrayList<>();
        this.birth = System.currentTimeMillis();
        this.enviando = false;
        this.rodando = true;
    }

    /**
     * Método construtor da classe SOcketTCP
     *
     * @param canal Objeto do tipo SocketChannel
     * @param tamanho_lista Tamanho do buffer de envio
     */
    public SocketTCP(SocketChannel canal, int tamanho_lista) {
        this.socket = canal;
        this.tamanho_lista = tamanho_lista;
        this.lista_envio = new ArrayList<>();
        this.birth = System.currentTimeMillis();
        this.enviando = false;
        this.rodando = true;
    }

    /**
     * Método que adiciona o ByteBuffer recebido como parâmetro à lista de
     * pacotes que serão enviados se houver espaço disponível, senão a thread
     * aguarda em esperas de 10 milisegundos, até que o pacote possa ser
     * adicionado, seguindo com a execução
     *
     * @param buffer - ByteBuffer que contém os dados sobre os quais se têm o
     * interesse de enviar
     * @throws InterruptedException - esta exceção pode ser lançada no caso da
     * thread ser interrompida enquanto "dorme"
     * @see ByteBuffer
     */
    public void envia(ByteBuffer buffer) throws InterruptedException {
        this.transicao = ByteBuffer.allocate(buffer.array().length);

        this.transicao.position(0);
        this.transicao.put(buffer.array());
        this.adicionado = false;
        while (!adicionado) {
            if (lista_envio.size() < tamanho_lista) {
                transicao.position(0);
                lista_envio.add(transicao);
                adicionado = true;
            } else {
                Thread.sleep(10);
            }
        }
        transicao.clear();
    }

    /**
     * Método que envia os dados presentes na lista um (cada ByteBuffer) a um
     * através do socket da classe alterando o valor da variável <b>enviando</b>
     * antes e depois do processo de envio. (socket.write())
     *
     * @param buffer - Objeto do tipo ByteBuffer que será "escrito" no socket
     * (efetivamente enviado)
     * @throws IOException - A exceção é lançada no caso de ocorrer algum erro
     * relacionado à escrita (SocketChannel.write())
     * @see SocketChannel, ByteBuffer
     */
    private void escreve(ByteBuffer buffer) throws IOException {
        buffer.flip();
        while (buffer.hasRemaining()) {
            enviando = true;
            enviado += socket.write(buffer);
            enviando = false;
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
            try {
                while (!lista_envio.isEmpty()) {
                    escreve(lista_envio.get(0));
                    lista_envio.remove(lista_envio.get(0));
                }
            } catch (IOException ex) {
                Logger.escrever(ConstantesLogger.ERRO, SocketTCP.class.getName());
                Logger.escrever_erro(ex);
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
     * escrita dos dados no SocketChannel da classe
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
        transicao = null;
    }

    /**
     * Método que finaliza a execução da classe através da entrada de um tipo de
     * finalização
     *
     * @param type TiposFinalizacao tipo de finalização escolhido
     * @throws IOException - Exceção lançada no caso de ocorrer um erro na
     * obtenção do tamanho da lista de envio (lista_envio)
     * @see TiposFinalizacao
     */
    public void encerra(TiposFinalizacao type) throws IOException {
        rodando = false;
        if (!lista_envio.isEmpty()) {
            switch (type) {
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
     * Método que retorna o tempo de atividade da classe
     *
     * @return - long tempo de atividade da classe (contado à partir do momento
     * em que a mesma é construída)
     */
    public long obter_uptime() {
        return (System.currentTimeMillis() - birth) / 1000;
    }

    /**
     * Método que retorna a porta inserida na construção da classe
     *
     * @return int - porta remota
     */
    public int obter_porta_remota() {
        return socket.socket().getPort();
    }

    /**
     * Método que retorna um endereço inserido na construção da classe
     *
     * @return String - endereço remoto
     */
    public String obter_endereco_remoto() {
        return socket.socket().getInetAddress().getHostAddress();
    }

    /**
     * Retorna a quantidade de bytes enviados
     *
     * @return long - quantidade de bytes enviados
     */
    public long obter_enviado() {
        return enviado;
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
     * @return boolean - true se e apenas se estiver sendo alguma operação de
     * escrita no socket da classe (envio de dados através do socket da classe).
     */
    public boolean esta_enviando() {
        return enviando;
    }
}
