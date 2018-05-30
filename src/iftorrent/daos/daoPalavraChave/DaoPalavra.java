package iftorrent.daos.daoPalavraChave;

import iftorrent.daos.Dao;
import iftorrent.daos.EstabelecerConexao;
import static iftorrent.daos.daoPalavraChave.ConstantesDaoPalavra.*;
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
 * A classe <b>DaoPalavra</b> serve para controlar o acesso a tabela palavra_chave
 * do banco de dados.
 *
 * @author Otávio Farinon
 *
 * 
 */
public class DaoPalavra implements Dao<PalavraChave>{
        
    /**
     * {@inheritDoc }
     */
    @Override
    public void adicionar(PalavraChave palavra_chave) throws BancoDeDadosExcecao {
        String comando = INSERIR;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setInt(1, palavra_chave.getId_Palavra());
            codigo_sql.setString(2, palavra_chave.getPalavra());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_INSERCAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void alterar(PalavraChave palavra_chave) throws BancoDeDadosExcecao {
        String comando = ALTERAR;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setInt(1, palavra_chave.getId_Palavra());
            codigo_sql.setString(2, palavra_chave.getPalavra());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_ALTERACAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void remover(PalavraChave palavra_chave) throws BancoDeDadosExcecao {
        String comando = REMOVER;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setInt(1, palavra_chave.getId_Palavra());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_REMOCAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<PalavraChave> pesquisar() throws BancoDeDadosExcecao {
        String comando = PESQUISAR;
        List<PalavraChave> palavras = new ArrayList();

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            ResultSet conjunto_de_palavras = codigo_sql.executeQuery();

            while (conjunto_de_palavras.next()) {
                PalavraChave palavra_chave = new PalavraChave();

                palavra_chave.setId_Palavra(conjunto_de_palavras.getInt("id_palavra"));
                palavra_chave.setPalavra(conjunto_de_palavras.getString("palavra"));
                palavras.add(palavra_chave);
            }
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_PESQUISA, ex);
        }
        return palavras;
    }

    
}
