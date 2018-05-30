package iftorrent.criptografia;

/**
 * Enum que representa os algoritmos de hash
 * @author Rafael Coelho
 * @author Garren Souza
 * 
 */
public enum Algoritmos {
        MD2("MD2"),
        MD4("MD4"),
        MD5("MD5"),
        SHA_1("SHA-1"),
        SHA_224("SHA-224"),
        SHA_256("SHA-256"),
        SHA_384("SHA-384"),
        SHA_512("SHA-512"),
        SHA_3_224("SHA-3.224"),
        SHA_3_256("SHA-3.256"),
        SHA_3_384("SHA-3.384"),
        SHA_3_512("SHA-3.512"),
        SKEIN_1024("Skein-1024"),
        BLAKE2B_160("Blake2b_160"),
        BLAKE2B_256("Blake2b_256"),
        BLAKE2B_384("Blake2b_384"),
        BLAKE2B_512("Blake2b_512"),
        RIPEMD128("RIPEMD128"),
        RIPEMD160("RIPEMD160"),
        RIPEMD256("RIPEMD256"),
        RIPEMD320("RIPEMD320");
        String algoritmo;

        public String getAlgoritmo() {
            return algoritmo;
        }

        Algoritmos(String algoritmo) {
            this.algoritmo = algoritmo;
        }
    
}
