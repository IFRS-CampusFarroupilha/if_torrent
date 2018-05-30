package iftorrent.criptografia;

import java.io.UnsupportedEncodingException;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/**
 * Classe de codificação bidirecional que opera com o algoritmo de encriptação
 * AES, utilizando o algoritmo de encadeamento de blocos CBC (Cipher Block
 * Chaining) e o sistema de preenchimento PKCS7 à partir de implementação
 * proveniente do projeto Bouncy Castle. Website: https://www.bouncycastle.org
 *
 * @author Garren Souza
 */
public class Codificador {

    private String inicializador_CBC;

    /** 
     * Construtor da classe <b>Codificador</b> sem parâmetros que insere um
     * inicializador aleatório na variável que representa o inicializador do
     * algoritmo de encadeamento de blocos (CBC).
     * 
     *
     */
    public Codificador() {
        inserir_inicializadorCBC(gera_inicializador_CBC());
    }

    /** 
     * Construtor da classe <b>Codificador</b> que recebe como parâmetro um
     * objeto <b>String</b> que será inserido na respectiva variável no caso de
     * apresentar tamanho válido (128 bits). Retorna <b>true</b> em caso
     * afirmativo.
     * 
     *
     * @param inicializador_CBC
     * @return <b>true</b> || <b>false</b>
     */
    public boolean Codificador(String inicializador_CBC) {
        return inserir_inicializadorCBC(inicializador_CBC);
    }

    /**
     * Método que retorna um objeto <b>String</b> que representa o inicializador
     * do algoritmo de encadeamento de blocos (CBC)
     *
     * @return inicializador_CBC - String
     */
    public String obter_inicializadorCBC() {
        return inicializador_CBC;
    }

    /**
     * Método que recebe como parâmetro um objeto <b>String</b>, se este
     * apresentar tamanho válido será inserido na respectiva variável, em caso
     * de sucesso retorna-se <b>true</b>.
     *
     * @param inicializador_CBC
     * @return <b>true</b> || <b>false</b>
     */
    public boolean inserir_inicializadorCBC(String inicializador_CBC) {
        if (inicializador_CBC.getBytes().length == ConstantesCriptografia.TAMAMHO_16) {
            this.inicializador_CBC = inicializador_CBC;
            return true;
        }
        return false;
    }

    /**
     * Gera um inicializador com tamanho válido
     *
     * @return inicializador_CBC - String O inicializador do algorítmo de encadeamento de blocos
     */
    public String gera_inicializador_CBC() {
        return gera_texto(16);
    }

    /**
     * Gera texto aleatório com o tamanho (em bytes) especificado no parâmetro
     * de entrada tamanho (este deve ser igual a uma das constantes)
     * 
     * @see ConstantesCriptografia
     * @param tamanho - int QUantidade de bytes do texto que será gerado
     * @return - String texto gerado
     */
    public String gera_texto(int tamanho) {
        String[] caracteres = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z", "A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z", "!", "@", "#", "$", "%", "&", "*"};
        String senha = new String();

        if (tamanho == ConstantesCriptografia.TAMAMHO_16
                || tamanho == ConstantesCriptografia.TAMAMHO_24
                || tamanho == ConstantesCriptografia.TAMAMHO_32) {
            for (int x = 0; x < tamanho; x++) {
                int j = (int) (Math.random() * caracteres.length);
                senha += caracteres[j];
            }
        }
        return senha;
    }

    /**
     * Gera uma chave à partir da codificação(AES) do texto de entrada,
     * retornando um objeto SecretKeySpec
     *
     * @param texto_chave - String Texto que será utilizado como chave
     * @param charset - Charsets Codificação dos caracteres que compões o
     * parâmetro texto_chave
     * @return chave - SecretKeySpec A chave
     * @throws UnsupportedEncodingException
     */
    public SecretKeySpec gera_chave(String texto_chave, String charset) throws UnsupportedEncodingException {
        SecretKeySpec chave_final = null;

        if (valida_chave(texto_chave, charset)) {
            chave_final = new SecretKeySpec(texto_chave.getBytes(charset), "AES");
        }
        return chave_final;
    }

    /**
     * Confere o tamanho da chave em bytes
     *
     * @param charset - String Codificação do texto que será utilizado como
     * chave
     * @param chave - String Texto que será utilizado como chave
     * @return - boolean Retorna verdadeiro se a chave possuir um dos tamanhos
     * válidos (16,24 e 32 bits)
     * @throws UnsupportedEncodingException
     * @see String
     */
    public boolean valida_chave(String chave, String charset) throws UnsupportedEncodingException {
        switch (chave.getBytes(charset).length) {
            case ConstantesCriptografia.TAMAMHO_16:
                return true;
            case ConstantesCriptografia.TAMAMHO_24:
                return true;
            case ConstantesCriptografia.TAMAMHO_32:
                return true;
        }
        return false;
    }

