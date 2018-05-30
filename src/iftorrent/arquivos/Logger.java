package iftorrent.arquivos;

import static iftorrent.arquivos.ConstantesLogger.*;
import static iftorrent.arquivos.Modo.ANEXACAO;
import iftorrent.colecoes.ListaEncadeada;
import iftorrent.excecoes.ESExcecao;
import iftorrent.excecoes.EnumNaoEncontradaExcecao;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A classe <b>Logger</b> serve para registrar eventos importantes que acontecem
 * na execução do programa.
 *
 * @author Guilherme Giordani
 */
public class Logger {

    private static final File LOG = new File(NOME_ARQUIVO);
    private static ManipuladorTexto arquivo;
    private static boolean em_uso = false;
    private static final ListaEncadeada<String> MENSAGENS = new ListaEncadeada();

    /**
     * Este método abre um arquivo de LOG em um diretório constante, e o nome do
     * arquivo será a data e hora atual.
     * 
     * @throws ESExcecao Se não for possível criar o arquivo.
     */
    public static void abrir_log() throws ESExcecao {
        repara_erros_arquivos();
        arquivo = new ManipuladorTexto(NOME_ARQUIVO, ANEXACAO);
        Date data_horaAtual = new Date();
        String data = new SimpleDateFormat("dd/MM/yyyy").format(data_horaAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(data_horaAtual);

        arquivo.escrever_linha("\r\n" + data + " - " + hora + "\r\n");
    }

    /**
     * Este método abre o arquivo de LOG e começa escrevendo a hora e o minuto
     * em que ocorreu o evento, e logo em seguida o próprio evento, se alguém já
     * estiver escrevendo no arquivo, o método guardará em uma ListaEncadeada a
     * hora e o evento, para quando, na próxima vez que algúem for escrever, ele
     * escreva os eventos guardados anteriormente.
     *
     * @param acontecimento Qual o tipo do evento que aconteceu (erro,
     * execução,...)
     * @param nome Objeto, função, método, rotina, erro, etc... Que ocorreu.
     *
     * @return true caso escreveu no arquivo ou guardou o que precisa escrever ,
     * e false caso contrário.
     */
    public static boolean escrever(int acontecimento, String nome) {
        try {
            Date data_horaAtual = new Date();
            String linha_inteira = "\r\n[" + new SimpleDateFormat("dd/MM/yyyy").format(data_horaAtual) + "]"
                    + new SimpleDateFormat("HH:mm").format(data_horaAtual) + "\t";

            switch (acontecimento) {
                case EXECUCAO:
                    linha_inteira += "Executando: ";
                    break;
                case INICIALIZACAO:
                    linha_inteira += "Iniciando: ";
                    break;
                case FINALIZACAO:
                    linha_inteira += "Finalizando: ";
                    break;
                case ERRO:
                    linha_inteira += "Erro: ";
            }
            linha_inteira += nome;
            if (!em_uso) {
                em_uso = true;
                MENSAGENS.adicionar(linha_inteira);
                escrever_tudo_que_falta();
                em_uso = false;
            } else {
                MENSAGENS.adicionar(linha_inteira);
            }
        } catch (ESExcecao | EnumNaoEncontradaExcecao ex) {
            return false;
        }
        return true;
    }

    /**
     * Este método recebe por parametro um erro, onde extrairá as principais
     * informações do mesmo para escrever no LOG.
     *
     * @param excecao Exceção ("Erro") que será escrito no LOG.
     *
     * @see #escrever(int, java.lang.String)
     */
    public static void escrever_erro(Exception excecao) {
        StackTraceElement[] informacoes_erro = excecao.getStackTrace();
        String erro;
        String classe_erro = "";
        String linha_erro = "";

        for (StackTraceElement elemento : informacoes_erro) {
            classe_erro = elemento.getClassName();
            linha_erro = ((Integer) elemento.getLineNumber()).toString();
        }
        erro = excecao.getClass().getName() + "\r\n";
        erro += "\t\t\tDescrição: " + excecao.getMessage() + "\r\n";
        erro += "\t\t\tClasse onde está erro: " + classe_erro + "\r\n";
        erro += "\t\t\tLinha do erro: " + linha_erro + "\r\n";
        escrever(ERRO, erro);
    }

    /**
     * Este método finaliza o arquivo de LOG, escrevendo tudo o que faltava,
     * caso algo ficou salvo na ListaEncadeada.
     *
     * @return true caso o arquivo foi finalizado devidamente, e false caso
     * estiver em uso ou algum problema ocorrer.
     */
    public static boolean fechar_log() {
        if (!em_uso) {
            try {
                escrever_tudo_que_falta();
                arquivo.fechar();
            } catch (ESExcecao | EnumNaoEncontradaExcecao ex) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Este método abre o arquivo de LOG e escreve tudo o que está salvo na
     * ListaEncadeada.
     *
     * @throws ESExcecao
     * @throws EnumNaoEncontradaExcecao
     * @see #escrever(int, java.lang.String)
     */
    private static void escrever_tudo_que_falta() throws ESExcecao, EnumNaoEncontradaExcecao {
        while (!MENSAGENS.eh_vazio()) {
            arquivo.escrever_com_quebra_de_linha((String) MENSAGENS.obter_na_posicao(0).obter_objeto());
            MENSAGENS.remover_inicio();
        }
    }

    /**
     * Este método previne que um arquivo com o mesmo nome do diretório do LOG
     * exista na pasta do projeto, e caso o diretório não exista, aqui será
     * criado.
     */
    private static void repara_erros_arquivos() throws ESExcecao {
        if (LOG.exists()) {
            LOG.delete();
        }
        try {
            LOG.createNewFile();
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
    }
}
