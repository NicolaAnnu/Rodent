package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoDAO;

import java.io.IOException;

@WebServlet(name = "getProdotti", value = "/getProdotti")
public class getProdotti extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdottoDAO prodottodao = new ProdottoDAO();
        String categoria = (String) req.getAttribute("active");
        if (categoria == null || categoria == "0") {
            req.setAttribute("prodotti", prodottodao.doRetrieveAll());
        } else {
            req.setAttribute("prodotti", prodottodao.doRetrieveByCategoria(Integer.parseInt(categoria)));
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher((String) req.getAttribute("origin"));
        dispatcher.forward(req, resp);
    }
}