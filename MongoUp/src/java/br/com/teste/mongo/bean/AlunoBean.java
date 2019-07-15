
package br.com.teste.mongo.bean;

import br.com.teste.mongo.dao.AlunoDAO;
import br.com.teste.mongo.modelo.Aluno;
import br.com.teste.mongo.modelo.Curso;
import br.com.teste.mongo.modelo.Habilidade;
import br.com.teste.mongo.modelo.Nota;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


@ManagedBean(name = "bean")
public class AlunoBean {
    
    private Aluno aluno;
    private Curso curso;
    private Habilidade habilidade;
    private Nota n1;
    private Nota n2;
    private Nota n3;
    private List<Habilidade> habilidades;
    private List<Nota> notas;
    private List<Curso> cursos;
    
    private List<Aluno> alunos;
    
    private AlunoDAO dao;

    
    public AlunoBean(){
        aluno = new Aluno();
        curso = new Curso();
        habilidade = new Habilidade();
        n1 = new Nota();
        n2 = new Nota();
        n3 = new Nota();
        
        cursos = new ArrayList<>();
        notas = new ArrayList<>();
        habilidades = new ArrayList<>();
        alunos = new ArrayList<>();
        
        dao = new AlunoDAO();
        alunos = dao.getTodosAlunos();
        
    }
    
    public void salvar(){
        notas.add(n1);
        notas.add(n2);
        notas.add(n3);
        
        habilidades.add(habilidade);
        cursos.add(curso);
        
        aluno.setCurso(curso);
        aluno.setHabilidades(habilidades);
        aluno.setNotas(notas);
        
        dao.salvar(aluno);
        
        FacesMessage msg = new FacesMessage("Atenção", "Aluno Cadastrado com sucesso " );
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Habilidade getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(Habilidade habilidade) {
        this.habilidade = habilidade;
    }

    public Nota getN1() {
        return n1;
    }

    public void setN1(Nota n1) {
        this.n1 = n1;
    }

    public Nota getN2() {
        return n2;
    }

    public void setN2(Nota n2) {
        this.n2 = n2;
    }

    public Nota getN3() {
        return n3;
    }

    public void setN3(Nota n3) {
        this.n3 = n3;
    }

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    


    
    
    
}
