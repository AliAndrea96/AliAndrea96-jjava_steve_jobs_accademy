package IndovinaNumeroGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    // Componenti dell'Interfaccia Utente (GUI)
    private JLabel messaggioLabel;
    private JTextField inputField;
    private JButton indovinaButton;
    private JButton resetButton;

    // Variabili di Stato del Gioco
    private int numeroDaIndovinare;
    private int tentativiMassimi = 10;
    private int tentativiFatti = 0;
    private Random random;

    public Game() {
        // --- Configurazione della Finestra del Gioco (JFrame) ---
        setTitle("Indovina il Numero!");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        random = new Random();

        // --- Creazione e Configurazione del Pannello Principale (JPanel) ---
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // 1. Inizializzazione della JLabel per i messaggi
        messaggioLabel = new JLabel();
        messaggioLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(messaggioLabel);

        // 2. Inizializzazione del JTextField per l'input dell'utente
        inputField = new JTextField(10);
        inputField.setHorizontalAlignment(JTextField.CENTER);
        panel.add(inputField);

        // 3. Inizializzazione del JButton "Indovina!"
        indovinaButton = new JButton("Indovina!");
        indovinaButton.addActionListener(this);
        panel.add(indovinaButton);

        // 4. Inizializzazione del JButton "Ricomincia"
        resetButton = new JButton("Ricomincia");
        resetButton.addActionListener(this);
        resetButton.setEnabled(false);
        panel.add(resetButton);

        add(panel);
        setVisible(true);

        // Chiama il metodo di reset all'avvio dell'applicazione per configurare la prima partita
        resetGame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == indovinaButton) {
            processaTentativo();
        } else if (e.getSource() == resetButton) {
            resetGame();
        }
    }

    private void processaTentativo() {
        try {
            int inputUtente = Integer.parseInt(inputField.getText());
            tentativiFatti++;

            if (inputUtente == numeroDaIndovinare) {
                messaggioLabel.setText("Complimenti! Hai indovinato il numero " + numeroDaIndovinare + " in " + tentativiFatti + " tentativi!");
                disabilitaControlli();
                resetButton.setEnabled(true);
            } else {
                String feedbackDirezione = ""; // "Troppo alto!" o "Troppo basso!"
                String feedbackVicinanza = ""; // "Fuochissimo!", "Freddo!", ecc.

                int differenza = Math.abs(numeroDaIndovinare - inputUtente);

                // Determina il feedback sulla direzione
                if (inputUtente < numeroDaIndovinare) {
                    feedbackDirezione = "Il tuo numero è basso! ";
                } else {
                    feedbackDirezione = "Il tuo numero è alto! ";
                }

                // --- Logica migliorata dei suggerimenti basata sulla differenza ---
                if (differenza <= 1) { // Estremamente vicino
                    feedbackVicinanza = "Fuochissimo!";
                } else if (differenza <= 3) { // Molto vicino
                    feedbackVicinanza = "Fuoco!";
                } else if (differenza <= 8) { // Abbastanza vicino
                    feedbackVicinanza = "Fuochino!";
                } else if (differenza <= 15) { // Ti stai avvicinando
                    feedbackVicinanza = "Tiepido..";
                } else if (differenza <= 25) { // Un po' lontano
                    feedbackVicinanza = "Freddo.";
                } else { // Molto lontano
                    feedbackVicinanza = "Ghiacciato!";
                }
                
                messaggioLabel.setText(feedbackDirezione + feedbackVicinanza + " Tentativi rimasti: " + (tentativiMassimi - tentativiFatti));
            }

            // Controlla se i tentativi sono esauriti
            if (tentativiFatti >= tentativiMassimi && inputUtente != numeroDaIndovinare) {
                messaggioLabel.setText("Hai perso! Il numero era " + numeroDaIndovinare + ". Premi Ricomincia.");
                disabilitaControlli();
                resetButton.setEnabled(true);
            }
            inputField.setText("");

        } catch (NumberFormatException ex) {
            messaggioLabel.setText("Errore: Inserisci un numero valido!");
            inputField.setText("");
        }
    }

    private void resetGame() {
        numeroDaIndovinare = random.nextInt(100) + 1;
        tentativiFatti = 0;
        messaggioLabel.setText("Ho pensato a un numero tra 1 e 100. Hai " + tentativiMassimi + " tentativi.");
        inputField.setText("");
        abilitaControlli();
        resetButton.setEnabled(false);
    }

    private void disabilitaControlli() {
        inputField.setEnabled(false);
        indovinaButton.setEnabled(false);
    }

    private void abilitaControlli() {
        inputField.setEnabled(true);
        indovinaButton.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game();
            }
        });
    }
}