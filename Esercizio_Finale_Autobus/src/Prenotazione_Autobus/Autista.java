package Prenotazione_Autobus;

// Questa classe estende Utente, che ora è Serializable, quindi lo è anche Autista
public class Autista extends Utente {
    private static final long serialVersionUID = 1L; // Aggiungi serialVersionUID

    private String patente;
    private int anniEsperienza;

    public Autista(String nome, String cognome, String codiceFiscale, String patente, int anniEsperienza) {
        super(nome, cognome, codiceFiscale, TipoUtente.AUTISTA);
        this.patente = patente;
        this.anniEsperienza = anniEsperienza;
    }

    // ... (resto del codice invariato)
    public String getPatente() { return patente; }
    public int getAnniEsperienza() { return anniEsperienza; }

    @Override
    public String toString() {
        return super.toString() + ", Patente: " + patente + ", Anni Exp: " + anniEsperienza;
    }
}