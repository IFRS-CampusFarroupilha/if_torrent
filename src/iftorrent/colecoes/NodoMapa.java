package iftorrent.colecoes;

import java.util.Objects;

/**
 * A classe Nodo representa um elemento (chave, valor) para o Mapa 
 * 
 * @author Guilherme Giordani
 * @author Rafael Coelho
 * @param <K> indica a chave no par (chave, valor)
 * @param <V> indica o valor no par (chave, valor)
 */
public class NodoMapa<K, V> {

    private K chave;
    private V valor;

    /**
     * Construtor para preencher um nodo.
     * 
     * @param chave - Chave do nodo.
     * @param valor - Valor do nodo.
     */
    NodoMapa(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
    }

    /**
     * 
     * @return Retorna a chave do nodo.
     */
    public K obter_chave() {
        return chave;
    }

    /**
     * 
     * @return Retorna o valor do nodo.
     */
    public V obter_valor() {
        return valor;
    }
    
    /**
     * 
     * @return Retorna o nodo em forma de texto.
     */
    public String para_texto() {
        return chave + "=" + valor;
    }

    /**
     * Altera o valor do nodo com o novo valor recebido por parametro.
     * 
     * @param novo_valor - É o valor recebido por parametro.
     * @return Retorna true caso alterou o valor com sucesso.
     */
    public boolean alterar_valor(V novo_valor) {
        V valor_antigo = valor;
        valor = novo_valor;
        return true;
    }
    
    
    /**
     * O método equals serve para verificar se um nodo contém a mesma chave que
     * outro.
     * @return true caso os objetos guardarem a mesma chave e false 
     * caso contrário.
     */
    @Override
    public boolean equals(Object objeto) {
        return !(objeto == null 
                || !(objeto instanceof NodoMapa)
                || !(this.obter_chave().equals(((NodoMapa) objeto).obter_chave())));
    }
    
    /**
     * O método igual serve para verificar se um nodo é exatamente igual a 
     * outro, com exeção do próprio endereço.
     *  
     * @return true caso os objetos forem iguais e false caso contrário.
     */
    public boolean igual(Object objeto) {
        return !(objeto == null 
                || !(objeto instanceof NodoMapa)
                || !(this.obter_chave().equals(((NodoMapa) objeto).obter_chave()))
                || !(this.obter_valor().equals(((NodoMapa) objeto).obter_valor())));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 19 * hash + Objects.hashCode(this.chave);
        hash = 19 * hash + Objects.hashCode(this.valor);
        return hash;
    }
}
