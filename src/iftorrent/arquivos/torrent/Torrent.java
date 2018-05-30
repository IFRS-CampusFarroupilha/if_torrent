package iftorrent.arquivos.torrent;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.bcodec.BDecoder;
import com.turn.ttorrent.client.SharedTorrent;
import iftorrent.ConstantesGerais;
import static iftorrent.ConstantesGerais.DIRETORIO_ATUAL;
import static iftorrent.ConstantesGerais.EXTENSAO_ARQUIVOS;
import iftorrent.JanelaPrincipal;
import static iftorrent.arquivos.ConstantesLogger.EXECUCAO;
import iftorrent.arquivos.Logger;
import static iftorrent.arquivos.torrent.ConstantesTorrent.CHAVE_MAPA_MAGNET_INFOHASH;
import static iftorrent.arquivos.torrent.ConstantesTorrent.CHAVE_MAPA_MAGNET_PARES;
import static iftorrent.arquivos.torrent.ConstantesTorrent.CHAVE_MAPA_MAGNET_RASTREADORES;
import static iftorrent.arquivos.torrent.ConstantesTorrent.CHAVE_MAPA_MAGNET_TMP_NOME;
import static iftorrent.arquivos.torrent.ConstantesTorrent.IDENTIFICADOR_INFO_HASH_MAGNET_LINK;
import static iftorrent.arquivos.torrent.ConstantesTorrent.IDENTIFICADOR_MAGNET_LINK;
import static iftorrent.arquivos.torrent.ConstantesTorrent.IDENTIFICADOR_PEER_MAGNET_LINK;
import static iftorrent.arquivos.torrent.ConstantesTorrent.IDENTIFICADOR_TEMP_NAME_MAGNET_LINK;
import static iftorrent.arquivos.torrent.ConstantesTorrent.IDENTIFICADOR_TRACKER_MAGNET_LINK;
import static iftorrent.arquivos.torrent.ConstantesTorrent.PREFIXO_INFO_HASH_MAGNET_LINK;
import static iftorrent.arquivos.torrent.ConstantesTorrent.PREFIXO_PEER_MAGNET_LINK;
import static iftorrent.arquivos.torrent.ConstantesTorrent.PREFIXO_TEMP_NAME_MAGNET_LINK;
import static iftorrent.arquivos.torrent.ConstantesTorrent.PREFIXO_TRACKER_MAGNET_LINK;
import iftorrent.conexao.InterfaceDHT;
import iftorrent.criptografia.Charsets;
import iftorrent.excecoes.ESExcecao;
import iftorrent.gui.ferramentas.Auxiliar;
import static iftorrent.gui.ferramentas.Auxiliar.criar_thread;
import iftorrent.gui.ferramentas.Comunicador;
import iftorrent.gui.janelaConfiguracoes.Configuracao;
import static iftorrent.gui.janelaConfiguracoes.ConstantesJanelaConfiguracoes.NOME_ARQUIVO_CONFIGURACOES;
import static iftorrent.gui.janelaPrincipal.ConstantesJanelaPrincipal.NOME_ARQUIVO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import static java.io.File.separator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import javafx.scene.control.Alert;

/**
 * Classe contendo os métodos auxiliares relativos a Torrent.
 *
 * @author Rafael Coelho
 */
public class Torrent {

    public static InterfaceDHT interface_DHT;

