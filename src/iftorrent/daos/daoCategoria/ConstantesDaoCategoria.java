package iftorrent.daos.daoCategoria;

/**
 * 
 * 
 * A classe <b>ConstantesDaoCategoria</b> é utilizada para agrupar todas as 
 * constantes da classe <b>DaoCategoria</b>.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class ConstantesDaoCategoria {
    
    public static final String INSERIR = "insert into "
            + "categoria (id_categoria, fk_categoria) "
            + "values (?,?)";
    public static final String ALTERAR = "update categoria set "
            + "fk_categoria=? where id_categoria=?";
    public static final String REMOVER = "delete from categoria where id_categoria=?";
    public static final String PESQUISAR = "select * from categoria";
    public static final String MENSAGEM_DE_ERRO_DE_INSERCAO = "Erro ao inserir dados na tabela categoria";
    public static final String MENSAGEM_DE_ERRO_DE_ALTERACAO = "Erro ao alterar dados na tabela categoria";
    public static final String MENSAGEM_DE_ERRO_DE_REMOCAO = "Erro ao remover dados na tabela categoria";
    public static final String MENSAGEM_DE_ERRO_DE_PESQUISA = "Erro ao pesquisar dados na tabela categoria";
    
}
