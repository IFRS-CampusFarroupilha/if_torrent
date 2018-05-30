package iftorrent.excecoes;

/**
 * 
 * 
 * A classe <b>EnumNaoEncontradaExcecao</b> é uma exceção para métodos de busca
 * de enuns.
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public class EnumNaoEncontradaExcecao extends Exception {

    /**
     * 
     * 
     * Construtor sem parâmetros que armazena uma mensagem padrão representada pela
     * constante do tipo <b>String</b> {@link ConstantesExcecoes#ENUM_NAO_ENCONTRADA1 
     * ENUM_NAO_ENCONTRADA1}.
     * 
     * 
     * 
     * @see ConstantesExcecoes
     */
    public EnumNaoEncontradaExcecao() {
        super(ConstantesExcecoes.ENUM_NAO_ENCONTRADA1);
    }

    /**
     * 
     * 
     * Construtor que armazena uma mensagem.
     * 
     * @param mensagem Um objeto do tipo <b>String</b> que armazena uma mensagem.<br>
     * 
     * 
     */
    public EnumNaoEncontradaExcecao(String mensagem) {
        super(mensagem);
    }

    /**
     * 
     * 
     * Construtor que armazena uma mensagem e armazena uma exceção que 
     * gerou a exceção lançada (provavelmente capturada por um <i>try-catch</i>).
     * 
     * @param mensagem Um objeto do tipo <b>String</b> que armazena uma mensagem.<br>
     * @param causa Um objeto do tipo <b>Throwable</b> que causou a exceção lançada.<br>
     * 
     * 
     */
    public EnumNaoEncontradaExcecao(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    /**
     * 
     * 
     * Construtor que armazena uma exceção que gerou a exceção lançada 
     * (provavelmente capturada por um <i>try-catch</i>).
     * 
     * @param causa Um objeto do tipo <b>Throwable</b> que causou a exceção lançada.<br>
     * 
     * 
     */
    public EnumNaoEncontradaExcecao(Throwable causa) {
        super(causa);
    }
       
}