    /**
     * Cria o cliente que representa o torrent.
     *
     * @param arquivo File
     * @return Retorna se foi poss[ivel abrir o arquivo torrent.
     */
    public static boolean abre_torrent(File arquivo) {
        if (arquivo != null) {
            criar_thread(() -> {
                try {
                    executa_cliente(arquivo);
                } catch (IOException | NoSuchAlgorithmException ex) {
                    Logger.escrever_erro(ex);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(Torrent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
            return true;
        }
        return false;
    }

    /**
     * Inicia o download do torrent e o observer para atualizar o estado do
     * mesmo.
     *
     * @param arquivo File
     * @throws NoSuchAlgorithmException Algoritmo inválido
     * @throws IOException Erro ao salvar as partes do arquivo (.part)
     */
    public static void executa_cliente(File arquivo) throws NoSuchAlgorithmException, IOException, Exception {
        Client cliente = new Client(
                InetAddress.getLocalHost(),
                SharedTorrent.fromFile(arquivo,
                        new File(ConstantesGerais.PASTA_DOWNLOADS + separator)));

        if (contem_cliente(cliente)) {
            return;
        }
        Files.copy(arquivo.toPath(),
                new File(ConstantesGerais.PASTA_TORRENTS + cliente.getTorrent().getName() + ".torrent").toPath(),
                StandardCopyOption.REPLACE_EXISTING);
        JanelaPrincipal.clientes.add(cliente);
        cliente.setMaxDownloadRate(obter_taxa_download());
        cliente.setMaxUploadRate(obter_taxa_upload());
//        while (!InterfaceDHT.nodo_servidor_online()) {
//            System.out.println("AGUARDANDO ATIVAÇÃO DO MÓDULO DE SERVIÇO DHT...");
//            Thread.sleep(125);
//        }
//        System.out.println(InterfaceDHT.nodo_servidor_online());
//        interface_DHT = new InterfaceDHT();
//        cliente.handleDiscoveredPeers(interface_DHT.get_Peers(cliente.getTorrent().getHexInfoHash()));
        cliente.share();
        if (cliente.getState() == Client.ClientState.SHARING) {
            cliente.info();
        }
        cria_arquivo_propriedade_torrents(cliente);
        cliente.addObserver((Observable observable, Object data) -> {
            Client cliente_observado = (Client) observable;

            atualizar_gui(cliente_observado);
            if (cliente_observado.getTorrent().isComplete()) {
                File arquivo_completo = new File(ConstantesGerais.PASTA_DOWNLOADS + separator + cliente_observado.getTorrent().getName() + ".part");

                if (arquivo_completo.isFile()) {
                    if (arquivo_completo.renameTo(new File(ConstantesGerais.PASTA_DOWNLOADS + separator + cliente_observado.getTorrent().getName()))) {
                        Logger.escrever(EXECUCAO, "Arquivo " + cliente_observado.getTorrent().getName() + " completo!");
                        criar_thread(() -> {
                            Auxiliar.criar_dialogo(Alert.AlertType.INFORMATION, "Informação", cliente_observado.getTorrent().getName(), "Download completo!");
                        });
                    } else {
                        Logger.escrever(EXECUCAO, "ERRO: Arquivo completo " + cliente_observado.getTorrent().getName() + " não renomeado.");
                        criar_thread(() -> {
                            Auxiliar.criar_dialogo(Alert.AlertType.ERROR, "Erro", cliente_observado.getTorrent().getName(), "Arquivo não renomeado");
                        });
                    }
                } else if (arquivo_completo.isDirectory()) {
                    remove_part_arquivos_pasta(arquivo_completo);
                }
            }
        });
        cliente.share();
    }

    private static void atualizar_gui(Client cliente) {
        ArquivoTorrentPropriedades arquivo_torrent
                = cria_arquivo_propriedades_tabela(cliente);

        if (Comunicador.getMenu_principal() != null) {
            Comunicador.getMenu_principal().atualiza_torrent(arquivo_torrent);
            if (Comunicador.getMenu_principal().isSelecionado(arquivo_torrent)) {
                Comunicador.getAbas_propriedades().atualizar_pares(arquivo_torrent);
            }
        }
    }

    /**
     * Remove a extensão .part dos arquivos concluídos.
     *
     * @param arquivo_completo File
     */
    public static void remove_part_arquivos_pasta(File arquivo_completo) {
        File pasta = new File(arquivo_completo.getAbsolutePath().replaceAll(".part", ""));

        File[] arquivos = pasta.listFiles();

        for (File arq : arquivos) {
            if (arq.isFile()) {
                if (arq.renameTo(new File(arq.getAbsolutePath().replaceAll(".part", "")))) {
                    Logger.escrever(EXECUCAO, "Arquivo " + arq.getName() + " completo!");
                } else {
                    Logger.escrever(EXECUCAO, "ERRO: Arquivo completo " + arq.getName() + " não renomeado.");
                }
            } else {
                remove_part_arquivos_pasta(arq);
            }
        }
    }

    /**
     * Salva nome do torrent no arquivo torrents.txt (desta forma, ele pode ser
     * reaberto quando o programa é reiniciado).
     *
     * @param cliente Client
     */
    public static void salva_nome_torrent_em_arquivo(Client cliente) {
        File arquivo_torrents = new File(DIRETORIO_ATUAL + NOME_ARQUIVO + EXTENSAO_ARQUIVOS);

        try (FileWriter out = new FileWriter(arquivo_torrents, true)) {
            out.write(cliente.getTorrent().getName() + System.lineSeparator());
        } catch (IOException ex) {
            Logger.escrever_erro(ex);
        }
    }

    /**
     * Cria o objeto ArquivoPropriedadeTorrent (representa uma linha na teabela)
     * a partir do objeto Cliente.
     *
     * @param cliente Representa o objeto cliente (torrent na biblioteca tt)
     */
    public static void cria_arquivo_propriedade_torrents(Client cliente) {
        ArquivoTorrentPropriedades arquivo_torrent = cria_arquivo_propriedades_tabela(cliente);

        if (Comunicador.getMenu_principal() != null) {
            Comunicador.getMenu_principal().adiciona_torrent_na_tabela(arquivo_torrent);
        }
    }

    public static ArquivoTorrentPropriedades cria_arquivo_propriedades_tabela(Client cliente) {
        ArquivoTorrentPropriedades arquivo_torrent = null;

        arquivo_torrent = new ArquivoTorrentPropriedades(cliente);
        System.out.printf("Progresso %s: %.2f%%\n", arquivo_torrent.getNome(), arquivo_torrent.getProgresso() * 100);
        Logger.escrever(EXECUCAO, String.format("\nProgresso %s: %.2f%%", arquivo_torrent.getNome(), arquivo_torrent.getProgresso() * 100));
        return arquivo_torrent;
    }

    public static void abre_magnet_link(String magnet_uri) throws UnknownHostException {
        //ArquivoTorrentPropriedades arquivo_torrent = cria_magnet_link(magnet_uri);
    }

    /**
     * Método que decodifica a URL recebida (na forma de uma instância de
     * String)
     *
     * @param magnet_uri String - Instância de String que contém a sequencia de
     * caracteres sobre os quais se deseja realizar a decodificação
     * @return String - Instância de String que contem a URL recebida na forma
     * decodificada
     * @throws UnsupportedEncodingException - Lançada no caso de não haver
     * suporte à codificação escolhida (Neste caso tem-se como constante a
     * utilização do Charset <b>"UTF-8"</b>)
     */
    public static String decodifica_URL(String magnet_uri) throws UnsupportedEncodingException {
        return java.net.URLDecoder.decode(magnet_uri, Charsets.CHARSET_UTF_8.getCharset());
    }

    /**
     * Método que decodifica um "magnet_link" recebido na forma de uma URL
     * contida por uma instância de String
     *
     * @param magnet_link String - Instância de String que contem a URL que será
     * tratada como um "magnet link"
     * @return HashMap - Mapa que contém os dados extraídos da URL recebida
     */
    public Map<String, String> decodifica_magnet(String magnet_link) throws UnsupportedEncodingException {
        magnet_link = decodifica_URL(magnet_link).replace(IDENTIFICADOR_MAGNET_LINK, "");
        String[] secoes_magnet_link = magnet_link.split("&");
        Map informacoes_magnet_link = new HashMap();
        for (String secao : secoes_magnet_link) {
            String identificador = secao.substring(0, 2);
            switch (identificador) {
                case IDENTIFICADOR_INFO_HASH_MAGNET_LINK:
                    informacoes_magnet_link.put(CHAVE_MAPA_MAGNET_INFOHASH,
                            this.checa_existencia(CHAVE_MAPA_MAGNET_INFOHASH,
                                    secao.substring(PREFIXO_INFO_HASH_MAGNET_LINK.length()),
                                    informacoes_magnet_link));
                    break;
                case IDENTIFICADOR_TEMP_NAME_MAGNET_LINK:
                    informacoes_magnet_link.put(CHAVE_MAPA_MAGNET_TMP_NOME,
                            this.checa_existencia(CHAVE_MAPA_MAGNET_TMP_NOME,
                                    secao.replace(PREFIXO_TEMP_NAME_MAGNET_LINK, ""),
                                    informacoes_magnet_link));
                    break;
                case IDENTIFICADOR_TRACKER_MAGNET_LINK:
                    informacoes_magnet_link.put(CHAVE_MAPA_MAGNET_RASTREADORES,
                            this.checa_existencia(CHAVE_MAPA_MAGNET_RASTREADORES,
                                    secao.replace(PREFIXO_TRACKER_MAGNET_LINK, ""),
                                    informacoes_magnet_link));
                    break;
                case IDENTIFICADOR_PEER_MAGNET_LINK:
                    informacoes_magnet_link.put(CHAVE_MAPA_MAGNET_PARES,
                            this.checa_existencia(CHAVE_MAPA_MAGNET_PARES,
                                    secao.replace(PREFIXO_PEER_MAGNET_LINK, ""),
                                    informacoes_magnet_link));
                    break;
                default:
                    throw new iftorrent.excecoes.ArgumentoIlegalExcecao("A seção: \"" + secao + "\" do magnet link possui formato inválido!");
            }
        }
        return informacoes_magnet_link;
    }

    /**
     * Método que garante a inclusão de múltiplos itens em uma mesma chave do
     * mapa que irá representar os dados de um magnet link
     *
     * @param chave String - Chave sob a qual se deseja inserir o parâmetro
     * <strong>valor</strong>
     * @param valor String - Valor que será inserido sob o parâmetro
     * <strong>chave</strong>
     * @param mapa HashMap - Mapa sobre o qual se desejam realizar as operações
     * citadas
     * @return
     */
    public Object checa_existencia(String chave, String valor, Map mapa) {
        if (mapa.containsKey(chave)) {
            if (mapa.get(chave) instanceof List) {
                ((List) mapa.get(chave)).add(valor);
                return mapa.get(chave);
            } else {
                return Stream.of(mapa.get(chave), valor).collect(toList());
            }
        }
        return valor;
    }

    public static ArquivoTorrentPropriedades cria_magnet_link(String magnet_uri) {
        /*        MagnetUri magnet = MagnetUriParser.lenientParser().parse(magnet_uri);

        ArquivoTorrentPropriedades arquivo_torrent = cria_arquivo_propriedades_tabela(magnet);
        Comunicador.getMenu_principal().adiciona_torrent_na_tabela(arquivo_torrent);
        return arquivo_torrent;
         */
        return null;
    }
    
    public static String cria_magnet_link(Client cliente){
        return null;
    }

    private static void codifica_object(Object objeto, OutputStream saida) throws IOException {
        if (objeto instanceof String) {
            codifica_string((String) objeto, saida);
        } else if (objeto instanceof Map) {
            codifica_map((Map) objeto, saida);
        } else if (objeto instanceof byte[]) {
            codifica__vetor_bytes((byte[]) objeto, saida);
        } else if (objeto instanceof Number) {
            codifica_long(((Number) objeto).longValue(), saida);
        } else if (objeto instanceof List) {
            codifica_list(((List) objeto), saida);
        } else {
            throw new ESExcecao("Não é possível codificar");
        }
    }

    private static void codifica_long(long valor, OutputStream saida) throws IOException {
        saida.write('i');
        saida.write(Long.toString(valor).getBytes("US-ASCII"));
        saida.write('e');
    }

    /**
     * Método que codifica uma instância de <b>List</b>(BEncode), transferindo-a
     * à uma instância de OutputStream, ambas recebidas como parâmetro
     *
     * @param lista - List lista com os dados que farão parte do arquivo
     * "*.torrent"
     * @param saida - OutputStream que irá conter a lista(já codificada) citada
     * logo acima
     * @throws IOException
     */
    private static void codifica_list(List<Object> lista, OutputStream saida) throws IOException {
        saida.write('l');
        for (Object item : lista) {
            if (item instanceof String) {
                codifica_string((String) item, saida);
            } else if (item instanceof Number) {
                codifica_long(((Number) item).longValue(), saida);
            } else if (item instanceof List) {
                codifica_list((List) item, saida);
            } else {
                throw new ESExcecao("Não é possível codificar");
            }
        }
        saida.write('e');
    }

    private static void codifica__vetor_bytes(byte[] vetor_bytes, OutputStream saida) throws IOException {
        saida.write(Integer.toString(vetor_bytes.length).getBytes("US-ASCII"));
        saida.write(':');
        saida.write(vetor_bytes);
    }

    private static void codifica_string(String texto, OutputStream saida) throws IOException {
        codifica__vetor_bytes(texto.getBytes("UTF-8"), saida);
    }

    private static void codifica_map(Map<String, Object> mapa, OutputStream saida) throws IOException {
        SortedMap<String, Object> sortedMap = new TreeMap(mapa);
        saida.write('d');
        for (Entry<String, Object> e : sortedMap.entrySet()) {
            codifica_string(e.getKey(), saida);
            codifica_object(e.getValue(), saida);
        }
        saida.write('e');
    }

    private static byte[] hash_chunks(File arquivo, int tamanho_chunk) throws IOException {
        MessageDigest sha1;
        try {
            sha1 = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            throw new Error("SHA1 not supported");
        }
        ByteArrayOutputStream pieces;
        int pieceByteCount;

        try (InputStream in = new FileInputStream(arquivo)) {
            pieces = new ByteArrayOutputStream();
            byte[] bytes = new byte[tamanho_chunk];
            pieceByteCount = 0;
            int readCount = in.read(bytes, 0, tamanho_chunk);
            while (readCount != -1) {
                pieceByteCount += readCount;
                sha1.update(bytes, 0, readCount);
                if (pieceByteCount == tamanho_chunk) {
                    pieceByteCount = 0;
                    pieces.write(sha1.digest());
                }
                readCount = in.read(bytes, 0, tamanho_chunk - pieceByteCount);
            }
        }
        if (pieceByteCount > 0) {
            pieces.write(sha1.digest());
        }
        return pieces.toByteArray();
    }

    /**
     * Cria um novo arquivo torrent em disco a partir da seleção de arquivos.
     *
     * @param arquivo_torrent Novo arquivo torrent (File)
     * @param arquivos_entrada Lista de arquivos de entrada selecionados
     * @param lista_trackers Lista de trackers associados ao arquivo.
     * @throws IOException
     */
    public static void cria_torrent(File arquivo_torrent, List<File> arquivos_entrada, ArrayList lista_trackers) throws IOException {
        final int pieceLength = 512 * 1024;
        Map<String, Object> info = new HashMap();

        for (File arquivo_entrada : arquivos_entrada) {
            info.put("name", arquivo_entrada.getName());
            info.put("length", arquivo_entrada.length());
            info.put("piece length", pieceLength);
            info.put("pieces", hash_chunks(arquivo_entrada, pieceLength));
            info.put("creation date", new Timestamp(System.currentTimeMillis()).getTime());
        }
        Map<String, Object> metainfo = new HashMap();
        metainfo.put("announce", lista_trackers.get(0));
        ArrayList list_wrapper = new ArrayList();
        list_wrapper.add(lista_trackers);
        metainfo.put("announce-list", list_wrapper);//descoheço o por quê, mas todos os arquivos que analisei levam hifen nesta chave
        metainfo.put("info", info);
        try (OutputStream out = new FileOutputStream(arquivo_torrent)) {
            codifica_map(metainfo, out);
        }
    }

    /**
     * Verifica se objeto Client recebido por parâmetro está contido na lista
     * estática de clientes da JanelaPrincipal.
     *
     * @param cliente Client
     * @return Retorna se foi achado ou não.
     */
    private static boolean contem_cliente(Client cliente) {
        return JanelaPrincipal.clientes.stream().anyMatch((c)
                -> (c.getTorrent().getHexInfoHash().equals(cliente.getTorrent().getHexInfoHash())));
    }

    private static double obter_taxa_download() {
        File restante = new File(NOME_ARQUIVO_CONFIGURACOES + EXTENSAO_ARQUIVOS);

        if (!restante.exists()) {
            return 0.0;
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(restante));) {
            Configuracao config = (Configuracao) ois.readObject();

            return config.getDownload();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.escrever_erro(ex);
        }
        return 0.0;
    }

    private static double obter_taxa_upload() {
        File restante = new File(NOME_ARQUIVO_CONFIGURACOES + EXTENSAO_ARQUIVOS);

        if (!restante.exists()) {
            return 0.0;
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(restante));) {
            Configuracao config = (Configuracao) ois.readObject();

            return config.getUpload();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.escrever_erro(ex);
        }
        return 0.0;
    }
}
