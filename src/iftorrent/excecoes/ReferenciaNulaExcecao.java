package iftorrent.excecoes;

/**
 *A classe ReferenciaNulaExcecao lança exceções quando o método tenta acessar
 * referencias nulas na memoria.
 * 
 * @author Leonardo Bortolini
 * @see NullPointerException
 */
public class ReferenciaNulaExcecao extends NullPointerException{

    /**
     * Construtor vazio que irá passar uma mensagem padrão como parametro.
     */
    
    public ReferenciaNulaExcecao() {
        super(ConstantesExcecoes.REFERENCIA_NULA1);
    }
    
    /**
     * Construtor que recebe como parametro uma String e a passa como parametro.
     * 
     * @param string mensagem passada para o construtor
     */
    
    public ReferenciaNulaExcecao(String string) {
        super(string);
    }
    
}
