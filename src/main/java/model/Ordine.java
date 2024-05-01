package model;

public class Ordine {
    private int id;
    private int idCarrello;
    private int idUtente;
    private String metodoDiPagamento;
    private String stato;
    private String cap;
    private String civico;
    private String via;

    private String numTelefono;
    private float totale;

    public Ordine() {
        // Costruttore vuoto
    }

    public Ordine(int id, int idCarrello, int idUtente, String metodoDiPagamento, String stato, String cap, String civico, String via, float totale, String numTelefono) {
        this.id = id;
        this.idCarrello = idCarrello;
        this.idUtente = idUtente;
        this.metodoDiPagamento = metodoDiPagamento;
        this.stato = stato;
        this.cap = cap;
        this.civico = civico;
        this.via = via;
        this.totale = totale;
        this.numTelefono = numTelefono;
    }

    // Getter e Setter per ogni campo

    public String getNumTelefono() {
        return this.numTelefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getMetodoDiPagamento() {
        return metodoDiPagamento;
    }

    public void setMetodoDiPagamento(String metodoDiPagamento) {
        this.metodoDiPagamento = metodoDiPagamento;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCivico() {
        return civico;
    }

    public void setCivico(String civico) {
        this.civico = civico;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }
}