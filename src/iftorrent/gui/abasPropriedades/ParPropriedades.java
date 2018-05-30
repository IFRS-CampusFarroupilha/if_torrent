package iftorrent.gui.abasPropriedades;

import com.turn.ttorrent.client.peer.SharingPeer;

public class ParPropriedades {
    
    private int numero;
    private String ip;
    private int porta;
    private boolean conectado;
    private boolean nos_bloqueando;
    private boolean bloqueado;
    private String pedaco;
    
    public ParPropriedades(SharingPeer par){
        this.numero = par.hashCode();
        this.ip = par.getIp();
        this.porta = par.getPort();
        this.conectado = par.isConnected();
        this.nos_bloqueando = par.isChoked();
        this.bloqueado = par.isChoking();
        this.pedaco = "";
        if(par.getRequestedPiece() != null){
            this.pedaco = Integer.toString(par.getRequestedPiece().getIndex());
        }
    }

    public ParPropriedades(int numero, String ip, int porta, boolean conectado, boolean nos_bloqueando, boolean bloqueado, String pedaco) {
        this.numero = numero;
        this.ip = ip;
        this.porta = porta;
        this.conectado = conectado;
        this.nos_bloqueando = nos_bloqueando;
        this.bloqueado = bloqueado;
        this.pedaco = pedaco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public String getEstado() {
        return conectado ? "Conectado" : "Desconectado";
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public String getPode_baixar() {
        return nos_bloqueando ? "Não" : "Sim";
    }

    public void setNos_bloqueando(boolean nos_bloqueando) {
        this.nos_bloqueando = nos_bloqueando;
    }

    public String getPode_enviar() {
        return bloqueado ? "Não" : "Sim"; 
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getPedaco() {
        return pedaco;
    }

    public void setPedaco(String pedaco) {
        this.pedaco = pedaco;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.numero;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ParPropriedades other = (ParPropriedades) obj;
        return this.numero == other.numero;
    }

    public void atualizar(ParPropriedades par){
        this.bloqueado = par.bloqueado;
        this.conectado = par.conectado;
        this.ip = par.getIp();
        this.nos_bloqueando = par.nos_bloqueando;
        this.numero = par.getNumero();
        this.pedaco = par.getPedaco();
        this.porta = par.getPorta();
    }
    
}
