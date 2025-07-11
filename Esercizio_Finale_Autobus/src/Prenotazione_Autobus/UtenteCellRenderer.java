package Prenotazione_Autobus;

import javax.swing.*;
import java.awt.*;

// Questa classe personalizza il modo in cui gli elementi (Passeggero o Autista)
// vengono visualizzati all'interno delle JList.
public class UtenteCellRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 1L; // Necessario per le classi serializzabili

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        // Chiama il metodo del superclass per ottenere il componente base (es. una JLabel)
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // Controlla il tipo di oggetto e formatta il testo di conseguenza
        if (value instanceof Passeggero) {
            Passeggero passeggero = (Passeggero) value;
            setText(passeggero.getNome() + " " + passeggero.getCognome() + " (Dest: " + passeggero.getDestinazione() + ")");
        } else if (value instanceof Autista) {
            Autista autista = (Autista) value;
            setText(autista.getNome() + " " + autista.getCognome() + " (Pat: " + autista.getPatente() + ")");
        } else if (value != null) {
            // Caso generico o per Utente se non Passeggero/Autista specifici
            if (value instanceof Utente) {
                Utente utente = (Utente) value;
                setText(utente.getNome() + " " + utente.getCognome() + " (" + utente.getTipoUtente() + ")");
            } else {
                setText(value.toString()); // Fallback al toString() predefinito
            }
        }
        return this; // Restituisce il componente (JLabel) con il testo formattato
    }
}