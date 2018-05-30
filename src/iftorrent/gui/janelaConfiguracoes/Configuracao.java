/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iftorrent.gui.janelaConfiguracoes;

import java.io.Serializable;

/**
 *
 * @author 05200195
 */
public class Configuracao implements Serializable{
    private double download;
    private double upload;
    private boolean segundo_plano, ip, nome, usuario;

    public Configuracao(double download, double upload, boolean segundo_plano, boolean ip, boolean nome, boolean usuario) {
        this.download = download;
        this.upload = upload;
        this.segundo_plano = segundo_plano;
        this.ip = ip;
        this.nome = nome;
        this.usuario = usuario;
    }

    public double getDownload() {
        return download;
    }

    public void setDownload(double download) {
        this.download = download;
    }

    public double getUpload() {
        return upload;
    }

    public void setUpload(double upload) {
        this.upload = upload;
    }

    public boolean isSegundo_plano() {
        return segundo_plano;
    }

    public void setSegundo_plano(boolean segundo_plano) {
        this.segundo_plano = segundo_plano;
    }

    public boolean isIp() {
        return ip;
    }

    public void setIp(boolean ip) {
        this.ip = ip;
    }

    public boolean isNome() {
        return nome;
    }

    public void setNome(boolean nome) {
        this.nome = nome;
    }

    public boolean isUsuario() {
        return usuario;
    }

    public void setUsuario(boolean usuario) {
        this.usuario = usuario;
    }

    
}
