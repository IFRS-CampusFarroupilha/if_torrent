package iftorrent.arquivos.torrent;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.Client.ClientState;
import com.turn.ttorrent.client.peer.SharingPeer;
import java.io.Serializable;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 
 *
 * A classe <b>ArquivoTorrentPropriedades</b> é utilizada para representar um
 * arquivo em uma linha da tabela MenuPrincipal.
 *
 * @author Rafael Coelho
 *
 */
public class ArquivoTorrentPropriedades implements Serializable {

    public static final String PAUSADO = "Pausado";
    public static final String PARADO = "Interrompido";
    public static final String BAIXANDO = "Baixando";
    public static final String BAIXADO = "Baixado";
    public static final String ENVIANDO = "Enviando";

    /**
     * 
     *
     * Um objeto do tipo <b>int</b> que armazena o número do arquivo.
     *
     * 
     */
    SimpleIntegerProperty numero;
    /**
     * 
     *
     * Um objeto do tipo <b>String</b> que armazena o nome do arquivo.
     *
     * 
     */
    SimpleStringProperty nome;
    /**
     * 
     *
     * Um objeto do tipo <b>long</b> que armazena quanto falta do total do
     * arquivo.
     *
     * 
     */
    SimpleLongProperty falta;
    /**
     * 
     *
     * Um objeto do tipo <b>long</b> que armazena o tamanho total do arquivo.
     *
     * 
     */
    SimpleLongProperty tamanho;
    /**
     * 
     *
     * Um objeto do tipo <b>long</b> que armazena quantos bytes foram feito
     * upload.
     *
     * 
     */
    SimpleLongProperty uploaded;
    /**
     * 
     *
     * Um objeto do tipo <b>long</b> que armazena quantos bytes foram feito
     * download.
     *
     * 
     */
    SimpleLongProperty downloaded;
    /**
     * 
     *
     * Um objeto do tipo <b>Integer</b> que armazena a quantidade de pares
     * conectados.
     *
     * 
     */
    SimpleIntegerProperty numero_pares;
    /**
     * 
     *
     * Um objeto do tipo <b>String</b> que armazena o estado do arquivo.
     *
     * 
     */
    SimpleStringProperty estado;
    /**
     * 
     *
     * Um objeto do tipo <b>Double</b> que armazena a porcentagem do progresso
     * do download.
     *
     * 
     */
    SimpleDoubleProperty progresso;
    /**
     * 
     *
     * Um objeto do tipo <b>Double</b> que armazena a taxa máxima de upload.
     *
     * 
     */
    SimpleDoubleProperty taxa_max_baixar;
    /**
     * 
     *
     * Um objeto do tipo <b>Double</b> que armazena a taxa máxima de download.
     *
     * 
     */
    SimpleDoubleProperty taxa_max_enviar;

    BitSet pedacos_baixados;

    BitSet pedacos_disponiveis;

    BitSet pedacos_requisitados;

    SimpleIntegerProperty numero_pedacos;

    SimpleBooleanProperty semeador;

    SimpleLongProperty baixado_durante_execucao;

    List<String> nomes_subarquivos;

    Set<SharingPeer> pares;

    public Set<SharingPeer> getPares() {
        return pares;
    }
    
    public int getNumero_de_pares_conectados(){
        int pares_conectados = 0;
        Iterator<SharingPeer> iterador = getPares().iterator();
        
        while(iterador.hasNext()){
            if(iterador.next().isConnected()){
                pares_conectados++;
            }
        }
        return pares_conectados;
    }

    public void setPares(Set<SharingPeer> pares) {
        this.pares = pares;
    }

    public int getNumero() {
        return numero.get();
    }

