package iftorrent.excecoes;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

/**
 * A classe SintaxeSQLException lança exceções quando alguma parte da sintaxe do
 * código de SQL não está certa.
 *
 * @author Leonardo Bortolini
 * @see MySQLSyntaxErrorException
 */
public class SintaxeSQLException extends MySQLSyntaxErrorException {

    /**
     * Construtor vazio que irá passar uma mensagem padrão como parametro.
     */
    public SintaxeSQLException() {
        super(ConstantesExcecoes.SINTAXE_SQL1);
    }

    /**
     * Construtor que gera uma exceção com a mensagem contendo a causa da exceção
     * e o estado do banco de dados.
     * 
     * @param reason causa da exceção
     * @param SQLState estado do bando de dados
     */
    
    public SintaxeSQLException(String reason, String SQLState) {
        super(reason, SQLState);
    }
    
    /**
     * Construtor que recebe como parametro uma String e a passa como parametro.
     * 
     * @param reason causa da exceção 
     */

    public SintaxeSQLException(String reason) {
        super(reason);
    }

}
