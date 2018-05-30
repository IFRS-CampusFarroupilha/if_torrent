package iftorrent.arquivos;

import iftorrent.excecoes.ArgumentoIlegalExcecao;
import iftorrent.excecoes.ESExcecao;
import iftorrent.excecoes.EnumNaoEncontradaExcecao;
import iftorrent.excecoes.SistemaOperacionalNaoSuportadoExcecao;
import static iftorrent.arquivos.ConstantesArquivos.FINAL_ARQUIVO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 *
 * A classe <b>Arquivo</b> auxilia em algumas tarefas envolvendo arquivos que
 * não necessitam modificações de conteúdos dos mesmos.
 * <p>
 * Esta classe necessita da enum <b>Permissao</b>.
 *
 * @author Eduardo Toffolo
 *
 * 
 * @see Permissao enum Permissao
 */
public class Arquivo {

    /**
     * 
     * 
     * Copia parte do conteúdo de um arquivo e anexa ao conteúdo de outro
     * (recebe o início e o fim do trecho a ser copiado do arquivo de entrada).
     *
     * @param entrada Um objeto do tipo <b>File</b> que representa o arquivo de
     * entrada.<br>
     * @param saida Um objeto do tipo <b>File</b> que representa o arquivo de
     * saída.<br>
     * @param inicio Um inteiro que representa o início do trecho a ser copiado
     * (indica a posição do primeiro caractere a ser lido, iniciando em 0).<br>
     * @param fim Um inteiro que representa o fim do trecho a ser copiado
     * (indica a posição do caractere que sucede o último caractere a ser lido).
     * A constante {@link ConstantesArquivos#FINAL_ARQUIVO} representa o final
     * do arquivo.<br>
     * 
     * @return Um objeto do tipo <b>File</b> que representa o arquivo de saída.<br>
     * 
     * @throws ESExcecao Se o arquivo de entrada não existir ou não permitir a
     * leitura ou se o arquivo de saida não permitir a escrita.<br>
     * 
     * 
     */
    public static File copiar_para(File entrada, File saida, int inicio,
            int fim) throws ESExcecao {
        try (OutputStream escritor = new FileOutputStream(saida, true)) {
            escritor.write(Arquivo.ler_trecho(entrada, inicio, fim));
        } catch (IOException ex) {
            throw new ESExcecao(saida, ex);
        }
        return saida;
    }
    
    /**
     * 
     * 
     * Lê um trecho binário do arquivo.
     * 
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo 
     * que será lido.<br>
     * @param inicio Um inteiro que representa o início da leitura.<br>
     * @param fim Um inteiro que representa o fim da leitura.<br>
     * 
     * @return Um vetor do tipo <b>byte</b> contendo o trecho lido do arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir a leitura.<br>
     * 
     * 
     */
    @SuppressWarnings("empty-statement")
    public static byte[] ler_trecho(File arquivo, int inicio, int fim) throws ESExcecao{
        if (inicio < 0 || inicio >= arquivo.length()) {
            throw new ArgumentoIlegalExcecao("Inicio do arquivo invalido");
        }
        if ((fim < 0 && fim != FINAL_ARQUIVO) || fim >= arquivo.length()) {
            throw new ArgumentoIlegalExcecao("Fim do arquivo invalido");
        }
        int tamanho = (fim == FINAL_ARQUIVO) ? (int) arquivo.length() : fim;
        byte[] trecho = new byte[tamanho];
        
        try (InputStream leitor = new FileInputStream(arquivo)) {
            for (int i = 0; i < inicio && leitor.read() != FINAL_ARQUIVO; i++);
            int byte_lido;

            for (int i = 0; i != fim - inicio
                    && (byte_lido = leitor.read()) != FINAL_ARQUIVO; i++) {
                trecho[i] = (byte) byte_lido;
            }
        } catch (IOException ex) {
            throw new ESExcecao(arquivo, ex);
        }
        return trecho;
    }

