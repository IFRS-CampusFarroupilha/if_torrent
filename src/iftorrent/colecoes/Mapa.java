package iftorrent.colecoes;

import iftorrent.excecoes.ReferenciaNulaExcecao;
import java.util.Objects;

/**
 * Representa um mapa de objetos contendo pares (chave, valor).
 * 
 * @author Guilherme Giordani
 * @author Rafael Coelho
 * @param <K> Chave
 * @param <V> Valor
 */
public class Mapa<K, V> {

    private Conjunto<NodoMapa> pares;
    private Conjunto<K> chaves;
    private ListaEncadeada<V> valores;

    /**
     * O construtor do mapa usa o método clear instanciar novos
     * objetos.
     * 
     * @see #limpar()
     */
    public Mapa() {
        limpar();
    }

    /**
     * O construtor do mapa recebe uma coleção Conjunto para preencher o mapa logo quando criado, 
     * cada posição do Conjunto contém um par de objetos (Nodo), sendo ele uma
     * chave e um objeto (key, value).
     * 
     * @param pares2 é um Conjunto de Nodos em que cada Nodo contém uma chave e um valor.
     */
    public Mapa(Conjunto<NodoMapa> pares2) {
        this.pares = pares2;
        this.chaves = new Conjunto(pares2.tamanho());
        this.valores = new ListaEncadeada();
        NodoLista percorre = pares2.obter_na_posicao(0);
        
        for (int c = 0; c < pares2.tamanho(); c++) {
            this.chaves.adicionar(((NodoMapa) percorre.obter_objeto()).obter_chave());
            this.valores.adicionar((V) ((NodoMapa) percorre.obter_objeto()).obter_valor());
            percorre = percorre.obter_proximo();
        }
    }

    /**
     * Informa o número de elementos do mapa
     * 
     * @return Retorna quantos pares (chave, valor) tem no mapa.
     */
    public int tamanho() {
        return pares.tamanho();
    }

    /**
     * 
     * @return Retorna true se o número de pares for 0, ou seja, não tenha nenhuma
     * chave e nenhum valor.
     * @see #tamanho() 
     */
    public boolean eh_vazio() {
        return tamanho() == 0;
    }

    /**
     * Percorre a lista de objetos para ver se o objeto recebido por parametro
     * está nela.
     * 
     * @param valor é o objeto a ser procurado na lista.
     * @return true caso o objeto esteja na lista, e false para o contrário.
     * @see ListaEncadeada#contem(java.lang.Object) 
     */
    public boolean contem_objeto(V valor) {
        return valores.contem(valor);
    }

    /**
     * Percorre a lista de chaves para ver se a chave recebida por parametro
     * está nela.
     * 
     * @param chave é a chave a ser procurada na lista.
     * @return true caso a chave esteja na lista, e false para o contrário.
     */
    public boolean contem_chave(K chave) {
        return chaves.contem(chave);
    }

    /**
     * Percorre o mapa em busca de uma chave, e se encontrar, retorna o objeto
     * a que ela está relacionado.

     * @param chave é a chave a ser procurada na lista.
     * @return Retorna o objeto que está relacionado a chave recebida por parametro.
     */
    public V obter_objeto(K chave) throws ReferenciaNulaExcecao {
        int posicao = chaves.obter_indice(chave);
        
        return (V) valores.obter_na_posicao(posicao);
    }

    /**
     * Adiciona um par (chave, valor) no mapa.
     * 
     * @param chave é a chave a ser adicionada.
     * @param valor é o objeto a ser adicionado.
     * @return Caso conseguir adicionar retorna o objeto recebido por parametro,
     * caso contrário retorna null.
     * @see ListaEncadeada#adicionar(java.lang.Object) 
     */
    public boolean adicionar(K chave, V valor) {
        if (chaves.adicionar(chave)) {
            pares.adicionar(new NodoMapa(chave, valor));
            valores.adicionar(valor);
            return true;
        }
        return false;
    }

    /**
     * Procura por uma chave no mapa, e quando a encontra, a remove e remove o
     * objeto a que ela está relacionada.
     * 
     * @param chave é a chave a ser procurada na lista.
     * @return Retorna true caso removeu com sucesso a chave e o objeto, e false
     * caso contrário.
     * @throws ReferenciaNulaExcecao Se a chave não existir.
     * @see Conjunto#remover(java.lang.Object) 
     * @see ListaEncadeada#remover(java.lang.Object) 
     * @see ListaEncadeada#obter_indice(java.lang.Object) 
     */
    public boolean remover(K chave) throws ReferenciaNulaExcecao{
        int posicao = chaves.obter_indice(chave);
        
        if (pares.remover(posicao)
                    && chaves.remover(posicao)) {
                valores.remover(posicao);
                return true;
        }
        return false;
    }    

    /**
     * Limpa e instancia todas as coleções usadas no mapa.
     */
    public void limpar() {
        this.pares = new Conjunto();
        this.chaves = new Conjunto();
        this.valores = new ListaEncadeada();
    }

    /**
     * Conjunto de chaves do mapa
     * @return Retorna uma coleção do tipo Conjunto contendo todas as chaves
     * do mapa.
     */
    public Conjunto<K> chaves() {
        return chaves;
    }
    
    public String para_texto() {
        String retorno = "{";
        Conjunto keys = chaves();
        ListaEncadeada values = valores();
        
        for (int i = 0; i < tamanho(); i++) {
            retorno += "(" + keys.obter_na_posicao(i).obter_objeto() + ";" + values.obter_na_posicao(i).obter_objeto() + ") ";
        }
        return retorno += "}";
    }

    /**
     * Lista de valores do mapa
     * @return Retorna uma coleção do tipo ListaEncadeada contendo todos os valores
     * do mapa.
     */
    public ListaEncadeada<V> valores() {
        return valores;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        
        hash = 67 * hash + Objects.hashCode(this.pares);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this != obj || (obj == null && getClass() != obj.getClass())) {
            return false;
        }
        final Mapa other = (Mapa) obj;
        
        return Objects.equals(this.pares, other.pares);
    }
}