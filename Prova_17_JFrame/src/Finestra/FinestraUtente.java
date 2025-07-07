package Finestra;

import javax.swing.*;
import java.util.ArrayList;

public class FinestraUtente extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Studente> listaStudenti = new ArrayList<>();
    private ArrayList<Professore> listaProfessori = new ArrayList<>();

    public FinestraUtente() {
        setTitle("Gestione Utenti");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // schermata iniziale
        setContentPane(new SceltaUtentePanel(this));
    }

    public ArrayList<Studente> getListaStudenti() {
        return listaStudenti;
    }

    public ArrayList<Professore> getListaProfessori() {
        return listaProfessori;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FinestraUtente().setVisible(true));
    }
}