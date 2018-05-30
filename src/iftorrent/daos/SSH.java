package iftorrent.daos;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import static iftorrent.daos.ConstantesConexao.SSH_IP;
import static iftorrent.daos.ConstantesConexao.SSH_LOGIN;
import static iftorrent.daos.ConstantesConexao.SSH_PASTA;
import static iftorrent.daos.ConstantesConexao.SSH_PORTA;
import static iftorrent.daos.ConstantesConexao.SSH_PROTOCOLO;
import static iftorrent.daos.ConstantesConexao.SSH_SENHA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 *
 * @author Rafael Coelho
 */
public class SSH {

    private static Session sessao;
    
    private static Channel abre_conexao() throws JSchException {
        JSch jsch = new JSch();
        sessao = jsch.getSession(SSH_LOGIN, SSH_IP, SSH_PORTA);

        sessao.setPassword(SSH_SENHA);
        Properties config = new Properties();

        config.put("StrictHostKeyChecking", "no");
        sessao.setConfig(config);
        sessao.connect();
        return sessao.openChannel(SSH_PROTOCOLO);
    }
    
    public static int lista_arquivos() throws JSchException, IOException {
        Channel c = abre_conexao();
        ChannelExec ce = (ChannelExec) c;
        
        ce.setCommand("ls");
        ce.setErrStream(System.err);
        ce.connect();

        le_dados_recebidos(ce);
        ce.disconnect();
        sessao.disconnect();
        return ce.getExitStatus();
    }

    private static void le_dados_recebidos(ChannelExec ce) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ce.getInputStream()));
        String line;
        
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static int cria_diretorio(String nova_pasta) throws JSchException, IOException {
        Channel c = abre_conexao();
        ChannelExec ce = (ChannelExec) c;

        ce.setCommand("mkdir " + nova_pasta);
        ce.setErrStream(System.err);

        ce.connect();
        le_dados_recebidos(ce);
        ce.disconnect();
        sessao.disconnect();
        return ce.getExitStatus();
    }

    public static void envia_arquivo_servidor(String nome_arquivo) throws JSchException, IOException, SftpException {
        Channel c = abre_conexao();
        ChannelSftp ce = (ChannelSftp) c;

        ce.connect();
        ce.cd(SSH_PASTA);
        ce.put(nome_arquivo, nome_arquivo);
        ce.disconnect();
        sessao.disconnect();
    }
}