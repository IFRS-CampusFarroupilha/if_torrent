package iftorrent.conexao;

import iftorrent.conexao.gerenciamento.GerenciadorPares;
import iftorrent.conexao.gerenciamento.Par;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

//Talvez esta classe seja descontinuada
/**
 * Classe de monitoramento do servidor TCP, conta com um seletor para realizar
 * operações de E/S sobre os canais adicionados através de utilização do
 * Servidor TCP (Classe ServidorTCP)
 *
 * @author Garren Souza
 */
public class MonitorTCP {

    private Selector seletor;
    private int limite_conexoes;
    private final ServidorTCP servidor;
    private int conexoes;
    private Thread execucao_servidor;
    private GerenciadorPares gerenciador;

    /**
     * Construtor da classe Monitor_TCP
     *
     * @param limite - limite de conexões
     * @throws IOException - Exceção lançada no caso de occorência de um erro de
     * E/S na abertura do seletor
     */
    MonitorTCP(int porta, int limite, GerenciadorPares gerenciador) throws IOException {
        seletor = Selector.open();
        this.limite_conexoes = limite;
        this.servidor = new ServidorTCP(porta, this);
        this.conexoes = 0;
        this.gerenciador = gerenciador;
    }

    /**
     * Método que coloca o servidor da classe em funcionamento à partir da
     * criação e inicialização de uma Thread
     *
     */
    public void inicia_servidor() {
        execucao_servidor = new Thread(this.servidor);
        execucao_servidor.start();
    }

    /**
     * Checa se o canal já existe na lista do monitor à partir de conferência de
     * endereço
     *
     * @param canal - O canal de socket sobre o qual se deseja realizar o teste
     * @return boolean - Retorna true se e apenas se já existir um SocketChannel
     * com este endereco (HostAddress) registrado neste monitor
     */
    public boolean existe_no_monitor(SocketChannel canal) {
        if (canal.isRegistered()) {
            Set<SelectionKey> conjunto_chaves = seletor.selectedKeys();
            Iterator<SelectionKey> iterador = conjunto_chaves.iterator();

            while (iterador.hasNext()) {
                String endereco_canal = canal.socket().getInetAddress().getHostAddress();
                if (endereco_canal.equals(iterador.next().attachment())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Método que adiciona o SocketChannel ao monitor
     *
     * @param canal - o SocketChannel que se deseja adicionar à lista e seletor
     * de monitoramento
     * @return boolean - true se e apenas se o canal for registrado no seletor
     * @throws ClosedChannelException - Exceção é lançada no caso em que o canal
     * se encontra fechado
     */
    public boolean adiciona_conexao(SocketChannel canal) throws ClosedChannelException, IOException {
        if (this.conexoes < this.limite_conexoes) {
            if (!existe_no_monitor(canal)) {
                String endereco = canal.socket().getInetAddress().getHostAddress();
                SocketTCP socket = new SocketTCP(canal, 32);
                Par par = new Par(socket, this.gerenciador);
                
                canal.register(seletor, SelectionKey.OP_READ | SelectionKey.OP_WRITE, endereco);
                conexoes++;
                if (conexoes == limite_conexoes) {
                    this.pausa_servidor(true);
                }                
                socket.entrando = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Método que pausa o recebimento de novas conexões do objeto de tipo
     * ServidorTCP pertencente à esta classe
     *
     * @param opcao - boolean que representa o status de recebimento de novas
     * conexoes, se verdadeiro, o servidor se encontra bloqueado à novas
     * conexões, do contrário, está disponível
     */
    public void pausa_servidor(boolean opcao) {
        this.servidor.pausa(opcao);
    }

    /**
     * Método que remove um canal do seletor da classe utilizando-se do objeto
     * do tipo SelectioKey recebido como parâmetro
     *
     * @param key - SelectionKey Objeto identificador do canal no seletor
     * @throws IOException - Lançada no caso de ocorrer um erro de E/S durante o
     * processo de fechamento deste canal.
     */
    public void remove_canal(SelectionKey key) throws IOException {
        if (existe_no_monitor((SocketChannel) key.channel())) {
            key.channel().close();
            key.cancel();
            if (conexoes == limite_conexoes) {
                this.pausa_servidor(false);
            }
            conexoes--;
        }
    }

    /**
     * Método que retorna o limite de conexoes estabelecido na classe
     * (delimitará a quantidade de conexoes no seletor)
     *
     * @return int - limite de conexoes
     */
    public int getLimite_conexoes() {
        return limite_conexoes;
    }

    public SocketChannel getCanal(String endereco) {
        Set conjunto = this.seletor.keys();
        Iterator iterador = conjunto.iterator();

        while (iterador.hasNext()) {
            SelectionKey chave = (SelectionKey) iterador.next();
            if (chave.attachment().equals(endereco)) {
                return (SocketChannel) chave.channel();
            }
        }
        return null;
    }

    /**
     * Método que insere o parâmetro recebido na variável em questão
     * (limite_conexoes)
     *
     * @param limite_conexoes - int limite de conexoes do monitor
     */
    public void setLimite_conexoes(int limite_conexoes) {
        this.limite_conexoes = limite_conexoes;
    }

    /**
     * Metodo que retorna o seletor (objeto do tipo Selector) da classe, este
     * que poderá ser a chave para a implementação do gerenciamento de pares
     *
     * @return Selector - Seletor de canais à partir do qual serão realizadas as
     * devidas operações de E/S
     */
    public Selector obter_seletor() {
        return this.seletor;
    }

    /**
     * Método de encerramento da classe monitor
     *
     * @throws IOException Exceção que é lançada em caso de erro durante o
     * fechamento do servidor
     */
    public void encerra_monitor() throws IOException {
        this.servidor.encerra();
        this.limite_conexoes = 0;
        this.seletor = null;
        this.conexoes = 0;
        // TO DO Encerramento da Thread Servidor
    }
}
