package iftorrent.texto;

import java.util.Arrays;

/**
 *
 * @author Sérgio Brunetta Júnior
 * @author Otávio Farinon
 */
public class StringCustom {

    /**
     *
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     *
     */
    private char[] string;

    /**
     *
     */
    private int tamanho;

    /**
     * Constrói um objeto StringCustom sem caracteres e uma capacidade inicial
     * de 16 caracteres.
     */
    public StringCustom() {
        string = new char[16];
        tamanho = 0;
    }

    /**
     * Constrói um objeto StringCustom sem caracteres e uma capacidade inicial
     * especificada pelo argumento {@code capacidade}.
     *
     * @param capacidade a capacidade inicial.
     * @throws NegativeArraySizeException se a {@code capacidade} for inferior a
     * {@code 0}.
     */
    public StringCustom(int capacidade) {
        string = new char[capacidade];
        tamanho = 0;
    }

    /**
     * Constrói um objeto StringCustom com o conteúdo inicial igual ao de
     * {@code str}. A capacidade inicial de StringCustom é {@code 16} mais o
     * comprimento de {@code str}.
     *
     * @param str O conteúdo inicial.
     */
    public StringCustom(String str) {
        this(str.length() + 16);
        concatenar(str);
    }

    /**
     * Constrói um objeto StringCustom com o conteúdo inicial igual ao de
     * {@code seq}. A capacidade inicial de StringCustom é {@code 16} mais o
     * comprimento de {@code seq}.
     *
     * @param seq O conteúdo inicial.
     */
    public StringCustom(CharSequence seq) {
        this(seq.length() + 16);
        concatenar(seq);
    }

    /**
     * Constrói um objeto StringCustom com o conteúdo inicial igual ao de
     * {@code str}. A capacidade inicial de StringCustom é {@code 16} mais o
     * comprimento de {@code str}.
     *
     * @param str O conteúdo inicial.
     */
    public StringCustom(StringCustom str) {
        this(str.tamanho() + 16);
        concatenar(str);
    }

    /**
     * Retorna o tamanho (contagem de caracteres).
     *
     * @return O tamanho atual.
     */
    public int tamanho() {
        return this.tamanho;
    }

    /**
     * Retorna a capacidade atual. A capacidade é a quantidade de armazenamento
     * disponível para caracteres.
     *
     * @return A capacidade atual
     */
    public int capacidade() {
        return string.length;
    }

    /**
     *
     * @param capacidadeMinima
     */
    public void garantirCapacidade(int capacidadeMinima) {
        if (capacidadeMinima > 0) {
            garantirCapacidadeInterna(capacidadeMinima);
        }
    }

    private void garantirCapacidadeInterna(int capacidadeMinima) {
        // overflow-conscious code
        if (capacidadeMinima - string.length > 0) {
            string = Arrays.copyOf(string,
                    novaCapaciade(capacidadeMinima));
        }
    }

    private int novaCapaciade(int capacidadeMinima) {
        // overflow-conscious code
        int nova = (string.length << 1) + 2;

        if (nova - capacidadeMinima < 0) {
            nova = capacidadeMinima;
        }
        return (nova <= 0 || MAX_ARRAY_SIZE - nova < 0)
                ? enormeCapacidade(capacidadeMinima)
                : nova;
    }

    private int enormeCapacidade(int capacidadeMinima) {
        if (Integer.MAX_VALUE - capacidadeMinima < 0) { // overflow
            throw new OutOfMemoryError();
        }
        return (capacidadeMinima > MAX_ARRAY_SIZE)
                ? capacidadeMinima : MAX_ARRAY_SIZE;
    }

    /**
     * Remove todos espaços não utilizados presentes no final da sequência de
     * caracteres, deixando a capacidade da sequência igual ao tamanho da mesma.
     */
    public void removerEspacos() {
        if (tamanho < string.length) {
            string = Arrays.copyOf(string, tamanho);
        }
    }

