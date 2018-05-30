package iftorrent.daos.daoArquivo;

/**
 * 
 *
 * A classe <b>Arquivo</b> serve para representar uma linha da tabela arquivo do
 * banco de dados.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class ArquivoTorrent {

    /**
     * Um inteiro que representa a id_arquivo primária única do objeto.<br>
     */
    private int id_arquivo;
    
    /**
     * Um objeto do tipo <b>String</b> que representa o titulo do arquivo.<br>
     */
    private String titulo;
    
    /**
     * Um ponto flutuante que representa o tamanho do arquivo.<br>
     */
    private float tamanho;
    
    /**
     * Um objeto do tipo <b>String</b> que representa o magnet link.<br>
     */
    private String magnet_link;

    /**
     * 
     *
     * Construtor vazio.
     *
     * 
     */
    public ArquivoTorrent() {
    }

    /**
     * 
     *
     * Construtor que inicializa todos os atributos.
     *
     * @param chave Um inteiro que representa a id_arquivo primária do objeto
 (única).<br>
     * @param nome Um objeto do tipo <b>String</b> que representa o titulo do
 arquivo.<br>
     * @param tamanho Um ponto flutuante que representa o tamanho do
     * arquivo.<br>
     * @param magnet Um objeto do tipo <b>String</b> que representa o
     * magnet link do arquivo.<br>
     *
     * 
     */
    public ArquivoTorrent(int chave, String nome, float tamanho, String magnet) {
        this.id_arquivo = chave;
        this.titulo = nome;
        this.tamanho = tamanho;
        this.magnet_link = magnet;
    }


    /**
     * 
     *
     * Construtor que inicializa todos os atributos.
     * @param nome Um objeto do tipo <b>String</b> que representa o titulo do
 arquivo.<br>
     * @param tamanho Um ponto flutuante que representa o tamanho do
     * arquivo.<br>
     * @param magnet Um objeto do tipo <b>String</b> que representa o
     * magnet link do arquivo.<br>
     *
     * 
     */
    public ArquivoTorrent(String nome, float tamanho, String magnet) {
        this.titulo = nome;
        this.tamanho = tamanho;
        this.magnet_link = magnet;
    }

    /**
     * 

 Getter da id_arquivo primária do objeto.
     *
     * @return Um inteiro que representa a id_arquivo primária do objeto.<br>
     *
     * 
     */
    public int getChave() {
        return id_arquivo;
    }

    /**
     * 

 Setter da id_arquivo primária do objeto.
     *
     * @param chave Um inteiro que representa a id_arquivo primária do objeto.<br>
     *
     * 
     */
    public void setChave(int chave) {
        this.id_arquivo = chave;
    }

    /**
     * 
 
 Getter do titulo do arquivo.
     *
     * @return Um objeto do tipo <b>String</b> que representa o titulo do 
 arquivo.<br>
     *
     * 
     */
    public String getNome() {
        return titulo;
    }

    /**
     * 

 Setter do titulo do arquivo.
     *
     * @param nome Um objeto do tipo <b>String</b> que representa o titulo do
 arquivo.<br>
     *
     * 
     */
    public void setNome(String nome) {
        this.titulo = nome;
    }

    /**
     * 
     * 
     * Getter do tamanho do arquivo.
     *
     * @return Um ponto flutuante que representa o tamanho do arquivo.<br>
     *
     * 
     */
    public float getTamanho() {
        return tamanho;
    }

    /**
     * 
     *
     * Setter do tamanho do arquivo.
     *
     * @param tamanho Um ponto flutuante que representa o tamanho do 
     * arquivo.<br>
     *
     * 
     */
    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * 
     * 
     * Getter do usuário proprietário do arquivo.
     *
     * @return Um objeto do tipo <b>String</b> que representa o usuário 
     * proprietário do arquivo.<br>
     *
     * 
     */
    public String getProprietario() {
        return magnet_link;
    }

    /**
     * 
     *
     * Setter do usuário proprietário do arquivo.
     *
     * @param proprietario Um objeto do tipo <b>String</b> que representa o 
     * usuário proprietário do arquivo.<br>
     *
     * 
     */
    public void setProprietario(String proprietario) {
        this.magnet_link = proprietario;
    }

    /**
     * 
 
 Método para comparar se um objeto é igual ao outro (testa somente a 
 id_arquivo primária, pois esta deve ser única).
     * 
     * @param objeto Um objeto do tipo <b>Object</b> que representa o objeto a
     * ser comparado.<br>
     * 
     * @return Um booleano que representa se os objetos são iguais ou não.<br>
     * 
     * 
     */
    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (objeto == null) {
            return false;
        }
        if (getClass() != objeto.getClass()) {
            return false;
        }
        ArquivoTorrent arquivo = (ArquivoTorrent) objeto;
        
        return this.id_arquivo == arquivo.id_arquivo;
    }

    /**
     * 
     * 
     * Método para gerar uma hash (um inteiro único para o objeto).
     * 
     * @return Um inteiro que representa a hash gerada. 
     * 
     * 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        
        hash = 71 * hash + this.id_arquivo;
        return hash;
    }

}
