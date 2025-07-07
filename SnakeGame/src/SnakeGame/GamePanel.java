package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {


	private static final long serialVersionUID = 1L;
	static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    static final int DELAY = 75;

    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    int bodyParts;
    int applesEaten;
    int appleX;
    int appleY;
    char direction;
    boolean running;
    Timer timer;
    Random random;

    private JButton resetButton;
    private JPanel controlPanel;
    private JLabel scoreLabel;

    private boolean gameStarted;

    GamePanel() {
        random = new Random();
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());

        setLayout(new BorderLayout());

        controlPanel = new JPanel();
        controlPanel.setBackground(Color.DARK_GRAY);
        controlPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, 50));
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Ink Free", Font.BOLD, 25));
        controlPanel.add(scoreLabel);

        resetButton = new JButton("Ricomincia");
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        controlPanel.add(resetButton);

        add(controlPanel, BorderLayout.SOUTH);

        prepareGame();
    }

    public void prepareGame() {
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R'; // Direzione iniziale verso destra
        running = true;
        gameStarted = false;

        // --- CALCOLO E IMPOSTAZIONE POSIZIONE INIZIALE AL CENTRO ---
        // Ottieni l'altezza effettiva dell'area di gioco (escludendo il controlPanel)
        int gameAreaHeight = SCREEN_HEIGHT - controlPanel.getPreferredSize().height;

        // Calcola le coordinate centrali per la testa del serpente
        // Assicurati che siano multipli di UNIT_SIZE per allinearsi alla griglia
        int centerX = (SCREEN_WIDTH / 2 / UNIT_SIZE) * UNIT_SIZE;
        int centerY = (gameAreaHeight / 2 / UNIT_SIZE) * UNIT_SIZE;

        // Imposta la testa del serpente al centro
        x[0] = centerX;
        y[0] = centerY;

        // Imposta le parti del corpo dietro la testa
        // Dato che la direzione iniziale è 'R' (destra), le parti del corpo saranno a sinistra della testa
        for (int i = 1; i < bodyParts; i++) {
            x[i] = centerX - (i * UNIT_SIZE);
            y[i] = centerY;
        }

        updateScoreLabel();
        newApple(); // Genera la prima mela (assicurandosi che non sia sotto il serpente)

        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(DELAY, this);

        resetButton.setEnabled(false);
        setFocusable(true);
        requestFocusInWindow();
        repaint();
    }

    public void startGame() {
        if (!gameStarted) {
            timer.start();
            gameStarted = true;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int drawHeight = SCREEN_HEIGHT - controlPanel.getPreferredSize().height;
        g.setColor(Color.black);
        g.fillRect(0, 0, SCREEN_WIDTH, drawHeight);

        g.setColor(Color.red);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.green);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                g.setColor(new Color(45, 180, 0));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        }

        if (!gameStarted && running) {
            g.setColor(Color.white);
            g.setFont(new Font("Ink Free", Font.BOLD, 30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Premi una freccia per iniziare!", (SCREEN_WIDTH - metrics.stringWidth("Premi una freccia per iniziare!")) / 2, SCREEN_HEIGHT / 2);
        }

        if (!running) {
            gameOver(g);
        }
    }

    public void newApple() {
        boolean collisionWithSnake;
        int maxGameY = SCREEN_HEIGHT - controlPanel.getPreferredSize().height;

        do {
            appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            appleY = random.nextInt((int) (maxGameY / UNIT_SIZE)) * UNIT_SIZE;

            collisionWithSnake = false;
            for (int i = 0; i < bodyParts; i++) {
                if (x[i] == appleX && y[i] == appleY) {
                    collisionWithSnake = true;
                    break;
                }
            }
        } while (collisionWithSnake);
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
            updateScoreLabel();
        }
    }

    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        int maxGameY = SCREEN_HEIGHT - controlPanel.getPreferredSize().height;
        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= maxGameY) {
            running = false;
        }

        if (!running) {
            timer.stop();
            resetButton.setEnabled(true);
            updateScoreLabel();
            gameStarted = false;
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over")) / 2, (SCREEN_HEIGHT - controlPanel.getPreferredSize().height) / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            prepareGame();
        } else if (e.getSource() == timer) {
            if (running && gameStarted) {
                move();
                checkApple();
                checkCollisions();
            }
            repaint();
        }
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + applesEaten);
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (!gameStarted) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_DOWN:
                        startGame();
                        break;
                    default:
                        return;
                }
            }

            if (running) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                }
            }
        }
    }
}