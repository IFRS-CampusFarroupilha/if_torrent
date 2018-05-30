package iftorrent.colecoes;

import java.util.Objects;

/**
 * Representa um nodo de uma lista encadeada
 * 
 * @author Guilherme Giordani
 * @param <tipo_objeto> objeto que estará contido na lista
 */
public class NodoLista<tipo_objeto> {
    private NodoLista proximo;
    private tipo_objeto objeto;
    
    /**
     * Construtor para preencher um nodo.
     * 
     * @param objeto é o objeto que o nodo vai guardar.
     */
    public NodoLista(tipo_objeto objeto) {
        this.objeto = objeto;
        this.proximo = null;
    }
    
    /**
     * 
     * @return Retorna o endereço do próximo nodo.
     */
    public NodoLista obter_proximo() {
        return proximo;
    }

    /**
     * Altera o valor do endereço do proximo nodo com o novo valor recebido por 
     * parametro.
     * 
     * @param proximo - É o valor recebido por parametro.
     */
    public void alterar_proximo(NodoLista proximo) {
        this.proximo = proximo;
    }

    /**
     * 
     * @return Retorna o objeto do nodo.
     */
    public tipo_objeto obter_objeto() {
        return objeto;
    }

    /**
     * Altera o valor do objeto nodo com o novo valor recebido por 
     * parametro.
     * 
     * @param valor - É o valor recebido por parametro.
     */
    public void alterar_objeto(tipo_objeto valor) {
        this.objeto = valor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        
        hash = 97 * hash + Objects.hashCode(this.proximo);
        hash = 97 * hash + Objects.hashCode(this.objeto);
        return hash;
    }

    public boolean equals(NodoLista objeto) {
        return !(objeto == null 
                || !(objeto instanceof NodoLista)
                || !(this.obter_proximo().equals(objeto.obter_proximo()))
                || !(this.obter_objeto().equals(objeto.obter_objeto())));
    }    
}