    public void setTamanho(int novo_tamanho) {
        if (tamanho < 0) {
            throw new StringIndexOutOfBoundsException(tamanho);
        }
        garantirCapacidadeInterna(tamanho);
        if (this.tamanho < tamanho) {
            Arrays.fill(string, this.tamanho, tamanho, '\0');
        }
        this.tamanho = tamanho;
    }

    /**
     * Retorna o caractere presente em uma posição específica.
     *
     * @param pos A posição do valor {@code char} desejado.
     * @return O valor {@code char} no índice especificado.
     * @throws IndexOutOfBoundsException Se {@code pos} for negativo ou maior ou
     * igual a {@code tamanho()}.
     */
    public char caractereNa(int pos) {
        if ((pos < 0) || (pos >= tamanho)) {
            throw new StringIndexOutOfBoundsException(pos);
        }
        return string[pos];
    }

    /**
     *
     * @param pos
     * @return
     */
    public int codePointAt(int pos) {
        if ((pos < 0) || (pos >= tamanho)) {
            throw new StringIndexOutOfBoundsException(pos);
        }
        return Character.codePointAt(string, pos);
    }

    /**
     *
     * @param pos
     * @return
     */
    public int codePointAntes(int pos) {
        int i = pos - 1;

        if ((i < 0) || (i >= tamanho)) {
            throw new StringIndexOutOfBoundsException(pos);
        }
        return codePointAt(i);
    }

    /**
     *
     * @param inicio
     * @param fim
     * @return
     */
    public int codePointCount(int inicio, int fim) {
        if (inicio < 0 || fim > tamanho || inicio > fim) {
            throw new IndexOutOfBoundsException();
        }
        return Character.codePointCount(string, inicio, fim - inicio);
    }

    /**
     *
     * @param pos
     * @param codePointOffset
     * @return
     */
    public int offsetByCodePoints(int pos, int codePointOffset) {
        if (pos < 0 || pos > tamanho) {
            throw new IndexOutOfBoundsException();
        }
        return Character.offsetByCodePoints(string, 0, tamanho, pos, codePointOffset);
    }

    /**
     *
     * @param inicio
     * @param fim
     * @param destino
     * @param inicio_destino
     */
    public void getChars(int inicio, int fim, char[] destino, int inicio_destino) {
        if (inicio < 0) {
            throw new StringIndexOutOfBoundsException(inicio);
        }
        if ((fim < 0) || (fim > tamanho)) {
            throw new StringIndexOutOfBoundsException(fim);
        }
        if (inicio > fim) {
            throw new StringIndexOutOfBoundsException("srcBegin > srcEnd");
        }
        System.arraycopy(string, inicio, destino, inicio_destino, fim - inicio);
    }

    /**
     * O caractere na posição especificada será alterado para {@code ch}. O
     * argumento do índice deve ser maior ou igual a {@code 0}, e menor do que o
     * {@code tamanho()} dessa seqüência.
     *
     * @param pos A posição do caractere a ser modificado.
     * @param ch O novo valor a ser inserido em {@code pos}.
     * @throws IndexOutOfBoundsException Se {@code pos} for negativo ou maior ou
     * igual a {@code tamanho()}.
     */
    public void alterarCaractere(int pos, char ch) {
        if ((pos < 0) || (pos >= tamanho)) {
            throw new StringIndexOutOfBoundsException(pos);
        }
        string[pos] = ch;
    }

    /**
     *
     * @param obj
     * @return
     */
    public StringCustom concatenar(Object obj) {
        return concatenar(String.valueOf(obj));
    }

    /**
     *
     * @param str
     * @return
     */
    public StringCustom concatenar(String str) {
        if (str == null) {
            return concatenaNull();
        }
        int t = str.length();

        garantirCapacidadeInterna(tamanho + t);
        str.getChars(0, t, string, tamanho);
        tamanho += t;
        return this;
    }

    /**
     *
     * @param sb
     * @return
     */
    public StringCustom concatenar(StringBuffer sb) {
        if (sb == null) {
            return concatenaNull();
        }
        int t = sb.length();

        garantirCapacidadeInterna(tamanho + t);
        sb.getChars(0, t, string, tamanho);
        tamanho += t;

        return this;
    }

