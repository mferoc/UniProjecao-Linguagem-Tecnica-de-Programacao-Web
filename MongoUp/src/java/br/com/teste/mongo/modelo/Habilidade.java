
package br.com.teste.mongo.modelo;

import java.io.Serializable;


public class Habilidade implements Serializable {
    
    private String nome;
    private String nivel;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    
    
    
}
