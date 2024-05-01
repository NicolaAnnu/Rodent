package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Utente;
import model.UtenteDAO;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "modify", value = "/modifyUser")
public class updateUser extends HttpServlet {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PHONE_REGEX = "^[0-9]{10}$";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String telefono = request.getParameter("telefono");
        String cap = request.getParameter("cap");
        String civico = request.getParameter("civico");
        String via = request.getParameter("via");

        if (username == null || username.isEmpty() ||
                email == null || email.isEmpty() || !isValidEmail(email) ||
                nome == null || nome.isEmpty() ||
                cognome == null || cognome.isEmpty() ||
                telefono == null || telefono.isEmpty() || !isValidPhoneNumber(telefono) ||
                cap == null || cap.isEmpty() ||
                civico == null || civico.isEmpty() ||
                via == null || via.isEmpty()) {
            response.sendError(401, "Uno dei dati forniti è nullo");
        }
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (id != utente.getId()) {
            response.sendError(403);
            return;
        }
        synchronized (utente) {
            utente.setUsername(username);
            utente.setEmail(email);
            utente.setNome(nome);
            utente.setCognome(cognome);
            utente.setNumTelefono(telefono);
            utente.setCap(cap);
            utente.setCivico(civico);
            utente.setVia(via);
        }
        UtenteDAO utenteDAO = new UtenteDAO();
        utenteDAO.doUpdate(utente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("areaUtente.jsp");
        dispatcher.forward(request, response);
    }

    private static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return Pattern.matches(PHONE_REGEX, phoneNumber);
    }
}
