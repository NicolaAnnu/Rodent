package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdineDAO {

    private Ordine parseOrdine(ResultSet rs) {
        try {
            Ordine ordine = new Ordine();
            ordine.setId(rs.getInt("id"));
            ordine.setIdCarrello(rs.getInt("id_carrello"));
            ordine.setIdUtente(rs.getInt("id_utente"));
            ordine.setMetodoDiPagamento(rs.getString("metodo_di_pagamento"));
            ordine.setTotale(rs.getFloat("totale"));
            ordine.setStato(rs.getString("stato"));
            ordine.setCap(rs.getString("cap"));
            ordine.setCivico(rs.getString("civico"));
            ordine.setVia(rs.getString("via"));
            ordine.setNumTelefono(rs.getString("num_telefono"));
            return ordine;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Ordine> parseOrdini(ResultSet rs) {
        ArrayList<Ordine> listaOrdini = new ArrayList<>();
        try {
            while (rs.next()) {
                listaOrdini.add(parseOrdine(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaOrdini;
    }

    public Ordine doRetrieveById(int id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Ordine WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return parseOrdine(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Ordine> doRetrieveByUser(int user_id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Ordine WHERE id_utente=?");
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            return parseOrdini(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Ordine> doRetrieveAll() {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Ordine ");
            ResultSet rs = ps.executeQuery();
            return parseOrdini(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(Ordine ordine) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO Ordine (id_carrello, id_utente, metodo_di_pagamento, stato, cap, civico, via, totale, num_telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, ordine.getIdCarrello());
            ps.setInt(2, ordine.getIdUtente());
            ps.setString(3, ordine.getMetodoDiPagamento());
            ps.setString(4, ordine.getStato());
            ps.setString(5, ordine.getCap());
            ps.setString(6, ordine.getCivico());
            ps.setString(7, ordine.getVia());
            ps.setFloat(8, ordine.getTotale());
            ps.setString(9, ordine.getNumTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateStato(int id, String stato) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE Ordine SET stato=? WHERE id=?");
            ps.setString(1, stato);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Ordine WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}