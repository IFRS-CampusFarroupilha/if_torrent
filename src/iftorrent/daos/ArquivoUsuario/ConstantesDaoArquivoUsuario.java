package iftorrent.daos.ArquivoUsuario;

/**
 * 
 * 
 * A classe <b>ConstantesDaoArquivoUsuario</b> é utilizada para agrupar todas as 
 * constantes da classe <b>DaoArquivoUsuario</b>.
 *
 * @author Rafael Coelho
 *
 * 
 */
public class ConstantesDaoArquivoUsuario {

    public static final String INSERIR = "insert into "
            + "arquivo (id_arquivo, id_usuario) "
            + "values (?,?)";
    public static final String MENSAGEM_DE_ERRO_DE_INSERCAO = "Erro ao inserir dados na tabela ArquivoUsuario";
}
