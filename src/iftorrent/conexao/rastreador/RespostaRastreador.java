package iftorrent.conexao.rastreador;

import iftorrent.conexao.gerenciamento.Par;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

/**Classe que representa uma resposta de um "tracker"(rastreador)
 *
 * @author Garren Souza
 */
public class RespostaRastreador {

    private final boolean sucesso;
    private final Optional<Throwable> error;
  
    private String mensagem_erro;
    private String mensagem_aviso;
    private int intervalo;
    private int intervalo_min;
    private Optional<byte[]> id_tracker;
    private int qntd_seeder;
    private int qntd_leecher;

    private Iterable<Par> pares;
    
    public RespostaRastreador(boolean sucesso, Throwable erro){
        this.sucesso = sucesso;
        this.error = Optional.ofNullable(erro);
        this.id_tracker = Optional.empty();
        this.pares = Collections.emptyList();
    }
    
    public RespostaRastreador(String mensagem_erro){
        this(false, null);
        this.mensagem_erro = mensagem_erro;
    }
    
    protected RespostaRastreador(Throwable erro) {
        this(false, Objects.requireNonNull(erro));
    }

    public String getMensagem_erro() {
        return mensagem_erro;
    }

    public void setMensagem_erro(String mensagem_erro) {
        this.mensagem_erro = mensagem_erro;
    }

    public String getMensagem_aviso() {
        return mensagem_aviso;
    }

    public void setMensagem_aviso(String mensagem_aviso) {
        this.mensagem_aviso = mensagem_aviso;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public int getIntervalo_min() {
        return intervalo_min;
    }

    public void setIntervalo_min(int intervalo_min) {
        this.intervalo_min = intervalo_min;
    }

    public Optional<byte[]> getTrackerId() {
        return id_tracker;
    }

    public void setTrackerId(Optional<byte[]> trackerId) {
        this.id_tracker = trackerId;
    }

    public int getQntd_seeder() {
        return qntd_seeder;
    }

    public void setQntd_seeder(int qntd_seeder) {
        this.qntd_seeder = qntd_seeder;
    }

    public int getQntd_leecher() {
        return qntd_leecher;
    }

    public void setQntd_leecher(int qntd_leecher) {
        this.qntd_leecher = qntd_leecher;
    }

    public Iterable<Par> getPares() {
        return pares;
    }

    public void setPares(Iterable<Par> pares) {
        this.pares = pares;
    }
}
