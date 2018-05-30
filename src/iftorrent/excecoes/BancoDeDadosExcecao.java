package iftorrent.excecoes;

import java.sql.SQLException;

/**
 * A classe BandoDeDadosExcecao lança exceções quando ocorrer algum erro no
 * banco de dados.
 * 
 * @author Leonardo Bortolini
 * @see SQLException
 */

public class BancoDeDadosExcecao extends SQLException{

    /**
     * Construtor que recebe como parametro uma String e a passa como parametro.
     * 
     * @param string mensagem passada como parametro para o construtor
     */
    
    public BancoDeDadosExcecao(String string) {
        super(string);
    }

     /**
     * Construtor vazio que irá passar uma mensagem padrão como parametro.
     */
    
    public BancoDeDadosExcecao() {
        super(ConstantesExcecoes.BANCO_DE_DADOS1);
    }

    /**
     * Construtor que recebe como parametro uma exceção que causou alguma outra,
     * ou seja, se uma exceção gerar essa, ela podera ser passada como parametro
     * 
     * @param thrwbl objeto causador da exceção
     */
    
    public BancoDeDadosExcecao(Throwable thrwbl) {
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
    
    public BancoDeDadosExcecao(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
}
