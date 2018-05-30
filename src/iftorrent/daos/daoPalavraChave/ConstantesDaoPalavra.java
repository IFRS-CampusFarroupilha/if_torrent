package iftorrent.daos.daoPalavraChave;

/**
 * 
 * 
 * A classe <b>ConstantesDaoPalavra</b> é utilizada para agrupar todas as 
 * constantes da classe <b>DaoPalavra</b>.
 *
 * @author Otávio Farinon
 *
 * 
 */
public class ConstantesDaoPalavra {    
    public static final String INSERIR = "insert into "
            + "palavra_chave (id_palavra, palavra) "
            + "values (?,?)";
    public static final String ALTERAR = "update palavra_chave set "
            + "palavra=? where id_palavra=?";
    public static final String REMOVER = "delete from palavra_chave where id_palavra=?";
    public static final String PESQUISAR = "select * from palavra_chave";
    public static final String MENSAGEM_DE_ERRO_DE_INSERCAO = "Erro ao inserir dados na tabela palavra chave";
    public static final String MENSAGEM_DE_ERRO_DE_ALTERACAO = "Erro ao alterar dados na tabela palavra chave";
    public static final String MENSAGEM_DE_ERRO_DE_REMOCAO = "Erro ao remover dados na tabela palavra chave";
    public static final String MENSAGEM_DE_ERRO_DE_PESQUISA = "Erro ao pesquisar dados na tabela palavra chave";
}
