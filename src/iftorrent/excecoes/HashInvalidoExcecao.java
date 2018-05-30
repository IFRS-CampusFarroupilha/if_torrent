package iftorrent.excecoes;

import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Rafael Coelho
 */
public class HashInvalidoExcecao extends NoSuchAlgorithmException {
     /**
     * Construtor que recebe como parametro uma String e a passa como parametro.
     * 
     * @param string mensagem passada como parametro para o construtor
     */
    
    public HashInvalidoExcecao(String string) {
        super(string);
    }

     /**
     * Construtor vazio que irá passar uma mensagem padrão como parametro.
     */
    
    public HashInvalidoExcecao() {
        super(ConstantesExcecoes.HASH);
    }

    /**
     * Construtor que recebe como parametro uma exceção que causou alguma outra,
     * ou seja, se uma exceção gerar essa, ela podera ser passada como parametro
     * 
     * @param thrwbl objeto causador da exceção
     */
    
    public HashInvalidoExcecao(Throwable thrwbl) {
        super(thrwbl);
    }

    /**
     * Construtor que recebe como parametro uma exceção que causou alguma outra,
     * ou seja, se uma exceção gerar essa, ela podera ser passada como parametro
     * e uma String com alguma mensagem.
     * 
     * @param string mensagem passada como parametro para o construtor
     * @param thrwbl objeto causador da exceção 
     */
    
    public HashInvalidoExcecao(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
