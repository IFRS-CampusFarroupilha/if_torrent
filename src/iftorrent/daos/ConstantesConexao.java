package iftorrent.daos;

/**
 * 
 * 
 * A classe <b>ConstantesConexao</b> é utilizada para agrupar todas as constantes 
 * gerais das DAOs.
 *
 * @author Eduardo Toffolo
 *
 * 
 */
public class ConstantesConexao {

    public static final String URL = "jdbc:mysql://web.farroupilha.ifrs.edu.br:1519/iftorrent";
    public static final String USUARIO = "iftorrent";
    public static final String SENHA = "ift0rr3ntdb*";
    public static final String MENSAGEM_DE_ERRO_DE_CONEXAO = "Erro ao conectar no banco de dados";
    public static final int ERRO_CONEXAO_LDAP = 2;
    public static final int ERRO_USUARIO_LDAP = 1;
    public static final int OK_CONEXAO_LDAP = 0;
    public static final String SSH_LOGIN = "iftorrent";
    public static final String SSH_IP = "/web.farroupilha.ifrs.edu.br";//"192.168.0.14";
    public static final int SSH_PORTA = 1518;
    public static final String SSH_SENHA = "ift0rr3nt9911*";
    public static final String SSH_PROTOCOLO = "sftp";
    public static final String SSH_PASTA = "./public_html/torrents/";
}