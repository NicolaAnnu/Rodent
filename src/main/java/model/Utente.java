package model;

public class Utente {
    private int id;
    private int ruolo;
    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String telefono;
    private String cap;
    private String civico;
    private String via;

    public Utente(int ruolo, String username, String email, String password, String nome, String cognome,
                  String telefono, String cap, String civico, String via) {
        this.ruolo = ruolo;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.cap = cap;
        this.civico = civico;
        this.via = via;
    }
    public Utente(){}

    // Getter e Setter per ogni campo
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getRuolo() {
        return ruolo;
    }

    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNumTelefono() {
        return telefono;
    }

    public void setNumTelefono(String numTelefono) {
        this.telefono = numTelefono;
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
