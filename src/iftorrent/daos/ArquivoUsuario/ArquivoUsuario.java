package iftorrent.daos.ArquivoUsuario;

/**
 *
 * @author Rafael Coelho
 */
public class ArquivoUsuario {
    private int id_arquivo;
    private String id_usuario;

    public ArquivoUsuario(int id_arquivo, String id_usuario) {
        this.id_arquivo = id_arquivo;
        this.id_usuario = id_usuario;
    }

    public int getId_arquivo() {
        return id_arquivo;
    }

    public void setId_arquivo(int id_arquivo) {
        this.id_arquivo = id_arquivo;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
}
