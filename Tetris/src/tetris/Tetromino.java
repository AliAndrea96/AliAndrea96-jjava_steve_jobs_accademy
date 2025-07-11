package tetris; // Assicurati che il package sia corretto

import java.awt.Color;
import java.util.Random;

public enum Tetromino {
    // Ogni Tetromino ora ha un array di forme per le sue rotazioni e il suo colore.
    // Le forme sono già pre-calcolate per ogni rotazione.
    I_SHAPE(new int[][][]{
        {{1, 1, 1, 1}}, // Rotazione 0
        {{1}, {1}, {1}, {1}}  // Rotazione 1
    }, Color.CYAN),

    J_SHAPE(new int[][][]{
        {{1, 0, 0}, {1, 1, 1}}, // Rotazione 0
        {{1, 1}, {1, 0}, {1, 0}}, // Rotazione 1
        {{1, 1, 1}, {0, 0, 1}}, // Rotazione 2
        {{0, 1}, {0, 1}, {1, 1}}  // Rotazione 3
    }, Color.BLUE),

    L_SHAPE(new int[][][]{
        {{0, 0, 1}, {1, 1, 1}}, // Rotazione 0
        {{1, 0}, {1, 0}, {1, 1}}, // Rotazione 1
        {{1, 1, 1}, {1, 0, 0}}, // Rotazione 2
        {{1, 1}, {0, 1}, {0, 1}}  // Rotazione 3
    }, Color.ORANGE),

    O_SHAPE(new int[][][]{
        {{1, 1}, {1, 1}} // L'O-shape non ruota, quindi ha solo una forma
    }, Color.YELLOW),

    S_SHAPE(new int[][][]{
        {{0, 1, 1}, {1, 1, 0}}, // Rotazione 0
        {{1, 0}, {1, 1}, {0, 1}}  // Rotazione 1
    }, Color.GREEN),

    T_SHAPE(new int[][][]{
        {{0, 1, 0}, {1, 1, 1}}, // Rotazione 0
        {{1, 0}, {1, 1}, {1, 0}}, // Rotazione 1
        {{1, 1, 1}, {0, 1, 0}}, // Rotazione 2
        {{0, 1}, {1, 1}, {0, 1}}  // Rotazione 3
    }, Color.MAGENTA),

    Z_SHAPE(new int[][][]{
        {{1, 1, 0}, {0, 1, 1}}, // Rotazione 0
        {{0, 1}, {1, 1}, {1, 0}}  // Rotazione 1
    }, Color.RED);

    private final int[][][] allRotationsShapes; // Array di tutte le forme ruotate per questo tipo di Tetromino
    private final Color color;                  // Colore del tetromino

    Tetromino(int[][][] allRotationsShapes, Color color) {
        this.allRotationsShapes = allRotationsShapes;
        this.color = color;
    }

    // Restituisce una specifica forma basata sull'indice di rotazione
    public int[][] getShape(int rotationIndex) {
        // Assicura che l'indice sia valido per il numero di rotazioni disponibili
        return allRotationsShapes[rotationIndex % allRotationsShapes.length];
    }

    public Color getColor() {
        return color;
    }

    // Restituisce il numero totale di rotazioni per questo tipo di tetromino
    public int getNumRotations() {
        return allRotationsShapes.length;
    }

    // Metodo statico per ottenere un tetromino casuale
    public static Tetromino getRandomTetromino() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}