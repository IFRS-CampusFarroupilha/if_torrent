package iftorrent.arquivos;

import iftorrent.criptografia.Charsets;
import iftorrent.excecoes.ESExcecao;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de decodificação de arquivo ".torrent", trata strings como vetores
 * byte, utiliza-se de leitura em bytes
 * <b>(byte[])</b> e valores inteiros como <b>Long</b>
 *
 * @author Garren Souza
 */
public class BDecodificador {

    BDecodificador() {
    }

    /**
     * Método que decodifica um arquivo ".torrent" contido no objeto de tipo
     * <b>File</b> recebido como parâmetro
     *
     * @param arquivo - Objeto do tipo File que contem os dados sobre os quais
     * se deseja realizar a decodificação
     * @return Map - Objeto que contem cada elemento do arquivo associado à uma
     * chave unica (no contexto deste e apenas deste mapa)
     * @throws ESExcecao - Bem como <b>IOException</b>, é lançada np caso de
     * ocorrer um erro durante a leitura de bytes da corrente.
     * @throws IOException - Bem como <b>ESExcecao</b>, é lançada np caso de
     * ocorrer um erro durante a leitura de bytes da corrente.
     */
    public Map decodifica(File arquivo) throws ESExcecao, IOException {
        return this.decodifica(Arquivo.ler_trecho(arquivo, 0, ConstantesArquivos.FINAL_ARQUIVO));
    }

    /**
     * Método que decodifica o arquivo ".torrent" recebido na forma de um vetor
     * de bytes
     *
     * @param dados - byte[] Vetor que contem os dados sobre os quais se deseja
     * realizar a decodificação
     * @return Map - Objeto que contem cada elemento do arquivo associado à uma
     * chave unica (no contexto deste e apenas deste mapa)
     * @throws ESExcecao - Bem como <b>IOException</b>, é lançada np caso de
     * ocorrer um erro durante a leitura de bytes da corrente.
     * @throws IOException - Bem como <b>ESExcecao</b>, é lançada np caso de
     * ocorrer um erro durante a leitura de bytes da corrente.
     */
    public Map decodifica(byte[] dados) throws ESExcecao, IOException {
        return (Map) this.decodifica((InputStream) new ByteArrayInputStream(dados));
    }

    /**
     * Método que decodifica o arquivo ".torrent" recebido através de um objeto
     * do tipo ByteArrayInputStream
     *
     * @param dados - ByteArrayInputStream que contem os dados sobre os quais se
     * deseja realizar a decodificação
     * @return Map - Objeto que contem cada elemento do arquivo associado à uma
     * chave unica (no contexto deste e apenas deste mapa)
     * @throws ESExcecao - Bem como <b>IOException</b>, é lançada np caso de
     * ocorrer um erro durante a leitura de bytes da corrente.
     * @throws IOException - Bem como <b>ESExcecao</b>, é lançada np caso de
     * ocorrer um erro durante a leitura de bytes da corrente.
     */
    public Map decodifica(ByteArrayInputStream dados) throws ESExcecao, IOException {
        return (Map) this.decodifica((InputStream) dados);
    }

    /**
     * Método que decodifica um arquivo ".torrent" recebido através de um objeto
     * do tipo BufferedInputStream
     *
     * @param dados - BufferedInputStream que contem os dados sobre os quais se
     * deseja realizar a decodificação
     * @return Map - Objeto que contem cada elemento do arquivo associado à uma
     * chave unica (no contexto deste e apenas deste mapa)
     * @throws ESExcecao - Bem como <b>IOException</b>, é lançada np caso de
     * ocorrer um erro durante a leitura de bytes da corrente.
     * @throws IOException - Bem como <b>ESExcecao</b>, é lançada np caso de
     * ocorrer um erro durante a leitura de bytes da corrente.
     */
    public Map decodifica(BufferedInputStream dados) throws ESExcecao, IOException {
        return (Map) this.decodifica((InputStream) dados);
    }

