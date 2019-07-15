/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.campeonato.dao;

import br.com.campeonato.modelo.Campeonato;
import br.com.campeonato.modelo.Partida;
import br.com.campeonato.modelo.Time;
import br.com.campeonato.conexao.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CampeonatoDao {

    private String sql;
    private Connection con;
    private PreparedStatement ps;

    public void inserirTime(Campeonato camp) {
        con = Conectar.conectar();
        try {
            sql = "insert into `TB_time` (`nmNome`, `nmSigla`, `dtFundacao`, "
                    + " `nmPresidente`, `nmVice`, `numPontos`) values (?,?,?,?,?,0)";
            ps = con.prepareStatement(sql);
            ps.setString(1, camp.getTm().getNome());
            ps.setString(2, camp.getTm().getSigla());
            ps.setString(3, camp.getTm().getFundacao());
            ps.setString(4, camp.getTm().getPresidente());
            ps.setString(5, camp.getTm().getVice());
            ps.execute();

            System.out.println("CADASTRO COM SUCESSO...");

        } catch (SQLException ex) {
            Logger.getLogger(CampeonatoDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NO CADASTRO...");
            ex.printStackTrace();

        }

    }

    public void inserirPartida(Campeonato camp) {
        con = Conectar.conectar();
        try {
            sql = "insert into `TB_partida` (`nmCasaSigla`, `nmVisitanteSigla`, `dtPartida`, "
                    + " `dsLocal`, `dsLocalUfEstado`, `dsLocalCidade`, `numGolsCasa`, `numGolsVisitante`) "
                    + " values (?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, camp.getPtd().getCasa());
            ps.setString(2, camp.getPtd().getVisitante());
            ps.setString(3, camp.getPtd().getData());
            ps.setString(4, camp.getPtd().getEstadio());
            ps.setString(5, camp.getPtd().getEstado());
            ps.setString(6, camp.getPtd().getCidade());
            ps.setInt(7, camp.getPtd().getGolsCasa());
            ps.setInt(8, camp.getPtd().getGolsVisitante());
            ps.execute();

            if (camp.getPtd().getGolsCasa() > camp.getPtd().getGolsVisitante()) {
                sql = "UPDATE `TB_time` SET numPontos = numPontos + 3 WHERE nmSigla = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, camp.getPtd().getCasa());
                ps.execute();

            } else if (camp.getPtd().getGolsCasa() == camp.getPtd().getGolsVisitante()) {
                sql = "UPDATE `TB_time` SET numPontos = numPontos + 1 WHERE nmSigla = ? OR nmSigla = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, camp.getPtd().getCasa());
                ps.setString(2, camp.getPtd().getVisitante());
                ps.execute();
            } else {
                sql = "UPDATE `TB_time` SET numPontos = numPontos + 3 WHERE nmSigla = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, camp.getPtd().getVisitante());
                ps.execute();
            }
            System.out.println("CADASTRO COM SUCESSO...");

        } catch (SQLException ex) {
            Logger.getLogger(CampeonatoDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NO CADASTRO...");
            ex.printStackTrace();

        }

    }

    public ArrayList<Campeonato> getListaCamp() {
        ResultSet rs;
        ArrayList<Campeonato> lista = new ArrayList<>();
        con = Conectar.conectar();
        sql = "SELECT * FROM TB_time;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Campeonato camp = new Campeonato();
                Time tm = new Time();
                tm.setNome(rs.getString("nmNome"));
                tm.setSigla(rs.getString("nmSigla"));
                tm.setFundacao(rs.getString("dtFundacao"));
                tm.setPresidente(rs.getString("nmPresidente"));
                tm.setVice(rs.getString("nmVice"));
                tm.setPontos(rs.getInt("numPontos"));
                camp.setTm(tm);
                lista.add(camp);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CampeonatoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Campeonato> getListaCampP() {
        ResultSet rs;
        ArrayList<Campeonato> lista = new ArrayList<>();
        con = Conectar.conectar();
        sql = "SELECT * FROM TB_partida;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Campeonato camp = new Campeonato();
                Partida ptd = new Partida();
                ptd.setCasa(rs.getString("nmCasaSigla"));
                ptd.setVisitante(rs.getString("nmVisitanteSigla"));
                ptd.setData(rs.getString("dtPartida"));
                ptd.setEstadio(rs.getString("dsLocal"));
                ptd.setEstado(rs.getString("dsLocalUfEstado"));
                ptd.setCidade(rs.getString("dsLocalCidade"));
                ptd.setGolsCasa(rs.getInt("numGolsCasa"));
                ptd.setGolsVisitante(rs.getInt("numGolsVisitante"));
                camp.setPtd(ptd);
                lista.add(camp);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CampeonatoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /*
    public ArrayList<Campeonato> getListaCamp() {
        ResultSet rs;
        ArrayList<Campeonato> lista = new ArrayList<>();
        con = Conectar.conectar();
        sql = "SELECT * FROM TB_time, TB_partida;";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Campeonato camp = new Campeonato();
                Time tm = new Time();
                Partida ptd = new Partida();
                tm.setNome(rs.getString("nmNome"));
                tm.setSigla(rs.getString("nmSigla"));
                tm.setFundacao(rs.getString("dtFundacao"));
                tm.setPresidente(rs.getString("nmPresidente"));
                tm.setVice(rs.getString("nmVice"));
                tm.setPontos(rs.getInt("numPontos"));
                ptd.setCasa(rs.getString("nmCasaSigla"));
                ptd.setVisitante(rs.getString("nmVisitanteSigla"));
                ptd.setData(rs.getString("dtPartida"));
                ptd.setEstadio(rs.getString("dsLocal"));
                ptd.setEstado(rs.getString("dsLocalUfEstado"));
                ptd.setCidade(rs.getString("dsLocalCidade"));
                ptd.setGolsCasa(rs.getInt("numGolsCasa"));
                ptd.setGolsVisitante(rs.getInt("numGolsVisitante"));
                camp.setTm(tm);
                camp.setPtd(ptd);
                lista.add(camp);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CampeonatoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     */
    public ArrayList<Campeonato> getListaPesquisa(Campeonato camp) {
        ResultSet rs;
        ArrayList<Campeonato> lista = new ArrayList<>();
        con = Conectar.conectar();
        sql = "SELECT p.nmCasaSigla, p.nmVisitanteSigla, p.dtPartida, p.dsLocal,"
                + " p.dsLocalUfEstado, p.dsLocalCidade, p.numGolsCasa, p.numGolsVisitante"
                + " FROM TB_partida AS p\n"
                + "JOIN\n"
                + "TB_time AS t ON p.nmCasaSigla = t.nmSigla OR p.nmVisitanteSigla = t.nmSigla\n"
                + "WHERE t.nmSigla = ?;";
        try {
            //Campeonato camp = new Campeonato();
            ps = con.prepareStatement(sql);
            ps.setString(1, camp.getTm().getSigla());
            rs = ps.executeQuery();

            while (rs.next()) {
                camp = new Campeonato();
                Partida ptd = new Partida();
                ptd.setCasa(rs.getString("nmCasaSigla"));
                ptd.setVisitante(rs.getString("nmVisitanteSigla"));
                ptd.setData(rs.getString("dtPartida"));
                ptd.setEstadio(rs.getString("dsLocal"));
                ptd.setEstado(rs.getString("dsLocalUfEstado"));
                ptd.setCidade(rs.getString("dsLocalCidade"));
                ptd.setGolsCasa(rs.getInt("numGolsCasa"));
                ptd.setGolsVisitante(rs.getInt("numGolsVisitante"));
                camp.setPtd(ptd);
                lista.add(camp);
               
                System.out.println("INSERIDO");

            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CampeonatoDao.class.getName()).log(Level.SEVERE, null, ex);
            //ex.printStackTrace();
            return null;
        }
    }
}
