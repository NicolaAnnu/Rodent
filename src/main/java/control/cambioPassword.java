package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UtenteDAO;

import java.io.IOException;

@WebServlet(name = "cambioPassword", value = "/cambioPassword")
public class cambioPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String id = req.getParameter("id");
        UtenteDAO utentedao = new UtenteDAO();
        utentedao.doUpdatePassword(Integer.parseInt(id), password);
        RequestDispatcher dispatcher = req.getRequestDispatcher("areaUtente.jsp");
        dispatcher.forward(req, resp);
    }
}
