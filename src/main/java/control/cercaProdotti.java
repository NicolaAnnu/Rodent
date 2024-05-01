package control;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Prodotto;
import model.ProdottoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "cercaProdotti", value = "/cercaProdotti")
public class cercaProdotti extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdottoDAO prodottodao = new ProdottoDAO();
        ArrayList<Prodotto> prodotti = new ArrayList<>();
        String searchParam = (String) req.getParameter("searchParam");
        prodotti = prodottodao.doRetrieveByName(searchParam);
        Gson gson = new Gson();
        String productJson = gson.toJson(prodotti);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(productJson);
        out.flush();
        out.close();
    }
}
