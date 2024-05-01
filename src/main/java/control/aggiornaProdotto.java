package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "AggiornaProdotto", value = "/aggiornaProdotto")
public class aggiornaProdotto extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente utente = (Utente) req.getSession().getAttribute("utente");
        int id = 0;
        boolean quantita;
        float prezzo = 0;
        if (utente == null) {
            resp.sendError(404, "Utente non trovato");
            return;
        }
        synchronized (utente) {
            if (utente.getRuolo() != 0) {
                resp.sendError(403, "Utente non autorizzato");
                return;
            }
        }
        String idParameter = req.getParameter("id");
        String quantitaParameter = req.getParameter("quantita");
        String prezzoParameter = req.getParameter("prezzo");
        if (idParameter.isEmpty() || idParameter == null || quantitaParameter == null || quantitaParameter.isEmpty() || prezzoParameter == null || prezzoParameter.isEmpty()) {
            resp.sendError(401, "Uno dei parametri è nullo");
            return;
        }
        if (!quantitaParameter.equals("true") && !quantitaParameter.equals("false")) {
            resp.sendError(400, "Valore della disponibilità non valido");
            return;
        }
        try {
            id = Integer.parseInt(idParameter);
            quantita = Boolean.valueOf(quantitaParameter);
            prezzo = Float.parseFloat(prezzoParameter);
            if (id < 0 || prezzo < 0) {
                resp.sendError(400, "Parametri invalidi");
                return;
            }
        } catch (Exception e) {
            resp.sendError(400, "Parametri non numerici");
            return;
        }
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        Prodotto p = prodottoDAO.doRetrieveByid(id);
        if (p == null) {
            resp.sendError(404, "Prodotto non esistente");
            return;
        }
        p.setDisponibilita(quantita);
        p.setPrezzo(prezzo);
        prodottoDAO.doUpdate(p);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
