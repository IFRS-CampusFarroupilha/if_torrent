package iftorrent.colecoes;

import iftorrent.excecoes.ArgumentoIlegalExcecao;
import iftorrent.excecoes.ReferenciaNulaExcecao;
import java.util.Iterator;

/**
 * Classe que representa uma lista com vetor (ArrayList)
 *
 * @author Leonardo Bortolini
 * @author Guilherme Giordani
 * @param <tipo_objeto> Objeto contido na lista
 */
public class Lista<tipo_objeto> implements ColecaoAbstrata<tipo_objeto>, Iterable<tipo_objeto> {

    private tipo_objeto[] vetor;

    /**
     * O metodo lista é o construtor inicial para iniciar a lista com 0 posições
     * que serão modificadas, adicionadas e removidas no programa conforme os
     * comandos.
     */
    public Lista() {
        vetor = (tipo_objeto[]) new Object[0];
    }

    /**
     * O metodo adicionar recebe como parametro um objeto, adiciona uma posição
     * no vetor usando o metodo ajustar_vetor para armazena-lo.
     *
     * @param objeto O objeto que vai ser adicionado
     * @see #ajustar_vetor(int)
     */
    @Override
    public boolean adicionar(tipo_objeto objeto) {
        this.vetor = ajustar_vetor(1);
        this.vetor[vetor.length - 1] = objeto;
        return true;
    }

    /**
     * O metodo adicionar recebe como parametro um objeto para adicionar na
     * posição do vetor especificada pelo parametro "index", esse index é testado
     * e se for invalido uma exceção de ArgumentoIlegal é lançada, caso contrario é
     * adicionada uma posição no vetor para poder armazenar o objeto usando o metodo
     * ajustar_vetor.
     *
     * @param objeto o objeto que vai ser adicionado
     * @param index o indice que o objeto vai ser adicionado
     * @throws ArgumentoIlegalExcecao - se o indíce for inválido
     * @see #ajustar_vetor(int)
     */
    public void adicionar(tipo_objeto objeto, int index) {
        if (!eh_indice_valido(index)) {
            throw new ArgumentoIlegalExcecao();
        }
        this.vetor = ajustar_vetor(1);

        for (int i = vetor.length - 1; i > index; i--) {
            this.vetor[i] = this.vetor[i - 1];
        }
        this.vetor[index] = objeto;
    }

