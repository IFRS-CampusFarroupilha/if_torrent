    package iftorrent.conexao.par;

/**
 *
 * @author Garren Souza
 */
public class Requisicao {

    private final int TEMPO_DE_VIDA = 60 * 1000;
    private final int numero_pedaco;
    private final int tamanho_pedaco;
    private final int conpensacao;
    private int nascimento;

    public Requisicao(int numero_pedaco, int compesacao, int tamanho) {
        this.numero_pedaco = numero_pedaco;
        this.conpensacao = compesacao;
        this.tamanho_pedaco = tamanho;
        this.nascimento = (int) System.currentTimeMillis();
    }

    public boolean eh_valida() {
        return ((System.currentTimeMillis() - this.nascimento) <= this.TEMPO_DE_VIDA);
    }

    public int getNumero() {
        return this.numero_pedaco;
    }

    public int getTamanho() {
        return this.tamanho_pedaco;
    }

    public int getCompensacao() {
        return this.conpensacao;
    }
    
    public void aumenta_vida(){
        this.nascimento = (int) System.currentTimeMillis();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null
                || !(obj instanceof Requisicao)) {
            return false;
        }
        final Requisicao other = (Requisicao) obj;

        return !(this.TEMPO_DE_VIDA != other.TEMPO_DE_VIDA
                || this.numero_pedaco != other.numero_pedaco
                || this.tamanho_pedaco != other.tamanho_pedaco
                || this.conpensacao != other.conpensacao
                || this.nascimento != other.nascimento);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.TEMPO_DE_VIDA;
        hash = 37 * hash + this.numero_pedaco;
        hash = 37 * hash + this.tamanho_pedaco;
        hash = 37 * hash + this.conpensacao;
        hash = 37 * hash + this.nascimento;
        return hash;
    }

}
