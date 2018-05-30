package iftorrent.colecoes;

/**
 * Interface das Coleções (Lista, ListaEncadeada, Conjunto)
 * Contém os priincipais métodos que uma coleção deveria ter.
 * 
 * @author Guilherme Giordani
 * @param <tipo_objeto> Tipo de objeto da coleção
 */
public interface ColecaoAbstrata<tipo_objeto> {
    
    /**
     * Diz o tamanho da coleção.
     * 
     * @return Retorna a quantidade de objetos que a coleção tem no momento.
     */
    public int tamanho();
    
    /**
     * Diz se a coleção está vazia.
     * 
     * @return Retorna true caso a coleção esteja vazia, e false caso contrário.
     */
    public boolean eh_vazio();
    
    /**
     * Deve adicionar um objeto na coleção.
     * 
     * @param objeto - É o objeto a ser adicionado na coleção.
     * @return Retorna true caso conseguiu adicionar, e false caso contrário.
     */
    public boolean adicionar(tipo_objeto objeto);
    
    /**
     * Deve remover um certo objeto da coleção caso exista.
     * 
     * @param objeto - É o objeto a ser removido da coleção.
     * @return Retorna true caso conseguiu remover, e false caso contrário.
     */
    public boolean remover(tipo_objeto objeto);
    
    /**
     * Altera um objeto já existente na coleção por outro.
     * 
     * @param objeto1 - É o objeto a ser alterado (que já está na coleção).
     * @param objeto2 - É o objeto que substituirá o objeto da coleção.
     * @return Retorna true caso modificou o objeto, e false caso contrário.
     */
    public boolean modificar_objeto(tipo_objeto objeto1, tipo_objeto objeto2);
    
    /**
     * Diz em que posição da coleção um objeto se encontra.
     * 
     * @param objeto - É o objeto a ser procurado na lista.
     * @return Retorna a posição do objeto procurado na coleção.
     */
    public int obter_indice(tipo_objeto objeto);
    
    /**
     * Mostra as informações dos objetos que se encontram na coleção.
     */
    public void mostrar_dados();
    
    /**
     * Trasnforma todo o conteúdo da coleção para um texto.
     * 
     * @return Retorna todas as informações dos objetos da coleção em forma de
     * texto.
     */
    public String para_texto();
    
    /**
     * Trasnforma todo o conteúdo da coleção para um vetor.
     * 
     * @return Retorna todos os objetos da coleção em um vetor.
     */
    public tipo_objeto[] para_vetor();
}