    /**
     * O metodo obter_indice recebe como parametro um objeto e percorre o vetor
     * da lista até encontrar um objeto idêntico a ele, assim achando em qual
     * indice esta localizado, caso o objeto seja nulo é lançado uma exceção 
     * do tipo ReferenciaNula é lançada.
     *
     * @param objeto que vai ser adicionado
     * @throws ReferenciaNulaExcecao - caso o objeto passado como parametro seja nulo
     * @return int i ou -1, caso o objeto não esteja inserido no vetor.
     */
    @Override
    public int obter_indice(tipo_objeto objeto) {
        if (objeto == null) {
            throw new ReferenciaNulaExcecao();
        }
        for (int i = 0; i < vetor.length; i++) {
            if (this.vetor[i].equals(objeto)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * O metodo eh_vazio testa se a lista está vazia.
     *
     * @return true se a lista estiver vazia e false caso contrario.
     */
    @Override
    public boolean eh_vazio() {
        return vetor.length == 0;
    }

    /**
     * O metodo mostrar_dados mostra todo conteudo do vetor.
     */
    @Override
    public void mostrar_dados() {
        for (tipo_objeto vetor1 : vetor) {
            System.out.print(vetor1 + " ");
        }
        System.out.println();
    }

    /**
     * O metodo ajustar_vetor parte do pressuposto de redimensionar o tamanho do
     * vetor, adicionando as posições que receber como parametro.
     *
     * @param controle indica quantas posições serão adicionadas no vetor.
     * @return retorna um vetor com seu novo tamanho, ou seja, um vetor com todo
     * conteudo do vetor antigo e mais as posições adicionadas.
     */
    private tipo_objeto[] ajustar_vetor(int controle) {
        tipo_objeto[] vetor1 = (tipo_objeto[]) new Object[vetor.length + controle];
        int fim = vetor.length;

        if (controle < 1) {
            fim += controle;
        }
        for (int i = 0; i < fim; i++) {
            vetor1[i] = (tipo_objeto) new Object();
            vetor1[i] = vetor[i];
        }
        return vetor1;
    }

    /**
     * O metodo obter_objeto_posicao recebe como parametro um indice e retornara
     * o objeto correspondente. Caso o indice seja inválido uma exceção do tipo
     * ArgumentoIlegal é lançada.
     *
     * @param index indice da lista desejado.
     * @return retorna o objeto do indice especificado
     * @throws ArgumentoIlegalExcecao - caso o indice passado seja inválido
     */
    public tipo_objeto obter_objeto_posicao(int index) {
        if (eh_indice_valido(index)) {
            return this.vetor[index];
        }
        throw new ArgumentoIlegalExcecao();
    }

    /**
     * O metodo remover remove a ultima posição da lista caso ela não esteja
     * vazia. A remoção é feita usando o metodo ajustar_vetor, simplesmente
     * diminuindo uma posição no vetor. Caso a lista ja esteja vazia é lançada
     * uma exceção de ReferenciaNula.
     *
     * @throws ReferenciaNulaExcecao - caso a lista esteja vazia
     * @return true caso esteja vazia e false caso não.
     */
    public boolean remover() {
        if (vetor.length > 0) {
            this.vetor = ajustar_vetor(-1);
            return true;
        }
        throw new ReferenciaNulaExcecao("A lista está vazia");
    }

    /**
     * O metodo remover recebe como parametro um indice que será validado e
     * então removido. Se o indice for inválido uma exceçao do tipo ArgumentoIlegal
     * é lançada.
     *
     * @param index indice para ser removdo
     * @return retorna true caso o objeto for removido com sucesso e false caso
     * contrario.
     * @throws ArgumentoIlegalExcecao - caso o indice seja inválido
     */
    public boolean remover(int index) {
        if (eh_indice_valido(index)) {
            for (int i = index; i < vetor.length - 1; i++) {
                this.vetor[i] = this.vetor[i + 1];
            }
            return remover();
        }
        throw new ArgumentoIlegalExcecao();
    }

    /**
     * O metodo remover recebe como parametro um objeto que sera removido. A
     * remoção ocorre por meio de dois outros metodos, a função indice e a
     * função remover(int). A função indice retorna o indice de tal objeto na
     * lista, então o objeto é passado como parametro para ela e o seu retorno é
     * passado como parametro para o metodo remover(int).
     *
     * @param objeto objeto que será removido.
     * @return retorna true caso o objeto tenha sido removido e falso caso não.
     */
    @Override
    public boolean remover(tipo_objeto objeto) {
        return remover(obter_indice(objeto));
    }

    /**
     * O metodo remover_trecho recebe como parametro dois indices que
     * especificarão um trecho que será removido da lista. Após os indices serem
     * validados, cada indice é removido individualmente usando a função
     * remover(int). Caso algum indice passado seja inválido uma exceção do tipo
     * ArgumentoIlegal é lançada.
     *
     * @param index1 indice que indica o início do trecho.
     * @param index2 indice que indica o fim do trecho.
     * @return retorna true caso o trecho tenha sido removido com sucesso e
     * false caso não.
     * @throws ArgumentoIlegalExcecao - caso algum indice passado seja inválido
     */
    public boolean remover_trecho(int index1, int index2) {
        if (eh_indice_valido(index1) && eh_indice_valido(index2)) {
            for (int i = index1; i < index2; i++) {
                remover(index1);
            }
            return true;
        }
        throw new ArgumentoIlegalExcecao();
    }

    /**
     * O método modificar_posicao recebe como paramentro um indice e um objeto.
     * O objeto será inserido nesse indice que ja esta sendo utilizado após este
     * indice ser validado. Se o indice for inválido, uma exceção do tipo Argumento
     * Ilegal é lançada.
     *
     * @param index indice para o objeto ser inserido
     * @param objeto objeto que será inserido
     * @return returna true caso a modificação seja feita com sucesso e false
     * caso não.
     * @throws ArgumentoIlegalExcecao
     */
    public boolean modificar_posicao(int index, tipo_objeto objeto) {
        if (eh_indice_valido(index)) {
            this.vetor[index] = objeto;
            return true;
        }
        throw new ArgumentoIlegalExcecao();
    }

    /**
     * O metodo para_vetor retorna um array da lista.
     *
     * @return retorna um array da lista.
     */
    @Override
    public tipo_objeto[] para_vetor() {
        return this.vetor;
    }

    /**
     * O metodo modificar_objeto recebe dois objetos como parametro. Todas
     * ocorrências do primeiro serão sobrepostas pelo segundo.
     *
     * @param objeto1 Objeto que será sobreposto.
     * @param objeto2 Objeto que será inserido no lugar do primeiro objeto.
     * @return retorna true após todas modificações serem concluídas.
     */
    @Override
    public boolean modificar_objeto(tipo_objeto objeto1, tipo_objeto objeto2) {
        boolean controle = false;
        
        for (int i = 0; i < vetor.length; i++) {
            if (this.vetor[i].equals(objeto1)) {
                this.vetor[i] = objeto2;
                controle = true;
            }
        }
        return controle;
    }

    /**
     * O metodo eh_indice_valido recebe como parametro um indice e verifica se
     * ele existe na lista.
     *
     * @param index indice que será verificado se está na lista.
     * @return retorna true caso a lista possua esse indice ou false caso não.
     */
    private boolean eh_indice_valido(int index) {
        return index >= 0 && index <= vetor.length - 1;
    }

    /**
     * O metodo tamanho retorna o tamanho da lista.
     *
     * @return retorna o tamanho da lista.
     */
    @Override
    public int tamanho() {
        return vetor.length;
    }

    /**
     * O metodo para_texto retorna uma String contendo todo o conteudo da lista.
     *
     * @return Retorna uma String contendo todo conteudo da lista.
     */
    @Override
    public String para_texto() {
        String texto = "";

        for (tipo_objeto vetor1 : vetor) {
            texto += vetor1.toString() + " ";
        }
        return texto;
    }

    @Override
    public Iterator<tipo_objeto> iterator() {
        return new Iterator<tipo_objeto>(){
            int indice = -1;
            
            @Override
            public boolean hasNext() {
                return eh_indice_valido(indice + 1);
            }

            @Override
            public tipo_objeto next() {
                indice++;
                return obter_objeto_posicao(indice);
            }

            @Override
            public void remove() {
                remover(indice);
                indice--;
            }
            
        };
    }
    
    
}
