package com.app.modelo;

import java.io.Serializable;

public class Cliente implements Serializable {
    
    private String nome;
    private String cpf;
    private String email;
    
    //Associando com a tabela de telefones
    //Colocando um objeto telefone dentro da classe Cliente
    private Telefone tel;
    
    public Cliente() {
        tel = new Telefone();
    }

    public Telefone getTel() {
        return tel;
    }

    public void setTel(Telefone tel) {
        this.tel = tel;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

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
}
