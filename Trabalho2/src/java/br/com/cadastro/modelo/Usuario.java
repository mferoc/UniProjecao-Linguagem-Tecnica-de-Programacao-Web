package br.com.cadastro.modelo;

import java.io.Serializable;

/**
 * Classe que guarda os dados do usuário
 *
 * @author mathe
 */
public class Usuario implements Serializable {

    protected String email;
    protected String nome;
    protected String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
