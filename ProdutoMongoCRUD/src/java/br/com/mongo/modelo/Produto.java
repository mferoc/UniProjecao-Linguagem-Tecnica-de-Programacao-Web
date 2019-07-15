
package br.com.mongo.modelo;

import java.io.Serializable;
import org.bson.types.ObjectId;

public class Produto implements Serializable{
    
    private ObjectId id;
    private String codigo;
    private String nome;
    private String descricao;
    private Integer quantidade;
    private Double preco;
    
    //get e set

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    
    //criaId
    public Produto criaId() {
        setId(new ObjectId());
        return this;
    }
    
    
    
            
    
}
