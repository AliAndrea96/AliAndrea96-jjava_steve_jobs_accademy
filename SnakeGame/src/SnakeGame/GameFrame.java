package SnakeGame;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GameFrame() {
        // Crea un'istanza di GamePanel e la aggiunge alla finestra
        this.add(new GamePanel());
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false); // Impedisce il ridimensionamento
        this.pack(); // Adatta la finestra alle dimensioni preferite del GamePanel
        this.setVisible(true);
        this.setLocationRelativeTo(null); // Centra la finestra
    }

    public static void main(String[] args) {
        new GameFrame(); // Crea un'istanza della finestra per avviare il gioco
    }
}