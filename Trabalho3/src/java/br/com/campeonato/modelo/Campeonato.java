/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.campeonato.modelo;

import java.io.Serializable;

/**
 *
 * @author mferoc
 */
public class Campeonato implements Serializable {
    
    private Time tm;
    private Partida ptd;
    
    public Campeonato() {
        tm = new Time();
        ptd = new Partida();
    }

    public Time getTm() {
        return tm;
    }

    public void setTm(Time tm) {
        this.tm = tm;
    }

    public Partida getPtd() {
        return ptd;
    }

    public void setPtd(Partida ptd) {
        this.ptd = ptd;
    }
       
}
