package tetris; // Assicurati che il package sia corretto

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.FontMetrics;

public class GameBoard extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private Timer timer;
    private Color[][] board;
    private Tetromino currentTetrominoType; // Ora memorizziamo solo il TIPO di Tetromino
    private int currentRotationIndex;       // E gestiamo l'INDICE di rotazione separatamente
    private int currentX, currentY;

    private boolean isPaused = false;
    private boolean isGameOver = false;

    public GameBoard() {
        setFocusable(true);
        setBackground(Color.BLACK);

        board = new Color[Tetris.BOARD_HEIGHT][Tetris.BOARD_WIDTH];
        for (int i = 0; i < Tetris.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Tetris.BOARD_WIDTH; j++) {
                board[i][j] = Color.BLACK;
            }
        }

        addKeyListener(new TAdapter());
        timer = new Timer(400, this);
    }

    public void startGame() {
        if (isGameOver) {
            resetGame();
        }
        spawnNewTetromino();
        timer.start();
    }

    private void resetGame() {
        for (int i = 0; i < Tetris.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Tetris.BOARD_WIDTH; j++) {
                board[i][j] = Color.BLACK;
            }
        }
        isGameOver = false;
        isPaused = false;
        currentTetrominoType = null;
        currentRotationIndex = 0; // Reset dell'indice di rotazione
        repaint();
    }

    private void spawnNewTetromino() {
        currentTetrominoType = Tetromino.getRandomTetromino(); // Ottiene un tipo di tetromino casuale
        currentRotationIndex = 0; // Inizia sempre dalla prima rotazione
        currentX = Tetris.BOARD_WIDTH / 2 - currentTetrominoType.getShape(currentRotationIndex)[0].length / 2;
        currentY = 0;

        if (!isValidPosition(currentTetrominoType.getShape(currentRotationIndex), currentX, currentY)) {
            timer.stop();
            isGameOver = true;
            int response = JOptionPane.showConfirmDialog(this, "Game Over!\nVuoi giocare di nuovo?", "Fine del Gioco", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                startGame();
            } else {
                System.exit(0);
            }
        }
    }

    private boolean isValidPosition(int[][] shape, int x, int y) {
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[row][col] != 0) {
                    int boardX = x + col;
                    int boardY = y + row;

                    if (boardX < 0 || boardX >= Tetris.BOARD_WIDTH || boardY >= Tetris.BOARD_HEIGHT) {
                        return false;
                    }
                    if (boardY >= 0 && board[boardY][boardX] != Color.BLACK) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void drop() {
        if (isPaused || isGameOver) return;

        if (isValidPosition(currentTetrominoType.getShape(currentRotationIndex), currentX, currentY + 1)) {
            currentY++;
        } else {
            fixTetrominoToBoard();
            checkLines();
            spawnNewTetromino();
        }
        repaint();
    }

    private void fixTetrominoToBoard() {
        int[][] currentShape = currentTetrominoType.getShape(currentRotationIndex);
        for (int row = 0; row < currentShape.length; row++) {
            for (int col = 0; col < currentShape[0].length; col++) {
                if (currentShape[row][col] != 0) {
                    board[currentY + row][currentX + col] = currentTetrominoType.getColor();
                }
            }
        }
    }

    private void checkLines() {
        for (int i = Tetris.BOARD_HEIGHT - 1; i >= 0; i--) {
            boolean lineFull = true;
            for (int j = 0; j < Tetris.BOARD_WIDTH; j++) {
                if (board[i][j] == Color.BLACK) {
                    lineFull = false;
                    break;
                }
            }
            if (lineFull) {
                removeLine(i);
                i++;
            }
        }
    }

    private void removeLine(int line) {
        for (int i = line; i > 0; i--) {
            for (int j = 0; j < Tetris.BOARD_WIDTH; j++) {
                board[i][j] = board[i - 1][j];
            }
        }
        for (int j = 0; j < Tetris.BOARD_WIDTH; j++) {
            board[0][j] = Color.BLACK;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < Tetris.BOARD_HEIGHT; i++) {
            for (int j = 0; j < Tetris.BOARD_WIDTH; j++) {
                if (board[i][j] != Color.BLACK) {
                    drawBlock(g, j * Tetris.BLOCK_SIZE, i * Tetris.BLOCK_SIZE, board[i][j]);
                }
            }
        }

        if (currentTetrominoType != null) {
            int[][] currentShape = currentTetrominoType.getShape(currentRotationIndex);
            for (int row = 0; row < currentShape.length; row++) {
                for (int col = 0; col < currentShape[0].length; col++) {
                    if (currentShape[row][col] != 0) {
                        drawBlock(g, (currentX + col) * Tetris.BLOCK_SIZE,
                                   (currentY + row) * Tetris.BLOCK_SIZE,
                                   currentTetrominoType.getColor());
                    }
                }
            }
        }

        if (isPaused) {
            drawMessage(g, "PAUSA");
        } else if (isGameOver) {
            drawMessage(g, "GAME OVER");
        }
    }

    private void drawBlock(Graphics g, int x, int inty, Color color) {
        g.setColor(color);
        g.fillRect(x, inty, Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(x, inty, Tetris.BLOCK_SIZE, Tetris.BLOCK_SIZE);
    }

    private void drawMessage(Graphics g, String message) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(message)) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g.drawString(message, x, y);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        drop();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (isGameOver) return;

            if (currentTetrominoType == null) return;

            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (!isPaused && isValidPosition(currentTetrominoType.getShape(currentRotationIndex), currentX - 1, currentY)) {
                        currentX--;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (!isPaused && isValidPosition(currentTetrominoType.getShape(currentRotationIndex), currentX + 1, currentY)) {
                        currentX++;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (!isPaused) {
                        drop();
                    }
                    break;
                case KeyEvent.VK_UP: // Rotazione
                    if (!isPaused) {
                        int nextRotationIndex = (currentRotationIndex + 1) % currentTetrominoType.getNumRotations();
                        int[][] nextRotatedShape = currentTetrominoType.getShape(nextRotationIndex);

                        if (isValidPosition(nextRotatedShape, currentX, currentY)) {
                            currentRotationIndex = nextRotationIndex; // Aggiorna l'indice di rotazione
                        }
                    }
                    break;
                case KeyEvent.VK_P: // Tasto 'P': Pausa/Riprendi
                    if (!isGameOver) {
                        isPaused = !isPaused;
                        if (isPaused) {
                            timer.stop();
                        } else {
                            timer.start();
                        }
                        repaint();
                    }
                    break;
            }
            repaint();
        }
    }
}