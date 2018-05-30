package iftorrent.excecoes;

/**
 * A classe AritmeticoExcecao lança exceções quando algum erro aritmetico 
 * ocorrer.
 * 
 * @author Leonardo Bortolini
 * @see ArithmeticException
 */
public class AritmeticoExcecao extends ArithmeticException{
    
    /**
     * Construtor que não recebe parametro e passa uma mensagem padrão como 
     * parametro
     */
    
    public AritmeticoExcecao() {
        super(ConstantesExcecoes.ARITMETICO1);
    }
    
    /**
     * Construtor que recebe como parametro uma mensagem e a passa como parametro.
     * 
     * @param string mensagem passada como parametro para o construtor
     */

    public AritmeticoExcecao(String string) {
        super(string);
    }
    
}
