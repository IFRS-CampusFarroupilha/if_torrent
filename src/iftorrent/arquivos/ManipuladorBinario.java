package iftorrent.arquivos;

import iftorrent.excecoes.ArgumentoIlegalExcecao;
import iftorrent.excecoes.ClasseNaoEncontradaExcecao;
import iftorrent.excecoes.ESExcecao;
import iftorrent.excecoes.EnumNaoEncontradaExcecao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * 
 * A classe <b>ManipuladorBinario</b> serve para modificar o conteúdo binário de um 
 * arquivo.
 * <p>Esta classe necessita da enum <b>Modo</b> e da enum <b>Permissao</b>.
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public class ManipuladorBinario extends Manipulador {
    
    /**
     * 
     * 
     * Um objeto do tipo <b>InputStream</b> para leitura em bytes.
     * 
     * 
     */
    private FileInputStream leitor_binario;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>OutputStream</b> para escrita em bytes.
     * 
     * 
     */
    private FileOutputStream escritor_binario;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>ObjectInputStream</b> para leitura de objetos.
     * 
     * 
     */
    private ObjectInputStream leitor_objetos;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>ObjectOutputStream</b> para escrita de objetos.
     * 
     * 
     */
    private ObjectOutputStream escritor_objetos;
    
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
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de 
     * abertura selecionado.<br>
     * 
     * 
     * 
     * @see Modo
     */
    public ManipuladorBinario(String arquivo) throws ESExcecao {
        super(arquivo);
        this.abrir();
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
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de 
     * abertura selecionado.<br>
     * 
     * 
     * 
     * @see Modo
     */
    public ManipuladorBinario(String arquivo, Modo modo) throws ESExcecao {
        super(arquivo, modo);
        this.abrir();
    }

    /**
     * 
     * 
     * Construtor que recebe o arquivo à ser aberto e o modo de 
     * abertura do arquivo.
     * 
     * @param arquivo Um objeto do tipo <b>String</b> que representa o deiretório
     * do arquivo.<br>
     * @param sigla Um objeto do tipo <b>String</b> que representa o modo de 
     * abertura do arquivo.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao 
     * parâmentro.<br>
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de 
     * abertura selecionado.<br>
     * 
     * 
     * 
     * @see Modo
     */
    public ManipuladorBinario(String arquivo, String sigla) throws ESExcecao, 
            EnumNaoEncontradaExcecao {
        super(arquivo, Modo.procurar_modo(sigla));
        this.abrir();
    }

    /**
     * 
     * 
     * Construtor que recebe o arquivo à ser aberto e o modo de abertura do arquivo.
     * 
     * @param arquivo Um objeto do tipo <b>String</b> que representa o deiretório
     * do arquivo.<br>
     * @param codigo Um inteiro que representa o modo de abertura do arquivo.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao 
     * parâmentro.<br>
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de 
     * abertura selecionado.<br>
     * 
     * 
     * 
     * @see Modo
     */
    public ManipuladorBinario(String arquivo, int codigo) throws ESExcecao, 
            EnumNaoEncontradaExcecao {
        super(arquivo, Modo.procurar_modo(codigo));
        this.abrir();
    }

    /**
     * 
     * 
     * Construtor que recebe apenas o arquivo à ser aberto, sendo o modo de 
     * abertura padrão representado pela enum {@link Modo#LEITURA_ESCRITA 
     * LEITURA_ESCRITA}.
     * 
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de 
     * abertura selecionado.<br>
     * 
     * 
     * 
     * @see Modo
     */
    public ManipuladorBinario(File arquivo) throws ESExcecao {
        super(arquivo);
        this.abrir();
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
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de 
     * abertura selecionado.<br>
     * 
     * 
     * 
     * @see Modo
     */
    public ManipuladorBinario(File arquivo, Modo modo) throws ESExcecao {
        super(arquivo, modo);
        this.abrir();
    }

    /**
     * 
     * 
     * Construtor que recebe o arquivo à ser aberto e o modo de 
     * abertura do arquivo.
     * 
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo.<br>
     * @param sigla Um objeto do tipo <b>String</b> que representa o modo de 
     * abertura do arquivo.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao 
     * parâmentro.<br>
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de 
     * abertura selecionado.<br>
     * 
     * 
     * 
     * @see Modo
     */
    public ManipuladorBinario(File arquivo, String sigla) throws ESExcecao, 
            EnumNaoEncontradaExcecao {
        super(arquivo, Modo.procurar_modo(sigla));
        this.abrir();
    }

    /**
     * 
     * 
     * Construtor que recebe o arquivo à ser aberto e o modo de 
     * abertura do arquivo.
     * 
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo.<br>
     * @param codigo Um inteiro que representa o modo de abertura do arquivo.<br>
     * 
     * @throws EnumNaoEncontradaExcecao Se não houver enum correspondente ao 
     * parâmentro.<br>
     * @throws ESExcecao Se o arquivo não existir ou não permitir o modo de 
     * abertura selecionado.<br>
     * 
     * 
     * 
     * @see Modo
     */
    public ManipuladorBinario(File arquivo, int codigo) throws ESExcecao, 
            EnumNaoEncontradaExcecao {
        super(arquivo, Modo.procurar_modo(codigo));
        this.abrir();
    }

    
    /**
     * 
     * 
     * Lê um trecho em binário do arquivo.
     * 
     * @param inicio Um inteiro que representa o início do trecho a ser copiado
     * (indica a posição do primeiro caractere a ser lido, iniciando em 0).<br>
     * @param fim Um inteiro que representa o fim do trecho a ser copiado
     * (indica a posição do caractere que sucede o último caractere a ser lido).
     * A constante {@link ConstantesArquivos#FINAL_ARQUIVO} representa o final
     * do arquivo.<br>
     * 
     * @return Um vetor do tipo <b>byte</b> contendo o trecho lido do arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir a leitura.<br>
     * 
     * 
     */
    public byte[] ler_trecho(int inicio, int fim) throws ESExcecao {
        return Arquivo.ler_trecho(this.arquivo, inicio, fim);
    }

    /**
     * 
     * 
     * Lê um objeto do arquivo.
     * 
     * @return Um objeto do tipo <b>Object</b> contendo um objeto do arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir a leitura.
     * @throws ClasseNaoEncontradaExcecao Se a classe do objeto serializado não
     * for encontrada.<br>
     * 
     * 
     */
    public Object ler_objeto() throws ESExcecao, ClasseNaoEncontradaExcecao {
        try {
            return this.leitor_objetos.readObject();
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        } catch (ClassNotFoundException ex) {
            throw new ClasseNaoEncontradaExcecao(ex);
        }
    }

    /**
     * 
     * 
     * Insere um trecho em binário no arquivo.
     * 
     * @param trecho Um vetor do tipo <b>byte</b> contendo o trecho a ser 
     * inserido do arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não permitir a escrita.<br>
     * 
     * 
     */
    public void escrever_trecho(byte[] trecho) throws ESExcecao {
        try {
            this.escritor_binario.write(trecho);
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
    }

    /**
     * 
     * 
     * Insere um objeto serializado no arquivo.
     * 
     * @param objeto Um objeto do tipo <b>Object</b> que representa o objeto a 
     * ser inserido.<br>
     * 
     * @throws ESExcecao Se o arquivo não permitir a escrita ou não existir.<br>
     * 
     * 
     */
    public void escrever_objeto(Object objeto) throws ESExcecao {
        try {
            this.escritor_objetos.writeObject(objeto);
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ManipuladorBinario copia() throws ESExcecao{
        return new ManipuladorBinario(this.arquivo, this.modo);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void fechar() throws ESExcecao {
        switch (this.modo) {
            case LEITURA:
                this.fechar_leitura();
                break;
            case LEITURA_ESCRITA:
                this.fechar_leitura();
                this.fechar_escrita();
                break;
            case REESCRITA:
                this.fechar_escrita();
                break;
            case REESCRITA_LEITURA:
                this.fechar_leitura();
                this.fechar_escrita();
                break;
            case ANEXACAO:
                this.fechar_escrita();
                break;
            case ANEXACAO_LEITURA:
                this.fechar_leitura();
                this.fechar_escrita();
                break;
            default:
                throw new ArgumentoIlegalExcecao("Modo inexistente");
        }
        this.arquivo = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void abrir() throws ESExcecao {
        switch (this.modo) {
            case LEITURA:
                this.abrir_leitura(arquivo);
                this.arquivo.setReadOnly();
                break;
            case LEITURA_ESCRITA:
                this.abrir_escrita(arquivo, true);
                this.abrir_leitura(arquivo);
                break;
            case REESCRITA:
                this.abrir_escrita(arquivo, false);
                break;
            case REESCRITA_LEITURA:
                this.abrir_escrita(arquivo, true);
                this.abrir_leitura(arquivo);
                break;
            case ANEXACAO:
                this.abrir_escrita(arquivo, true);
                break;
            case ANEXACAO_LEITURA:
                this.abrir_escrita(arquivo, true);
                this.abrir_leitura(arquivo);
                break;
            default:
                throw new ArgumentoIlegalExcecao("Modo inexistente");
        }
    }

    /**
     * 
     * 
     * Abre o leitor de arquivo do objeto.
     * 
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo 
     * para ser utilizado na abertura das Streams.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir a leitura.<br>
     * 
     * 
     */
    private void abrir_leitura(File arquivo) throws ESExcecao {
        try {
            this.leitor_binario = new FileInputStream(arquivo);
            this.leitor_objetos = new ObjectInputStream(this.leitor_binario);
        } catch (FileNotFoundException ex) {
            throw new ESExcecao(ex);
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
    }

    /**
     * 
     * 
     * Abre o escritor de arquivo do objeto.
     * 
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo 
     * para ser utilizado na abertura das Streams.<br>
     * @param anexar Um boleano que indentifica se o arquivo deve ser reescrito
     * ou ter conteúdo anexado ({@code true} para anexar, {@code false} para 
     * reescrever).<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir a escrita.<br>
     * 
     * 
     */
    private void abrir_escrita(File arquivo, boolean anexar) throws ESExcecao {
        try {
            this.criar_arquivo(arquivo);
            this.escritor_binario = new FileOutputStream(arquivo, anexar);
            this.escritor_objetos = new ObjectOutputStream(this.escritor_binario);
        } catch (FileNotFoundException ex) {
            throw new ESExcecao(ex);
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
    }

    /**
     * 
     * 
     * Fecha o leitor de arquivo do objeto.
     * 
     * @throws ESExcecao Se algum erro ocorrer.<br>
     * 
     * 
     */
    private void fechar_leitura() throws ESExcecao {
        try {
            this.leitor_objetos.close();
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
    }

    /**
     * 
     * 
     * Fecha o escritor de arquivo do objeto.
     * 
     * @throws ESExcecao Se algum erro ocorrer.<br>
     * 
     * 
     */
    private void fechar_escrita() throws ESExcecao {
        try {
            this.escritor_objetos.close();
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
    }

}
