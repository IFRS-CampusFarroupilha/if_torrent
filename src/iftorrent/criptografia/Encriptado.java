package iftorrent.criptografia;

import iftorrent.excecoes.CodificacaoNaoSuportadaExcecao;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

/**
 * Classe que representa uma estrutura completa de codificação bidirecional
 * resultante do uso da Classe Codificador, com chave, inicializador do
 * algoritmo de encadeamento de blocos, texto plano, texto codificado e
 * codificação utilizada no texto (charset).
 *
 * @see Codificador
 * @see Hash
 * @see Charsets
 * @author Garren Souza
 */
public class Encriptado {

    private String chave;
    private String inicializador_CBC;
    private String texto_plano;
    private byte[] texto_codificado;
    private String codificacao;

    /**
     * 
     * Construtor da classe <b>Encriptado</b> que recebe um objeto do tipo
     * Charsets inserindo-o posteriormente á variável codificacao.
     * 
     *
     * @see Charsets
     * @param charset Charsets
     */
    Encriptado(Charsets charset) {
        this.codificacao = charset.getCharset();
    }

    /**
     * 
     * Método que retorna a codificação utilizada na classe através de um
     * objeto.
     * <b>String</b>
     * 
     *
     * @see Charsets
     * @return codificação - String
     */
    public String obter_codificação() {
        return codificacao;
    }

    /**
     * 
     * Método de inserção da codificação utilizada na classe, recebe como
     * parâmetro um objeto do tipo <b>Charsets</b>.
     * 
     *
     * @see Charsets
     * @param charset
     */
    public void inserir_codificação(Charsets charset) {
        this.codificacao = charset.getCharset();
    }

    /**
     * 
     * Método que confere validade à um objeto <b>String</b> (recebido como
     * parâmetro) no caso do mesmo se encontrar contido na Enum <b>Charsets</b>
     * retornando <b>true</b> em caso verdadeiro e <b>false</b> no caso
     * contrário.
     * 
     *
     * @see Charsets
     * @param charset
     * @return <b>true</b> || <b>false</b>
     */
    public boolean valida_codificacao(String charset) {
        for (Charsets charsets : Charsets.values()) {
            if (charsets.getCharset().equals(this.codificacao)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * Método que retorna o texto codificado presente na classe (não confere se
     * o mesmo se encontra nulo ou não).
     * 
     *
     * @return texto_codificado - byte[]
     */
    public byte[] obter_texto_codificado() {
        return texto_codificado;
    }

    /**
     * 
     * Insere o parâmetro <b>byte[]</b> na variável texto_codificado (Utilizar
     * com atenção).
     *
     * @see Codificador
     * @param texto_codificado
     */
    public void inserir_texto_codificado(byte[] texto_codificado) {
        this.texto_codificado = texto_codificado;
    }

    /**
     * 
     * Método que retorna um objeto <b>String</b> que representa a chave
     * utilizada na encriptação da classe.
     * 
     *
     * @see Codificador
     * @return chave - String
     */
    public String obter_chave() {
        return chave;
    }

    /**
     * 
     * Método que insere um objeto <b>String</b> na variável que representa a
     * chave utilizada na encriptação da classe.
     * 
     *
     * @see Codificador
     * @param chave
     */
    public void inserir_chave(String chave) {
        this.chave = chave;
    }

    /** 
     * Método que retorna através de um objeto <b>String</b> o inicializador do
     * algoritmo de encadeamento de blocos utilizado no processo de encriptação
     * da classe (CBC block).
     * 
     *
     * @see Codificador
     * @return inicializador_CBC - String
     */
    public String obter_cnicializador_CBC() {
        return inicializador_CBC;
    }

    /**
     * 
     * Método que insere um objeto <b>String</b> na variável que representa o
     * inicializador do algoritmo de encadeamento de blocos utilizado no
     * processo de encriptação da classe (CBC block).
     * 
     *
     * @see Codificador
     * @param inicializador_CBC
     */
    public void inserir_inicializador_CBC(String inicializador_CBC) {
        this.inicializador_CBC = inicializador_CBC;
    }

    /**
     * 
     * Método que retorna através de um objeto <b>String</b> o texto plano
     * presente na classe.
     * 
     *
     * @see Codificador
     * @return
     */
    public String obter_texto() {
        return texto_plano;
    }

    /** 
     * Método sem retorno que insere um objeto do tipo <b>String</b> recebido
     * como parâmetro na variável <i>texto_plano</i>.
     * 
     *
     * @see Codificador
     * @param texto - String
     */
    public void inserir_texto(String texto) {
        this.texto_plano = texto;
    }

    /** 
     * Método que retorna um objeto do tipo <b>String</b> contendo os dados da
     * classe através da qual foi chamado.
     * 
     *
     * @return conteudo_da_classe - String
     * @throws UnsupportedEncodingException
     */
    public String mostrar_dados() throws UnsupportedEncodingException {
        return "Encriptado{" + "chave=" + chave + ", inicializador_CBC=" + inicializador_CBC + ", texto=" + texto_plano + ", texto_codificado=" + new String(texto_codificado, codificacao) + ", charset=" + codificacao + '}';
    }

    /**
     * 
     * Método que retorna um hash realizado sobre os dados da classe através do
     * <b>algoritmo</b> especificado
     * 
     *
     * @param algoritmo Algoritmos
     * @return hash - byte[]
     * @throws NoSuchAlgorithmException
     * @throws CodificacaoNaoSuportadaExcecao
     * @throws UnsupportedEncodingException
     */
    public byte[] hash(Algoritmos algoritmo) throws NoSuchAlgorithmException, CodificacaoNaoSuportadaExcecao, UnsupportedEncodingException {
        return Hash.hash(this.toString(), algoritmo, Charsets.valueOf(codificacao));
    }

    /**
     * 
     * Método que confere igualdade entre um objeto genérico recebido <b>Object
     * obj</b> e a própria classe, retornando true se houver igualdade e false
     * no caso contrário.
     * 
     *
     * @param obj Object
     * @return <b>true</b> || <b>false</b>
     */
    public boolean eh_igual(Object obj) {
        if (obj == null
                || this != obj
                || getClass() != obj.getClass()) {
            return false;
        }
        final Encriptado other;
        
        other = (Encriptado) obj;
        if (!Objects.equals(this.chave, other.chave)
                || !Objects.equals(this.inicializador_CBC, other.inicializador_CBC)
                || !Objects.equals(this.texto_plano, other.texto_plano)
                || !Objects.equals(this.codificacao, other.codificacao)
                || !Arrays.equals(this.texto_codificado, other.texto_codificado)) {
            return false;
        }
        return true;
    }
}
