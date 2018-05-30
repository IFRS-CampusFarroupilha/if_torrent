package iftorrent.daos.daoArquivo;

/**
 * 
 *
 * A classe <b>ConstantesDaoArquivo</b> é utilizada para agrupar todas as
 * constantes da classe <b>DaoArquivo</b>.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class ConstantesDaoArquivo {

    public static final String INSERIR = "insert into "
            + "arquivo (id_arquivo, titulo, tamanho, fk_usuario) "
            + "values (?,?,?,?)";
    public static final String ALTERAR = "update arquivo set "
            + "titulo=?, tamanho=?, fk_usuario=? where id_arquivo=?";
    public static final String REMOVER = "delete from arquivo where id_arquivo=?";
    public static final String PESQUISAR = "select * from arquivo";
    public static final String PESQUISAR_ID = "select id_arquivo from arquivo where titulo=?";
    public static final String MENSAGEM_DE_ERRO_DE_INSERCAO = "Erro ao inserir dados na tabela arquivo";
    public static final String MENSAGEM_DE_ERRO_DE_ALTERACAO = "Erro ao alterar dados na tabela arquivo";
    public static final String MENSAGEM_DE_ERRO_DE_REMOCAO = "Erro ao remover dados na tabela arquivo";
    public static final String MENSAGEM_DE_ERRO_DE_PESQUISA = "Erro ao pesquisar dados na tabela arquivo";

}