    /**
     * 
     * 
     * Copia parte do conteúdo de um arquivo e anexa ao conteúdo de outro
     * (recebe apenas a indicação do final do trecho, sendo o início o primeiro
     * caractere do arquivo de entrada).
     *
     * @param entrada Um objeto do tipo <b>File</b> que representa o arquivo de
     * entrada.<br>
     * @param saida Um objeto do tipo <b>File</b> que representa o arquivo de
     * saída.<br>
     * @param fim Um inteiro que representa o fim do trecho a ser copiado
     * (indica a posição do caracter que sucede o último caractere a ser lido).
     * Caso receba {@link ConstantesArquivos#FINAL_ARQUIVO FINAL_ARQUIVO}, o
     * final será o final do arquivo.<br>
     * 
     * @return Um objeto do tipo <b>File</b> que representa o arquivo de saída.<br>
     * 
     * @throws ESExcecao Se o arquivo de entrada não existir ou não permitir a
     * leitura ou se o arquivo de saida não permitir a escrita.<br>
     * 
     * 
     */
    public static File copiar_para(File entrada, File saida, int fim)
            throws ESExcecao {
        return Arquivo.copiar_para(entrada, saida, 0, fim);
    }

    /**
     * 
     * 
     * Copia o conteúdo inteiro de um arquivo e anexa ao conteúdo de outro.
     *
     * @param entrada Um objeto do tipo <b>File</b> que representa o arquivo de
     * entrada.<br>
     * @param saida Um objeto do tipo <b>File</b> que representa o arquivo de
     * saída.<br>
     * 
     * @return Um objeto do tipo <b>File</b> que representa o arquivo de saída.<br>
     * 
     * @throws ESExcecao Se o arquivo de entrada não existir ou não permitir a
     * leitura ou se o arquivo de saida não permitir a escrita.<br>
     * 
     * 
     */
    public static File copiar_para(File entrada, File saida) throws ESExcecao {
        return Arquivo.copiar_para(entrada, saida, 0, ConstantesArquivos.FINAL_ARQUIVO);
    }

    /**
     * 
     * 
     * Copia parte do conteúdo de um arquivo e anexa ao conteúdo de outro
     * (recebe o início e o fim do trecho a ser copiado do arquivo de entrada).
     *
     * @param entrada Um objeto do tipo String que representa o diretório do
     * arquivo de entrada.<br>
     * @param saida Um objeto do tipo String que representa o diretório do
     * arquivo de saída.<br>
     * @param inicio Um inteiro que representa o início do trecho a ser copiado
     * (indica a posição do primeiro caractere a ser lido, iniciando em 0).<br>
     * @param fim Um inteiro que representa o fim do trecho a ser copiado
     * (indica a posição do caractere que sucede o último caractere a ser lido).
     * Caso receba {@link ConstantesArquivos#FINAL_ARQUIVO FINAL_ARQUIVO}, o
     * final será o final do arquivo.<br>
     * 
     * @return Um objeto do tipo <b>File</b> que representa o arquivo de saída.<br>
     * 
     * @throws ESExcecao Se o arquivo de entrada não existir ou não permitir a
     * leitura ou se o arquivo de saida não permitir a escrita.<br>
     * 
     * 
     */
    public static File copiar_para(String entrada, String saida, int inicio,
            int fim) throws ESExcecao {
        return Arquivo.copiar_para(new File(entrada), new File(saida), inicio, fim);
    }

    /**
     * 
     * 
     * Copia parte do conteúdo de um arquivo e anexa ao conteúdo de outro
     * (recebe apenas a indicação do final do trecho, sendo o início o primeiro
     * caractere do arquivo de entrada).
     *
     * @param entrada Um objeto do tipo String que representa o diretório do
     * arquivo de entrada.<br>
     * @param saida Um objeto do tipo String que representa o diretório do
     * arquivo de saída.<br>
     * @param fim Um inteiro que representa o fim do trecho a ser copiado
     * (indica a posição do caracter que sucede o último caractere a ser lido).
     * Caso receba {@link ConstantesArquivos#FINAL_ARQUIVO FINAL_ARQUIVO}, o
     * final será o final do arquivo.<br>
     * 
     * @return Um objeto do tipo <b>File</b> que representa o arquivo de saída.<br>
     * 
     * @throws ESExcecao Se o arquivo de entrada não existir ou não permitir a
     * leitura ou se o arquivo de saida não permitir a escrita.<br>
     * 
     * 
     */
    public static File copiar_para(String entrada, String saida, int fim)
            throws ESExcecao {
        return Arquivo.copiar_para(new File(entrada), new File(saida), 0, fim);
    }

