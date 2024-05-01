package model;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UtenteDAO {
    public ArrayList<Utente> doRetrieveById(int id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Utente WHERE id=?");
            ps.setInt(1, id);
            return parseUtenti(ps.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Utente> doRetrieveBymail(String mail) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Utente WHERE email=?");
            ps.setString(1, mail);
            return parseUtenti(ps.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Utente parseUtente(ResultSet rs) throws SQLException {
        Utente utente = new Utente();
        utente.setId(rs.getInt("id"));
        utente.setRuolo(rs.getInt("ruolo"));
        utente.setUsername(rs.getString("username"));
        utente.setEmail(rs.getString("email"));
        utente.setPassword(rs.getString("password"));
        utente.setNome(rs.getString("nome"));
        utente.setCognome(rs.getString("cognome"));
        utente.setNumTelefono(rs.getString("num_telefono"));
        utente.setCap(rs.getString("cap"));
        utente.setCivico(rs.getString("civico"));
        utente.setVia(rs.getString("via"));
        return utente;
    }

    private ArrayList<Utente> parseUtenti(ResultSet rs) throws SQLException {
        ArrayList<Utente> utenti = new ArrayList<>();
        while (rs.next()) {
            utenti.add(parseUtente(rs));
        }
        return utenti;
    }

    public ArrayList<Utente> doRetrieveAll() {
        ArrayList<Utente> listaUtenti;
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Utente ");
            ResultSet rs = ps.executeQuery();
            listaUtenti = parseUtenti(rs);
            return listaUtenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean doSave(Utente utente) {
        try (Connection con = DbConnection.getConnection()) {
            String hashedPassword = BCrypt.withDefaults().hashToString(12, utente.getPassword().toCharArray());
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Utente (ruolo, username, email, password, nome, cognome, num_telefono, cap, civico, via) VALUES(?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, utente.getRuolo());
            ps.setString(2, utente.getUsername());
            ps.setString(3, utente.getEmail());
            ps.setString(4, hashedPassword);
            ps.setString(5, utente.getNome());
            ps.setString(6, utente.getCognome());
            ps.setString(7, utente.getNumTelefono());
            ps.setString(8, utente.getCap());
            ps.setString(9, utente.getCivico());
            ps.setString(10, utente.getVia());
            if (ps.executeUpdate() != 1) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Utente doLogin(String email, String password) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Utente WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente utente = parseUtente(rs);
                if (BCrypt.verifyer().verify(password.toCharArray(), utente.getPassword()).verified) {
                    return utente;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdate(Utente utente) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Utente SET ruolo=?, username=?, email=?, nome=?, cognome=?, num_telefono=?, cap=?, civico=?, via=? WHERE id=?");
            ps.setInt(1, utente.getRuolo());
            ps.setString(2, utente.getUsername());
            ps.setString(3, utente.getEmail());
            ps.setString(4, utente.getNome());
            ps.setString(5, utente.getCognome());
            ps.setString(6, utente.getNumTelefono());
            ps.setString(7, utente.getCap());
            ps.setString(8, utente.getCivico());
            ps.setString(9, utente.getVia());
            ps.setInt(10, utente.getId());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("UPDATE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdatePassword(int idUtente, String password) {
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Utente SET password=? WHERE id=?");
            ps.setString(1, hashedPassword);
            ps.setInt(2, idUtente);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
