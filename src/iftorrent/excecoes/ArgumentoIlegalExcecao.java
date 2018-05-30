package iftorrent.excecoes;

/**
 * A classe ArgumentoIlegalExcecao lança exceções quando algum argumento
 * que foi passado não está correto.
 * 
 * @author Leonardo Bortolini
 * @see IllegalArgumentException
 */
public class ArgumentoIlegalExcecao extends IllegalArgumentException{

    /**
     * Construtor vazio que irá passar uma mensagem padrão como parametro.
     */
    
    public ArgumentoIlegalExcecao() {
        super(ConstantesExcecoes.ARGUMENTO_ILEGAL1);
    }

    /**
     * Construtor que recebe como parametro uma String e a passa como parametro.
     * 
     * @param string mensagem passada para o construtor
     */
    
    public ArgumentoIlegalExcecao(String string) {
        super(string);
    }

    /**
     * Construtor que recebe como parametro uma exceção que causou alguma outra,
     * ou seja, se uma exceção gerar essa, ela podera ser passada como parametro
     * e uma String com alguma mensagem.
     * 
     * @param string mensagem passada para o construtor
     * @param thrwbl objeto que causou a exceção
     */
    
    public ArgumentoIlegalExcecao(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
    /**
     * Construtor que recebe como parametro uma exceção que causou alguma outra,
     * ou seja, se uma exceção gerar essa, ela podera ser passada como parametro
     * 
     * @param thrwbl objeto que causou a exceção
     */

    public ArgumentoIlegalExcecao(Throwable thrwbl) {
        super(thrwbl);
    }
}
