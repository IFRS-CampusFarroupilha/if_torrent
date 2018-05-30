package iftorrent.arquivos.torrent;

/**
 *
 * @author Garren Souza
 */
public class ConstantesTorrent {

    public static String CHAVE_MAPA_RESULTADO_DHT = "RESULTADO";
    public static String CHAVE_MAPA_ERRO_DHT = "ERRO";
    public static String CHAVE_MAPA_FIM_DHT = "FIM";
    public static final int PORTA_DEDICADA_SERVIDOR_DHT = 49001;
    public static final String COMANDO_OBTER_PEERS_DHT = "GETPEERS";
    public static final String COMANDO_OBTER_TORRENT_DHT = "GETTORRENT";
    public static final String IDENTIFICADOR_MAGNET_LINK = "magnet:?";
    public static final String IDENTIFICADOR_TRACKER_MAGNET_LINK = "tr";
    public static final String IDENTIFICADOR_TEMP_NAME_MAGNET_LINK = "dn";
    public static final String IDENTIFICADOR_PEER_MAGNET_LINK = "x.";
    public static final String IDENTIFICADOR_INFO_HASH_MAGNET_LINK = "xt";
    public static final String PREFIXO_TRACKER_MAGNET_LINK = "tr=";
    public static final String PREFIXO_TEMP_NAME_MAGNET_LINK = "dn=";
    public static final String PREFIXO_PEER_MAGNET_LINK = "x.pe=";
    public static final String PREFIXO_INFO_HASH_MAGNET_LINK = "xt=urn:btih:";
    public static final String CHAVE_MAPA_MAGNET_INFOHASH = "info_hash";
    public static final String CHAVE_MAPA_MAGNET_RASTREADORES = "announce";
    public static final String CHAVE_MAPA_MAGNET_PARES = "peers";
    public static final String CHAVE_MAPA_MAGNET_TMP_NOME = "tmp_name";
    public static final String ARGUMENTO_COMANDO_OBTER_PEERS_RAPIDO = "-fast";
}
