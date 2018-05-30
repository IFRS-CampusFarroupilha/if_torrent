package iftorrent.excecoes;

/**
 * A classe IndiceInvalidoExcecao lança exceções quando o comando tenta acessar
 * uma posição da array que não existe.
 * 
 * @author Rafael Vieira Coelho
 * @author Leonardo Bortolini
 * @see ArrayIndexOutOfBoundsException
 */
public class IndiceInvalidoExcecao extends ArrayIndexOutOfBoundsException {

    /**
     * Construtor vazio que irá passar uma mensagem padrão como parametro.
     */
    
    public IndiceInvalidoExcecao() {
        super(ConstantesExcecoes.INDICE_INVALIDO1);        
    }

    /**
     * Construtor que recebe como parametro qual indíce passado que é inválido
     * e passa uma mensagem usando essa informação.
     * 
     * @param index indíce inálido solicitado
     */
    
    public IndiceInvalidoExcecao(int index) {
        super("O índice " + index + " é inválido.");
    }

    /**
     * Construtor que recebe como parametro uma String e a passa como parametro.
     * 
     * @param s mensagem passada como parametro para o construtor
     */
    
    public IndiceInvalidoExcecao(String s) {
        super(s);
    }
    
}
