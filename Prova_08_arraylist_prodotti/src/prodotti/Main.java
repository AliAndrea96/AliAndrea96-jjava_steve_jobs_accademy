package prodotti;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Prodotto> prodotti = new ArrayList<>();

        prodotti.add(new Prodotto("Mela", "Melinda", 1.50, LocalDate.of(2025, 7, 12)));
        prodotti.add(new Prodotto("Pera", "FruttaTop", 1.30, LocalDate.of(2025, 7, 10)));
        prodotti.add(new Prodotto("Yogurt", "LatteSano", 0.90, LocalDate.of(2024, 5, 10)));
        
        for (Prodotto prodotto : prodotti) {
            System.out.println(prodotto);

            if (prodotto.isExpired()) {
                System.out.println("❌ Il prodotto è scaduto.");
            } else {
                System.out.println("✅ Il prodotto è valido.");
            }

            System.out.println();
        }
    }
}