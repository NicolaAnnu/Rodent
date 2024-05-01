package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "getProdottiPreferiti", value = "/getProdottiPreferiti")
public class getProdottiPreferiti extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente utente = (Utente) req.getSession().getAttribute("utente");
        ProdottoDAO prodottoDao = new ProdottoDAO();
        ArrayList<Prodotto> preferiti = prodottoDao.retrievePreferiti(utente.getId());
        req.setAttribute("preferiti", preferiti);
        RequestDispatcher dispatcher = req.getRequestDispatcher("preferiti.jsp");
        dispatcher.forward(req, resp);
    }
}
