package iftorrent.daos.daoUsuario;
/**
 * 
 * 
 * A classe <b>ConstantesDaoUsuario</b> é utilizada para agrupar todas as 
 * constantes da classe <b>DaoUsuario</b>.
 *
 * @author Otávio Farinon
 *
 * 
 */
public class ConstantesDaoUsuario {
  
    public static final String INSERIR = "insert into "
            + "usuario (login, senha) "
            + "values (?,?)";
    public static final String ALTERAR = "update usuario set "
            + "senha=? where login=?";
    public static final String REMOVER = "delete from usuario where login=?";
    public static final String PESQUISAR = "select * from usuario";
    
    public static final String MENSAGEM_DE_ERRO_DE_CODIFICACAO = "Erro devido a codificação do algoritmo de hash";
    public static final String MENSAGEM_DE_ERRO_DE_HASH = "Erro devido ao algoritmo de hash";
    public static final String MENSAGEM_DE_ERRO_DE_INSERCAO = "Erro ao inserir dados na tabela usuario";
    public static final String MENSAGEM_DE_ERRO_DE_ALTERACAO = "Erro ao alterar dados na tabela usuario";
    public static final String MENSAGEM_DE_ERRO_DE_REMOCAO = "Erro ao remover dados na tabela usuario";
    public static final String MENSAGEM_DE_ERRO_DE_PESQUISA = "Erro ao pesquisar dados na tabela usuario";
}
