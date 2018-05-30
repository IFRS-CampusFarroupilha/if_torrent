package iftorrent.excecoes;

/**
 * A classe FormatoDoNumeroExcecao lança exceções quando uma String é convertida
 * para algum tipo de número porem ela não tem o formato adequado.
 * 
 * @author Leonardo Bortolini
 * @see NumberFormatException
 */
public class FormatoDoNumeroExcecao extends NumberFormatException{

    /**
     * Construtor vazio que irá passar uma mensagem padrão como parametro.
     */
    
    public FormatoDoNumeroExcecao() {
        super(ConstantesExcecoes.FORMATO_DO_NUMERO1);
    }
    
    /**
     * Construtor que recebe como parametro uma String e a passa como parametro.
     * 
     * @param string mensagem passada para o construtor.
     */

    public FormatoDoNumeroExcecao(String string) {
        super(string);
    }
    
}
