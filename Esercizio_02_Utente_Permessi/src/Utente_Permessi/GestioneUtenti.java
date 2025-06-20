package Utente_Permessi;
import java.util.ArrayList;

class GestioneUtenti {
    private ArrayList<Utente> utenti;

    public GestioneUtenti() {
        utenti = new ArrayList<>();
    }

    public void aggiungiUtente(Utente u) {
        utenti.add(u);
    }

    public void stampaPermessi() {
        for (Utente utente : utenti) {
            utente.stampaInfo();
            System.out.println("Permessi: " + utente.getPermessi());
        }
    }
}