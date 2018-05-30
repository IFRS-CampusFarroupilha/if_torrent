package iftorrent.colecoes;

import iftorrent.excecoes.ReferenciaNulaExcecao;
import java.util.Iterator;

/**
 * Representa uma Lista Simplesmente Encadeada
 *
 * @author Guilherme Giordani
 * @author Rafael Coelho
 * @param <tipo_objeto> objeto que estará contido na lista
 */
public class ListaEncadeada<tipo_objeto> implements ColecaoAbstrata<tipo_objeto>, Iterable<tipo_objeto>{

    private NodoLista<tipo_objeto> primeiro;
    private NodoLista<tipo_objeto> ultimo;

    /**
     * Construtor que atribui null às coleções.
     *
     */
    public ListaEncadeada() {
        primeiro = null;
        ultimo = null;
    }

    /**
     * Construtor que recebe por parametro um objeto que já é colocado na lista.
     *
     * @param objeto é o objeto a ser colocado na lista.
     */
    public ListaEncadeada(tipo_objeto objeto) {
        primeiro = new NodoLista(objeto);
        ultimo = primeiro;
    }

    /**
     * Instancia um nodo (endereço_próximo_nodo, Value) que guardará objeto
     * recebido por parametro e testa se a lista está vazia, caso verdadeiro o
     * nodo será colocado em primeiro na lista, e caso seja falso será colocado
     * por último.
     *
     * @param objeto é o objeto a ser colocado na lista.
     * @return Retorna true caso adicionou com sucesso, e false caso contrário.
     */
    @Override
    public boolean adicionar(tipo_objeto objeto) {
        NodoLista novo = new NodoLista(objeto);

        if (eh_vazio()) {
            primeiro = novo;
        } else {
            ultimo.alterar_proximo(novo);
        }
        ultimo = novo;
        return true;
    }

    /**
     * Instancia um nodo (endereço_próximo_nodo, Value) que guardará objeto
     * recebido por parametro e testa se a lista está vazia, caso verdadeiro o
     * nodo será colocado em primeiro na lista, e caso seja falso o atributo
     * endereço_próximo_nodo do novo nodo instanciado apontará para quem está em
     * primeiro agora, e o novo nodo se tornará o primeiro.
     *
     * @param objeto é o objeto a ser colocado na lista.
     * @return Retorna true caso adicionou com sucesso, e false caso contrário.
     */
    public boolean adicionar_no_inicio(tipo_objeto objeto) {
        NodoLista novo = new NodoLista(objeto);

        if (!eh_vazio()) {
            novo.alterar_proximo(primeiro);
        } else {
            ultimo = novo;
        }
        primeiro = novo;
        return true;
    }

    /**
     * Percorre a lista até encontrar o objeto procurado e faz com que este
     * objeto procurado aponte para o novo que está sendo recebido por parametro
     * e o novo objeto apontará agora para onde o anterior dele apontava.
     *
     * @param objeto_procurado é o objeto a ser procurado na lista.
     * @param objeto é o objeto a ser colocado "na frente" do objeto procurado.
     * @return Retorna true caso adicionou com sucesso, e false caso contrário.
     */
    public boolean adicionar_apos(tipo_objeto objeto_procurado, tipo_objeto objeto) {
        NodoLista nodo = primeiro;

        while (nodo != null
                && !nodo.obter_objeto().equals(objeto_procurado)) {
            nodo = nodo.obter_proximo();
        }
        if (nodo != null) {
            NodoLista novo = new NodoLista(objeto);

            novo.alterar_proximo(nodo.obter_proximo());
            nodo.alterar_proximo(novo);
            if (novo.obter_proximo() == null) {
                ultimo = novo;
            }
            return true;
        }
        return false;
    }

    /**
     * Remove o primeiro objeto da lista, fazendo o segundo virar o primeiro.
     *
     * @return Retorna true caso removeu com sucesso, e false caso contrário.
     */
    public boolean remover_inicio() {
        if (!eh_vazio()) {
            NodoLista nodo = primeiro;

            primeiro = nodo.obter_proximo();
            return true;
        }
        return false;
    }

    /**
     * Percorre a lista até chegar no último objeto, remove ele e faz com que o
     * anterior a ele vire o último.
     *
     * @return Retorna true caso removeu com sucesso, e false caso contrário.
     */
    public boolean remover_ultimo() {
        if (!eh_vazio()) {
            NodoLista nodo = primeiro;
            NodoLista anterior = null;

            while (nodo.obter_proximo() != null) {
                anterior = nodo;
                nodo = nodo.obter_proximo();
            }
            anterior.alterar_proximo(null);
            ultimo = anterior;
            return true;
        }
        return false;
    }

    /**
     * Percorre a lista até chegar no objeto requerido, remove ele e faz com que
     * o anterior a ele aponte para onde o removido apontava.
     *
     * @param valor
     * @return Retorna true caso removeu com sucesso, e false caso contrário.
     */
    @Override
    public boolean remover(tipo_objeto valor) {
        if (!eh_vazio()) {
            NodoLista nodo = primeiro;
            NodoLista anterior = null;

            while (nodo != null
                    && !nodo.obter_objeto().equals(valor)) {
                anterior = nodo;
                nodo = nodo.obter_proximo();
            }
            if (nodo != null) {
                if(anterior != null){
                    anterior.alterar_proximo(nodo.obter_proximo());
                }else{
                    remover_inicio();
                }
                return true;
            }  
        }
        return false;
    }

