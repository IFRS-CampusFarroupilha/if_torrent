package iftorrent.ordenador;

import iftorrent.excecoes.ConverterClasseExcecao;
import static iftorrent.ordenador.ConstantesOrdenador.EXCECAO1;
import java.util.List;

/**
 * 
 *
 * A classe <b>OrdenadorRapido</b> serve para ordenar o conteúdo de um objeto 
 * que extende a classe <b>List</b>.
 * <p>
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class OrdenadorRapido {

    /**
     * 
     * 
     * Método para ordenar a lista.
     * 
     * @param vetor Um objeto que extende a classe <b>List</b> e que representa 
     * a lista a ser ordenada.<br>
     * 
     * 
     */
    public static void ordenar(List vetor) {
        if(!(vetor.get(0) instanceof Comparable)){
            throw new ConverterClasseExcecao(EXCECAO1);
        }
        quickSort(0, vetor.size(), vetor);
    }

    /**
     * 
     * 
     * Método que estabelece um pivô e ordena os elementos de maneira que os 
     * elementos menores que o pivô fiquem à esquerda do mesmo e os demais 
     * fiquem à sua direita.
     * 
     * @param inicio Um inteiro que demarca o início (inclusive) do trecho da 
     * lista a ser ordenada.<br>
     * @param fim Um inteiro que demarca o final (exclusive) do trecho da lista 
     * a ser ordenada.<br>
     * @param vetor Um objeto que extende a classe <b>List</b> e que representa 
     * a lista a ser ordenada.<br>
     * 
     * 
     */
    private static void quickSort(int inicio, int fim, List vetor) {
        if (inicio < fim - 1) {
            int posicaoPivo = separar(inicio, fim, vetor);

            quickSort(inicio, posicaoPivo, vetor);
            quickSort(posicaoPivo + 1, fim, vetor);
        }
    }

    /**
     * 
     * 
     * Método para dividir um trecho do vetor em dois trechos menores usando o 
     * pivô como separador de maneira que os elementos menores que ele fiquem 
     * à sua esquerda e os demais à sua direita.
     * 
     * @param <T> O tipo de dado armazenado na lista.
     * @param inicio Um inteiro que demarca o início (inclusive) do trecho da 
     * lista a ser ordenada.<br>
     * @param fim Um inteiro que demarca o final (exclusive) do trecho da lista 
     * a ser ordenada.<br>
     * @param vetor Um objeto que extende a classe <b>List</b> e que representa 
     * a lista a ser ordenada.<br>
     * 
     * @return Um inteiro que representa a posição final do pivô.<br>
     * 
     * 
     */
    private static <T extends Comparable<? super T>> int separar(int inicio, int fim, List<T> vetor) {
        T pivo = vetor.get(inicio);
        int i = inicio + 1;
        int f = fim - 1;

        while (i <= f) {
            if (vetor.get(i).compareTo(pivo) <= 0) {
                i++;
            } else if (vetor.get(f).compareTo(pivo) > 0) {
                f--;
            } else {
                trocar(i, f, vetor);
                    i++;
                    f--;
            }
        }
        trocar(inicio, f, vetor);
        return f;
    }

    /**
     * 
     * 
     * Método para trocar dois elementos entre si.
     * 
     * @param posicao1 Um inteiro que representa o índice de um dos elementos 
     * que serão trocados.<br>
     * @param posicao2 Um inteiro que representa o índice de um dos elementos 
     * que serão trocados.<br>
     * @param vetor Um objeto que extende a classe <b>List</b> e que representa 
     * a lista a ser ordenada.<br>
     * 
     * 
     */
    private static void trocar(int posicao1, int posicao2, List vetor) {
        Object aux = vetor.get(posicao1);
        
        vetor.set(posicao1, vetor.get(posicao2));
        vetor.set(posicao2, aux);
    }

}
