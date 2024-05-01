package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreferitiDAO {
    private ArrayList<Preferiti> parsePreferiti(ResultSet rs) {
        ArrayList<Preferiti> listaPreferiti = new ArrayList<>();
        try {
            while (rs.next()) {
                Preferiti p = new Preferiti();
                p.setIdUtente(rs.getInt("id_utente"));
                p.setIdProdotto(rs.getInt("id_prodotto"));
                listaPreferiti.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPreferiti;
    }

    public ArrayList<Preferiti> doRetrieveByUser(int user_id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Preferiti WHERE id_utente=?");
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return parsePreferiti(ps.executeQuery());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<Preferiti>();
    }

    public void doSave(int idUtente, int idProdotto) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Preferiti (id_utente, id_prodotto) VALUES(?,?)");
            ps.setInt(1, idUtente);
            ps.setInt(2, idProdotto);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int idUtente, int idProdotto) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Preferiti WHERE id_utente=? AND id_prodotto=?");
            ps.setInt(1, idUtente);
            ps.setInt(2, idProdotto);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean isPreferito(int idUtente, int idProdotto) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("select count(*) > 0 as result from preferiti where id_utente=? and id_prodotto = ?");
            ps.setInt(1, idUtente);
            ps.setInt(2, idProdotto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getInt("result") == 1) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


}

