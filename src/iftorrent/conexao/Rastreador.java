package iftorrent.conexao;

import com.turn.ttorrent.common.Torrent;
import com.turn.ttorrent.tracker.TrackedTorrent;
import com.turn.ttorrent.tracker.Tracker;
import iftorrent.arquivos.Logger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.logging.Level;

/**
 * Classe que representa um rastreador ("tracker").
 *
 * @author Garren Souza
 * @author Rafael Coelho
 */
public class Rastreador {

    private final Tracker rastreador;
    private final InetSocketAddress endereco;
    private String pasta_busca;

    /**
     * Construtor da classe Rastreador
     *
     * @param host String - Endereço aonde se deseja estabelecer o rastreador
     * @param porta int - Porta na qual o rastreador estará recebendo as
     * requisições
     * @param pasta_busca String - Pasta na qual o rastreador irá obter os
     * arquivos que devem ser anunciados pelo mesmo
     *
     * @throws IOException - Exceção lançada no caso de ocorrer um erro durante
     * a construção da instância de Tracker
     */
    Rastreador(String host, int porta, String pasta_busca) throws IOException {
        endereco = new InetSocketAddress(host, porta);
        this.pasta_busca = pasta_busca;
        this.rastreador = new Tracker(endereco);
    }

    /**
     * Método estático que retorna uma nova instância de Rastreador
     *
     * @param host String - Endereço aonde se deseja estabelecer o rastreador
     * @param porta int - Porta na qual o rastreador estará recebendo as
     * requisições
     * @param pasta_busca String - Pasta na qual o rastreador irá obter os
     * arquivos que devem ser anunciados pelo mesmo
     *
     * @return Rastreador - Uma nova instância de Rastreador
     *
     * @throws IOException Exceção lançada no caso de ocorrer um erro durante a
     * construção da instância de Tracker
     */
    public static Rastreador novo_rastreador(String host, int porta, String pasta_busca) throws IOException, IOException {
        return new Rastreador(host, porta, pasta_busca);
    }

    /**
     * Método que inicia o funcionamento do tracker, atribuindo ao mesmo os
     * arquivos contidos pelo caminho inserido durante a construção da classe.
     *
     * @throws IOException - Lançada no caso de haver um erro ao anunciar o
     * arquivo.
     * @throws NoSuchAlgorithmException - Lançada no caso de haver um erro ao
     * anunciar o arquivo.
     */
    public void iniciar() throws IOException, NoSuchAlgorithmException {
        FilenameFilter filtro_arquivo = (File dir, String name) -> name.endsWith(".torrent");

        for (File arquivo_torrent : new File(pasta_busca).listFiles(filtro_arquivo)) {
            this.rastreador.announce(TrackedTorrent.load(arquivo_torrent));
        }
        this.rastreador.start();
    }

    /**
     * Finaliza a execução do rastreador.
     */
    public void finaliza() {
        this.rastreador.stop();
    }

    /**
     * Remove o arquivo "torrent" em questão do rastreador imediatamente.
     *
     * @param arquivo_torrent_codificado
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void remove_torrent(byte[] arquivo_torrent_codificado) throws IOException, NoSuchAlgorithmException {
        this.remove_torrent(arquivo_torrent_codificado, 0);
    }

    /**
     * Remove o arquivo "torrent" em questão do rastreador após um determinado
     * período de tempo.
     *
     * @param arquivo_torrent_codificado byte[] - Arquivo ".torrent" ainda sob
     * codificação B-encode
     * @param atraso int - Período de tempo compreendido pelo intervalo da
     * requisição de remoção do arquivo e a execução da ação de remoção
     * @throws IOException - Lançada no caso de haver algum erro na remoção do
     * arquivo (no contexto da classe Tracker)
     * @throws NoSuchAlgorithmException - Lançada no caso de haver algum erro na
     * remoção do arquivo (no contexto da classe Tracker)
     */
    public void remove_torrent(byte[] arquivo_torrent_codificado, long atraso) throws IOException, NoSuchAlgorithmException {
        this.rastreador.remove(new Torrent(arquivo_torrent_codificado, false), atraso);
    }

    /**
     * Método que retorna uma coleção contendo os arquivos ".torrent" que se
     * encontram sob o anuncio do rastreador
     *
     * @return Collecion of TrackedTorrent - Arquivos "torrent" que se encontram
     * sob anuncio do rastreador
     */
    public Collection<TrackedTorrent> torrents_em_anuncio() {
        return this.rastreador.getTrackedTorrents();
    }

    /**
     * Método que retorna (através de uma instância de <b>URL</b>) a URL
     * pertencente à este rastreador
     *
     * @return URL - URL à qual este rastreador se encontra vinculado
     */
    public URL getURL_rastreador() {
        return this.rastreador.getAnnounceUrl();
    }

    /**
     * Método que retorna (através de um inteiro) a porta na qual este
     * rastreador se encontra estabelecido
     *
     * @return int - Inteiro que representa a porta na qual este rastreador se
     * encontra estabelecido
     */
    public int get_porta() {
        return this.endereco.getPort();
    }

    /**
     * Método que retorna (na forma de um vetor de bytes) o endereço no qual
     * este rastreador se encontra estabelecido
     *
     * @return byte[] - Endereço no qual este rastreador se encontra
     * estabelecido
     */
    public byte[] get_endereco() {
        return this.endereco.getAddress().getAddress();
    }

    /**
     * Método que retorna o caminho para diretório no qual o rastreador obtém os
     * arquivos que devem ser anunciados
     *
     * @return String - Caminho do diretório em questão
     */
    public String get_pasta_busca() {
        return this.pasta_busca;
    }

    /**
     * Método que altera o diretório no qual o rastreador busca os arquivos que
     * devem ser anunciados
     *
     * @param caminho - String que representa o caminho do diretório em questão
     */
    public void set_pasta_busca(String caminho) {
        this.pasta_busca = caminho;
    }

    /**
     * Método que retorna o IP sob o qual está máquina se encontra vinculada na
     * rede local
     *
     * @return byte[] - Vetor de bytes contendo o IP
     */
    public static String meu_IP() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface interface_conexao = interfaces.nextElement();
                if (interface_conexao.isLoopback() || !interface_conexao.isUp() || interface_conexao.isVirtual() || interface_conexao.isPointToPoint()) {
                    continue;
                }
                Enumeration<InetAddress> enumeracao_enderecos = interface_conexao.getInetAddresses();
                while (enumeracao_enderecos.hasMoreElements()) {
                    InetAddress endereco = enumeracao_enderecos.nextElement();

                    final String endereco_IP = endereco.getHostAddress();
                    if (Inet4Address.class == endereco.getClass()) {
                        return endereco_IP;
                    }
                }
            }
        } catch (SocketException e) {
            Logger.escrever_erro(e);
            throw new RuntimeException(e);
        }
        return null;
    }

    public static String meu_IP_externo() {
        URL checagem_IP;
        String IP = null;
        try {
            checagem_IP = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(checagem_IP.openStream()));
            IP = in.readLine();
        } catch ( IOException ex) {
            java.util.logging.Logger.getLogger(Rastreador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IP;
    }
}
