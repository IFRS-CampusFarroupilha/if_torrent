package iftorrent.conexao;

import iftorrent.arquivos.ConstantesLogger;
import iftorrent.excecoes.ESExcecao;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import iftorrent.arquivos.Logger;

/**
 * Classe que representa um servidor dedicado ao estabelecimento de conexões
 * através do protocolo TCP (Transmission Control Protocol).
 *
 * @author Garren Souza
 */
public class ServidorTCP implements Runnable {

    private ServerSocketChannel servidor;
    private MonitorTCP monitor;
    private boolean rodando;
    private boolean pausado;
    private final long nascimento;

    /**
     * Construtor da classe Servidor_TCP
     *
     * @param porta - porta sob a qual será estabelecido o servidor
     * @param monitor - Classe de gerenciamento do servidor
     * @throws IOException
     * @throws ESExcecao
     */
    ServidorTCP(int porta, MonitorTCP monitor) throws IOException, ESExcecao {
        this.monitor = monitor;
        this.servidor = ServerSocketChannel.open();
        this.servidor.bind(new InetSocketAddress(porta));
        this.servidor.configureBlocking(false);
        this.nascimento = System.currentTimeMillis();
        rodando = true;
    }

    /**
     * Método que checa a existência de conexões pendentes com relação ao
     * servidor da classe
     *
     * @throws IOException - Lançada no caso de ocorrer um erro durante a
     * realização de operações sobre o canal ('accept()', 'configureBlocking()'
     * e 'monitor.adiciona_conexao()')
     */
    private void recebe() throws IOException {
        if (!pausado) {
            SocketChannel canal = servidor.accept();

            if (canal != null) {
                canal.configureBlocking(false);
                this.monitor.adiciona_conexao(canal);
                canal = null;
            }
        }
    }

    /**
     * Método que retorna em milisegundos o período de tempo no qual a classe
     * existe (contado à partir de sua construção).
     *
     * @return long - Tempo de "vida" da classe
     */
    public long tempo_de_vida() {
        return System.currentTimeMillis() - this.nascimento;
    }

    /**
     * Método implementado de Runnable
     *
     */
    @Override
    public void run() {
        while (rodando) {
            try {
                recebe();
            } catch (IOException ex) {
                Logger.escrever(ConstantesLogger.ERRO, "Falha no processo de listening do servidor");
                Logger.escrever_erro(ex);
            }
        }
    }

    /**
     * Método que controla a execução do servidor, pausando o recebimento de
     * novas conexões
     *
     * @param opcao boolean - sendo verdadeiro, o recebimento de novas conexões
     * é pausado, do contrário o recebimento de conexões se mantém
     */
    public void pausa(boolean opcao) {
        this.pausado = opcao;
    }

    /**
     * Método que encerra a execução do servidor
     *
     * @return long - Tempo de atividade do servidor em milisegundos
     * @throws IOException - Lançada em caso de erro durante o fechamento do
     * canal servidor ou do socket pertencente à este canal
     */
    public long encerra() throws IOException {
        this.pausado = true;
        this.rodando = false;
        this.servidor.socket().close();
        this.servidor.close();
        this.monitor = null;
        return this.tempo_de_vida();
    }
}
