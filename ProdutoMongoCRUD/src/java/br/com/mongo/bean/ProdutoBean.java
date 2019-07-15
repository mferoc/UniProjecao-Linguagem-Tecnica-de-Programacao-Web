
package br.com.mongo.bean;

import br.com.mongo.dao.ProdutoDAO;
import br.com.mongo.modelo.Produto;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@SessionScoped
@ManagedBean (name = "bean")
public class ProdutoBean {
    
    //criar os objetos 
    private Produto produto;
    private ArrayList<Produto> produtos;
    private ProdutoDAO dao;
    
    //construtor
    public ProdutoBean(){
        produto = new Produto();
        produtos = new ArrayList<>();
        dao = new ProdutoDAO();
    }
    
    //get e set 
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public void salvar(){
        dao.salvar(produto);
        produto = new Produto();
    }
    
}
