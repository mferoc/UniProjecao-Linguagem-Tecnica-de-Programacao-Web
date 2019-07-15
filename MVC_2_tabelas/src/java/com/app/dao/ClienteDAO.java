package com.app.dao;

import com.app.conexao.Conectabd;
import com.app.modelo.Cliente;
import com.app.modelo.Telefone;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {

    private String sql;
    private Connection con;
    private PreparedStatement ps;

    public void inserir(Cliente clnt) {
       con = Conectabd.conectar();
        try {
            sql = "insert into cliente (cpf, nome, email) values (?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, clnt.getCpf());
            ps.setString(2, clnt.getNome());
            ps.setString(3, clnt.getEmail());
            ps.execute();
            sql = "insert into telefone (telefone, cpf) values (?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, clnt.getTel().getTelefone());
            ps.setString(2, clnt.getCpf());
            ps.execute();
            System.out.println("CADASTRO COM SUCESSO...");
           
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NO CADASTRO...");
            
        }

    }
    
    public ArrayList<Cliente> getListaClnt() {
        ResultSet rs;
        ArrayList<Cliente> lista = new ArrayList<>();
        con = Conectabd.conectar();
        sql = "SELECT C.cpf, C.nome, C.email, T.telefone FROM cliente AS C JOIN telefone AS T ON C.cpf = T.cpf";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente clnt = new Cliente();
                clnt.setCpf(rs.getString("C.cpf"));
                clnt.setNome(rs.getString("C.nome"));
                clnt.setEmail(rs.getString("C.email"));
                Telefone tel = new Telefone();
                tel.setTelefone(rs.getString("T.telefone"));
                clnt.setTel(tel);
                lista.add(clnt);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
