package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDAO{
    private ArrayList<Categoria> parseCategoria(ResultSet rs) {
        ArrayList<Categoria> listaCategorie = new ArrayList<>();
        try {
            while(rs.next()){
                Categoria p = new Categoria();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                listaCategorie.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCategorie;
    }

    public ArrayList<Categoria> doRetrieveAll() {
        ArrayList<Categoria> listaCategorie;
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Categoria");
            ResultSet rs = ps.executeQuery();
            listaCategorie = parseCategoria(rs);
            return listaCategorie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Categoria> doRetrieveByName(String name) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Categoria WHERE nome=?");
            ps.setString(1, name);
            return parseCategoria(ps.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Categoria> doRetrieveByid(int id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Categoria WHERE id=?");
            ps.setInt(1, id);
            return parseCategoria(ps.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

