package model;

public class Prodotto {
    private int id;
    private String nome;
    private float prezzo;
    private boolean disponibilita;
    private int categoria;

    private String descrizione;

    public Prodotto() {
        // Costruttore vuoto
    }

    public Prodotto(int id, String nome, float prezzo, boolean disponibilita, int categoria, String descrizione) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.disponibilita = disponibilita;
        this.categoria = categoria;
        this.descrizione = descrizione;
    }

    // Getter e Setter per ogni campo

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public boolean getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}