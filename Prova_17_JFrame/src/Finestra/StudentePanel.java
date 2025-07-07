package Finestra;

import javax.swing.*;
import java.awt.*;

public class StudentePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentePanel(FinestraUtente frame) {
        setLayout(new GridLayout(6, 2, 5, 5));

        JTextField txtNome = new JTextField();
        JTextField txtCognome = new JTextField();
        JTextField txtData = new JTextField();
        JTextField txtMatricola = new JTextField();

        JButton btnInvia = new JButton("Invia");
        JButton btnIndietro = new JButton("Indietro");

        btnInvia.addActionListener(_ -> {
            Studente s = new Studente(
                txtNome.getText(),
                txtCognome.getText(),
                txtData.getText(),
                txtMatricola.getText()
            );
            frame.getListaStudenti().add(s);
            JOptionPane.showMessageDialog(frame, "Studente aggiunto:\n" + s);
        });

        btnIndietro.addActionListener(_ -> {
            frame.setContentPane(new SceltaUtentePanel(frame));
            frame.validate();
        });

        add(new JLabel("Nome:")); add(txtNome);
        add(new JLabel("Cognome:")); add(txtCognome);
        add(new JLabel("Data di nascita:")); add(txtData);
        add(new JLabel("Matricola:")); add(txtMatricola);
        add(btnIndietro); add(btnInvia);
    }
}