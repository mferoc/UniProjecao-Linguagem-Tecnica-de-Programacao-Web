package br.com.cadastro.bean;

import br.com.cadastro.DAO.UsuarioDAO;
import br.com.cadastro.modelo.Usuario;
import javax.faces.application.FacesMessage;
//import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean(name = "bean")
public class UsuarioBean {

    private Usuario usr;
    private UsuarioDAO dao;

    public UsuarioBean() {
        usr = new Usuario();
        dao = new UsuarioDAO();
    }

    public Usuario getUsr() {
        return usr;
    }

    public void setUsr(Usuario usr) {
        this.usr = usr;
    }

    public UsuarioDAO getDao() {
        return dao;
    }

    public void setDao(UsuarioDAO dao) {
        this.dao = dao;
    }

    public void addUsr() {
        dao.inserir(usr);
        usr = new Usuario();
    }

    public String logar() {
        boolean result = dao.fazerLogin(usr);
        boolean ex = dao.existe(usr);

        if (result) {
            FacesContext.getCurrentInstance().addMessage(
                    "myform:entrar",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Login!",
                            "Successo!"));
            return "entrar";
        } else if (ex == false) {

            FacesContext.getCurrentInstance().addMessage(
                    "myform:entrar",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Erro!",
                            "Login Inválido! Usuário não cadastrado"));
        } else {

            FacesContext.getCurrentInstance().addMessage(
                    "myform:entrar",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Erro!",
                            "Login Inválido! Senha incorreta"));
        }
        return "entrar";
    }

}
