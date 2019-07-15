package br.com.campeonato.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conectar {

    private static Connection con;
    private static final String URL = "jdbc:mysql://localhost:3306/DB_campeonato?useTimezone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PSW = "root";

    public static Connection conectar() {
        if (con == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(URL, USER, PSW);
                System.out.println("CONECTADO COM SUCESSO...");
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("OCORREU UM ERRO..." + ex);
            }
        }
        return con;
    }

    public static void desconectar() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Desconectado...");
            } catch (SQLException ex) {
                System.out.println("Erro ao Desconecta o banco de dados " + ex);
            }
        }

    }
    
    public static void main(String[] args) {
        conectar();
        desconectar();
    }
    //mysql://localhost:3306/agendajava
}