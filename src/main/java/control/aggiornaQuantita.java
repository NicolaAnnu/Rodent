package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Carrello;
import model.ProdottiInCarrello;
import model.ProdottiInCarrelloDAO;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "aggiornaQuantita", value = "/aggiornaQuantita")
public class aggiornaQuantita extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        int quantita;
        try {
            id = Integer.parseInt(req.getParameter("id"));
            quantita = Integer.parseInt(req.getParameter("quantita"));
        } catch (Exception e) {
            resp.sendError(400);
            return;
        }
        if (quantita < 0) {
            resp.sendError(400);
            return;
        }
        HttpSession session = req.getSession();
        synchronized (session) {
            Carrello carrello = (Carrello) session.getAttribute("carrello");
            ArrayList<ProdottiInCarrello> prodotti = carrello.getProdotti();
            for (int i = 0; i < prodotti.size(); i++) {
                if (prodotti.get(i).getIdProdotto() == id) {
                    prodotti.get(i).setQuantita(quantita);
                    if (quantita == 0) {
                        Utente utente = (Utente) session.getAttribute("utente");
                        if (utente != null) {
                            ProdottiInCarrelloDAO prodottiInCarrelloDAO = new ProdottiInCarrelloDAO();
                            prodottiInCarrelloDAO.doDelete(prodotti.get(i));
                        }
                        prodotti.remove(i);
                    }
                    resp.setStatus(200);
                    return;
                }
            }
            resp.sendError(404);
        }
    }
}
