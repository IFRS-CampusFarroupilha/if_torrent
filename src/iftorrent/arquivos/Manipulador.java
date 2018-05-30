package iftorrent.arquivos;

import iftorrent.excecoes.ESExcecao;
import iftorrent.excecoes.EnumNaoEncontradaExcecao;
import iftorrent.excecoes.SistemaOperacionalNaoSuportadoExcecao;
import java.io.File;
import java.io.IOException;

/**
 * 
 * 
 * A classe <b>Manipulador</b> serve para modificar o conteúdo de um arquivo.
 * <p>Esta classe necessita da enum <b>Modo</b> e da enum <b>Permissao</b>,
 * além da classe <b>Arquivo</b>.
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public abstract class Manipulador implements ManipuladorInterface {
    
    /**
     * 
     * 
     * Um objeto do tipo <b>File</b> que representa o arquivo a ser modificado.
     * 
     * 
     */
    protected File arquivo;
    
    /**
     * 
     * 
     * Uma enum <b>Modo</b> que representa o modo de abertura do arquivo. Armazenado 
     * para o método <i>fechar()</i>.
     * 
     * 
     */
    protected Modo modo = Modo.REESCRITA_LEITURA;

    /**
     * 
     * 
     * Construtor que recebe apenas o arquivo à ser aberto, sendo o modo de 
     * abertura padrão representado pela enum {@link Modo#LEITURA_ESCRITA 
     * LEITURA_ESCRITA}.
     * 
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo.<br>
     * 
     * 
     * 
     * @see Modo
     */
    public Manipulador(File arquivo) {
        this.arquivo = arquivo;
    }
    
    /**
     * 
     * 
     * Construtor que recebe o arquivo à ser aberto e o modo de 
     * abertura do arquivo.
     * 
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo.<br>
     * @param modo Uma enum que representa o modo de abertura do arquivo.<br>
     * 
     * 
     * 
     * @see Modo
     */
    public Manipulador(File arquivo, Modo modo){
        this.arquivo = arquivo;
        this.modo = modo;
    }
    
    /**
     * 
     * 
     * Construtor que recebe apenas o arquivo à ser aberto, sendo o modo de 
     * abertura padrão representado pela enum {@link Modo#LEITURA_ESCRITA 
     * LEITURA_ESCRITA}.
     * 
     * @param arquivo Um objeto do tipo <b>String</b> que representa o diretório
     * do arquivo.<br>
     * 
     * 
     * 
     * @see Modo
     */
    public Manipulador(String arquivo) {
        this.arquivo = new File(arquivo);
    }
    
    /**
     * 
     * 
     * Construtor que recebe o arquivo à ser aberto e o modo de 
     * abertura do arquivo.
     * 
     * @param arquivo Um objeto do tipo <b>String</b> que representa o diretório
     * do arquivo.<br>
     * @param modo Uma enum que representa o modo de abertura do arquivo.<br>
     * 
     * 
     * 
     * @see Modo
     */
    public Manipulador(String arquivo, Modo modo){
        this.arquivo = new File(arquivo);
        this.modo = modo;
    }
    
    /**
     * 
     * 
     * Altera as permissões do arquivo para o dono.
     * 
     * @param dono Um inteiro que representa as permissões do dono quanto ao
     * arquivo.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao 
     * parâmentro.<br>
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     * 
     * @see Permissao
     */
    public void mudar_permissao(int dono) throws ESExcecao, EnumNaoEncontradaExcecao {
        this.mudar_permissao(Permissao.procurar_permissao(dono));
    }

    /**
     * 
     * 
     * Altera as permissões do arquivo para o dono e para outros usuários.
     * 
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
     * @see Permissao
     */
    public void mudar_permissao(int dono, int outros) throws ESExcecao, 
            EnumNaoEncontradaExcecao {
        this.mudar_permissao(Permissao.procurar_permissao(dono),
                Permissao.procurar_permissao(outros));
    }

    /**
     * 
     * 
     * Altera as permissões do arquivo para o dono.
     * 
     * @param dono Um objeto do tipo <b>String</b> que representa as permissões
     * do dono quanto ao arquivo.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao 
     * parâmentro.<br>
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     * 
     * @see Permissao
     */
    public void mudar_permissao(String dono) throws ESExcecao, EnumNaoEncontradaExcecao {
        this.mudar_permissao(Permissao.procurar_permissao(dono));
    }

    /**
     * 
     * 
     * Altera as permissões do arquivo para o dono e para outros usuários.
     * 
     * @param dono Um objeto do tipo <b>String</b> que representa as permissões
     * do dono quanto ao arquivo.<br>
     * @param outros Um objeto do tipo <b>String</b> que representa as permissões
     * de outros usuários quanto ao arquivo.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao 
     * parâmentro.<br>
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     * 
     * @see Permissao
     */
    public void mudar_permissao(String dono, String outros) throws ESExcecao, 
            EnumNaoEncontradaExcecao {
        this.mudar_permissao(Permissao.procurar_permissao(dono),
                Permissao.procurar_permissao(outros));
    }
    
    /**
     * 
     * 
     * Altera as permissões do arquivo para o dono.
     * 
     * @param dono Uma enum <b>Permissao</b> que representa as permissões do 
     * dono quanto ao arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     * 
     * @see Permissao
     */
    public void mudar_permissao(Permissao dono) throws ESExcecao {
        Arquivo.mudar_permissao(this.arquivo, dono);
    }

    /**
     * 
     * 
     * Altera as permissões do arquivo para o dono e para outros usuários.
     * 
     * @param dono Uma enum <b>Permissao</b> que representa as permissões do 
     * dono quanto ao arquivo.<br>
     * @param outros Uma enum <b>Permissao</b> que representa as permissões de 
     * outros usuários quanto ao arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     * 
     * @see Permissao
     */
    public void mudar_permissao(Permissao dono, Permissao outros) throws ESExcecao {
        Arquivo.mudar_permissao(this.arquivo, dono, outros);
    }
    
    /**
     * 
     * 
     * Cria uma cópia do arquivo.
     * 
     * @param copia Um objeto do tipo <b>File</b> que representa a cópia do arquivo.<br>
     * 
     * @return Um objeto do tipo <b>File</b> que representa a cópia do arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de abertura.<br>
     * 
     * 
     */
    public File copiar_arquivo(File copia) throws ESExcecao {
        return Arquivo.copiar_para(this.arquivo, copia);
    }

    /**
     * 
     * 
     * Cria uma cópia do arquivo.
     * 
     * @param diretorio Um objeto do tipo <b>String</b> que representa o diretório
     * do arquivo.<br>
     * 
     * @return Um objeto do tipo <b>File</b> que representa a cópia do arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de abertura.<br>
     * 
     * 
     */
    public File copiar_arquivo(String diretorio) throws ESExcecao {
        return this.copiar_arquivo(new File(diretorio));
    }

    /**
     * 
     * 
     * Cria uma cópia do arquivo.
     * 
     * @return Um objeto do tipo <b>File</b> que representa a cópia do arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de abertura.<br>
     * 
     * 
     */
    public File copiar_arquivo() throws ESExcecao {
        return this.copiar_arquivo(this.diretorio() + " (copia)");
    }

    /**
     * 
     * 
     * Apaga todo conteúdo do arquivo.
     * 
     * @throws ESExcecao Se o arquivo não permitir a escrita.<br>
     * 
     * 
     */
    public void apagar_conteudo() throws ESExcecao {
        Arquivo.apagar_conteudo(this.arquivo);
    }

    /**
     * 
     * 
     * Exclui o arquivo.
     * 
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     */
    public void excluir() throws ESExcecao {
        Arquivo.excluir(this.arquivo);
    }

    /**
     * 
     * 
     * Pega o diretório absoluto do arquivo.
     * 
     * @return Um objeto do tipo <b>String</b> que representa o diretório do 
     * arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir.<br>
     * 
     * 
     */
    public String diretorio() throws ESExcecao {
        return Arquivo.diretorio(this.arquivo);
    }
    
    /**
     * 
     * 
     * Oculta o arquivo (funciona somente para o sistema Windows e para as 
     * distribuições Linux).
     * 
     * @throws ESExcecao Se o arquivo não existir.<br>
     * @throws SistemaOperacionalNaoSuportadoExcecao Se o método não suporta o 
     * Sistema Operacional.<br>
     * 
     * 
     */
    public void ocultar_arquivo() throws ESExcecao, 
            SistemaOperacionalNaoSuportadoExcecao {
        Arquivo.ocultar_arquivo(this.arquivo);
    }

    /**
     * 
     * 
     * Desoculta o arquivo (funciona somente para o sistema Windows e para as 
     * distribuições Linux).
     * 
     * @throws ESExcecao Se o arquivo não existir.<br>
     * @throws SistemaOperacionalNaoSuportadoExcecao Se o método não suporta o 
     * Sistema Operacional.<br>
     * 
     * 
     */
    public void desocultar_arquivo() throws ESExcecao, 
            SistemaOperacionalNaoSuportadoExcecao {
        Arquivo.desocultar_arquivo(this.arquivo);
    }
    
    /**
     * 
     * 
     * Altera o arquivo em edição. O método salva automaticamente as 
     * modificações, não sendo necessário a invocação do método <i>salvar()</i>.
     * 
     * @param arquivo Um objeto do tipo <b>String</b> que representa o diretório 
     * do novo arquivo à ser editado.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de 
     * abertura selecionado.<br>
     * 
     * 
     */
    public void alterar_arquivo(String arquivo) throws ESExcecao {
        this.alterar_arquivo(new File(arquivo));
    }

    /**
     * 
     * 
     * Altera o arquivo em edição. O método salva automaticamente as 
     * modificações, não sendo necessário a invocação do método <i>salvar()</i>.
     * 
     * @param arquivo Um objeto do tipo <b>String</b> que representa o novo 
     * arquivo à ser editado.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de 
     * abertura selecionado.<br>
     * 
     * 
     */
    public void alterar_arquivo(File arquivo) throws ESExcecao {
        this.arquivo = arquivo;
        this.fechar();
        this.abrir();
    }
    
    /**
     * 
     * 
     * Cria um arquivo se o mesmo não existir.
     * 
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo 
     * para ser aberto.<br>
     * 
     * @throws ESExcecao Se ocorrer um erro durante a abertura.<br>
     * 
     * 
     */
    protected void criar_arquivo(File arquivo) throws ESExcecao {
        try {
            if(!this.arquivo.exists()){
                this.arquivo.createNewFile();
            }
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
    }
    
}