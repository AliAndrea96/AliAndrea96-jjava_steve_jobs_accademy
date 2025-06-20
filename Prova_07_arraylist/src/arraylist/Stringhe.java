package arraylist;

import java.util.ArrayList;

public class Stringhe {
    public static void main(String[] args) {

        ArrayList<String> prodotti = new ArrayList<>();

        prodotti.add("Mela");
        prodotti.add("Pera");
        prodotti.add("Kiwi");

        System.out.println("Lista iniziale:");
        for (int i = 0; i < prodotti.size(); i++) {
            System.out.println(prodotti.get(i));
        }
        
        prodotti.add("Ananas");

        prodotti.remove("Pera");
        
        prodotti.add(1, "Cocco");
        
        System.out.println("\nLista aggiornata:");
        for (String prodotto : prodotti) {
            System.out.println(prodotto);
        }
    }
}