
package br.com.teste.mongo.modelo;

import java.io.Serializable;


public class Curso implements Serializable{
    private String nome;

    public Curso(String nome) {
        this.nome=nome;
    }

    public Curso() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
