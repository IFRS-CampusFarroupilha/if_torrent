package iftorrent.arquivos;

import iftorrent.excecoes.ArgumentoIlegalExcecao;
import iftorrent.excecoes.ESExcecao;
import iftorrent.excecoes.EnumNaoEncontradaExcecao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 
 * 
 * A classe <b>ManipuladorTexto</b> serve para modificar o conteúdo literal de 
 * um arquivo.
 * <p>Esta classe necessita da enum <b>Modo</b> e da enum <b>Permissao</b>.
 * 
 * @author Eduardo Toffolo
 * 
 * 
 */
public class ManipuladorTexto extends Manipulador {
    
    /**
     * 
     * 
     * Um objeto do tipo <b>InputStream</b> para leitura em bytes (utilizado apenas na
     * instanciação do objeto do tipo <b>InputStreamReader</b>).
     * 
     * 
     */
    private InputStream leitor_binario;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>InputStreamReader</b> para leitura em chars (utilizado 
     * apenas na instanciação do objeto do tipo <b>BufferedReader</b>).
     * 
     * 
     */
    private InputStreamReader leitor_caractere;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>BufferedReader</b> para leitura de literais do tipo <b>String</b>.
     * 
     * 
     */
    private BufferedReader leitor_literal;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>OutputStream</b> para escrita em bytes (utilizado apenas na
     * instanciação do objeto do tipo <b>OutputStreamWriter</b>).
     * 
     * 
     */
    private OutputStream escritor_binario;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>OutputStreamWriter</b> para escrita em chars (utilizado 
     * apenas na instanciação do objeto do tipo <b>BufferedWriter</b>).
     * 
     * 
     */
    private OutputStreamWriter escritor_caractere;
    
