package iftorrent.criptografia;

import iftorrent.arquivos.ManipuladorTexto;
import static iftorrent.arquivos.Modo.LEITURA;
import static iftorrent.criptografia.Hash.digestor_SHA_3_512;
import iftorrent.excecoes.ESExcecao;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author Guilherme Giordani
 */

public class IntegridadeSHA1 {
    
    public byte[] arquivo_para_hash(File arquivo) throws ESExcecao, UnsupportedEncodingException{
        ManipuladorTexto manipulador_arquivo = new ManipuladorTexto(arquivo, LEITURA);
        String conteudo_arquivo[] = manipulador_arquivo.ler_texto();
        String conteudo_arquivo_total = null;
        byte[] hash_arquivo = null;

        for (int i = 0; i < conteudo_arquivo.length; i++) {
            conteudo_arquivo_total += conteudo_arquivo[i];
        }
        hash_arquivo = digestor_SHA_3_512(conteudo_arquivo_total.getBytes(Charsets.CHARSET_UTF_8.getCharset()));
        return base64(hash_arquivo);
    }
    
    public byte[] string_para_hash(String string) throws UnsupportedEncodingException{
        byte[] hash_string = null;
        
        hash_string = digestor_SHA_3_512(string.getBytes(Charsets.CHARSET_UTF_8.getCharset()));
        return base64(hash_string);
    }
    
    public byte[] bytes_para_hash(byte[] bytes){
        return base64(digestor_SHA_3_512(bytes));
    }

    public boolean integridade_hashXarquivo(byte[] hash, File arquivo) throws ESExcecao, UnsupportedEncodingException {
        return arquivo_para_hash(arquivo).equals(hash);
    }
    
    public boolean integridade_arquivoXarquivo(File arquivo1, File arquivo2) throws ESExcecao, UnsupportedEncodingException {
        return arquivo_para_hash(arquivo1).equals(arquivo_para_hash(arquivo2));
    }
    
    public byte[] base64(byte[] vetor){
        return Base64.getEncoder().encode(vetor);
    }
}
