package iftorrent.daos;

import iftorrent.excecoes.BancoDeDadosExcecao;
import java.util.List;

/**
 * 
 * 
 * A interface <b>Dao</b> serve para padronizar as interações da aplicação 
 * com o banco de dados.
 * 
 * @author Rafael Vieira Coelho
 * @author Eduardo Toffolo
 * 
 * @param <T> Tipo de dado para o qual será convertido cada linha de uma 
 * determinada tabela.
 * 
 * 
 */
public interface Dao<T> {

    /**
     * 
     * 
     * Método para adicionar uma linha a tabela.
     * 
     * @param m Um objeto do tipo especificado que representa uma linha da 
     * tabela.<br>
     * 
     * @throws BancoDeDadosExcecao Se ocorrer um erro na abertura de conexão 
     * com o banco de dados.<br>
     * 
     * 
     */
    public void adicionar(T m) throws BancoDeDadosExcecao;

    /**
     * 
     * 
     * Método para alterar o conteúdo de uma linha da tabela.
     * 
     * @param m Um objeto do tipo especificado que representa uma linha da 
     * tabela.<br>
     * 
     * @throws BancoDeDadosExcecao Se ocorrer um erro na abertura de conexão 
     * com o banco de dados.<br>
     * 
     * 
     */
    public void alterar(T m) throws BancoDeDadosExcecao;

    /**
     * 
     * 
     * Método para remover uma linha da tabela.
     * 
     * @param m Um objeto do tipo especificado que representa uma linha da 
     * tabela.<br>
     * 
     * @throws BancoDeDadosExcecao Se ocorrer um erro na abertura de conexão 
     * com o banco de dados.<br>
     * 
     * 
     */
    public void remover(T m) throws BancoDeDadosExcecao;

    /**
     * 
     * 
     * Método para pesquisar todas as linhas da tabela.
     * 
     * @return Uma lista de objetos do tipo especificado que representa todas as 
     * linhas da tabela.<br>
     * 
     * @throws BancoDeDadosExcecao Se ocorrer um erro na abertura de conexão 
     * com o banco de dados.<br>
     * 
     * 
     */
    public List<T> pesquisar() throws BancoDeDadosExcecao;

}
