package model;

import java.util.ArrayList;

public class Carrello {
    private int id;
    private int utente;
    private ArrayList<ProdottiInCarrello> prodotti;

    public Carrello() {
        this.prodotti = new ArrayList<>();
        // Costruttore vuoto
    }

    public Carrello(int id, int utente) {
        this.id = id;
        this.utente = utente;
        this.prodotti = new ArrayList<ProdottiInCarrello>();
    }

    // Getter e Setter per ogni campo

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtente() {
        return utente;
    }

    public void setUtente(int utente) {
        this.utente = utente;
    }

    public void setProdotti(ArrayList<ProdottiInCarrello> arrayList) {
        this.prodotti = arrayList;
    }

    public void addProdotto(ProdottiInCarrello p) {
        for (ProdottiInCarrello p1 : prodotti) {
            if (p1.getIdProdotto() == p.getIdProdotto()) {
                return;
            }
        }
        this.prodotti.add(p);
    }

    public float getTotale() {
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        Prodotto prodotto;
        float totale = 0;
        for (ProdottiInCarrello p : prodotti) {
            prodotto = prodottoDAO.doRetrieveByid(p.getIdProdotto());
            totale += prodotto.getPrezzo() * p.getQuantita();
        }
        return totale;
    }

    public ArrayList<ProdottiInCarrello> getProdotti() {
        return this.prodotti;
    }
}