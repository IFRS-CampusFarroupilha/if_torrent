package iftorrent.daos.daoCategoria;

import iftorrent.daos.Dao;
import iftorrent.daos.EstabelecerConexao;
import static iftorrent.daos.daoCategoria.ConstantesDaoCategoria.*;
import iftorrent.excecoes.BancoDeDadosExcecao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 * A classe <b>DaoCategoria</b> serve para controlar o acesso a tabela categoria
 * do banco de dados.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class DaoCategoria implements Dao<Categoria> {

    /**
     * {@inheritDoc }
     */
    @Override
    public void adicionar(Categoria categoria) throws BancoDeDadosExcecao {
        String comando = INSERIR;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setInt(1, categoria.getId_categoria());
            codigo_sql.setInt(2, categoria.getFk_categoria());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_INSERCAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void alterar(Categoria categoria) throws BancoDeDadosExcecao {
        String comando = ALTERAR;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setInt(1, categoria.getFk_categoria());
            codigo_sql.setInt(2, categoria.getId_categoria());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_ALTERACAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void remover(Categoria categoria) throws BancoDeDadosExcecao {
        String comando = REMOVER;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setInt(1, categoria.getId_categoria());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_REMOCAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Categoria> pesquisar() throws BancoDeDadosExcecao {
        String comando = PESQUISAR;
        List<Categoria> categorias = new ArrayList();

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            ResultSet conjunto_de_categorias = codigo_sql.executeQuery();

            while (conjunto_de_categorias.next()) {
                Categoria categoria = new Categoria();

                categoria.setId_categoria(conjunto_de_categorias.getInt("id_categoria"));
                categoria.setFk_categoria(conjunto_de_categorias.getInt("fk_categoria"));
                categorias.add(categoria);
            }
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_PESQUISA, ex);
        }
        return categorias;
    }

}
