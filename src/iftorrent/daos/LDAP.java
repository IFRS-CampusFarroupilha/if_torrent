package iftorrent.daos;

import static iftorrent.ConstantesGerais.LDAP_INITIAL_CONTEXT_FACTORY;
import static iftorrent.ConstantesGerais.LDAP_SECURITY_AUTHENTICATION;
import static iftorrent.ConstantesGerais.LDAP_URL;
import static iftorrent.arquivos.ConstantesLogger.INICIALIZACAO;
import iftorrent.arquivos.Logger;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/**
 *
 * @author Rafael Coelho
 */
public class LDAP {

    private static String obtem_login(String nome, String senha) throws NamingException, Exception {
        DirContext contexto = conecta_ldap(
                "cn=iftorrent,cn=Users,dc=IFTORRENT,dc=IFRS",
                "iftorrent123"
        );

        SearchControls pesquisa = new SearchControls();
        String[] atributos_retornados = {"sAMAccountName", "givenName", "sn"};
        String filtro = "(&(objectclass=person)(sAMAccountName={0}))";
        String[] atributos_filtro = {nome};
        String baseDN = "dc=IFTORRENT,dc=IFRS";
        pesquisa.setReturningAttributes(atributos_retornados);
        pesquisa.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> dados = contexto.search(baseDN, filtro, atributos_filtro, pesquisa);
        String login = "";

        if (dados.hasMore()) {
            SearchResult sr = dados.next();

            login = sr.getNameInNamespace();
        }
        return login;
    }

    private static DirContext conecta_ldap(String nome, String senha) throws NamingException {
        Hashtable variaveis_ambiente = new Hashtable(11);

        variaveis_ambiente.put(Context.INITIAL_CONTEXT_FACTORY, LDAP_INITIAL_CONTEXT_FACTORY);
        variaveis_ambiente.put(Context.PROVIDER_URL, LDAP_URL);
        variaveis_ambiente.put(Context.SECURITY_AUTHENTICATION, LDAP_SECURITY_AUTHENTICATION);
        variaveis_ambiente.put(Context.SECURITY_PRINCIPAL, nome);
        variaveis_ambiente.put(Context.SECURITY_CREDENTIALS, senha);
        DirContext contexto = new InitialDirContext(variaveis_ambiente);

        return contexto;
    }

    public static int verifica_senha(String nome, String senha) throws Exception {
        try {
            String login = obtem_login(nome, senha);
            
            if (login.isEmpty()) {
                return ConstantesConexao.ERRO_USUARIO_LDAP;
            }
            DirContext contexto = conecta_ldap(login, senha);

            Logger.escrever(INICIALIZACAO, "Usuário " + nome + " loggado com sucesso.");
        } catch (NamingException e) {
            Logger.escrever_erro(e);
            return ConstantesConexao.ERRO_CONEXAO_LDAP;
        }
        return ConstantesConexao.OK_CONEXAO_LDAP;
    }
}