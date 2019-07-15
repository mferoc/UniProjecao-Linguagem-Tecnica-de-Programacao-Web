package com.app.controle;

import com.app.dao.ClienteDAO;
import com.app.modelo.Cliente;
import java.util.ArrayList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

//@ApplicationScoped //os valores armazenados permanecem até modificar a aplicação (nova compilacao)
@SessionScoped //escopo de sessão//os valores dos objetos permanecem enquanto durar a sessão (navegador aberto)
//@RequestScoped //escopo de requisição//os objetos são modificados a cada nova requisição
//@ViewScoped //o escopo é da tela de visão// os objetos são modificados ao mudar pra uma nova tela (aba) 
@ManagedBean(name = "bean")
public class ClienteBean {
    
    private Cliente clnt;
    private ArrayList<Cliente> listaClnt;
    private ClienteDAO dao;

    public ClienteBean() {
        clnt = new Cliente();
        dao = new ClienteDAO();
        listaClnt = dao.getListaClnt();
    }

    public ArrayList<Cliente> getListaClnt() {
        return listaClnt;
    }

    public void setListaClnt(ArrayList<Cliente> listaClnt) {
        this.listaClnt = listaClnt;
    }
    
    public Cliente getClnt() {
        return clnt;
    }

    public void setClnt(Cliente clnt) {
        this.clnt = clnt;
    }
    
    /*
    Enviar para uma nova página
    public String enviar() {
        
        return "principal";
    }
    */
    
    public void enviar() {
        //clnt = new Cliente();
        dao.inserir(clnt);
        listaClnt.add(clnt);
        clnt = new Cliente();
    }
}
