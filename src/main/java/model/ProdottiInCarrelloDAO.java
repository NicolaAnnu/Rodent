package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottiInCarrelloDAO {
    private ArrayList<ProdottiInCarrello> parseProdottiInCarrello(ResultSet rs) {
        ArrayList<ProdottiInCarrello> listaProdottiInCarrello = new ArrayList<>();
        try {
            while (rs.next()) {
                ProdottiInCarrello p = new ProdottiInCarrello();
                p.setIdUtente(rs.getInt("id_utente"));
                p.setIdCarrello(rs.getInt("id_carrello"));
                p.setIdProdotto(rs.getInt("id_prodotto"));
                p.setQuantita(rs.getInt("quantita"));
                listaProdottiInCarrello.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProdottiInCarrello;
    }

    public ArrayList<ProdottiInCarrello> doRetrieveByCart(int cart_id, int user_id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Prodotti_in_carrello WHERE id_carrello=? and id_utente = ?");
            ps.setInt(1, cart_id);
            ps.setInt(2, user_id);
            ResultSet rs = ps.executeQuery();
            return parseProdottiInCarrello(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(ProdottiInCarrello prodottoInCarrello) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO prodotti_in_carrello (id_utente, id_carrello, id_prodotto, quantita) VALUES (?, ?, ?, ?) AS new_values ON DUPLICATE KEY UPDATE quantita = prodotti_in_carrello.quantita + new_values.quantita");
            ps.setInt(1, prodottoInCarrello.getIdUtente());
            ps.setInt(2, prodottoInCarrello.getIdCarrello());
            ps.setInt(3, prodottoInCarrello.getIdProdotto());
            ps.setInt(4, prodottoInCarrello.getQuantita());
            int valoreRitorno = ps.executeUpdate();
            if (valoreRitorno == -1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(ProdottiInCarrello prodottoInCarrello) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Prodotti_in_carrello SET quantita=? WHERE id_utente=? AND id_carrello=? AND id_prodotto=?");
            ps.setInt(1, prodottoInCarrello.getQuantita());
            ps.setInt(2, prodottoInCarrello.getIdUtente());
            ps.setInt(3, prodottoInCarrello.getIdCarrello());
            ps.setInt(4, prodottoInCarrello.getIdProdotto());
            if (ps.executeUpdate() == -1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(ProdottiInCarrello prodottoInCarrello) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Prodotti_in_carrello WHERE id_utente=? AND id_carrello=? AND id_prodotto=?");
            ps.setInt(1, prodottoInCarrello.getIdUtente());
            ps.setInt(2, prodottoInCarrello.getIdCarrello());
            ps.setInt(3, prodottoInCarrello.getIdProdotto());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

