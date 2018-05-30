package iftorrent.arquivos;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Classe de codificação (Bencode)
 * @author Garren Souza
 */
public class BCodificador {

    /**
     * Método que transforma um objeto de tipo Map em um vetor de bytes que
     * contem os dados em formato Bencode
     *
     * @param mapa Objeto de tipo Map que contem os dados á serem convertidos em
     * um vetor de bytes[]
     * @return byte[] - vetor de bytes contendo os dados do <b>mapa</b> em
     * codificação Bencode
     * @throws IOException - Exceção lançada em caso de erro durante escrita no
     * objeto de tipo ByteArrayInputStream
     */
    public byte[] codifica(Map mapa) throws IOException {
        ByteArrayOutputStream dados = new ByteArrayOutputStream();
        this.codifica(dados, mapa);
        return dados.toByteArray();
    }

    /**
     * Método de codificação de dados (Bencode), mapas devem ter chaves
     * numéricas, sendo que a ordem dos elementos será de acordo com a ordenação
     * das mesmas de maneira crescente
     *
     * @param dados - Objeto do tipo ByteArrayInputStream no qual se deseja
     * incluir os dados codificados
     * @param objeto - Objeto que contem os dados que serão codificados
     * @throws IOException
     */
    public void codifica(ByteArrayOutputStream dados, Object objeto) throws IOException {
        if (objeto instanceof String) {
            String texto = (String) objeto;
            dados.write((texto.length() + ':' + texto).getBytes());
        } else if (objeto instanceof Long) {
            dados.write(('i' + String.valueOf(((Long) objeto)) + 'e').getBytes());
        } else if (objeto instanceof List) {
            dados.write('l');
            for (int i = 0; i < ((List) objeto).size(); i++) {
                this.codifica(dados, ((List) objeto).get(i));
            }
            dados.write('e');
        } else if (objeto instanceof byte[]) {
            dados.write(((byte[]) objeto).length);
            dados.write(':');
            dados.write((byte[]) objeto);
        } else if (objeto instanceof Map) {
            Map mapa = (Map) objeto;
            TreeMap ordenador;

            dados.write('d');
            if (mapa instanceof TreeMap) {
                ordenador = (TreeMap) mapa;
            } else {
                ordenador = new TreeMap(mapa);
            }
            List chaves = new ArrayList(ordenador.values());
            for (Object chave : chaves) {
                this.codifica(dados, mapa.get(chave));
            }
            dados.write('e');

        }
    }
}