    /**
     * Método que decodifica um objeto do tipo InputStream que contem os dados
     * do arquivo ".torrent" do qual se desejam extrair as informações
     *
     * @param dados
     * @return Map - Objeto que contem cada elemento do arquivo associado à uma
     * chave
     * @throws ESExcecao - Bem como <b>IOException</b>, é lançada no caso de
     * ocorrer um erro durante a leitura de bytes do fluxo.
     * @throws IOException - Bem como <b>ESExcecao</b>, é lançada no caso de
     * ocorrer um erro durante a leitura de bytes do fluxo.
     * @see InputStream
     * @see HashMap
     * @see ArrayList
     */
    public Object decodifica(InputStream dados) throws ESExcecao, IOException {
        dados.mark(Integer.MAX_VALUE);
        int inicio = dados.read();

        switch (inicio) {
            case 'd':
                Map mapa = new HashMap();
                Object chave;

                while ((chave = this.decodifica(dados)) != null) {
                    Object conteudo = this.decodifica(dados);
                    mapa.put(new String((byte[]) chave, Charsets.CHARSET_UTF_8.getCharset()), conteudo);
                }
                return mapa;
            case 'l':
                ArrayList lista = new ArrayList();
                Object _temporario;

                while ((_temporario = this.decodifica(dados)) != null) {
                    lista.add(_temporario);
                }
                return lista;
            case 'i':
                return this.getInteiro(dados, 'e');
            case 'e':
                return null;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                dados.reset();
                return this.getString(dados);
            default:
                throw new ESExcecao("ERRO DE CODIFICAÇÃO: " + inicio);
        }
    }

    /**
     * Método que retorna um inteiro contido na corrente de dados recebida como
     * parâmetro, construindo o mesmo à partir do estabelecimento de um limite
     * de leitura, limite este que é definido pelo parâmetro fim
     *
     * @param dados
     * @param fim
     * @return long - valor construído à partir dos parâmetros
     * @throws ESExcecao - Bem como <b>IOException</b>, é lançada np caso de
     * ocorrer um erro durante a leitura de bytes da corrente.
     * @throws IOException - Bem como <b>ESExcecao</b>, é lançada np caso de
     * ocorrer um erro durante a leitura de bytes da corrente.
     * @see StringBuilder
     * @see byte
     */
    public long getInteiro(InputStream dados, char fim) throws IOException, ESExcecao {
        StringBuilder numero = new StringBuilder();
        char temporario = (char) dados.read();

        while (temporario != fim) {
            numero.append(temporario);
            temporario = (char) dados.read();
        }
        return Long.parseLong(numero.toString());
    }

    /**
     * Método que converte um determinado trecho da inputStream em um vetor de
     * bytes com o objetivo de preservar a codificação
     *
     * @param dados InputStream - Corrente da qual se deseja retirar uma string
     * @return texto - byte[] - texto em forma de vetor byte
     * @throws ESExcecao - Bem como <b>IOException</b>, é lançada np caso de
     * ocorrer um erro durante a leitura de bytes da corrente.
     * @throws IOException - Bem como <b>ESExcecao</b>, é lançada np caso de
     * ocorrer um erro durante a leitura de bytes da corrente.
     */
    public byte[] getString(InputStream dados) throws IOException, ESExcecao {
        int tamanho = (int) this.getInteiro(dados, ':');

        if (tamanho < 0) {
            return null;
        }
        byte[] texto = new byte[tamanho];
        dados.read(texto, 0, tamanho);
        return texto;
    }

    /**
     * Método que garante a integridade aparente do arquivo com base no trecho
     * contido pelo subdicionario de informações que se encontra sob a chave
     * "info" através de <b>checagem de comprimento da sequencia de caracteres
     * que contem as assinaturas hash (SHA-1) de cada "chunk"</b>, conferindo
     * apenas se o comprimento condiz com o modulo 20, alem de <b>checar a
     * existência de alguma irregularidade na codificação do arquivo</b> (<b>e.g.</b> literais com tamanho
     * inconsistente -> "3:abcd").
     *
     * @param arquivo Instância de File que contem o arquivo o qual se deseja
     * expor aos testes
     * @return boolean - Retorna verdadeiro se e apenas se não houver erro na
     * codificação das informações  e o comprimento da cadeia de caracteres que
     * representa as assinaturas hash em questão possuir um tamanho válido
     *
     */
    public boolean checa_integridade(File arquivo) {
        BDecodificador decoder = new BDecodificador();
        Map mapa;
        try {
            mapa = decoder.decodifica(arquivo);
        } catch (ESExcecao ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        return (((byte[]) ((Map) mapa.get("info")).get("pieces")).length % 20) == 0;
    }
}
