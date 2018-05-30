package iftorrent.daos.daoArquivo;

import iftorrent.daos.Dao;
import iftorrent.daos.EstabelecerConexao;
import static iftorrent.daos.daoArquivo.ConstantesDaoArquivo.*;
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
 * A classe <b>DaoArquivo</b> serve para controlar o acesso a tabela arquivo do
 * banco de dados.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class DaoArquivo implements Dao<ArquivoTorrent> {

    /**
     * {@inheritDoc }
     */
    @Override
    public void adicionar(ArquivoTorrent arquivo) throws BancoDeDadosExcecao {
        String comando = INSERIR;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setInt(1, arquivo.getChave());
            codigo_sql.setString(2, arquivo.getNome());
            codigo_sql.setFloat(3, arquivo.getTamanho());
            codigo_sql.setString(4, arquivo.getProprietario());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_INSERCAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void alterar(ArquivoTorrent arquivo) throws BancoDeDadosExcecao {
        String comando = ALTERAR;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setString(1, arquivo.getNome());
            codigo_sql.setFloat(2, arquivo.getTamanho());
            codigo_sql.setString(3, arquivo.getProprietario());
            codigo_sql.setInt(4, arquivo.getChave());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_ALTERACAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void remover(ArquivoTorrent arquivo) throws BancoDeDadosExcecao {
        String comando = REMOVER;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setInt(1, arquivo.getChave());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_REMOCAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<ArquivoTorrent> pesquisar() throws BancoDeDadosExcecao {
        String comando = PESQUISAR;
        List<ArquivoTorrent> arquivos = new ArrayList();

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            ResultSet conjunto_de_arquivos = codigo_sql.executeQuery();

            while (conjunto_de_arquivos.next()) {
                ArquivoTorrent arquivo = new ArquivoTorrent();

                arquivo.setChave(conjunto_de_arquivos.getInt("id_arquivo"));
                arquivo.setNome(conjunto_de_arquivos.getString("titulo"));
                arquivo.setTamanho(conjunto_de_arquivos.getFloat("tamanho"));
                arquivo.setProprietario(conjunto_de_arquivos.getString("fk_usuario"));
                arquivos.add(arquivo);
            }
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_PESQUISA, ex);
        }
        return arquivos;
    }

    public int obtem_id(String nome) throws BancoDeDadosExcecao {
        String comando = PESQUISAR_ID;
        ArquivoTorrent arquivo = new ArquivoTorrent();

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            ResultSet conjunto_de_arquivos = codigo_sql.executeQuery();

            if (conjunto_de_arquivos.next()) {
                arquivo.setChave(conjunto_de_arquivos.getInt("id_arquivo"));
            }
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_PESQUISA, ex);
        }
        return arquivo.getChave();
    }
}
