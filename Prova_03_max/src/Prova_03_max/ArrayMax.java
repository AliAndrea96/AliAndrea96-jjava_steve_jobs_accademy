package Prova_03_max;

public class ArrayMax {
    public static void main(String[] args) {
        int[] numeri = {2, 40, 35, 100, 85, 7};

        int massimo = numeri[0]; // supponiamo che il primo sia il massimo

        for (int i = 1; i < numeri.length; i++) {
            if (numeri[i] > massimo) {
                massimo = numeri[i];
            }
        }

        System.out.println("Il valore massimo è: " + massimo);
    }
}
