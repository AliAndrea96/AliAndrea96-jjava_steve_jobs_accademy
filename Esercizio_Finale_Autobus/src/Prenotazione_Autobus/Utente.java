package Prenotazione_Autobus;

import java.io.Serializable; // Importa Serializable

public abstract class Utente implements Serializable { // Aggiungi implements Serializable
    private static final long serialVersionUID = 1L; // Aggiungi serialVersionUID

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private TipoUtente tipoUtente; // Enum per tipo di utente

    public Utente(String nome, String cognome, String codiceFiscale, TipoUtente tipoUtente) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.tipoUtente = tipoUtente;
    }

    // ... (resto del codice invariato)
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getCodiceFiscale() { return codiceFiscale; }
    public TipoUtente getTipoUtente() { return tipoUtente; }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Cognome: " + cognome + ", CF: " + codiceFiscale;
    }
}