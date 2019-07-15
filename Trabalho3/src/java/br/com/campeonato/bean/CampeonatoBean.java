/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.campeonato.bean;

import br.com.campeonato.dao.CampeonatoDao;
import br.com.campeonato.modelo.Campeonato;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author mferoc
 */
@RequestScoped
@ManagedBean(name = "bean")
public class CampeonatoBean {

    private Campeonato camp;
    private ArrayList<Campeonato> listaCampPartidas;
    private ArrayList<Campeonato> listaCampTimes;
    private ArrayList<Campeonato> listaCampResultados;
    private CampeonatoDao dao;

    public CampeonatoBean() {
        camp = new Campeonato();
        dao = new CampeonatoDao();
        listaCampTimes = dao.getListaCamp();
        listaCampPartidas = dao.getListaCampP();
        listaCampResultados = dao.getListaPesquisa(camp);
    }

    public Campeonato getCamp() {
        return camp;
    }

    public void setCamp(Campeonato camp) {
        this.camp = camp;
    }

    public ArrayList<Campeonato> getListaCampPartidas() {
        return listaCampPartidas;
    }

    public void setListaCampPartidas(ArrayList<Campeonato> listaCampPartidas) {
        this.listaCampTimes = listaCampPartidas;
    }

    public ArrayList<Campeonato> getListaCampTimes() {
        return listaCampTimes;
    }

    public void setListaCampTimes(ArrayList<Campeonato> listaCampTimes) {
        this.listaCampTimes = listaCampTimes;
    }

    public ArrayList<Campeonato> getListaCampResultados() {
        return listaCampResultados;
    }

    public void setListaCampResultados(ArrayList<Campeonato> listaCampResultados) {
        this.listaCampResultados = listaCampResultados;
    }

    public CampeonatoDao getDao() {
        return dao;
    }

    public void setDao(CampeonatoDao dao) {
        this.dao = dao;
    }

    //Metódos ligando tela e banco de dados
    public void inserirTime() {
        dao.inserirTime(camp);
        listaCampTimes.add(camp);
        camp = new Campeonato();
    }

    public void inserirPartida() {
        dao.inserirPartida(camp);
        listaCampPartidas.add(camp);
        camp = new Campeonato();
    }

    public void listarResultados() {
        //camp = new Campeonato();
        //dao.listarResultados(camp);
        listaCampResultados = dao.getListaPesquisa(camp);
        listaCampResultados.add(camp);
        //camp = new Campeonato();
    }

}