    /**
     * Percorre a lista até chegar no objeto de número requerido, remove o
     * objeto e faz com que o anterior a ele aponte para onde o removido
     * apontava.
     *
     * @param index índice que será removido
     * @return Retorna true caso removeu com sucesso, e false caso contrário.
     */
    public boolean remover(int index) {
        if (!eh_vazio()) {
            NodoLista nodo = primeiro;
            NodoLista anterior = null;
            int c = 0;

            while (nodo != null
                    && c != index) {
                anterior = nodo;
                nodo = nodo.obter_proximo();
                c++;
            }
            if (anterior != null
                    && nodo != null) {
                anterior.alterar_proximo(nodo.obter_proximo());
            }
            return true;
        }
        return false;
    }

    /**
     * Verifica se a lista não contém elementos.
     *
     * @return Retorna true caso a lista esteja vazia e false caso contrário. (A
     * lista está vazia quando seu tamanho é 0).
     */
    @Override
    public boolean eh_vazio() {
        return tamanho() == 0;
    }

    /**
     * Percorre a lista até chegar no último objeto contando +1 a cada objeto
     * percorrido.
     *
     * @return Retorna o número de objetos contidos na lista.
     */
    @Override
    public int tamanho() {
        NodoLista x = primeiro;
        int cont = 0;

        while (x != null) {
            x = x.obter_proximo();
            cont++;
        }
        return cont;
    }

    /**
     * Percorre a lista mostrando as informações de cada objeto percorrido.
     */
    @Override
    public void mostrar_dados() {
        NodoLista x = primeiro;

        while (x != null) {
            System.out.println(x.obter_objeto().toString());
            x = x.obter_proximo();
        }
        System.out.println();
    }

    /**
     * Percorre a lista até chegar na posição desejada, retorna o objeto que se
     * encontra nessa posição.
     *
     * @param index é a posição desejada
     * @return Retorna o objeto da lista que se encontra na posição recebida por
     * parametro.
     */
    public NodoLista obter_na_posicao(int index) {
        NodoLista x = primeiro;
        int cont = 0;

        while (x != null
                && index != cont) {
            x = x.obter_proximo();
            cont++;
        }
        return x;
    }

    /**
     *
     * Transforma lista em vetor.
     *
     * @return Retorna um vetor de todos os elementos da lista.
     */
    @Override
    public tipo_objeto[] para_vetor() {
        tipo_objeto[] vetor = (tipo_objeto[]) new Object[tamanho()];
        NodoLista x = primeiro;
        int c = 0;

        while (x != null) {
            vetor[c] = (tipo_objeto) x.obter_objeto();
            x = x.obter_proximo();
            c++;
        }
        return vetor;
    }

    /**
     * Recebe um objeto como parametro e percorre a lista toda em sua procura.
     *
     * @param objeto é o objeto a ser procurado na lista
     * @return Retorna true caso o objeto se encontre na fila, e false caso
     * contrário.
     */
    public boolean contem(tipo_objeto objeto) {
        NodoLista procura = primeiro;

        while (procura != null) {
            if(objeto.equals(procura.obter_objeto())) {
                return true;
            }
            procura = procura.obter_proximo();
        }
        return false;
    }

    /**
     * Percorre a lista contando por quantos objetos passou até encontrar o
     * objeto requerido.
     *
     * @param objeto é o objeto a ser procurado na lista
     * @return Retorna a quantidade de objetos que estão na frente do requerido.
     * @throws ReferenciaNulaExcecao Se a chave não existir.
     */
    @Override
    public int obter_indice(tipo_objeto objeto) throws ReferenciaNulaExcecao {
        NodoLista procura = primeiro;
        int c = 0;

        while (procura != null) {
            if (procura.obter_objeto().equals(objeto)) {
                return c;
            }
            procura = procura.obter_proximo();
            c++;
        }
        throw new ReferenciaNulaExcecao("Objeto não encontrado!");
    }

    /**
     * Concatena todos os toString dos objetos em uma string apenas.
     *
     * @return Retorna em forma de string todas as informações de todos os
     * objetos
     */
    @Override
    public String para_texto() {
        String saida = "";
        NodoLista percorre = primeiro;

        while (percorre != null) {
            saida += " " + percorre.obter_objeto().toString();
            percorre = percorre.obter_proximo();
        }
        return saida;
    }

    /**
     * Altera um objeto já existente na lista por outro.
     *
     * @param procurado - É o objeto a ser alterado (que já está na lista).
     * @param objeto - É o objeto que substituirá o objeto da lista.
     * @return Retorna true caso modificou o objeto, e false caso contrário.
     */
    @Override
    public boolean modificar_objeto(tipo_objeto procurado, tipo_objeto objeto) {
        NodoLista nodo = primeiro;

        while (nodo != null
                && !nodo.obter_objeto().equals(procurado)) {
            nodo = nodo.obter_proximo();
        }
        if (nodo != null) {
            nodo.alterar_objeto(objeto);
            return true;
        }
        return false;
    }

    @Override
    public Iterator<tipo_objeto> iterator() {
        return new Iterator<tipo_objeto>(){
            NodoLista percorre = primeiro;
            NodoLista anterior = percorre;
            
            @Override
            public boolean hasNext() {
                return percorre != null;
            }

            @Override
            public tipo_objeto next() {
                anterior = percorre;
                percorre = percorre.obter_proximo();
                return (tipo_objeto) anterior.obter_objeto();
            }

            @Override
            public void remove() {
                if(!eh_vazio() && anterior != null){
                    anterior.obter_objeto();
                    remover((tipo_objeto) anterior.obter_objeto());
                    anterior = null;
                }
            }
            
            public void resetIterator(){
                percorre = primeiro;
            }
        };
    }
    
    
}
