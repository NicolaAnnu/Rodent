package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "login", value = "/login")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getParameter("email");
        String password = (String) req.getParameter("password");
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            resp.sendError(403, "Email o password nulli");
            return;
        }
        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = utenteDAO.doLogin(email, password);
        if (utente == null) {
            resp.sendError(403, "Utente non trovato");
            return;
        }
        req.getSession().setAttribute("utente", utente);
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        Carrello carrello = carrelloDAO.doRetrieveActive(utente.getId());
        if (carrello == null) {
            carrello = new Carrello();
            carrello.setUtente(utente.getId());
            carrelloDAO.doSave(carrello.getUtente());
            carrello = carrelloDAO.doRetrieveActive(utente.getId());
        }
        Carrello vecchio = (Carrello) req.getSession().getAttribute("carrello");
        synchronized (vecchio) {
            ArrayList<ProdottiInCarrello> prodotti = vecchio.getProdotti();
            ProdottiInCarrelloDAO prodottiInCarrelloDAO = new ProdottiInCarrelloDAO();
            for (ProdottiInCarrello p : prodotti) {
                p.setIdUtente(utente.getId());
                p.setIdCarrello(carrello.getId());
                prodottiInCarrelloDAO.doSave(p);
                carrello.addProdotto(p);
            }
        }
        req.getSession().setAttribute("carrello", carrello);
        resp.sendRedirect("login.jsp");

    }
}

