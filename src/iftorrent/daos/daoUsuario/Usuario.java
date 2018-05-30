package iftorrent.daos.daoUsuario;

import java.util.Arrays;
import java.util.Objects;

/**
 * 
 *
 * A classe <b>Usuario</b> serve para representar uma linha da tabela Usuários
 * do banco de dados.
 *
 * @author Otávio Farinon
 *
 * 
 */
public class Usuario {
    
    /**
     * Um objeto do tipo <b>int</b> que representa a chave primária única do
     * objeto.<br>
     */
    private String nome;

    /**
     * Um objeto do tipo <b>byte[]</b> que representa a senha do usuário.<br>
     */
    private byte[] senha;

    /**
     * 
     *
     * Construtor vazio.
     *
     * 
     */
    public Usuario() {
    }

    /**
     * 
     *
     * Construtor que inicializa todos os atributos.
     *
     * @param nome Um objeto do tipo <b>String</b> que representa a chave
     * primária única do objeto.<br>
     * @param senha Um objeto do tipo <b>String</b> que representa a senha do
     * usuário.<br>
     *
     * 
     */
    public Usuario(String nome, byte[] senha) {
        this.nome = nome;
        this.senha = senha;
    }

    /**
     * 
     *
     * Getter da chave primária do objeto.
     *
     * @return Um inteiro que representa a chave primária do objeto.<br>
     *
     * 
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     *
     * Setter da chave primária do objeto.
     *
     * @param nome representa a chave primária do objeto.<br>
     *
     * 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * 
     *
     * Getter da senha.
     *
     * @return Um objeto do tipo <b>String</b> que representa a senha do
     * usuário.<br>
     *
     * 
     */
    public byte[] getSenha() {
        return senha;
    }

    /**
     * 
     *
     * Setter da senha.
     *
     * @param senha Um objeto do tipo <b>String</b> que representa a senha do
     * usuário.<br>
     *
     * 
     */
    public void setSenha(byte[] senha) {
        this.senha = senha;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.nome);
        hash = 43 * hash + Objects.hashCode(this.senha);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return Arrays.equals(this.senha, other.senha);
    }
    
    
}
