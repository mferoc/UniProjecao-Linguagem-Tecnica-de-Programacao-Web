package br.com.atividade.bean;

import br.com.atividade.DAO.CursoDAO;
import br.com.atividade.modelo.Curso;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean(name = "bean")
public class CursoBean {

    private Curso crs;
    private CursoDAO dao;
    private ArrayList<Curso> listaCS;

    public CursoBean() {
        crs = new Curso();
        dao = new CursoDAO();
        listaCS = dao.getListaCS();
    }

    public ArrayList<Curso> getListaCS() {
        return listaCS;
    }

    public void setListaCS(ArrayList<Curso> listaCS) {
        this.listaCS = listaCS;
    }

    public Curso getCrs() {
        return crs;
    }

    public void setCrs(Curso crs) {
        this.crs = crs;
    }

    public CursoDAO getDao() {
        return dao;
    }

    public void setDao(CursoDAO dao) {
        this.dao = dao;
    }

    public void cadastrar() {
        dao.cadastrar(crs);
        listaCS.add(crs);
        crs = new Curso();
    }

    public String pesquisar() {
        boolean result = dao.pesquisar(crs);

        if (result) {
            FacesContext.getCurrentInstance().addMessage(
                    "formulario:pesquisar",
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Sucesso!",
                            "Código encontrado"));
            return "index";
        } else {

            FacesContext.getCurrentInstance().addMessage(
                    "formulario:pesquisar",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Erro!",
                            "Código não encontrado"));
        }
        return "index";
    }

    public void excluir() {
        dao.excluir(crs);
        listaCS = dao.getListaCS();
        crs = new Curso();
    }

    public void editar() {
        dao.atualizar(crs);
        listaCS = dao.getListaCS();
        crs = new Curso();
    }

}
