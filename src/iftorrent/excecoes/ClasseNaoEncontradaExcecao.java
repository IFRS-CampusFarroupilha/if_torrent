package iftorrent.excecoes;

/**
 * A classe ClasseNaoEncontrada lança exceções quando alguma classe for 
 * requisitada e não é encontrada.
 * 
 * @author Leonardo Bortolini
 * @see ClassNotFoundException
 */
public class ClasseNaoEncontradaExcecao extends ClassNotFoundException{

    /**
     * Construtor vazio que irá passar uma mensagem padrão como parametro.
     */
    
    public ClasseNaoEncontradaExcecao() {
        super(ConstantesExcecoes.CLASSE_NAO_ENCONTRADA1);
    }

    /**
     * Construtor que recebe como parametro uma String e a passa como parametro.
     * 
     * @param string mensagem passada para o construtor
     */
    
    public ClasseNaoEncontradaExcecao(String string) {
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
    
    public ClasseNaoEncontradaExcecao(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }
    
    /**
     * Construtor que recebe como parametro uma exceção que causou alguma outra,
     * ou seja, se uma exceção gerar essa, ela podera ser passada como parametro
     * 
     * @param thrwbl objeto que causou a exceção
     */
    
    public ClasseNaoEncontradaExcecao(Throwable thrwbl) {
        super(ConstantesExcecoes.CLASSE_NAO_ENCONTRADA1, thrwbl);
    }
    
}
