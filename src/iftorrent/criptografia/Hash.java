package iftorrent.criptografia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import org.bouncycastle.jcajce.provider.digest.Blake2b;
import org.bouncycastle.jcajce.provider.digest.MD2;
import org.bouncycastle.jcajce.provider.digest.MD4;
import org.bouncycastle.jcajce.provider.digest.MD5;
import org.bouncycastle.jcajce.provider.digest.RIPEMD128;
import org.bouncycastle.jcajce.provider.digest.RIPEMD160;
import org.bouncycastle.jcajce.provider.digest.RIPEMD256;
import org.bouncycastle.jcajce.provider.digest.RIPEMD320;
import org.bouncycastle.jcajce.provider.digest.SHA1;
import org.bouncycastle.jcajce.provider.digest.SHA224;
import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA384;
import org.bouncycastle.jcajce.provider.digest.SHA512;
import org.bouncycastle.jcajce.provider.digest.Skein;

/**
 * Classe de codificação unidirecional que opera com os algoritmos
 * contidos na enum Algoritmos à partir de implementação proveniente do projeto
 * Bouncy Castle https://www.bouncycastle.org
 *
 * @author Garren Souza
 */
public class Hash {

    private Algoritmos alg;

    /**
     * Digestor MD2
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_MD_2(byte[] texto) {
        MD2.Digest md2;

        md2 = new MD2.Digest();
        return md2.digest(texto);
    }

    /**
     * Digestor MD4
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_MD_4(byte[] texto) {
        MD4.Digest md4;

        md4 = new MD4.Digest();

        return md4.digest(texto);
    }

    /**
     * Digestor MD5
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_MD_5(byte[] texto) {
        MD5.Digest md5;

        md5 = new MD5.Digest();
        return md5.digest(texto);
    }

    /**
     * Digestor SHA-1
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_SHA_1(byte[] texto) {
        SHA1.Digest sha1;

        sha1 = new SHA1.Digest();
        return sha1.digest(texto);
    }

    /**
     * Digestor SHA-224
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_SHA_224(byte[] texto) {
        SHA224.Digest sha_224;

        sha_224 = new SHA224.Digest();
        return sha_224.digest(texto);
    }

    /**
     * Digestor SHA-256
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_SHA_256(byte[] texto) {
        SHA256.Digest sha_256;

        sha_256 = new SHA256.Digest();
        return sha_256.digest(texto);
    }

    /**
     * Digestor SHA-384
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_SHA_384(byte[] texto) {
        SHA384.Digest sha_384;

        sha_384 = new SHA384.Digest();
        return sha_384.digest(texto);
    }

    /**
     * Digestor SHA-512
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_SHA_512(byte[] texto) {
        SHA512.Digest sha_512;

        sha_512 = new SHA512.Digest();
        return sha_512.digest(texto);
    }

    /**
     * Digestor SHA-3 (224)
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_SHA_3_224(byte[] texto) {
        SHA3.Digest224 sha_3_224;

        sha_3_224 = new SHA3.Digest224();
        return sha_3_224.digest(texto);
    }

    /**
     * Digestor SHA-3 (256)
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_SHA_3_256(byte[] texto) {
        SHA3.Digest256 sha_3_256;

        sha_3_256 = new SHA3.Digest256();
        return sha_3_256.digest(texto);
    }

    /**
     * Digestor SHA-3 (384)
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_SHA_3_384(byte[] texto) {
        SHA3.Digest384 sha_3_384;

        sha_3_384 = new SHA3.Digest384();
        return sha_3_384.digest(texto);
    }

    /**
     * Digestor SHA-3 (512)
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_SHA_3_512(byte[] texto) {
        SHA3.Digest512 sha_3_512;

        sha_3_512 = new SHA3.Digest512();
        return sha_3_512.digest(texto);
    }

    /**
     * Digestor Skein-1024
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_Skein_1024(byte[] texto) {
        Skein.DigestSkein1024 skein;

        skein = new Skein.DigestSkein1024(512);
        return skein.digest(texto);
    }

    /**
     * Digestor Blake2b-160
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_Blake2b_160(byte[] texto) {
        Blake2b.Blake2b160 blake2b_160;

        blake2b_160 = new Blake2b.Blake2b160();
        return blake2b_160.digest(texto);
    }

    /**
     * Digestor Blake2b-256
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_Blake2b_256(byte[] texto) {
        Blake2b.Blake2b256 blake2b_256;

        blake2b_256 = new Blake2b.Blake2b256();
        return blake2b_256.digest(texto);
    }

    /**
     * Digestor Blake2b-384
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_Blake2b_384(byte[] texto) {
        Blake2b.Blake2b384 blake2b_384;

        blake2b_384 = new Blake2b.Blake2b384();
        return blake2b_384.digest(texto);
    }

    /**
     * Digestor Blake2b-512
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_Blake2b_512(byte[] texto) {
        Blake2b.Blake2b512 blake2b_512;

        blake2b_512 = new Blake2b.Blake2b512();
        return blake2b_512.digest(texto);
    }

    /**
     * Digestor RIPEMD-128
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_RIPEMD_128(byte[] texto) {
        RIPEMD128.Digest ripemd128;

        ripemd128 = new RIPEMD128.Digest();
        return ripemd128.digest(texto);
    }

    /**
     * Digestor RIPEMD-160
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_RIPEMD_160(byte[] texto) {
        RIPEMD160.Digest ripemd160;

        ripemd160 = new RIPEMD160.Digest();
        return ripemd160.digest(texto);
    }

    /**
     * Digestor RIPEMD-256
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_RIPEMD_256(byte[] texto) {
        RIPEMD256.Digest ripemd256;

        ripemd256 = new RIPEMD256.Digest();
        return ripemd256.digest(texto);
    }

    /**
     * Digestor RIPEMD-320
     *
     * @param texto - byte[] texto de entrada
     * @return hash - byte[] texto codificado
     */
    public static byte[] digestor_RIPEMD_320(byte[] texto) {
        RIPEMD320.Digest ripemd320;

        ripemd320 = new RIPEMD320.Digest();
        return ripemd320.digest(texto);
    }

