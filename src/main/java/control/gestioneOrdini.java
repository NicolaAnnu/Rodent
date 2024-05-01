package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrdineDAO;
import model.Utente;

import java.io.IOException;

@WebServlet(name = "gestioneOrdini", value = "/gestioneOrdini")
public class gestioneOrdini extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente utente = (Utente) req.getSession().getAttribute("utente");
        if (utente == null) {
            resp.sendError(403, "Utente non loggato");
            return;
        }
        synchronized (utente) {
            if (utente.getRuolo() != 0) {
                resp.sendError(401, "Utente non autorizzato");
                return;
            }
        }
        String idOrdine = req.getParameter("id");
        String status = req.getParameter("status");
        if (idOrdine == null || idOrdine.isEmpty() || status == null || status.isEmpty()) {
            resp.sendError(400, "Parametri non validi");
            return;
        }
        int id = 0;
        try {
            id = Integer.parseInt(idOrdine);
        } catch (Exception e) {
            resp.sendError(400, "Id non numerico");
            return;
        }
        OrdineDAO ordineDAO = new OrdineDAO();
        ordineDAO.updateStato(id, status);

    }
}
