package iftorrent.daos.daoCategoria;

/**
 * 
 *
 * A classe <b>Categoria</b> serve para representar uma linha da tabela
 * categoria do banco de dados.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class Categoria {

    /**
     * Um inteiro que representa a chave primária única do objeto.<br>
     */
    private int id_categoria;
    
    /**
     * Um inteiro que representa a chave primária de uma subcategoria.<br>
     */
    private int fk_categoria;

    /**
     * 
     *
     * Construtor vazio.
     *
     * 
     */
    public Categoria() {
    }

    /**
     * 
     *
     * Construtor que inicializa todos os atributos.
     *
     * @param id_categoria Um inteiro que representa a chave primária do objeto
     * (única).<br>
     * @param fk_categoria Um inteiro que representa a chave primária de uma 
     * subcategoria.<br>
     *
     * 
     */
    public Categoria(int id_categoria, int fk_categoria) {
        this.id_categoria = id_categoria;
        this.fk_categoria = fk_categoria;
    }

    /**
     * 
     *
     * Getter da chave primária do objeto.
     *
     * @return Um inteiro que representa a chave primária do objeto.<br>
     *
     * 
     */
    public int getId_categoria() {
        return id_categoria;
    }

    /**
     * 
     *
     * Setter da chave primária do objeto.
     *
     * @param id_categoria Um inteiro que representa a chave primária do objeto.<br>
     *
     * 
     */
    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    /**
     * 
     *
     * Getter da subcategoria do objeto.
     *
     * @return Um inteiro que representa a chave primária de uma 
     * subcategoria.<br>
     *
     * 
     */
    public int getFk_categoria() {
        return fk_categoria;
    }

    /**
     * 
     *
     * Setter da subcategoria do objeto.
     *
     * @param fk_categoria Um inteiro que representa a chave primária de uma 
     * subcategoria.<br>
     *
     * 
     */
    public void setFk_categoria(int fk_categoria) {
        this.fk_categoria = fk_categoria;
    }

}
