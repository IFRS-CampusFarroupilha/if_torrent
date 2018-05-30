package iftorrent.colecoes;

/**
 * Representa um conjunto de dados que não podem se repetir (Set)
 * @author Guilherme Giordani
 * @author Rafael Coelho
 * @param <tipo> Objeto que estará contido no conjunto
 */
public class Conjunto<tipo> extends ListaEncadeada {

    /**
     * Construtor vazio.
     */
    public Conjunto() {
        super();
    }

    /**
     * Construtor que recebe por parametro um objeto que já é colocado no 
     * conjunto.
     * 
     * @param objeto é o objeto a ser colocado no conjunto.
     */
    public Conjunto(tipo objeto) {
        super(objeto);
    }

    /**
     * Adiciona um objeto "na frente" do objeto procurado, se o objeto a ser
     * adicionado já não está no conjunto.
     * 
     * @param objetoProcurado objeto a ser procurado e que apontará para o 
     * novo.<br>
     * @param objeto objeto a ser adicionado no conjunto e que apontará para 
     * onde o anterior a ele apontava.
     * @return Retorna true caso adicionou o objeto, e false caso contrário.
     */
    @Override
    public boolean adicionar_apos(Object objetoProcurado, Object objeto) {
        if (!super.contem(objeto)) {
            super.adicionar_apos(objetoProcurado, objeto);
            return true;
        }
        return false;
    }

    /**
     * Adiciona um objeto no inicio do conjunto, se o objeto a ser
     * adicionado já não está no conjunto.
     * 
     * @param objeto objeto a ser adicionado no conjunto e que apontará para 
     * onde o anterior a ele apontava.
     * @return Retorna true caso adicionou o objeto, e false caso contrário.
     */
    @Override
    public boolean adicionar_no_inicio(Object objeto) {
        if (!super.contem(objeto)) {
            super.adicionar_no_inicio(objeto);
            return true;
        }
        return false;
    }

    /**
     * Adiciona um objeto no conjunto, se o objeto a ser
     * adicionado já não está no conjunto.
     * 
     * @param objeto objeto a ser adicionado no conjunto e que apontará para 
     * onde o anterior a ele apontava.
     * @return Retorna true caso adicionou o objeto, e false caso contrário.
     */
    @Override
    public boolean adicionar(Object objeto) {
        if (!super.contem(objeto)) {
            super.adicionar(objeto);
            return true;
        }
        return false;
    }
}