    public void setNumero(int numero) {
        this.numero.set(numero);
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public long getTamanho() {
        return tamanho.get();
    }

    public void setTamanho(long tamanho) {
        this.tamanho.set(tamanho);
    }

    public long getFalta() {
        return falta.get();
    }

    public void setFalta(long falta) {
        this.falta.set(falta);
    }

    public long getUploaded() {
        return uploaded.get();
    }

    public void setUploaded(long uploaded) {
        this.uploaded.set(uploaded);
    }

    public long getDownloaded() {
        return downloaded.get();
    }

    public void setDownloaded(long downloaded) {
        this.downloaded.set(downloaded);
    }

    public int getNumero_de_pares() {
        return numero_pares.get();
    }

    public void setNumero_de_pares(int pares_conectados) {
        this.numero_pares.set(pares_conectados);
    }

    public String getEstado() {
        return estado.get();
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public Double getProgresso() {
        return progresso.get() / 100;
    }

    public void setProgresso(Double progresso) {
        this.progresso.set(progresso);
    }

    public BitSet getPedacos_baixados() {
        return pedacos_baixados;
    }

    public void setPedacos_baixados(BitSet pedacos_baixados) {
        this.pedacos_baixados = pedacos_baixados;
    }

    public BitSet getPedacos_disponiveis() {
        return pedacos_disponiveis;
    }

    public void setPedacos_disponiveis(BitSet pedacos_disponiveis) {
        this.pedacos_disponiveis = pedacos_disponiveis;
    }

    public BitSet getPedacos_requisitados() {
        return pedacos_requisitados;
    }

    public void setPedacos_requisitados(BitSet pedacos_requisitados) {
        this.pedacos_requisitados = pedacos_requisitados;
    }

    public int getNumero_pedacos() {
        return numero_pedacos.get();
    }

    public void setNumero_pedacos(int numero_pedacos) {
        this.numero_pedacos.set(numero_pedacos);
    }

    public boolean getSemeador() {
        return semeador.get();
    }

    public void setSemeador(boolean semeador) {
        this.semeador.set(semeador);
    }

    public long getBaixado_durante_execucao() {
        return baixado_durante_execucao.get();
    }

    public void setBaixado_durante_execucao(long baixado_durante_execucao) {
        this.baixado_durante_execucao.set(baixado_durante_execucao);
    }

    public List<String> getNomes_subarquivos() {
        return nomes_subarquivos;
    }

    public void setNomes_subarquivos(List<String> nomes_subarquivos) {
        this.nomes_subarquivos = nomes_subarquivos;
    }

    public double getTaxa_max_baixar() {
        return taxa_max_baixar.get();
    }

    public void setTaxa_max_baixar(double taxa_max_baixar) {
        this.taxa_max_baixar.set(taxa_max_baixar);
    }

    public double getTaxa_max_enviar() {
        return taxa_max_enviar.get();
    }

    public void setTaxa_max_enviar(double taxa_max_enviar) {
        this.taxa_max_enviar.set(taxa_max_enviar);
    }

    public ArquivoTorrentPropriedades(Client cliente) {
        this.pedacos_baixados = new BitSet();
        this.pedacos_disponiveis = new BitSet();
        this.pedacos_requisitados = new BitSet();
        this.pares = new HashSet<>();

        if (cliente.getTorrent().isComplete() || cliente.getTorrent().isInitialized()) {
            this.pedacos_baixados = cliente.getTorrent().getCompletedPieces();
            this.pedacos_disponiveis = cliente.getTorrent().getAvailablePieces();
            this.pedacos_requisitados = cliente.getTorrent().getRequestedPieces();
            this.pares = cliente.getPeers();
        }
        this.numero = new SimpleIntegerProperty(cliente.getTorrent().getHexInfoHash().hashCode());
        this.nome = new SimpleStringProperty(cliente.getTorrent().getName());
        this.tamanho = new SimpleLongProperty(cliente.getTorrent().getSize() / (1024 * 1024));
        this.numero_pares = new SimpleIntegerProperty(cliente.getPeers().size());
        this.estado = new SimpleStringProperty(obtem_nome_estado(cliente.getState()));
        this.progresso = new SimpleDoubleProperty(cliente.getTorrent().getCompletion());
        this.falta = new SimpleLongProperty((int) Math.ceil(cliente.getTorrent().getLeft() / (1024 * 1024)));
        this.taxa_max_baixar = new SimpleDoubleProperty(cliente.getTorrent().getMaxDownloadRate());
        this.taxa_max_enviar = new SimpleDoubleProperty(cliente.getTorrent().getMaxUploadRate());
        this.uploaded = new SimpleLongProperty(cliente.getTorrent().getUploaded() / (1024 * 1024));
        this.downloaded = new SimpleLongProperty(this.tamanho.getValue() - this.falta.getValue());
        this.numero_pedacos = new SimpleIntegerProperty(cliente.getTorrent().getPieceCount());
        this.nomes_subarquivos = cliente.getTorrent().getFilenames();
        this.semeador = new SimpleBooleanProperty(cliente.isSeed());
        this.baixado_durante_execucao = new SimpleLongProperty(cliente.getTorrent().getDownloaded() / (1024 * 1024));
    }

    /**
     *
     * Construtor que cria um ArquivoTorrentPropriedades
     **
     * @param numero Numero do hash do torrent
     * @param nome Nome do torrent
     * @param tamanho Tamanho em MB
     * @param uploaded Quantidade enviada (uploaded)
     * @param downloaded Quantidade baixada (downloaded)
     * @param falta
     * @param numero_pares Quantidade de pares conectados
     * @param estado Estado atual do torrent
     * @param progresso Taxa de progresso (%)
     * @param tamanho_baixado Tamanho baixado (MB)
     * @param tamanho_disponivel Tamanho disponivel (MB)
     * @param taxa_max_baixar Taxa máxima de download
     * @param taxa_max_enviar Taxa máxima de upload
     * @param pedacos_baixados
     * @param pedacos_requisitados
     * @param pedacos_disponiveis
     * @param numero_pedacos
     * @param semeador
     * @param nome_subarquivos
     * @param qtd_baixada_execucao
     */
    public ArquivoTorrentPropriedades(
            int numero,
            String nome,
            long tamanho,
            long uploaded,
            long downloaded,
            long falta,
            int numero_pares,
            String estado,
            double progresso,
            int tamanho_baixado,
            int tamanho_disponivel,
            double taxa_max_baixar,
            double taxa_max_enviar,
            BitSet pedacos_baixados,
            BitSet pedacos_disponiveis,
            BitSet pedacos_requisitados,
            int numero_pedacos,
            boolean semeador,
            List<String> nome_subarquivos,
            long qtd_baixada_execucao,
            Set<SharingPeer> pares) {
        this.numero = new SimpleIntegerProperty(numero);
        this.nome = new SimpleStringProperty(nome);
        this.tamanho = new SimpleLongProperty(tamanho);
        this.uploaded = new SimpleLongProperty(uploaded);
        this.downloaded = new SimpleLongProperty(downloaded);
        this.numero_pares = new SimpleIntegerProperty(numero_pares);
        this.estado = new SimpleStringProperty(estado);
        this.progresso = new SimpleDoubleProperty(progresso);
        this.falta = new SimpleLongProperty(falta);
        this.taxa_max_baixar = new SimpleDoubleProperty(taxa_max_baixar);
        this.taxa_max_enviar = new SimpleDoubleProperty(taxa_max_enviar);
        this.pedacos_baixados = pedacos_baixados;
        this.pedacos_disponiveis = pedacos_disponiveis;
        this.pedacos_requisitados = pedacos_requisitados;
        this.numero_pedacos.setValue(numero_pedacos);
        this.nomes_subarquivos = nome_subarquivos;
        this.semeador.set(semeador);
        this.baixado_durante_execucao.set(qtd_baixada_execucao);
        this.pares = pares;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ArquivoTorrentPropriedades other = (ArquivoTorrentPropriedades) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return Objects.equals(this.tamanho, other.tamanho);
    }

    @Override
    public String toString() {
        return nome.get();
    }

    public static String obtem_nome_estado(ClientState estado) {
        switch (estado) {
            case WAITING:
                return "Esperando";
            case VALIDATING:
                return "Validando";
            case SHARING:
                return "Baixando";
            case SEEDING:
                return "Enviando";
            case ERROR:
                return "Erro";
            case DONE:
                return "Completo";
        }
        return "";
    }

    public void atualizar(ArquivoTorrentPropriedades arquivo) {
        this.pedacos_baixados = arquivo.getPedacos_baixados();
        this.pedacos_disponiveis = arquivo.getPedacos_disponiveis();
        this.pedacos_requisitados = arquivo.getPedacos_requisitados();
        this.numero = new SimpleIntegerProperty(arquivo.getNumero());
        this.nome = new SimpleStringProperty(arquivo.getNome());
        this.tamanho = new SimpleLongProperty(arquivo.getTamanho());
        this.numero_pares = new SimpleIntegerProperty(arquivo.getNumero_de_pares());
        this.estado = new SimpleStringProperty(arquivo.getEstado());
        this.progresso = new SimpleDoubleProperty(arquivo.getProgresso() * 100);
        this.falta = new SimpleLongProperty(arquivo.getFalta());
        this.taxa_max_baixar = new SimpleDoubleProperty(arquivo.getTaxa_max_baixar());
        this.taxa_max_enviar = new SimpleDoubleProperty(arquivo.getTaxa_max_enviar());
        this.uploaded = new SimpleLongProperty(arquivo.getUploaded());
        this.downloaded = new SimpleLongProperty(arquivo.getDownloaded());
        this.numero_pedacos = new SimpleIntegerProperty(arquivo.getNumero_pedacos());
        this.nomes_subarquivos = arquivo.getNomes_subarquivos();
        this.semeador = new SimpleBooleanProperty(arquivo.getSemeador());
        this.baixado_durante_execucao = new SimpleLongProperty(arquivo.getBaixado_durante_execucao());
        this.pares = arquivo.getPares();
    }
}
