package iftorrent.excecoes;

/**
 * A classe ConverterClasseExcecao lança uma exceção quando a conversão de algum
 * objeto para outra classe não é feita com sucesso (cast).
 * 
 * @author Leonardo Bortolini
 * @see ClassCastException
 */
public class ConverterClasseExcecao extends ClassCastException{

    /**
     * Construtor vazio que irá passar uma mensagem padrão como parametro.
     */
    
    public ConverterClasseExcecao() {
        super(ConstantesExcecoes.CONVERTER_CLASSE1);
    }

    /**
     * Construtor que recebe como parametro uma String e a passa como parametro.
     * 
     * @param string mensagem passada para o construtor
     */
    
    public ConverterClasseExcecao(String string) {
        super(string);
    }
    
}
