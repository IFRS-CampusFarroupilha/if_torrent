package iftorrent.criptografia;

/**
 * 
 * <b>--Classe que contem os tamanhos válidos/utilizados pela classe
 * Codificador--</b><br>
 * A constante <b>TAMANHO_16</b> constitui o tamanho do bloco (em octetos) sob o
 * qual o algoritmo <b>AES</b> opera, assim como o tamanho do bloco de
 * inicialização do algoritmo de encadeamento de blocos (<b>CBC</b>), os demais
 * (<b>TAMANHO_24</b> e
 * <b>TAMANHO_32</b>) representam (juntamente com <b>TAMANHO_16</b>) os tamanhos
 * possíveis de chave (<b>respectivamente 128, 192 e 256 bits</b>). Caso
 * encontre problemas com chaves de tamanho superior à 128 bits visite 
 * 
 * @see Codificador
 * @author Garren Souza
 */
public class ConstantesCriptografia {

    public static final int TAMAMHO_16 = 16;
    public static final int TAMAMHO_24 = 24;
    public static final int TAMAMHO_32 = 32;
}