    /**
     * Método que retorna o texto encriptado à partir do algorítmo especificado
     * pelo parâmetro alg
     *
     * @param alg - Algoritmos Algoritmo que será utilizado
     * @param texto - byte[] Texto que será processado
     * @return texto_encriptado - byte[] Texto encriptado
     */
    public static byte[] hash(Algoritmos alg, byte[] texto) {
        switch (alg) {
            case MD2:
                return digestor_MD_2(texto);
            case MD4:
                return digestor_MD_4(texto);
            case MD5:
                return digestor_MD_5(texto);
            case SHA_1:
                return digestor_SHA_1(texto);
            case SHA_224:
                return digestor_SHA_224(texto);
            case SHA_256:
                return digestor_SHA_256(texto);
            case SHA_384:
                return digestor_SHA_384(texto);
            case SHA_512:
                return digestor_SHA_512(texto);
            case SHA_3_224:
                return digestor_SHA_3_224(texto);
            case SHA_3_256:
                return digestor_SHA_3_256(texto);
            case SHA_3_384:
                return digestor_SHA_3_384(texto);
            case SHA_3_512:
                return digestor_SHA_3_512(texto);
            case SKEIN_1024:
                return digestor_Skein_1024(texto);
            case BLAKE2B_160:
                return digestor_Blake2b_160(texto);
            case BLAKE2B_256:
                return digestor_Blake2b_256(texto);
            case BLAKE2B_384:
                return digestor_Blake2b_384(texto);
            case BLAKE2B_512:
                return digestor_Blake2b_512(texto);
            case RIPEMD128:
                return digestor_RIPEMD_128(texto);
            case RIPEMD160:
                return digestor_RIPEMD_160(texto);
            case RIPEMD256:
                return digestor_RIPEMD_256(texto);
            case RIPEMD320:
                return digestor_RIPEMD_320(texto);
        }
        return null;
    }

    /**
     * Gera um hash à partir dos parâmetros de entrada
     *
     * @param algoritmo - String Algoritmo que será utilizado na codificação
     * (Recomenda-se o uso da enum Algoritmos)
     * @param charset - String Codificação do texto de entrada (Recomenda-se o
     * uso da enum Charsets)
     * @param texto - String Texto que será codificado
     * @return hash - byte[] Hash gerado
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     * @see Charsets
     */
    public static byte[] hash(String texto, Algoritmos algoritmo, Charsets charset) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return hash(algoritmo, texto.getBytes(charset.getCharset()));
    }

    /**
     * Gera um hash à partir dos parâmetros de entrada
     *
     * @param arquivo - File Arquivo (".txt") do qual será extraído o texto que
     * será codificado
     * @param algoritmo - String Algoritmo que será utilizado na codificação
     * (Recomenda-se o uso da enum Algoritmos)
     * @param charset - String Codificação do texto existente no arquivo
     * (Recomenda-se o uso da enum Charsets)
     * @return digestor_final - byte[] Hash gerado
     * @throws NoSuchAlgorithmException
     * @throws FileNotFoundException
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @see Algoritmos
     * @see Charsets
     */
    public static byte[] hash(File arquivo, Algoritmos algoritmo, Charsets charset) throws NoSuchAlgorithmException, FileNotFoundException, IOException, UnsupportedEncodingException {
        byte[] buffer;
        String texto;

        buffer = Files.readAllBytes(arquivo.toPath());
        texto = new String(buffer, charset.getCharset());
        return hash(algoritmo, texto.getBytes(charset.getCharset()));
    }
}
