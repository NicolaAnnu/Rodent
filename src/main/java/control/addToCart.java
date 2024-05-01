package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "addToCart", value = "/addToCart")
public class addToCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParameter = req.getParameter("id");
        Boolean checkProdotto = false;
        int quantita = 0;
        try {
            quantita = Integer.parseInt(req.getParameter("quantita"));
        } catch (Exception e) {
            resp.sendError(400, "La quantità non è un numero");
            return;
        }
        if (quantita < 0) {
            resp.sendError(400, "La quantità deve essere maggiore di 0");
            return;
        }
        int id = 0;
        try {
            id = Integer.parseInt(idParameter);
        } catch (Exception e) {
            resp.sendError(404, "Id inserito non numerico");
            return;
        }
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        Prodotto prodotto1 = prodottoDAO.doRetrieveByid(id);
        if (prodotto1 == null) {
            resp.sendError(404, "Prodotto non trovato");
            return;
        }
        if (!prodotto1.getDisponibilita()) {
            resp.sendError(409, "Disponibilità esaurita");
            return;
        }
        HttpSession session = req.getSession();
        synchronized (session) {
            Utente utente = (Utente) session.getAttribute("utente");
            Carrello carrello = (Carrello) session.getAttribute("carrello");
            ArrayList<ProdottiInCarrello> prodottiInCarrello = carrello.getProdotti();
            for (int i = 0; i < prodottiInCarrello.size(); i++) {
                if (prodottiInCarrello.get(i).getIdProdotto() == id) {
                    quantita += prodottiInCarrello.get(i).getQuantita();
                    prodottiInCarrello.remove(i);
                    checkProdotto = true;
                }
            }

            ProdottiInCarrello prodotto = new ProdottiInCarrello();
            prodotto.setIdProdotto(id);
            prodotto.setQuantita(quantita);
            if (utente != null) {
                ProdottiInCarrelloDAO prodottiInCarrelloDAO = new ProdottiInCarrelloDAO();
                prodotto.setIdCarrello(carrello.getId());
                prodotto.setIdUtente(utente.getId());
                if (checkProdotto) {
                    prodottiInCarrelloDAO.doDelete(prodotto);
                }
                prodottiInCarrelloDAO.doSave(prodotto);

            }
            carrello.addProdotto(prodotto);
        }
    }
}
