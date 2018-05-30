package iftorrent.excecoes;

import java.io.File;
import java.io.IOException;

/**
 * 
 * 
 * A classe <b>ESExcecao</b> é uma exceção para entradas e saídas de dados.
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public class ESExcecao extends IOException {
    
    /**
     * 
     * 
     * Construtor sem parâmetros que armazena uma mensagem padrão representada pela
     * constante do tipo <b>String</b> {@link ConstantesExcecoes#ARQUIVO_NAO_ENCONTRADO1 
     * ARQUIVO_NAO_ENCONTRADO1}.
     * 
     * 
     * 
     * @see ConstantesExcecoes
     */
    public ESExcecao(){
        super(ConstantesExcecoes.ARQUIVO_NAO_ENCONTRADO1);
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
    public ESExcecao(String mensagem){
        super(mensagem);
    }
    
    /**
     * 
     * 
     * Construtor que armazena uma mensagem informando o arquivo 
     * que gerou o problema.
     * 
     * @param arquivo Um objeto do tipo File que representa o arquivo.<br>
     * 
     * 
     */
    public ESExcecao(File arquivo){
        super("O arquivo " + arquivo.getAbsolutePath() + " gerou problemas");
    }
    
    /**
     * 
     * 
     * Construtor que armazena uma mensagem e armazena uma exceção que 
     * gerou a exceção lançada (provavelmente capturada por um <i>try-catch</i>).
     * 
     * @param mensagem Um objeto do tipo <b>String</b> que armazena uma mensagem.<br>
     * 
     * @param causa Um objeto do tipo <b>Throwable</b> que causou a exceção lançada.<br>
     * 
     * 
     */
    public ESExcecao(String mensagem, Throwable causa) {
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
    public ESExcecao(Throwable causa) {
        super(ConstantesExcecoes.ARQUIVO_NAO_ENCONTRADO1, causa);
    }
    
    /**
     * 
     * 
     * Construtor que armazena uma mensagem informando o arquivo 
     * que gerou o problema e armazena uma exceção que gerou a exceção lançada 
     * (provavelmente capturada por um <i>try-catch</i>).
     * 
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo.<br>
     * @param causa Um objeto do tipo <b>Throwable</b> que causou a exceção lançada.<br>
     * 
     * 
     */
    public ESExcecao(File arquivo, Throwable causa) {
        super("O arquivo " + arquivo.getAbsolutePath() + " gerou problemas", 
                causa);
    }
}
