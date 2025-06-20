package Prova_01_calcolatrice;
import java.util.Scanner;

public class Calcolatrice {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {  // <-- Scanner dichiarato nel try

            System.out.print("Inserisci il primo numero: ");
            float numero1 = input.nextFloat();

            System.out.print("Inserisci il secondo numero: ");
            float numero2 = input.nextFloat();

            System.out.println("Scegli un'operazione:");
            System.out.println("1 - Somma");
            System.out.println("2 - Sottrazione");
            System.out.println("3 - Moltiplicazione");
            System.out.println("4 - Divisione");
            System.out.print("Inserisci il numero dell'operazione: ");
            int scelta = input.nextInt();

            switch (scelta) {
                case 1:
                    System.out.println("Risultato: " + (numero1 + numero2));
                    break;
                case 2:
                    System.out.println("Risultato: " + (numero1 - numero2));
                    break;
                case 3:
                    System.out.println("Risultato: " + (numero1 * numero2));
                    break;
                case 4:
                    if (numero2 != 0) {
                        System.out.println("Risultato: " + (numero1 / numero2));
                    } else {
                        System.out.println("Errore: divisione per zero!");
                    }
                    break;
                default:
                    System.out.println("Scelta non valida!");
            }

        } // <-- Scanner viene chiuso automaticamente qui
    }
}
