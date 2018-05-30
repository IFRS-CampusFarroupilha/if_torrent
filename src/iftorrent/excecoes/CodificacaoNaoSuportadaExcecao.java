package iftorrent.excecoes;

import java.io.UnsupportedEncodingException;

/**
 * 
 * A classe <b> CodificacaoNaoSuportadaExcecao</b> é uma exceção para
 * codificações ('charset') não suportadas.
 * 
 *
 * @see ConstantesExcecoes
 *
 * @author Garren Souza
 */
public class CodificacaoNaoSuportadaExcecao extends UnsupportedEncodingException {

    /**
     * 
     * Construtor da exceção (sem parâmetros) que armazena uma mensagem representada
     * pela constante do tipo <b>String</b>
     * {@link ConstantesExcecoes#CODIFICACAO_NAO_ENCONTRADA1}.
     * 
     *
     * @see ConstantesExcecoes
     */
    public CodificacaoNaoSuportadaExcecao() {
        super(ConstantesExcecoes.CODIFICACAO_NAO_ENCONTRADA1);
    }

}