    /**
     * Concatena ao final da sequência de caracteres, o valor presente em
     * {@code custom}.
     *
     * @param custom O conteúdo a ser concatenado.
     * @return Esta StringCustom modificada.
     */
    public StringCustom concatenar(StringCustom custom) {
        if (custom == null) {
            return concatenaNull();
        }
        int t = custom.tamanho();

        garantirCapacidadeInterna(tamanho + t);
        custom.getChars(0, t, string, tamanho);
        tamanho += t;

        return this;
    }

    /**
     * Concatena ao final da sequência de caracteres, o valor presente em
     * {@code seq}.
     *
     * @param seq O valor a ser inserido.
     * @return Esta StringCustom modificada.
     */
    public StringCustom concatenar(CharSequence seq) {
        if (seq == null) {
            return concatenaNull();
        }
        if (seq instanceof String) {
            return this.concatenar((String) seq);
        }
        if (seq instanceof StringCustom) {
            return this.concatenar((StringCustom) seq);
        }
        return this.concatenar(seq, 0, seq.length());
    }

    /**
     * Concatena ao final da StringCustom atual o valor {@code null}.
     *
     * @return Esta StringCustom modificada
     */
    private StringCustom concatenaNull() {
        int c = tamanho;

        garantirCapacidadeInterna(c + 4);
        string[c++] = 'n';
        string[c++] = 'u';
        string[c++] = 'l';
        string[c++] = 'l';
        tamanho = c;

        return this;
    }

    /**
     *
     * @param seq
     * @param inicio
     * @param fim
     * @return
     */
    public StringCustom concatenar(CharSequence seq, int inicio, int fim) {
        if (seq == null) {
            seq = "null";
        }
        if ((inicio < 0) || (inicio > fim) || (fim > seq.length())) {
            throw new IndexOutOfBoundsException(
                    "inicio " + inicio + ", fim " + fim + ", s.tamanho() "
                    + seq.length());
        }
        int t = fim - inicio;

        garantirCapacidadeInterna(tamanho + t);
        for (int i = inicio, j = tamanho; i < fim; i++, j++) {
            string[j] = seq.charAt(i);

        }
        tamanho += t;

        return this;
    }

    /**
     * Concatena ao final deste objeto StringCustom, o conteúdo presente em um
     * vetor de caracteres.
     *
     * @param str O conteúdo a ser concatenado.
     * @return Esta StringCustom modificada.
     */
    public StringCustom concatenar(char[] str) {
        int t = str.length;

        garantirCapacidadeInterna(tamanho + t);
        System.arraycopy(str, 0, string, tamanho, t);
        tamanho += t;

        return this;
    }

    /**
     *
     * @param str
     * @param pos
     * @param t
     * @return
     */
    public StringCustom concatenar(char str[], int pos, int t) {
        if (t > 0) {
            garantirCapacidadeInterna(tamanho + t);
        }
        System.arraycopy(str, pos, string, tamanho, t);
        tamanho += t;

        return this;
    }

    /**
     * Concatena ao final da sequência de caracteres, o valor presente em
     * {@code b}.
     *
     * @param b O valor {@code boolean} a ser inserido.
     * @return Esta StringCustom modificada.
     */
    public StringCustom concatenar(boolean b) {
        if (b) {
            garantirCapacidadeInterna(tamanho + 4);
            string[tamanho++] = 't';
            string[tamanho++] = 'r';
            string[tamanho++] = 'u';
            string[tamanho++] = 'e';

        } else {
            garantirCapacidadeInterna(tamanho + 5);
            string[tamanho++] = 'f';
            string[tamanho++] = 'a';
            string[tamanho++] = 'l';
            string[tamanho++] = 's';
            string[tamanho++] = 'e';

        }
        return this;
    }

