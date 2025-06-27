package eccezioni_array;
import java.util.Scanner;

public class Eccezione_array {
    public static void main(String[] args) {
        String[] nomi = {"Ugo", "Eva", "Emma"};
        
        Scanner indice = new Scanner(System.in);
        System.out.println("Array di nomi:");
        
        // Stampa gli elementi dell'array
        for (int i = 0; i < nomi.length; i++) {
            System.out.println(i + ": " + nomi[i]);
        }
        
        System.out.print("\nInserisci un indice per vedere il nome corrispondente: ");
        
        try {
            int scelta = indice.nextInt();
            String nomeScelto = nomi[scelta];
            System.out.println("Nome selezionato: " + nomeScelto);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Errore: Indice non valido!");
            System.out.println("Inserisci un numero tra 0 e " + (nomi.length - 1));
        } catch (java.util.InputMismatchException e) {
            System.out.println("Errore: Devi inserire un numero intero!");
        } finally {
            indice.close();
        }
    }
}