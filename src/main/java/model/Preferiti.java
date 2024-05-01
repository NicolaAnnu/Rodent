package model;

public class Preferiti {
    private int idUtente;
    private int idProdotto;

    public Preferiti() {
        // Costruttore vuoto
    }

    public Preferiti(int idUtente, int idProdotto) {
        this.idUtente = idUtente;
        this.idProdotto = idProdotto;
    }

    // Getter e Setter per ogni campo

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
}