    /**
     * 
     * 
     * Copia o conteúdo inteiro de um arquivo e anexa ao conteúdo de outro.
     *
     * @param entrada Um objeto do tipo String que representa o diretório do
     * arquivo de entrada.<br>
     * @param saida Um objeto do tipo String que representa o diretório do
     * arquivo de saída.<br>
     * 
     * @return Um objeto do tipo <b>File</b> que representa o arquivo de saída.<br>
     * 
     * @throws ESExcecao Se o arquivo de entrada não existir ou não permitir a
     * leitura ou se o arquivo de saida não permitir a escrita.<br>
     * 
     * 
     */
    public static File copiar_para(String entrada, String saida) throws ESExcecao {
        return Arquivo.copiar_para(new File(entrada), new File(saida), 0,
                ConstantesArquivos.FINAL_ARQUIVO);
    }

    /**
     * 
     * 
     * Apaga todo conteúdo do arquivo.
     *
     * @param arquivo Um objeto do tipo String que representa o diretório do
     * arquivo.<br>
     * 
     * @return Um objeto do tipo <b>File</b> que representa o arquivo em branco.<br>
     *
     * @throws ESExcecao Se o arquivo não permitir a escrita.<br>
     * 
     * 
     */
    public static File apagar_conteudo(String arquivo) throws ESExcecao {
        return Arquivo.apagar_conteudo(new File(arquivo));
    }

