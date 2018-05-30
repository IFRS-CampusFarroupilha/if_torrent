package iftorrent.excecoes;

/**
 * 
 * 
 * A classe <b>SistemaOperacionalNaoSuportadoExcecao</b> é uma exceção para 
 * Sistemas Operacionais não suportados pelo método.
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public class SistemaOperacionalNaoSuportadoExcecao extends Exception {

    /**
     * 
     * 
     * Construtor sem parâmetros que armazena uma mensagem padrão representada pela
     * constante do tipo <b>String</b> {@link ConstantesExcecoes#S_O_NAO_SUPORTADO1 
     * S_O_NAO_SUPORTADO1}.
     * 
     * 
     * 
     * @see ConstantesExcecoes
     */
    public SistemaOperacionalNaoSuportadoExcecao() {
        super(ConstantesExcecoes.S_O_NAO_SUPORTADO1);
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
    public SistemaOperacionalNaoSuportadoExcecao(String mensagem) {
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
    public SistemaOperacionalNaoSuportadoExcecao(String mensagem, 
            Throwable causa) {
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
    public SistemaOperacionalNaoSuportadoExcecao(Throwable causa) {
        super(causa);
    }
    
}
