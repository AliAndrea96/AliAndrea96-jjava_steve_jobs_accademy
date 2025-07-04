package Classe_Oggetti;

public class Main {
    public static void main(String[] args) {

        Studente studente1 = new Studente("Andrea", "Alì", 1996);
        Studente studente2 = new Studente("Valentino", "Rossi", 1979);

        studente1.stampaScheda();
        studente2.stampaScheda();
    }
}
