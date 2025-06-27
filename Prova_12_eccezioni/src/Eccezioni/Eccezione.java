package Eccezioni;
import java.util.Scanner;

public class Eccezione {
    public static void main(String args[]) {
        Scanner numero = new Scanner(System.in);
        
        System.out.println("Inserisci dividendo: ");
        int a = numero.nextInt();
        
        System.out.println("Inserisci divisore: ");
        int b = numero.nextInt();
        
        
        /* Usiamo il try per il codice che può generare un’eccezione
           il catch sarà il codice eseguito in caso di errore
           e il finally (Opzionale) per il codice eseguito sempre, anche in caso di errore */
        
        try {
            double c = a/b;
            System.out.println(c);
        } catch(ArithmeticException e) {
            System.out.println("Impossibile dividere per: 0");
        } finally {
        	numero.close();
        }
    }
}