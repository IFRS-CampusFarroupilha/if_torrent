package iftorrent;

import iftorrent.arquivos.torrent.ConstantesTorrent;
import iftorrent.conexao.Rastreador;
import static java.io.File.separator;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 *
 * @author Leonardo Bortolini
 */
public class ConstantesGerais {

    public static final String EXTENSAO_ARQUIVOS = ".txt";
    public static final String EXTENSAO_TORRENTS = ".torrent";
    public static final String DIRETORIO_PADRAO = System.getProperty("user.dir");
    public static String DIRETORIO_ATUAL = DIRETORIO_PADRAO + separator;
    public static String PASTA_DOWNLOADS = DIRETORIO_PADRAO + separator;
    public static String PASTA_TORRENTS = DIRETORIO_ATUAL + "torrents" + separator;
    public static final int CODIGO_PROGRAMA_FECHADO_SUCESSO = 0;
    public static final String UNIDADE_DE_INFORMACAO_PADRAO1 = "MB";
    public static final String UNIDADE_DE_TEMPO_PADRAO1 = "s";
    public static final String UNIDADE_DE_VELOCIDADE_PADRAO1 = "Mb/s";
    public static final String LDAP_URL = "ldap://192.168.7.250:389";
    public static final String LDAP_INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    public static final String LDAP_SECURITY_AUTHENTICATION = "simple";
    public static final String MEU_ENDERECO_IP_LOCAL = Rastreador.meu_IP();
    public static final String MEU_ENDERECO_IP_EXTERNO = Rastreador.meu_IP_externo();
    public static final String MEU_ENDERECO_LOOPBACK = new InetSocketAddress(InetAddress.getLoopbackAddress(), ConstantesTorrent.PORTA_DEDICADA_SERVIDOR_DHT).getHostName();
    public static final int PORTA_DEDICADA_RASTREADOR = 50230;
}