    /**
     * Concatena ao final da sequência de caracteres, o valor presente em
     * {@code c}.
     *
     * @param caractere O caractere a ser concatenado.
     * @return Esta StringCustom modificada.
     */
    public StringCustom concatenar(char caractere) {
        garantirCapacidadeInterna(tamanho + 1);
        string[tamanho++] = caractere;

        return this;
    }

    /**
     * Concatena ao final da sequência de caracteres, o valor presente em
     * {@code i}.
     *
     * @param i O valor {@code int} a ser concatenado.
     * @return Esta StringCustom modificada.
     */
    public StringCustom concatenar(int i) {
        concatenar(Integer.toString(i));
        return this;
    }

    /**
     * Concatena ao final da sequência de caracteres, o valor presente em
     * {@code l}.
     *
     * @param l O valor {@code long} a ser concatenado.
     * @return Esta StringCustom modificada.
     */
    public StringCustom concatenar(long l) {
        concatenar(Long.toString(l));
        return this;
    }

    /**
     * Concatena ao final da sequência de caracteres, o valor presente em
     * {@code f}.
     *
     * @param f O valor {@code float} a ser concatenado.
     * @return Esta StringCustom modificada.
     */
    public StringCustom concatenar(float f) {
        concatenar(Float.toString(f));
        return this;
    }

    /**
     * Concatena ao final da sequência de caracteres, o valor presente em
     * {@code d}.
     *
     * @param d O valor {@code double} a ser concatenado.
     * @return Esta StringCustom modificada.
     */
    public StringCustom concatenar(double d) {
        concatenar(Double.toString(d));
        return this;
    }

    /**
     * Remove os caracteres em um determinado intervalo.
     *
     * @param inicio A posição inicial para remoção, inclusivo.
     * @param fim A posição final para remoção, exclusivo.
     * @return Esta StringCustom modficada.
     * @throws StringIndexOutOfBoundsException Se {@code inicio} for negativo,
     * maior que {@code tamanho()}, ou maior do que {@code fim}.
     */
    public StringCustom deletar(int inicio, int fim) {
        if (inicio < 0) {
            throw new StringIndexOutOfBoundsException(inicio);
        }
        if (fim > tamanho) {
            fim = tamanho;

        }
        if (inicio > fim) {
            throw new StringIndexOutOfBoundsException();
        }
        int t = fim - inicio;

        if (t > 0) {
            System.arraycopy(string, inicio + t, string, inicio, tamanho - fim);
            tamanho -= t;

        }
        return this;
    }

    /**
     * Remove um caractere em uma posição específica.
     *
     * @param posicao A posição do caractere a ser removido.
     * @return Esta StringCustom modificada.
     * @throws StringIndexOutOfBoundsException Se a {@code posicao} for negativa
     * ou maior ou igual a {@code tamanho()}.
     */
    public StringCustom deletarCaractereNa(int posicao) {
        if ((posicao < 0) || (posicao >= tamanho)) {
            throw new StringIndexOutOfBoundsException(posicao);
        }
        System.arraycopy(string, posicao + 1, string, posicao, tamanho - posicao - 1);        
        tamanho -= 1;
        return this;
    }

    /**
     *
     * @param inicio
     * @param fim
     * @param str
     * @return
     */
    public StringCustom alterar(int inicio, int fim, String str) {
        if (inicio < 0) {
            throw new StringIndexOutOfBoundsException(inicio);
        }
        if (inicio > this.tamanho) {
            throw new StringIndexOutOfBoundsException("inicio > tamanho()");
        }
        if (inicio > fim) {
            throw new StringIndexOutOfBoundsException("inicio > fim");
        }

        if (fim > this.tamanho) {
            fim = this.tamanho;
        }
        int t = str.length();
        int novoTamanho = this.tamanho + t - (fim - inicio);

        garantirCapacidadeInterna(novoTamanho);
        System.arraycopy(this.string, fim, this.string, inicio + t, this.tamanho - fim);
        str.getChars(0, str.length(), string, inicio);
        this.tamanho = novoTamanho;

        return this;
    }

