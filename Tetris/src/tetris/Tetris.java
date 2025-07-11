package tetris; // Assicurati che il package sia corretto

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.SwingUtilities; // Import aggiuntivo per SwingUtilities

public class Tetris extends JFrame {

    private static final long serialVersionUID = 1L;
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;
    public static final int BLOCK_SIZE = 30; // Dimensione di un singolo blocco in pixel

    private GameBoard gameBoard;

    public Tetris() {
        setTitle("Tetris Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Non permettiamo il ridimensionamento della finestra

        // Inizializza la GameBoard (dove avviene il gioco)
        gameBoard = new GameBoard();
        gameBoard.setPreferredSize(new Dimension(BOARD_WIDTH * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE));

        add(gameBoard, BorderLayout.CENTER); // Aggiungi la GameBoard al centro del frame
        pack(); // Adatta la dimensione del frame ai componenti
        setLocationRelativeTo(null); // Centra la finestra sullo schermo

        // Avvia la logica di gioco in un thread separato
        gameBoard.startGame();
    }

    public static void main(String[] args) {
        // Esegui l'applicazione nell'Event Dispatch Thread (necessario per Swing)
        // Creiamo un'istanza di Tetris e poi la rendiamo visibile.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Tetris game = new Tetris(); // Crea l'istanza della finestra
                game.setVisible(true);       // Rendi l'istanza visibile
            }
        });
    }
}