    /**
     * 
     * 
     * Apaga todo conteúdo do arquivo.
     *
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo.<br>
     * 
     * @return Um objeto do tipo <b>File</b> que representa o arquivo em branco.<br>
     * 
     * @throws ESExcecao Se o arquivo não permitir a escrita.<br>
     * 
     * 
     */
    public static File apagar_conteudo(File arquivo) throws ESExcecao {
        try {
            new FileOutputStream(arquivo).close();
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
        return arquivo;
    }

    /**
     * 
     * 
     * Muda as permissões do arquivo apenas para o dono.
     *
     * @param arquivo Um objeto do tipo <b>File</b> que representa o
     * arquivo.<br>
     * @param dono Um inteiro que representa as permissões do dono quanto ao
     * arquivo.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao
     * parâmentro.<br>
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     * 
     * @see Permissao enum Permissao
     * @see File#setReadable(boolean) <br> File.setReadable(boolean)
     * @see File#setWritable(boolean) <br> File.setWritable(boolean)
     * @see File#setExecutable(boolean) <br> File.setExecutable(boolean)
     */
    public static void mudar_permissao(File arquivo, int dono) throws ESExcecao,
            EnumNaoEncontradaExcecao {
        Arquivo.mudar_permissao(arquivo, Permissao.procurar_permissao(dono));
    }

    /**
     * 
     * 
     * Muda as permissões do arquivo para o dono e para outros usuários.
     *
     * @param arquivo Um objeto do tipo <b>File</b> que representa o
     * arquivo.<br>
     * @param dono Um inteiro que representa as permissões do dono quanto ao
     * arquivo.<br>
     * @param outros Um inteiro que representa as permissões de outros usuários
     * quanto ao arquivo.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao
     * parâmentro.<br>
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     * 
     * @see Permissao enum Permissao
     * @see File#setReadable(boolean) <br> File.setReadable(boolean)
     * @see File#setWritable(boolean) <br> File.setWritable(boolean)
     * @see File#setExecutable(boolean) <br> File.setExecutable(boolean)
     */
    public static void mudar_permissao(File arquivo, int dono, int outros) throws
            ESExcecao, EnumNaoEncontradaExcecao {
        Arquivo.mudar_permissao(arquivo, Permissao.procurar_permissao(dono),
                Permissao.procurar_permissao(outros));
    }

    /**
     * 
     * 
     * Muda as permissões do arquivo apenas para o dono.
     *
     * @param arquivo Um objeto do tipo <b>File</b> que representa o
     * arquivo.<br>
     * @param dono Um objeto do tipo String que representa as permissões do dono
     * quanto ao arquivo.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao
     * parâmentro.<br>
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     * 
     * @see Permissao enum Permissao
     * @see File#setReadable(boolean) <br> File.setReadable(boolean)
     * @see File#setWritable(boolean) <br> File.setWritable(boolean)
     * @see File#setExecutable(boolean) <br> File.setExecutable(boolean)
     */
    public static void mudar_permissao(File arquivo, String dono) throws
            ESExcecao, EnumNaoEncontradaExcecao {
        Arquivo.mudar_permissao(arquivo, Permissao.procurar_permissao(dono));
    }

    /**
     * 
     * 
     * Muda as permissões do arquivo para o dono e para outros usuários.
     *
     * @param arquivo Um objeto do tipo <b>File</b> que representa o
     * arquivo.<br>
     * @param dono Um objeto do tipo String que representa as permissões do dono
     * quanto ao arquivo.<br>
     * @param outros Um objeto do tipo String que representa as permissões de
     * outros usuários quanto ao arquivo.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao
     * parâmentro.<br>
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     * 
     * @see Permissao enum Permissao
     * @see File#setReadable(boolean) <br> File.setReadable(boolean)
     * @see File#setWritable(boolean) <br> File.setWritable(boolean)
     * @see File#setExecutable(boolean) <br> File.setExecutable(boolean)
     */
    public static void mudar_permissao(File arquivo, String dono, String outros)
            throws ESExcecao, EnumNaoEncontradaExcecao {
        Arquivo.mudar_permissao(arquivo, Permissao.procurar_permissao(dono),
                Permissao.procurar_permissao(outros));
    }

    /**
     * 
     * 
     * Muda as permissões do arquivo apenas para o dono.
     *
     * @param arquivo Um objeto do tipo <b>File</b> que representa o
     * arquivo.<br>
     * @param dono Uma enum do tipo Permissao que representa as permissões do
     * dono quanto ao arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     * 
     * @see Permissao enum Permissao
     * @see File#setReadable(boolean) <br> File.setReadable(boolean)
     * @see File#setWritable(boolean) <br> File.setWritable(boolean)
     * @see File#setExecutable(boolean) <br> File.setExecutable(boolean)
     */
    public static void mudar_permissao(File arquivo, Permissao dono) throws ESExcecao {
        if (!arquivo.exists()) {
            throw new ESExcecao();
        }
        arquivo.setReadable(dono.pode_ler());
        arquivo.setWritable(dono.pode_escrever());
        arquivo.setExecutable(dono.pode_executar());
    }

    /**
     * 
     * 
     * Muda as permissões do arquivo para o dono e para outros usuários.
     *
     * @param arquivo Um objeto do tipo <b>File</b> que representa o
     * arquivo.<br>
     * @param dono Uma enum do tipo Permissao que representa as permissões do
     * dono quanto ao arquivo.<br>
     * @param outros Uma enum do tipo Permissao que representa as permissões de
     * outros usuários quanto ao arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     * 
     * @see Permissao enum Permissao
     * @see File#setReadable(boolean, boolean)
     * <br> File.setReadable(boolean, boolean)
     * @see File#setWritable(boolean, boolean)
     * <br> File.setWritable(boolean, boolean)
     * @see File#setExecutable(boolean, boolean)
     * <br> File.setExecutable(boolean, boolean)
     */
    public static void mudar_permissao(File arquivo, Permissao dono,
            Permissao outros) throws ESExcecao {
        if (!arquivo.exists()) {
            throw new ESExcecao();
        }
        arquivo.setReadable(dono.pode_ler(), !outros.pode_ler());
        arquivo.setWritable(dono.pode_escrever(), !outros.pode_escrever());
        arquivo.setExecutable(dono.pode_executar(), !outros.pode_executar());
    }

    /**
     * 
     * 
     * Exclui o arquivo.
     *
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     */
    public static void excluir(File arquivo) throws ESExcecao {
        if (!arquivo.exists()) {
            throw new ESExcecao();
        }
        arquivo.delete();
    }

    /**
     * 
     * 
     * Retorna o diretório do arquivo.
     *
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo.<br>
     * 
     * @return Um objeto do tipo String que armazena o diretório do arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     */
    public static String diretorio(File arquivo) throws ESExcecao {
        if (!arquivo.exists()) {
            throw new ESExcecao();
        }
        return arquivo.getAbsolutePath();
    }

    /**
     * 
     * 
     * Oculta o arquivo (funciona somente para o sistema Windows e para as
     * distribuições Linux).
     *
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir a ação.<br>
     * @throws SistemaOperacionalNaoSuportadoExcecao Se o método não suporta o
     * Sistema Operacional.<br>
     * 
     * 
     */
    public static void ocultar_arquivo(File arquivo) throws ESExcecao,
            SistemaOperacionalNaoSuportadoExcecao {
        if (!(arquivo.exists() && !arquivo.isHidden())) {

        }
        String sistema_operacional = System.getProperty("os.name").split(" ")[0];

        switch (sistema_operacional) {
            case "Windows":
                try {
                    Runtime.getRuntime().exec("cmd /c attrib +h +s +r " + arquivo);
                } catch (IOException ex) {
                    throw new ESExcecao(ex);
                }
                break;
            case "Linux":
                arquivo.renameTo(new File("." + arquivo.getAbsolutePath()));
                break;
            default:
                throw new SistemaOperacionalNaoSuportadoExcecao();
        }

    }

    /**
     * 
     * 
     * Oculta o arquivo (funciona somente para o sistema Windows e para as
     * distribuições Linux).
     *
     * @param arquivo Um objeto do tipo String que representa o diretório do
     * arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir a ação.<br>
     * @throws SistemaOperacionalNaoSuportadoExcecao Se o método não suporta o
     * Sistema Operacional.<br>
     * 
     * 
     */
    public static void ocultar_arquivo(String arquivo) throws ESExcecao,
            SistemaOperacionalNaoSuportadoExcecao {
        Arquivo.ocultar_arquivo(new File(arquivo));
    }

    /**
     * 
     * 
     * Desoculta o arquivo (funciona somente para o sistema Windows e para as
     * distribuições Linux).
     *
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir a ação.<br>
     * @throws SistemaOperacionalNaoSuportadoExcecao Se o método não suporta o
     * Sistema Operacional.<br>
     * 
     * 
     */
    public static void desocultar_arquivo(File arquivo) throws ESExcecao,
            SistemaOperacionalNaoSuportadoExcecao {
        if (!(arquivo.exists() && arquivo.isHidden())) {
            throw new ESExcecao();
        }
        String sistema_operacional = System.getProperty("os.name").split(" ")[0];

        switch (sistema_operacional) {
            case "Windows":
                try {
                    Runtime.getRuntime().exec("cmd /c attrib -h -s -r " + arquivo);
                } catch (IOException ex) {
                    throw new ESExcecao(ex);
                }
                break;
            case "Linux":
                arquivo.renameTo(new File(arquivo.getAbsolutePath().substring(1)));
                break;
            default:
                throw new SistemaOperacionalNaoSuportadoExcecao();
        }
    }

    /**
     * 
     * 
     * Desoculta o arquivo (funciona somente para o sistema Windows e para as
     * distribuições Linux).
     *
     * @param arquivo Um objeto do tipo String que representa o diretório do
     * arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir a ação.<br>
     * @throws SistemaOperacionalNaoSuportadoExcecao Se o método não suporta o
     * Sistema Operacional.<br>
     * 
     * 
     */
    public static void desocultar_arquivo(String arquivo) throws ESExcecao,
            SistemaOperacionalNaoSuportadoExcecao {
        Arquivo.desocultar_arquivo(new File(arquivo));
    }
}
