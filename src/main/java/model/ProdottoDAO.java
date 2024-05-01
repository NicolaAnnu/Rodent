package model;

import java.sql.*;
import java.util.ArrayList;

public class ProdottoDAO {
    private Prodotto parseProdotto(ResultSet rs) {
        try {
            Prodotto p = new Prodotto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setPrezzo(rs.getFloat("prezzo"));
            p.setDisponibilita(rs.getBoolean("disponibilita"));
            p.setCategoria(rs.getInt("categoria"));
            p.setDescrizione(rs.getString("descrizione"));
            return p;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Prodotto> parseProdotti(ResultSet rs) {
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        try {
            while (rs.next()) {
                prodotti.add(parseProdotto(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prodotti;
    }

    public ArrayList<Prodotto> doRetrieveAll() {
        ArrayList<Prodotto> listaProdotti;
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Prodotto ");
            ResultSet rs = ps.executeQuery();

            listaProdotti = parseProdotti(rs);
            return listaProdotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Prodotto> doRetrieveByName(String name) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Prodotto WHERE nome LIKE ?");
            ps.setString(1, "%" + name + "%");
            return parseProdotti(ps.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Prodotto doRetrieveByid(int id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Prodotto WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return parseProdotto(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Prodotto> doRetrieveByCategoria(int id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Prodotto WHERE categoria=?");
            ps.setInt(1, id);
            return parseProdotti(ps.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(Prodotto p) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO prodotto (nome, prezzo, disponibilita, categoria, descrizione) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getNome());
            ps.setFloat(2, p.getPrezzo());
            ps.setBoolean(3, p.getDisponibilita());
            ps.setInt(4, p.getCategoria());
            ps.setString(5, p.getDescrizione());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public void doUpdate(Prodotto p) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Prodotto SET nome=?, prezzo=?, disponibilita=?, categoria=? WHERE id=?");
            ps.setString(1, p.getNome());
            ps.setFloat(2, p.getPrezzo());
            ps.setBoolean(3, p.getDisponibilita());
            ps.setInt(4, p.getCategoria());
            ps.setInt(5, p.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doDelete(int id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM Prodotto WHERE id=?");
            ps.setInt(1, id);
            if (ps.executeUpdate() != 1) {
                return -1;
            } else  {
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Prodotto> retrievePreferiti(int user_id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Preferiti as P JOIN Prodotto as Pr where Pr.id = P.id_prodotto and id_utente = ?");
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return parseProdotti(ps.executeQuery());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }
}

