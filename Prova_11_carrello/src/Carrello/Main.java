package Carrello;

public class Main {
    public static void main(String[] args) {
        Carrello carrello = new Carrello();
        
        carrello.aggiungiProdotto("Mele");
        carrello.aggiungiProdotto("Pasta", 2);
        carrello.aggiungiProdotto("Olio");
        carrello.aggiungiProdotto("Caffè", 3);
        
        carrello.stampaCarrello();
    }
}