package iftorrent.daos.daoPalavraChave;

/**
 * 
 *
 * A classe <b>PalavraChave</b> serve para representar uma linha da tabela
 * palavra_chave do banco de dados.
 *
 * @author Otávio Farinon
 *
 * 
 */
public class PalavraChave {

    /**
     * Um inteiro que representa a chave primária única do objeto.<br>
     */
    private int id_palavra;
    /**
     * Um objeto do tipo <b>String</b> que representa a palavra chave.<br>
     */
    private String palavra;

    /**
     * 
     *
     * Construtor vazio.
     *
     * 
     */
    public PalavraChave() {
    }

    /**
     * 
     *
     * Construtor que inicializa todos os atributos.
     *
     * @param id_palavra Um inteiro que representa a chave primária única do
     * objeto.<br>
     * @param palavra Um objeto do tipo <b>String</b> que representa a palavra
     * chave.<br>
     *
     * 
     */
    public PalavraChave(int id_palavra, String palavra) {
        this.id_palavra = id_palavra;
        this.palavra = palavra;
    }

    /**
     * 
     *
     * Getter da chave primária do objeto.
     *
     * @return Um inteiro que representa a chave primária única do objeto.<br>
     *
     * 
     */
    public int getId_Palavra() {
        return id_palavra;
    }

    /**
     * 
     *
     * Setter da chave primária do objeto.
     *
     * @param id_palavra Um inteiro que representa a chave primária do
     * objeto.<br>
     *
     * 
     */
    public void setId_Palavra(int id_palavra) {
        this.id_palavra = id_palavra;
    }

    /**
     * 
     *
     * Getter da palavra chave.
     *
     * @return Um objeto do tipo <b>String</b> que representa a palavra
     * chave.<br>
     *
     * 
     */
    public String getPalavra() {
        return palavra;
    }

    /**
     * 
     *
     * Setter da palavra chave.
     *
     * @param palavra Um objeto do tipo <b>String</b> que representa a palavra
     * chave.<br>
     *
     * 
     */
    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }
}
