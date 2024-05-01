package control;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Preferiti;
import model.PreferitiDAO;
import model.Utente;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "getPreferiti", value = "/getPreferiti")
public class getPreferiti extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson parser = new Gson();
        PreferitiDAO preferitiDAO = new PreferitiDAO();
        Utente utente = (Utente) req.getSession().getAttribute("utente");
        ArrayList<Integer> id_preferiti = new ArrayList<>();
        if (utente != null) {
            ArrayList<Preferiti> preferiti = preferitiDAO.doRetrieveByUser(utente.getId());
            for (Preferiti p : preferiti) {
                id_preferiti.add(p.getIdProdotto());
            }
        }
        resp.setHeader("content-type", "application/json");
        String out = parser.toJson(id_preferiti);
        PrintWriter writer = resp.getWriter();
        writer.write(out);
    }
}
