package br.com.cadastro.DAO;

import br.com.cadastro.conexao.Conectabd;
import br.com.cadastro.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathe
 */
public class UsuarioDAO {

    private String sql;
    private Connection con;
    private PreparedStatement ps;

    public boolean inserir(Usuario usr) {
        con = Conectabd.conectar();
        sql = "insert into TB_usuarios (TX_email, NM_nome, TX_senha) values (?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getEmail());
            ps.setString(2, usr.getNome());
            ps.setString(3, usr.getSenha());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NO CADASTRO...");
            return false;
        }

    }

    public boolean fazerLogin(Usuario usr) {
        ResultSet rs;
        con = Conectabd.conectar();
        sql = "select TX_email, TX_senha from TB_usuarios where TX_email=? and TX_senha=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getEmail());
            ps.setString(2, usr.getSenha());
            rs = ps.executeQuery();
            return rs.next(); //
            //usr = new Usuario();
            //usr.setEmail(rs.getString("TX_email")));
            //usr.setSenha(rs.getString("TX_senha"));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

    }
    
    public boolean existe(Usuario usr){
        ResultSet rs;
        con = Conectabd.conectar();
        sql = "select TX_email from TB_usuarios where TX_email=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getEmail());
            rs = ps.executeQuery();
            return rs.next();
            //usr = new Usuario();
            //usr.setEmail(rs.getString("TX_email")));
            //usr.setSenha(rs.getString("TX_senha"));
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

    }
}