    /**
     *
     * @param inicio
     * @return
     */
    public String substring(int inicio) {
        return substring(inicio, tamanho);
    }

    /**
     *
     * @param inicio
     * @param fim
     * @return
     */
    public CharSequence subsequencia(int inicio, int fim) {
        return substring(inicio, fim);
    }

    /**
     *
     * @param inicio
     * @param fim
     * @return
     */
    public String substring(int inicio, int fim) {
        if (inicio < 0) {
            throw new StringIndexOutOfBoundsException(inicio);
        }
        if (fim > tamanho) {
            throw new StringIndexOutOfBoundsException(fim);
        }
        if (inicio > fim) {
            throw new StringIndexOutOfBoundsException(fim - inicio);
        }
        return new String(string, inicio, fim - inicio);
    }

    /**
     *
     * @param pos
     * @param vet
     * @param posicao_primeiro_elemento
     * @param quantidade_elementos
     * @return
     */
    public StringCustom inserir(int pos, char[] vet, int posicao_primeiro_elemento, int quantidade_elementos) {
        if ((pos < 0) || (pos > tamanho())) {
            throw new StringIndexOutOfBoundsException(pos);
        }
        if ((posicao_primeiro_elemento < 0) || (quantidade_elementos < 0) || (posicao_primeiro_elemento > vet.length - quantidade_elementos)) {
            throw new StringIndexOutOfBoundsException(
                    "offset " + posicao_primeiro_elemento + ", len " + quantidade_elementos + ", str.length "
                    + vet.length);
        }
        garantirCapacidadeInterna(tamanho + quantidade_elementos);
        System.arraycopy(string, pos, string, pos + quantidade_elementos, tamanho - pos);
        System.arraycopy(vet, posicao_primeiro_elemento, string, pos, quantidade_elementos);
        tamanho += quantidade_elementos;
        return this;
    }

    /**
     *
     * @param pos
     * @param obj
     * @return
     */
    public StringCustom inserir(int pos, Object obj) {
        return inserir(pos, String.valueOf(obj));
    }

    /**
     *
     * @param pos
     * @param str
     * @return
     */
    public StringCustom inserir(int pos, String str) {
        if ((pos < 0) || (pos > tamanho())) {
            throw new StringIndexOutOfBoundsException(pos);
        }
        if (str == null) {
            str = "null";

        }
        int t = str.length();

        garantirCapacidadeInterna(tamanho + t);
        System.arraycopy(this.string, pos, this.string, pos + t, tamanho - pos);
        str.getChars(0, str.length(), string, pos);
        tamanho += t;

        return this;
    }

    /**
     *
     * @param pos
     * @param vet
     * @return
     */
    public StringCustom inserir(int pos, char[] vet) {
        if ((pos < 0) || (pos > tamanho())) {
            throw new StringIndexOutOfBoundsException(pos);
        }
        int t = vet.length;

        garantirCapacidadeInterna(tamanho + t);
        System.arraycopy(this.string, pos, this.string, pos + t, tamanho - pos);
        //System.arraycopy(string, 0, this.string, posicao, t);
        tamanho += t;

        return this;
    }

    /**
     *
     * @param pos
     * @param seq
     * @return
     */
    public StringCustom inserir(int pos, CharSequence seq) {
        if (seq == null) {
            seq = "null";

        }
        if (seq instanceof String) {
            return inserir(pos, (String) seq);
        }
        return inserir(pos, seq, 0, seq.length());
    }

    /**
     *
     * @param pos
     * @param seq
     * @param inicio_seq
     * @param fim_seq
     * @return
     */
    public StringCustom inserir(int pos, CharSequence seq, int inicio_seq, int fim_seq) {
        if (seq == null) {
            seq = "null";

        }
        if ((pos < 0) || (pos > tamanho())) {
            throw new IndexOutOfBoundsException("dstOffset " + pos);
        }
        if ((inicio_seq < 0) || (fim_seq < 0) || (inicio_seq > fim_seq) || (fim_seq > seq.length())) {
            throw new IndexOutOfBoundsException(
                    "start " + inicio_seq + ", end " + fim_seq + ", s.length() "
                    + seq.length());
        }
        int t = fim_seq - inicio_seq;

        garantirCapacidadeInterna(tamanho + t);
        System.arraycopy(string, pos, string, pos + t,
                tamanho - pos);
        for (int i = inicio_seq; i < fim_seq; i++) {
            string[pos++] = seq.charAt(i);

        }
        tamanho += t;

        return this;
    }

