package Carrello;

import java.util.ArrayList;

public class Carrello {
    private ArrayList<String> prodotti;

    public Carrello() {
        prodotti = new ArrayList<>();
    }

// Overloading 1
    public void aggiungiProdotto(String nomeProdotto) {
        prodotti.add(nomeProdotto);
    }

// Overloading 2
    public void aggiungiProdotto(String nomeProdotto, int quantita) {
        prodotti.add(nomeProdotto + " x" + quantita);
    }

    public void stampaCarrello() {
        for (String elemento : prodotti) {
            System.out.println(elemento);
        }
    }
}