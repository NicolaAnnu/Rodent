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

@WebServlet(name = "getProdotti" , value ="/getProdotti")
public class ServletProdotti extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdottoDAO prodottodao = new ProdottoDAO();
        ArrayList<Prodotto> prodotti = prodottodao.doRetrieveAll();
        resp.setHeader("content-type","application/json");
        Gson gson = new Gson();
        String returnValue = gson.toJson(prodotti);
        PrintWriter out = resp.getWriter();
        out.write(returnValue);
    }
}
