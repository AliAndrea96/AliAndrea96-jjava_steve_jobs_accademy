package Prova_04_classe;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Andrea", "Alì", 29, "Hello");

        // Uso dei metodi getter
        System.out.println("Nome: " + p1.getName());
        System.out.println("Cognome: " + p1.getSurname());
        System.out.println("Età: " + p1.getAge());

        // Modifica e stampa della password con metodi sicuri
        p1.setPassword("ttt");
        System.out.println("Password aggiornata: " + p1.getPassword());
    }
}
