package br.com.atividade.DAO;

import br.com.atividade.conexao.Conectabd;
import br.com.atividade.modelo.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CursoDAO {

    private String sql;
    private Connection con;
    private PreparedStatement ps;

    public boolean cadastrar(Curso crs) {
        con = Conectabd.conectar();
        sql = "insert into TB_cursos (cod, nome, categoria, carga, preco) values (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, crs.getCod());
            ps.setString(2, crs.getNome());
            ps.setString(3, crs.getCategoria());
            ps.setString(4, crs.getCarga());
            ps.setString(5, crs.getPreco());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NO CADASTRO...");
            return false;
        }
    }

    public boolean pesquisar(Curso crs) {
        ResultSet rs;
        con = Conectabd.conectar();
        sql = "select * from TB_cursos where cod=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, crs.getCod());
            ps.execute();
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NA PESQUISA...");
            return false;
        }
    }

    public boolean excluir(Curso crs) {
        con = Conectabd.conectar();
        sql = "DELETE FROM TB_cursos WHERE cod=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, crs.getCod());
            ps.execute();
            System.out.println("EXCLUIDO COM SUCESSO");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NA EXCLUSÃO...");
            return false;
        }
    }

    public boolean atualizar(Curso crs) {
        con = Conectabd.conectar();
        sql = "UPDATE TB_cursos\n"
                + "SET nome = ?, categoria = ?,carga = ?, preco = ?\n"
                + "WHERE cod = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, crs.getNome());
            ps.setString(2, crs.getCategoria());
            ps.setString(3, crs.getCarga());
            ps.setString(4, crs.getPreco());
            ps.setString(5, crs.getCod());
            ps.execute();
            System.out.println("ATUALIZADO COM SUCESSO");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO NA ATUALIZAÇÃO...");
            return false;
        }
    }

    public ArrayList<Curso> getListaCS() {
        ResultSet rs;
        ArrayList<Curso> lista = new ArrayList<>();
        con = Conectabd.conectar();
        sql = "select * from TB_cursos ";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Curso crs = new Curso();
                crs.setCod(rs.getString("cod"));
                crs.setNome(rs.getString("nome"));
                crs.setCategoria(rs.getString("categoria"));
                crs.setCarga(rs.getString("carga"));
                crs.setPreco(rs.getString("preco"));
                lista.add(crs);
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(CursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
