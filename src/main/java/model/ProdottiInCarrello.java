package model;

public class ProdottiInCarrello {
    private int idCarrello;
    private int idUtente;
    private int idProdotto;
    private int quantita;

    public ProdottiInCarrello() {
        // Costruttore vuoto
    }

    public ProdottiInCarrello(int idCarrello, int idUtente, int idProdotto, int quantita) {
        this.idCarrello = idCarrello;
        this.idUtente = idUtente;
        this.idProdotto = idProdotto;
        this.quantita = quantita;
    }

    // Getter e Setter per ogni campo

    public int getIdCarrello() {
        return idCarrello;
    }

    public void setIdCarrello(int idCarrello) {
        this.idCarrello = idCarrello;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}