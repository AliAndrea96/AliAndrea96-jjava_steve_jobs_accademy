package Finestra;

import javax.swing.*;
import java.awt.*;

public class SceltaUtentePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SceltaUtentePanel(FinestraUtente frame) {
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnStudente = new JButton("Sono Studente");
        JButton btnProfessore = new JButton("Sono Professore");
        JButton btnMostra = new JButton("Mostra tutti");

        btnStudente.addActionListener(_ -> {
            frame.setContentPane(new StudentePanel(frame));
            frame.validate();
        });

        btnProfessore.addActionListener(_ -> {
            frame.setContentPane(new ProfessorePanel(frame));
            frame.validate();
        });

        btnMostra.addActionListener(_ -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Studenti:\n");
            for (Studente s : frame.getListaStudenti()) {
                sb.append("- ").append(s).append("\n");
            }
            sb.append("\nProfessori:\n");
            for (Professore p : frame.getListaProfessori()) {
                sb.append("- ").append(p).append("\n");
            }
            JOptionPane.showMessageDialog(frame, sb.toString(), "Tutti i dati inseriti", JOptionPane.INFORMATION_MESSAGE);
        });

        add(new JLabel("Seleziona tipo utente:", SwingConstants.CENTER));
        add(btnStudente);
        add(btnProfessore);
        add(btnMostra);
    }
}