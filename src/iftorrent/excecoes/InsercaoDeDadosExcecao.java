package iftorrent.excecoes;

import java.util.InputMismatchException;

/**
 * A classe InsercaoDeDadosExcecao lança exceções quando o Scanner tenta ler
 * dados de tipo não esperado.
 * 
 * @author Leonardo Bortolini
 * @see InputMismatchException
 */
public class InsercaoDeDadosExcecao extends InputMismatchException{

    /**
     * Construtor vazio que irá passar uma mensagem padrão como parametro.
     */
    
    public InsercaoDeDadosExcecao() {
        super(ConstantesExcecoes.INSERCAO_DE_DADOS);
    }
    
    /**
     * Construtor que recebe como parametro uma String e a passa como parametro.
     * 
     * @param string mensagem passada como parametro para o construtor
     */

    public InsercaoDeDadosExcecao(String string) {
        super(string);
    }
    
}
