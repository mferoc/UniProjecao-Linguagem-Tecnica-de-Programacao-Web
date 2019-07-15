
package br.com.teste.mongo.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;


public class Aluno  implements Serializable{

  private ObjectId id;
  private String nome;
  private String email;
  private Date dataNascimento;
  private Curso curso;
  private List<Nota> notas;
  private List<Habilidade> habilidades;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }
    
    public Aluno criaId() {
        setId(new ObjectId());
        return this;
    }

    @Override
    public String toString() {
        return id + " " +nome;
    }
   
}
