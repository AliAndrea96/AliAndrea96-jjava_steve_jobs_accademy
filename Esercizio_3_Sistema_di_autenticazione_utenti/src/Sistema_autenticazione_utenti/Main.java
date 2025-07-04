package Sistema_autenticazione_utenti;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Utente> utenti = new ArrayList<>();
        utenti.add(new Studente("Mario", "mario@studenti.it", "S12345"));
        utenti.add(new Professore("Rossi", "rossi@unict.it", "Matematica"));
        utenti.add(new Segreteria("Lucia", "lucia@unict.it", "Ufficio Iscrizioni"));

        for (Utente u : utenti) {
            u.presentati();
            if (u instanceof Autenticabile) {
                try {
                    if (u instanceof Studente) {
                        ((Autenticabile) u).autentica("studente123"); // prova password giusta
                    } else if (u instanceof Professore) {
                        ((Autenticabile) u).autentica("sbagliata"); // prova password sbagliata
                    }
                } catch (AutenticazioneException e) {
                    System.out.println("Errore di autenticazione: " + e.getMessage());
                }
            }
            System.out.println();
        }
    }
}