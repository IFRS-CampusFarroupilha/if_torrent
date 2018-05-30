package iftorrent.daos.daoUsuario;

import iftorrent.criptografia.Algoritmos;
import iftorrent.criptografia.Charsets;
import iftorrent.criptografia.Hash;
import iftorrent.daos.Dao;
import iftorrent.daos.EstabelecerConexao;
import static iftorrent.daos.daoUsuario.ConstantesDaoUsuario.*;
import iftorrent.excecoes.BancoDeDadosExcecao;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 *
 * A classe <b>DaoUsuario</b> serve para controlar o acesso a tabela usuario
 * do banco de dados.
 *
 * @author Otávio Farinon
 *
 * 
 */
public class DaoUsuario implements Dao<Usuario> {
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void adicionar(Usuario usuario) throws BancoDeDadosExcecao {
        String comando = INSERIR;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setString(1, usuario.getNome());
            codigo_sql.setBytes(2, usuario.getSenha());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_INSERCAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void alterar(Usuario usuario) throws BancoDeDadosExcecao {
        String comando = ALTERAR;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setString(1, usuario.getNome());
            codigo_sql.setBytes(2, usuario.getSenha());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_ALTERACAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void remover(Usuario usuario) throws BancoDeDadosExcecao {
        String comando = REMOVER;

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            codigo_sql.setString(1, usuario.getNome());
            codigo_sql.execute();
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_REMOCAO, ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Usuario> pesquisar() throws BancoDeDadosExcecao {
        String comando = PESQUISAR;
        List<Usuario> usuarios = new ArrayList();

        try (Connection conexao = EstabelecerConexao.pegar_conexao();
                PreparedStatement codigo_sql = conexao.prepareStatement(comando)) {
            ResultSet conjunto_de_usuarios = codigo_sql.executeQuery();

            while (conjunto_de_usuarios.next()) {
                Usuario usuario = new Usuario();

                usuario.setNome(conjunto_de_usuarios.getString("login"));
                usuario.setSenha(conjunto_de_usuarios.getBytes("senha"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            throw new BancoDeDadosExcecao(MENSAGEM_DE_ERRO_DE_PESQUISA, ex);
        }
        return usuarios;
    }
    
    public boolean usuario_existe_bd(String nome, String senha) throws BancoDeDadosExcecao, NoSuchAlgorithmException, UnsupportedEncodingException {
        List<Usuario> usuarios = pesquisar();
        
        return usuarios.contains(new Usuario(nome, 
                Hash.hash(senha, Algoritmos.SHA_3_256, Charsets.CHARSET_UTF_8)));
    }

    public boolean verifica_senha(String nome, String password) throws BancoDeDadosExcecao, NoSuchAlgorithmException, UnsupportedEncodingException {
        List<Usuario> usuarios = pesquisar();
     
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome) 
                    && Arrays.equals(usuario.getSenha(), Hash.hash(password, Algoritmos.SHA_3_256, Charsets.CHARSET_UTF_8))) {
                return true;
            }
        }
        return false;
    }
}
