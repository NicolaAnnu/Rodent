package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Prodotto;
import model.ProdottoDAO;
import model.Utente;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "nuovoProdotto", value = "/nuovoProdotto")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // Dimensione soglia dei file
        maxFileSize = 1024 * 1024 * 10, // Dimensione massima dei file
        maxRequestSize = 1024 * 1024 * 20 // Dimensione massima della richiesta
)
public class nuovoProdotto extends HttpServlet {
    private static final String CARTELLA_UPLOAD = "images";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utente utente = (Utente) req.getSession().getAttribute("utente");
        if (utente == null || utente.getRuolo() != 0) {
            resp.sendError(403, "Utente non autorizzato");
            return;
        }
        String nome = req.getParameter("nome");
        String prezzoParameter = req.getParameter("prezzo");
        String descrizione = req.getParameter("descrizione");
        String disponibilitaParameter = req.getParameter("disponibilita");
        String categoriaParameter = req.getParameter("categoria");
        List<Part> fileParts = req.getParts().stream().filter(part -> "image".equals(part.getName()) && part.getSize() > 0).collect(Collectors.toList());

        checkDati(nome, prezzoParameter, categoriaParameter, fileParts, disponibilitaParameter, resp);

        float prezzo = 0;
        int categoria = 0;

        try {
            prezzo = Float.parseFloat(prezzoParameter);
            categoria = Integer.parseInt(categoriaParameter);
        } catch (Exception e) {
            resp.sendError(400, "Valore prezzo non valido");
            return;
        }
        boolean disponibilita = Boolean.valueOf(disponibilitaParameter);
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        Prodotto prodotto = new Prodotto();
        prodotto.setNome(nome);
        prodotto.setPrezzo(prezzo);
        prodotto.setDisponibilita(disponibilita);
        prodotto.setDescrizione(descrizione);
        prodotto.setCategoria(categoria);
        int idProdotto = prodottoDAO.doSave(prodotto);

        String Imagepath = CARTELLA_UPLOAD + File.separator + idProdotto;
        int i = 1;
        for (Part filePart : fileParts) {
            String fileName = i + ".jpg";
            i++;
            String destinazione = Imagepath + File.separator + fileName;
            Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));

            InputStream fileInputStream = filePart.getInputStream();
            // crea CARTELLA_UPLOAD, se non esiste
            Files.createDirectories(pathDestinazione.getParent());
            // scrive il file
            Files.copy(fileInputStream, pathDestinazione);
        }

        resp.sendRedirect("areaAdmin.jsp");

    }

    private void checkDati(String nome, String prezzoParameter, String categoriaParameter, List<Part> fileParts, String disponibilitaParameter, HttpServletResponse resp) throws IOException {
        if (nome == null || nome.isEmpty() && prezzoParameter == null || prezzoParameter.isEmpty() || categoriaParameter == null || categoriaParameter.isEmpty()) {
            resp.sendError(401, "Parametri invalidi");
            return;
        }
        if (!categoriaParameter.equals("1") && !categoriaParameter.equals("2") && !categoriaParameter.equals("3") && !categoriaParameter.equals("4")) {
            resp.sendError(401, "Categoria non valida");
            return;
        }

        if (fileParts.size() == 0) {
            resp.sendError(401, "Nessuna immagine caricata");
            return;
        }
        if (!disponibilitaParameter.equals("true") && !disponibilitaParameter.equals("false")) {
            resp.sendError(400, "Valore della disponibilità non valido");
            return;
        }
    }
}