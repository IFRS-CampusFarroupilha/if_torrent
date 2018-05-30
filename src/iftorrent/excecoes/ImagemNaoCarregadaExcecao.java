package iftorrent.excecoes;

/**
 * 
 * 
 * A classe <b>ImagemNaoCarregadaExcecao</b> é uma exceção para erro no
 * carregamento de imagens.
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public class ImagemNaoCarregadaExcecao extends Exception {

    /**
     * 
     * 
     * Construtor sem parâmetros que armazena uma mensagem padrão representada pela
     * constante do tipo <b>String</b> {@link ConstantesExcecoes#IMAGEM_NAO_CARREGADA1
     * IMAGEM_NAO_CARREGADA1}.
     * 
     * 
     * 
     * @see ConstantesExcecoes
     */
    public ImagemNaoCarregadaExcecao() {
        super(ConstantesExcecoes.IMAGEM_NAO_CARREGADA1);
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
    public ImagemNaoCarregadaExcecao(String mensagem) {
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
    public ImagemNaoCarregadaExcecao(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    /**
     * 
     * 
     * Construtor que armazena uma exceção que gerou a exceção lançada 
     * (provavelmente capturada por um <i>try-catch</i>).
     * 
     * @param causa Um objeto do tipo <b>Throwable</b> que causou a exceção 
     * lançada.<br>
     * 
     * 
     */
    public ImagemNaoCarregadaExcecao(Throwable causa) {
        super(causa);
    }
       
}