    /**
     * Codificador AES que utiliza o modo de operação CBC e o algorítmo de
     * preenchimento PKCS7 (utiliza como inicializador do algoritmo de
     * encadeamento a variável interna (inicializador_CBC))
     *
     * @param texto - String Texto que será codificado
     * @param texto_chave - Texto que será utilizado com chave (deve possuir
     * tamanho de 16, 24 ou 32 bytes)
     * @param charset - Charsets Codificação utilizada no texto de entrada e na
     * chave
     * @return texto_encriptado - byte[] Texto codificado em um vetor de bytes
     * @throws Exception
     * @see Charsets
     */
    public byte[] codifica(String texto, String texto_chave, Charsets charset) throws Exception {
        return codifica(texto, texto_chave, this.inicializador_CBC, charset);
    }

    /**
     * Codificador AES que utiliza o modo de operação CBC e o algorítmo de
     * preenchimento PKCS7
     *
     * @param texto - String Texto que será codificado
     * @param texto_chave - Texto que será utilizado com chave (deve possuir
     * tamanho de 16, 24 ou 32 bytes)
     * @param inicializador_CBC - String String inicializador do algorítmo de
     * encadeamento de blocos (deve conter 16 bytes obrigatoriamente)
     * @param charset - Charsets Codificação utilizada no texto de entrada e na
     * chave
     * @return texto_encriptado - byte[] Texto codificado em um vetor de bytes
     * @throws Exception
     * @see Charsets
     */
    public byte[] codifica(String texto, String texto_chave, String inicializador_CBC, Charsets charset) throws Exception {
        byte[] texto_plano;//
        byte[] vetor_chave;
        SecretKeySpec chave;
        KeyParameter parametros_chave;
        CipherParameters parametros_nucleo;
        BlockCipherPadding preenchimento;
        BufferedBlockCipher nucleo;

        texto_plano = texto.getBytes(charset.getCharset());
        chave = gera_chave(texto_chave, charset.getCharset());
        vetor_chave = chave.getEncoded();
        parametros_chave = new KeyParameter(vetor_chave);
        //parametros do encriptador - chave(AES) e inicializador(CBC)
        parametros_nucleo = new ParametersWithIV(parametros_chave, inicializador_CBC.getBytes(charset.getCharset()));
        //Algoritmo de preenchimento de blocos
        preenchimento = new PKCS7Padding();
        //encriptador
        nucleo = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()), preenchimento);
        nucleo.reset();
        nucleo.init(true, parametros_nucleo);
        byte[] buf = new byte[nucleo.getOutputSize(texto_plano.length)];
        int len = nucleo.processBytes(texto_plano, 0, texto_plano.length, buf, 0);
        nucleo.doFinal(buf, len);
        return buf;
    }

    /**
     * Decodificador AES que utiliza o modo de operação CBC e o algorítmo de
     * preenchimento PKCS7 (utiliza como inicializador do algoritmo de
     * encadeamento a variável interna (inicializador_CBC))
     *
     * @param texto_codificado - byte[] Texto encriptado que será decodificado
     * @param texto_chave - String Chave que foi utilizada na codificação
     * encadeamento de blocos
     * @param charset - Charsets Codificação dos caracteres utilizados no
     * inicializador e na chave
     * @return texto decriptado - byte[]
     * @throws Exception
     * @see Charsets
     */
    public byte[] decodifica(byte[] texto_codificado, String texto_chave, Charsets charset) throws Exception {
        return decodifica(texto_codificado, texto_chave, this.inicializador_CBC, charset);
    }

    /**
     * Decodificador AES que utiliza o modo de operação CBC e o algorítmo de
     * preenchimento PKCS7
     *
     * @param texto_codificado - byte[] Texto encriptado que será decodificado
     * @param texto_chave - String Chave que foi utilizada na codificação
     * @param inicializador_CBC - String inicializador do algorítmo de
     * encadeamento de blocos (deve conter 16 bytes obrigatoriamente)
     * @param charset - Charsets Codificação dos caracteres utilizados no
     * inicializador e na chave
     * @return texto decriptado - byte[]
     * @throws Exception
     * @see Charsets
     */
    public byte[] decodifica(byte[] texto_codificado, String texto_chave, String inicializador_CBC, Charsets charset) throws Exception {
        byte[] vetor_chave;
        SecretKeySpec chave;
        KeyParameter parametros_chave;
        CipherParameters parametros_nucleo;
        BlockCipherPadding preenchimento;
        BufferedBlockCipher nucleo;

        chave = gera_chave(texto_chave, charset.getCharset());
        vetor_chave = chave.getEncoded();
        parametros_chave = new KeyParameter(vetor_chave);
        //parametros do encriptador - chave(AES) e inicializador(CBC)
        parametros_nucleo = new ParametersWithIV(parametros_chave, inicializador_CBC.getBytes(charset.getCharset()));
        preenchimento = new PKCS7Padding();//Algoritmo de preenchimento de blocos
        //encriptador
        nucleo = new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()), preenchimento);
        nucleo.reset();
        nucleo.init(false, parametros_nucleo);
        byte[] buf = new byte[nucleo.getOutputSize(texto_codificado.length)];
        int len = nucleo.processBytes(texto_codificado, 0, texto_codificado.length, buf, 0);
        len += nucleo.doFinal(buf, len);
        // remove preenchimento
        byte[] out = new byte[len];
        System.arraycopy(buf, 0, out, 0, len);
        return out;
    }

}
