package model;

import java.sql.*;
import java.util.ArrayList;

public class CarrelloDAO {
    private Carrello parseCarrello(ResultSet rs) {
        Carrello p = new Carrello();
        try {
            p.setId(rs.getInt("id"));
            p.setUtente(rs.getInt("utente"));
            ArrayList<ProdottiInCarrello> prodottiInCarrello = new ProdottiInCarrelloDAO().doRetrieveByCart(p.getId(), p.getUtente());
            p.setProdotti(prodottiInCarrello);
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Carrello> parseCarrelli(ResultSet rs) {
        ArrayList<Carrello> listaCarrello = new ArrayList<>();
        try {
            while (rs.next()) {
                listaCarrello.add(parseCarrello(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaCarrello;
    }

    public Carrello doRetrieveActive(int user_id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Carrello as C WHERE utente=? and not exists (SELECT * from Ordine where id_utente=C.utente and id_carrello= C.id) ");
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return parseCarrello(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(int idUtente) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Carrello (utente) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, idUtente);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}

