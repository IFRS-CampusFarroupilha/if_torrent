package iftorrent.daos;

import static iftorrent.daos.ConstantesConexao.*;
import iftorrent.excecoes.BancoDeDadosExcecao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 *
 * A classe <b>EstabelecerConexao</b> é utilizada para criar uma conexão com um
 * banco de dados.
 *
 * @author Rafael Vieira Coelho
 * @author Eduardo Toffolo
 *
 * 
 */
public class EstabelecerConexao {

    /**
     * 
     *
     * Método para criar a conexão e retornar ela.
     *
     * @return Um objeto do tipo <b>Connection</b> que representa a conexão com
     * o banco de dados.<br>
     *
     * @throws BancoDeDadosExcecao Se ocorrer um erro na abertura de conexão com
     * o banco de dados.<br>
     *
     * 
     */
    public static Connection pegar_conexao() throws BancoDeDadosExcecao {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_CONEXAO, ex);
        }
    }

    /**
     * 
     *
     * Método para testar a conexão com o banco de dados.
     *
     * @return Um booleano representando se houve exito ou não na conexão com o
     * banco de dados.<br>
     *
     * 
     */
    public static boolean testar_conexao() {
        try {
            pegar_conexao().close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public static void main(String[] args) {
        if (testar_conexao()) {
            System.out.println("OK");
        }
    }
}
