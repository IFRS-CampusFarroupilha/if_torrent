package iftorrent.gui.janelaConfiguracoes;

import java.io.Serializable;

/**
 *
 * @author Leonardo Bortolini
 */
public class Diretorio implements Serializable{
    private String diretorio;

    public Diretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }
}