    /**
     *
     * @param posicao
     * @param b
     * @return
     */
    public StringCustom inserir(int posicao, boolean b) {
        return inserir(posicao, String.valueOf(b));
    }

    /**
     *
     * @param pos
     * @param c
     * @return
     */
    public StringCustom inserir(int pos, char c) {
        garantirCapacidadeInterna(tamanho + 1);
        System.arraycopy(string, pos, string, pos + 1, tamanho - pos);
        string[pos] = c;
        tamanho += 1;

        return this;
    }

    /**
     *
     * @param pos
     * @param i
     * @return
     */
    public StringCustom inserir(int pos, int i) {
        return inserir(pos, String.valueOf(i));
    }

    /**
     *
     * @param pos
     * @param l
     * @return
     */
    public StringCustom inserir(int pos, long l) {
        return inserir(pos, Long.toString(l));
    }

    /**
     *
     * @param pos
     * @param f
     * @return
     */
    public StringCustom inserir(int pos, float f) {
        return inserir(pos, Float.toString(f));
    }

    /**
     *
     * @param pos
     * @param d
     * @return
     */
    public StringCustom inserir(int pos, double d) {
        return inserir(pos, Double.toString(d));
    }

    /**
     * Retorna a posição da primeira ocorrência de {@code str}.
     *
     * @param str O valor a ser procurado.
     * @return A posição da primeira ocorrência de {code @str}. Caso {@code str}
     * não ocorra, o valor {@code -1} é retornado.
     */
    public int contemNa(String str) {
        return contemNa(str, 0);
    }

    /**
     * Retorna a posição da primeira ocorrência de {@code str} em um intervalo
     * específico de caracteres.
     *
     * @param str O valor a ser procurado.
     * @param apartir_de A posição em que iniciará a busca.
     * @return A posição da primeira ocorrência de {code @str}. Caso {@code str}
     * não ocorra, o valor {@code -1} é retornado.
     */
    public int contemNa(String str, int apartir_de) {
        return new String(string).indexOf(str, apartir_de);
    }

    /**
     *
     *
     * @param str
     * @return
     */
    public int ultimaOcorrenciaDe(String str) {
        return ultimaOcorrenciaDe(str, tamanho);
    }

    /**
     *
     * @param str
     * @param apartir_de
     * @return
     */
    public int ultimaOcorrenciaDe(String str, int apartir_de) {
        return new String(string).lastIndexOf(str, apartir_de);
    }

    /**
     *
     * @return
     */
    public StringCustom inverter() {
        boolean hasSurrogates = false;
        int n = tamanho - 1;

        for (int j = (n - 1) >> 1; j >= 0; j--) {
            int k = n - j;
            char cj = string[j];
            char ck = string[k];
            string[j] = ck;
            string[k] = cj;

            if (Character.isSurrogate(cj)
                    || Character.isSurrogate(ck)) {
                hasSurrogates = true;
            }
        }
        if (hasSurrogates) {
            reverseAllValidSurrogatePairs();
        }
        return this;
    }

    /**
     *
     */
    private void reverseAllValidSurrogatePairs() {
        for (int i = 0; i < tamanho - 1; i++) {
            char c2 = string[i];
            if (Character.isLowSurrogate(c2)) {
                char c1 = string[i + 1];
                if (Character.isHighSurrogate(c1)) {
                    string[i++] = c1;
                    string[i] = c2;
                }
            }
        }
    }

    /**
     *
     * @return
     */
    final char[] getString() {
        return string;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return new String(string).toString();
    }

}
