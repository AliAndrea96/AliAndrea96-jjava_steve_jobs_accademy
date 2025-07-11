package Prenotazione_Autobus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Importazioni aggiuntive per il DocumentFilter
import javax.swing.text.PlainDocument;
import javax.swing.text.AbstractDocument;

import javax.swing.DefaultListModel;

public class PrenotazioneAutobusGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final int MAX_PASSEGGERI = 20;
    private static final int MAX_AUTISTI = 2;
    private static final int MAX_POSTI_DISABILI = 2;

    // Rimosso: private static final String DATA_FILE = "autobus_data.ser";

    private int postiPasseggeriRimanenti = MAX_PASSEGGERI;
    private int postiAutistiRimanenti = MAX_AUTISTI;
    private int postiDisabiliRimanenti = MAX_POSTI_DISABILI;

    private JLabel labelPostiPasseggeri;
    private JLabel labelPostiAutisti;
    private JLabel labelPostiDisabili;

    private JButton btnAggiungiPasseggero;
    private JButton btnRimuoviPasseggero;
    private JButton btnAggiungiAutista;
    private JButton btnRimuoviAutista;

    // Rimosso: private ArrayList<UtenteAutobus> utentiRegistrati;
    private DefaultListModel<Passeggero> passeggeriListModel;
    private JList<Passeggero> passeggeriJList;

    private DefaultListModel<Autista> autistiListModel;
    private JList<Autista> autistiJList;

    private final String[] destinazioni = {"Roma", "Milano", "Napoli", "Firenze", "Venezia", "Palermo", "Catania"};

    public PrenotazioneAutobusGUI() {
        setTitle("Sistema di Prenotazione Autobus");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Impostato su DISPOSE_ON_CLOSE, System.exit(0) chiamato nel listener
        setLocationRelativeTo(null);
        setResizable(true);

        // Gli utentiRegistrati non sono più necessari se non salviamo/carichiamo
        // passeggeriListModel e autistiListModel gestiscono direttamente lo stato GUI
        passeggeriListModel = new DefaultListModel<>();
        autistiListModel = new DefaultListModel<>();

        // Rimosso: 1. caricaDati();
        // Rimosso: 2. popolaListeDaUtentiRegistrati();
        // Ora l'applicazione parte sempre con i posti pieni e liste vuote

        // --- INIZIALIZZAZIONE DI TUTTI I COMPONENTI DELLA GUI ---

        // Pannello Superiore (JLabel)
        JPanel topPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        labelPostiPasseggeri = new JLabel("Posti Passeggeri Rimanenti: " + postiPasseggeriRimanenti);
        labelPostiAutisti = new JLabel("Posti Autisti Rimanenti: " + postiAutistiRimanenti);
        labelPostiDisabili = new JLabel("Posti Disabili Rimanenti: " + postiDisabiliRimanenti);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        labelPostiPasseggeri.setFont(labelFont);
        labelPostiAutisti.setFont(labelFont);
        labelPostiDisabili.setFont(labelFont);

        topPanel.add(labelPostiPasseggeri);
        topPanel.add(labelPostiAutisti);
        topPanel.add(labelPostiDisabili);

        // Pannelli Centrali (JList e JButton)
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.add(topPanel, BorderLayout.NORTH); // Aggiungi il topPanel al mainPanel

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 0));

        // Pannello Passeggeri
        JPanel passeggeriPanel = new JPanel(new BorderLayout(5, 5));
        passeggeriPanel.setBorder(BorderFactory.createTitledBorder("Passeggeri Registrati"));
        passeggeriJList = new JList<>(passeggeriListModel);
        passeggeriJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        passeggeriJList.setCellRenderer(new UtenteCellRenderer()); // Assicurati che UtenteCellRenderer sia definito o rimosso se non usato
        JScrollPane passeggeriScrollPane = new JScrollPane(passeggeriJList);
        passeggeriPanel.add(passeggeriScrollPane, BorderLayout.CENTER);

        JPanel passeggeriButtonsPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        btnAggiungiPasseggero = new JButton("Aggiungi Passeggero");
        btnRimuoviPasseggero = new JButton("Rimuovi Passeggero");
        passeggeriButtonsPanel.add(btnAggiungiPasseggero);
        passeggeriButtonsPanel.add(btnRimuoviPasseggero);
        passeggeriPanel.add(passeggeriButtonsPanel, BorderLayout.SOUTH);
        centerPanel.add(passeggeriPanel);

        // Pannello Autisti
        JPanel autistiPanel = new JPanel(new BorderLayout(5, 5));
        autistiPanel.setBorder(BorderFactory.createTitledBorder("Autisti Registrati"));
        autistiJList = new JList<>(autistiListModel);
        autistiJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        autistiJList.setCellRenderer(new UtenteCellRenderer()); // Assicurati che UtenteCellRenderer sia definito o rimosso se non usato
        JScrollPane autistiScrollPane = new JScrollPane(autistiJList);
        autistiPanel.add(autistiScrollPane, BorderLayout.CENTER);

        JPanel autistiButtonsPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        btnAggiungiAutista = new JButton("Aggiungi Autista");
        btnRimuoviAutista = new JButton("Rimuovi Autista");
        autistiButtonsPanel.add(btnAggiungiAutista);
        autistiButtonsPanel.add(btnRimuoviAutista);
        autistiPanel.add(autistiButtonsPanel, BorderLayout.SOUTH);
        centerPanel.add(autistiPanel);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel); // Aggiungi il pannello principale al frame

        aggiornaEtichette(); // Aggiorna le etichette con i valori iniziali

        // Aggiunta degli ActionListener
        btnAggiungiPasseggero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiPasseggero();
            }
        });

        btnRimuoviPasseggero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rimuoviPasseggero();
            }
        });

        btnAggiungiAutista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiAutista();
            }
        });

        btnRimuoviAutista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rimuoviAutista();
            }
        });

        // Aggiungi un WindowListener per gestire la chiusura della finestra
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Rimosso: salvaDati();
                dispose(); // Chiudi la finestra
                System.exit(0); // Termina l'applicazione
            }
        });

        setVisible(true); // Rendi la finestra visibile
    }

    // Rimosso: private void popolaListeDaUtentiRegistrati() { ... }
    // Rimosso: private void salvaDati() { ... }
    // Rimosso: private void caricaDati() { ... }

    private void aggiungiPasseggero() {
        try {
            if (postiPasseggeriRimanenti <= 0) {
                throw new PostiEsauritiPasseggeroException("Tutti i posti per i passeggeri sono esauriti!");
            }

            String nome = JOptionPane.showInputDialog(this, "Inserisci il nome del passeggero:", "Aggiungi Passeggero", JOptionPane.QUESTION_MESSAGE);
            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Operazione annullata o nome non inserito.", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String cognome = JOptionPane.showInputDialog(this, "Inserisci il cognome del passeggero:", "Aggiungi Passeggero", JOptionPane.QUESTION_MESSAGE);
            if (cognome == null || cognome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Operazione annullata o cognome non inserito.", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String codiceFiscale = getCodiceFiscaleInput("Aggiungi Passeggero");
            if (codiceFiscale == null) {
                JOptionPane.showMessageDialog(this, "Operazione annullata o codice fiscale non valido. Deve essere di 16 caratteri alfanumerici.", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JComboBox<String> destinazioneComboBox = new JComboBox<>(destinazioni);
            int result = JOptionPane.showConfirmDialog(this, destinazioneComboBox, "Seleziona la destinazione:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            String destinazione;
            if (result == JOptionPane.OK_OPTION) {
                destinazione = (String) destinazioneComboBox.getSelectedItem();
            } else {
                JOptionPane.showMessageDialog(this, "Operazione annullata o destinazione non selezionata.", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int sceltaBagaglio = JOptionPane.showConfirmDialog(this, "Il passeggero ha un bagaglio?", "Bagaglio", JOptionPane.YES_NO_OPTION);
            boolean haBagaglio = (sceltaBagaglio == JOptionPane.YES_OPTION);

            int sceltaSediaRotelle = JOptionPane.showConfirmDialog(this, "Il passeggero è in sedia a rotelle?", "Sedia a Rotelle", JOptionPane.YES_NO_OPTION);
            boolean inSediaARotelle = (sceltaSediaRotelle == JOptionPane.YES_OPTION);

            if (inSediaARotelle) {
                if (postiDisabiliRimanenti <= 0) {
                    JOptionPane.showMessageDialog(this, "Non ci sono posti disabili disponibili!", "Errore Posti Disabili", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                postiDisabiliRimanenti--;
            }

            Passeggero nuovoPasseggero = new Passeggero(nome, cognome, codiceFiscale, destinazione, haBagaglio, inSediaARotelle);
            // Non c'è più bisogno dell'ArrayList utentiRegistrati
            passeggeriListModel.addElement(nuovoPasseggero);
            postiPasseggeriRimanenti--;
            aggiornaEtichette();
            JOptionPane.showMessageDialog(this, "Passeggero aggiunto con successo!\n" + nuovoPasseggero.getNome() + " " + nuovoPasseggero.getCognome(), "Successo", JOptionPane.INFORMATION_MESSAGE);

        } catch (PostiEsauritiPasseggeroException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rimuoviPasseggero() {
        int selectedIndex = passeggeriJList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona un passeggero da rimuovere dalla lista.", "Nessuna Selezione", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Passeggero passeggeroDaRimuovere = passeggeriListModel.getElementAt(selectedIndex);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Sei sicuro di voler rimuovere il passeggero:\n" + passeggeroDaRimuovere.getNome() + " " + passeggeroDaRimuovere.getCognome() + "?",
                "Conferma Rimozione", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            passeggeriListModel.remove(selectedIndex);
            postiPasseggeriRimanenti++;
            if (passeggeroDaRimuovere.isInSediaARotelle()) {
                postiDisabiliRimanenti++;
            }
            aggiornaEtichette();
            JOptionPane.showMessageDialog(this, "Passeggero rimosso con successo.", "Rimozione Effettuata", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void aggiungiAutista() {
        try {
            if (postiAutistiRimanenti <= 0) {
                throw new PostiEsauritiAutistaException("Tutti i posti per gli autisti sono esauriti!");
            }

            String nome = JOptionPane.showInputDialog(this, "Inserisci il nome dell'autista:", "Aggiungi Autista", JOptionPane.QUESTION_MESSAGE);
            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Operazione annullata o nome non inserito.", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String cognome = JOptionPane.showInputDialog(this, "Inserisci il cognome dell'autista:", "Aggiungi Autista", JOptionPane.QUESTION_MESSAGE);
            if (cognome == null || cognome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Operazione annullata o cognome non inserito.", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String codiceFiscale = getCodiceFiscaleInput("Aggiungi Autista");
            if (codiceFiscale == null) {
                JOptionPane.showMessageDialog(this, "Operazione annullata o codice fiscale non valido. Deve essere di 16 caratteri alfanumerici.", "Attenzione", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int sceltaPatente = JOptionPane.showConfirmDialog(this,
                    "Hai una patente D o superiore?", "Verifica Patente", JOptionPane.YES_NO_OPTION);

            if (sceltaPatente == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "Non puoi essere l'autista di quest'autobus senza una patente D o superiore.", "Qualifiche Insufficienti", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String patente = "D/Superiore";

            String anniExpStr = JOptionPane.showInputDialog(this, "Inserisci gli anni di esperienza (0-60):", "Aggiungi Autista", JOptionPane.QUESTION_MESSAGE);
            int anniEsperienza;
            try {
                anniEsperienza = Integer.parseInt(anniExpStr);
                if (anniEsperienza < 0 || anniEsperienza > 60) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Anni di esperienza non validi. Inserisci un numero intero tra 0 e 60.", "Errore Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Autista nuovoAutista = new Autista(nome, cognome, codiceFiscale, patente, anniEsperienza);
            // Non c'è più bisogno dell'ArrayList utentiRegistrati
            autistiListModel.addElement(nuovoAutista);
            postiAutistiRimanenti--;
            aggiornaEtichette();
            JOptionPane.showMessageDialog(this, "Autista aggiunto con successo!\n" + nuovoAutista.getNome() + " " + nuovoAutista.getCognome(), "Successo", JOptionPane.INFORMATION_MESSAGE);
        } catch (PostiEsauritiAutistaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void rimuoviAutista() {
        int selectedIndex = autistiJList.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Seleziona un autista da rimuovere dalla lista.", "Nessuna Selezione", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Autista autistaDaRimuovere = autistiListModel.getElementAt(selectedIndex);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Sei sicuro di voler rimuovere l'autista:\n" + autistaDaRimuovere.getNome() + " " + autistaDaRimuovere.getCognome() + "?",
                "Conferma Rimozione", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            autistiListModel.remove(selectedIndex);
            postiAutistiRimanenti++;
            aggiornaEtichette();
            JOptionPane.showMessageDialog(this, "Autista rimosso con successo.", "Rimozione Effettuata", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void aggiornaEtichette() {
        labelPostiPasseggeri.setText("Posti Passeggeri Rimanenti: " + postiPasseggeriRimanenti);
        labelPostiAutisti.setText("Posti Autisti Rimanenti: " + postiAutistiRimanenti);
        labelPostiDisabili.setText("Posti Disabili Rimanenti: " + postiDisabiliRimanenti);

        btnAggiungiPasseggero.setEnabled(postiPasseggeriRimanenti > 0);
        btnAggiungiAutista.setEnabled(postiAutistiRimanenti > 0);

        btnRimuoviPasseggero.setEnabled(!passeggeriListModel.isEmpty());
        btnRimuoviAutista.setEnabled(!autistiListModel.isEmpty());
    }

    private String getCodiceFiscaleInput(String dialogTitle) {
        JTextField codiceFiscaleField = new JTextField(16);
        codiceFiscaleField.setDocument(new PlainDocument());
        ((AbstractDocument) codiceFiscaleField.getDocument()).setDocumentFilter(new LengthDocumentFilter(16));

        JPanel cfPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cfPanel.add(new JLabel("Inserisci il codice fiscale (16 caratteri alfanumerici):"));
        cfPanel.add(codiceFiscaleField);

        int cfResult = JOptionPane.showConfirmDialog(this, cfPanel, dialogTitle,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (cfResult == JOptionPane.OK_OPTION) {
            String codiceFiscale = codiceFiscaleField.getText().trim();
            if (codiceFiscale.matches("[a-zA-Z0-9]{16}")) {
                return codiceFiscale;
            } else {
                return null;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Impossibile impostare il Look and Feel Nimbus: " + e);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PrenotazioneAutobusGUI();
            }
        });
    }
}