    /**
     * 
     * 
     * Um objeto do tipo <b>BufferedWriter</b> para escrita de literais do tipo 
     * <b>String</b>.
     * 
     * 
     */
    private BufferedWriter escritor_literal;

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
    public ManipuladorTexto(String arquivo) throws ESExcecao {
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
    public ManipuladorTexto(String arquivo, Modo modo) throws ESExcecao {
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
    public ManipuladorTexto(String arquivo, String sigla) throws ESExcecao, 
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
    public ManipuladorTexto(String arquivo, int codigo) throws ESExcecao, 
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
    public ManipuladorTexto(File arquivo) throws ESExcecao {
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
    public ManipuladorTexto(File arquivo, Modo modo) throws ESExcecao {
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
    public ManipuladorTexto(File arquivo, String sigla) throws ESExcecao, 
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
     * 
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
    public ManipuladorTexto(File arquivo, int codigo) throws ESExcecao, 
            EnumNaoEncontradaExcecao {
        super(arquivo, Modo.procurar_modo(codigo));
        this.abrir();
    }

    /**
     * 
     * 
     * Lê uma linha inteira do arquivo (até um caractere de terminação de linha).
     * 
     * @return Um objeto do tipo <b>String</b> contendo a linha lida do arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir a leitura.<br>
     * 
     * 
     */
    public String ler_linha() throws ESExcecao {
        try {
            return this.leitor_literal.readLine();
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
    }

    /**
     * 
     * 
     * Lê o arquivo inteiro.
     * 
     * @return Um vetor de objetos do tipo <b>String</b> contendo, cada um, uma 
     * linha inteira do arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir a leitura.<br>
     * 
     * 
     */
    public String[] ler_texto() throws ESExcecao  {
        String texto = "";
        String frase;

        while ((frase = this.ler_linha()) != null) {
            texto += frase + "\n";
        }
        return texto.split("\n");
    }

    /**
     * 
     * 
     * Insere uma linha no arquivo.
     * 
     * @param frase Um objeto do tipo <b>String</b> à ser inserido no arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não permitir a escrita.<br>
     * 
     * 
     */
    public void escrever_linha(String frase) throws ESExcecao {
        try {
            this.escritor_literal.write(frase);
            this.escritor_literal.flush();
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
    }

    /**
     * 
     * 
     * Insere uma linha com quebra de linha no arquivo.
     * 
     * @param frase Um objeto do tipo <b>String</b> à ser inserido no arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não permitir a escrita.<br>
     * 
     * 
     */
    public void escrever_com_quebra_de_linha(String frase) throws ESExcecao {
        this.escrever_linha(frase + "\r\n");
    }

    /**
     * 
     * 
     * Insere um conjunto de linhas no arquivo.
     * 
     * @param frases Um vetor de objetos do tipo <b>String</b> à serem inseridos
     * no arquivo.<br>
     * 
     * @throws ESExcecao Se o arquivo não permitir a escrita.<br>
     * 
     * 
     */
    public void escrever_texto(String[] frases) throws ESExcecao {
        for (String frase : frases) {
            this.escrever_com_quebra_de_linha(frase);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public ManipuladorTexto copia() throws ESExcecao{
        return new ManipuladorTexto(this.arquivo, this.modo);
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
                this.abrir_leitura(arquivo);
                this.abrir_escrita(arquivo, true);
                break;
            case REESCRITA:
                this.abrir_escrita(arquivo, false);
                break;
            case REESCRITA_LEITURA:
                this.abrir_leitura(arquivo);
                this.abrir_escrita(arquivo, false);
                break;
            case ANEXACAO:
                this.abrir_escrita(arquivo, true);
                break;
            case ANEXACAO_LEITURA:
                this.abrir_leitura(arquivo);
                this.abrir_escrita(arquivo, true);
                break;
            default:
                throw new ArgumentoIlegalExcecao("Modo inexistente");
        }
    }

    /**
     * 
     * 
     * Abre os leitores de arquivo do objeto.
     * 
     * @param arquivo Um objeto do tipo <b>File</b> que representa o arquivo 
     * para ser utilizado na abertura das streams.<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou não permitir a leitura.<br>
     * 
     * 
     */
    private void abrir_leitura(File arquivo) throws ESExcecao {
        try {
            this.leitor_binario = new FileInputStream(arquivo);
        } catch (FileNotFoundException ex) {
            throw new ESExcecao(ex);
        }
        this.leitor_caractere = new InputStreamReader(leitor_binario);
        this.leitor_literal = new BufferedReader(leitor_caractere);
    }

    /**
     * 
     * 
     * Abre os escritores de arquivo do objeto.
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
            this.escritor_binario = new FileOutputStream(arquivo, anexar);
        } catch (FileNotFoundException ex) {
            throw new ESExcecao(ex);
        }
        this.escritor_caractere = new OutputStreamWriter(escritor_binario);
        this.escritor_literal = new BufferedWriter(escritor_caractere);
    }

    /**
     * 
     * 
     * Fecha os leitores de arquivo do objeto.
     * 
     * @throws ESExcecao Se algum erro ocorrer.<br>
     * 
     * 
     */
    private void fechar_leitura() throws ESExcecao {
        try {
            this.leitor_literal.close();
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
    }

    /**
     * 
     * 
     * Fecha os escritores de arquivo do objeto.
     * 
     * @throws ESExcecao Se algum erro ocorrer.<br>
     * 
     * 
     */
    private void fechar_escrita() throws ESExcecao {
        try {
            this.escritor_literal.close();
        } catch (IOException ex) {
            throw new ESExcecao(ex);
        }
    }

    /**
     * 
     * 
     * Procura se existe um trecho no arquivo.
     * 
     * @param busca Um objeto do tipo <b>String</b> para a busca no arquivo.<br>
     * 
     * @return Um boleano que representa se encontrou o trecho ({@code true} para
     * encontrado e {@code false} para não encontrado).<br>
     * 
     * @throws ESExcecao Se o arquivo não existir ou se o arquivo não permitir a
     * leitura.<br>
     * 
     * 
     */
    public boolean contem(String busca) throws ESExcecao {
        String frase;

        while ((frase = this.ler_linha()) != null) {
            if (frase.contains(busca)) {
                return true;
            }
        }
        return false;
    }

}