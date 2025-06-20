package Prova_02_calcolatrice_funzioni;

import java.util.Scanner;

public class Calcolatrice {

    // Funzione per la somma
    public static float somma(float a, float b) {
        return a + b;
    }

    // Funzione per la sottrazione
    public static float sottrazione(float a, float b) {
        return a - b;
    }

    // Funzione per la moltiplicazione
    public static float moltiplicazione(float a, float b) {
        return a * b;
    }

    // Funzione per la divisione
    public static float divisione(float a, float b) {
        if (b != 0) {
            return a / b;
        } else {
            System.out.println("Errore: divisione per zero!");
            return 0;
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {

            // Inserimento dei due numeri
            System.out.print("Inserisci il primo numero: ");
            float numero1 = input.nextFloat();

            System.out.print("Inserisci il secondo numero: ");
            float numero2 = input.nextFloat();

            // Menu operazioni
            System.out.println("Scegli un'operazione:");
            System.out.println("1 - Somma");
            System.out.println("2 - Sottrazione");
            System.out.println("3 - Moltiplicazione");
            System.out.println("4 - Divisione");
            System.out.print("Inserisci il numero dell'operazione: ");
            int scelta = input.nextInt();

            // Switch con chiamata ai metodi
            float risultato = 0;
            boolean valido = true;

            switch (scelta) {
                case 1:
                    risultato = somma(numero1, numero2);
                    break;
                case 2:
                    risultato = sottrazione(numero1, numero2);
                    break;
                case 3:
                    risultato = moltiplicazione(numero1, numero2);
                    break;
                case 4:
                    if (numero2 != 0) {
                        risultato = divisione(numero1, numero2);
                    } else {
                        valido = false;
                    }
                    break;
                default:
                    System.out.println("Scelta non valida!");
                    valido = false;
            }

            if (valido) {
                System.out.println("Risultato: " + risultato);
            }
        }
    }
}
