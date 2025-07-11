package Prenotazione_Autobus;

// Questa classe estende Utente, che ora è Serializable, quindi lo è anche Passeggero
public class Passeggero extends Utente {
    private static final long serialVersionUID = 1L; // Aggiungi serialVersionUID

    private String destinazione;
    private boolean haBagaglio;
    private boolean inSediaARotelle;

    public Passeggero(String nome, String cognome, String codiceFiscale, String destinazione, boolean haBagaglio, boolean inSediaARotelle) {
        super(nome, cognome, codiceFiscale, TipoUtente.PASSEGGERO);
        this.destinazione = destinazione;
        this.haBagaglio = haBagaglio;
        this.inSediaARotelle = inSediaARotelle;
    }

    // ... (resto del codice invariato)
    public String getDestinazione() { return destinazione; }
    public boolean haBagaglio() { return haBagaglio; }
    public boolean isInSediaARotelle() { return inSediaARotelle; }

    @Override
    public String toString() {
        return super.toString() + ", Destinazione: " + destinazione +
               ", Bagaglio: " + (haBagaglio ? "Sì" : "No") +
               ", Sedia a Rotelle: " + (inSediaARotelle ? "Sì" : "No");
    }
}