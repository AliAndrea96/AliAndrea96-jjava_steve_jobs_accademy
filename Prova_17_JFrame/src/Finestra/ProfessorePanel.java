package Finestra;

import javax.swing.*;
import java.awt.*;

public class ProfessorePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfessorePanel(FinestraUtente frame) {
        setLayout(new GridLayout(6, 2, 5, 5));

        JTextField txtNome = new JTextField();
        JTextField txtCognome = new JTextField();
        JTextField txtData = new JTextField();
        JTextField txtId = new JTextField();

        JButton btnInvia = new JButton("Invia");
        JButton btnIndietro = new JButton("Indietro");

        btnInvia.addActionListener(_ -> {
            Professore p = new Professore(
                txtNome.getText(),
                txtCognome.getText(),
                txtData.getText(),
                txtId.getText()
            );
            frame.getListaProfessori().add(p);
            JOptionPane.showMessageDialog(frame, "Professore aggiunto:\n" + p);
        });

        btnIndietro.addActionListener(_ -> {
            frame.setContentPane(new SceltaUtentePanel(frame));
            frame.validate();
        });

        add(new JLabel("Nome:")); add(txtNome);
        add(new JLabel("Cognome:")); add(txtCognome);
        add(new JLabel("Data di nascita:")); add(txtData);
        add(new JLabel("Numero ID:")); add(txtId);
        add(btnIndietro); add(btnInvia);
    }
}