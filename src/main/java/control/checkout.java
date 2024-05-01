package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "checkout", value = "/checkout")
public class checkout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String telefono = request.getParameter("telefono");
        String via = request.getParameter("via");
        String civico = request.getParameter("civico");
        String cap = request.getParameter("cap");
        String metodoPagamento = request.getParameter("metodo-di-pagamento");

        boolean isValid = validateInputs(email, nome, cognome, telefono, via, civico, cap, metodoPagamento);
        if (!isValid) {
            response.sendError(400, "Campi non validi");
            return;
        }
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
        synchronized (carrello) {
            boolean flag = false;
            ProdottoDAO prodottoDAO = new ProdottoDAO();
            ProdottiInCarrelloDAO prodottiInCarrelloDAO = new ProdottiInCarrelloDAO();
            ArrayList<ProdottiInCarrello> prodotti = carrello.getProdotti();
            for (int i = 0; i < prodotti.size(); i++) {
                ProdottiInCarrello prodottoAttuale = prodotti.get(i);
                System.out.println("Controllo" + prodottoAttuale.getIdProdotto());
                if (!prodottoDAO.doRetrieveByid(prodottoAttuale.getIdProdotto()).getDisponibilita()) {
                    flag = true;
                    prodotti.remove(i);
                    prodottiInCarrelloDAO.doDelete(prodottoAttuale);
                    System.out.println("Rimosso " + prodottoAttuale.getIdProdotto());
                    i--;
                }
            }

            if (flag) {
                response.sendError(409, "Uno o più prodotti non sono più disponibili");
                return;
            }
            Ordine ordine = new Ordine();
            CarrelloDAO carrelloDAO = new CarrelloDAO();
            OrdineDAO ordineDAO = new OrdineDAO();
            ordine.setStato("Inviato");
            ordine.setIdUtente(utente.getId());
            ordine.setIdCarrello(carrello.getId());
            ordine.setVia(via);
            ordine.setCivico(civico);
            ordine.setCap(cap);
            ordine.setNumTelefono(telefono);
            ordine.setMetodoDiPagamento(metodoPagamento);
            ordine.setTotale(carrello.getTotale());
            ordineDAO.insert(ordine);
            Carrello newCarrello = new Carrello();
            newCarrello.setUtente(carrello.getUtente());
            int idCarrello = carrelloDAO.doSave(carrello.getUtente());
            newCarrello.setId(idCarrello);
            request.getSession().setAttribute("carrello", newCarrello);

        }
    }

    private boolean validateInputs(String email, String nome, String cognome, String telefono,
                                   String via, String civico, String cap, String metodoPagamento) {
        if (email == null || email.isEmpty() || nome == null || nome.isEmpty() ||
                cognome == null || cognome.isEmpty() || telefono == null || telefono.isEmpty() ||
                via == null || via.isEmpty() ||
                civico == null || civico.isEmpty() || cap == null || cap.isEmpty() ||
                metodoPagamento == null || metodoPagamento.isEmpty()) {
            return false;
        }

        boolean isEmailValid = email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

        boolean isPhoneValid = telefono.matches("\\d{10}");

        boolean isMetodoPagamentoValid = metodoPagamento.equals("Carta di credito") || metodoPagamento.equals("PayPal") || metodoPagamento.equals("Bonifico");
        return isEmailValid && isPhoneValid && isMetodoPagamentoValid;
    }
}