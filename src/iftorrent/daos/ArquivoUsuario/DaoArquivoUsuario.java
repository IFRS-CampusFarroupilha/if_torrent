package iftorrent.daos.ArquivoUsuario;

import static iftorrent.daos.ArquivoUsuario.ConstantesDaoArquivoUsuario.INSERIR;
import static iftorrent.daos.ArquivoUsuario.ConstantesDaoArquivoUsuario.MENSAGEM_DE_ERRO_DE_INSERCAO;
import iftorrent.daos.Dao;
import iftorrent.daos.EstabelecerConexao;
import iftorrent.excecoes.BancoDeDadosExcecao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 
 *
 * A classe <b>DaoArquivoUsuario</b> serve para controlar o acesso a tabela
 * ArquivoUsuario do banco de dados.
 *
 * @author Rafael Coelho
 *
 * 
 */
public class DaoArquivoUsuario implements Dao<ArquivoUsuario> {

    /**
     * {@inheritDoc }
     */
    @Override
    public void adicionar(ArquivoUsuario arquivo) throws BancoDeDadosExcecao {
        String comando = INSERIR;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setInt(1, arquivo.getId_arquivo());
            codigo_sql.setString(2, arquivo.getId_usuario());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_INSERCAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void alterar(ArquivoUsuario arquivo) throws BancoDeDadosExcecao {

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void remover(ArquivoUsuario arquivo) throws BancoDeDadosExcecao {

    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<ArquivoUsuario> pesquisar() throws BancoDeDadosExcecao {
        return null;
    }

}
