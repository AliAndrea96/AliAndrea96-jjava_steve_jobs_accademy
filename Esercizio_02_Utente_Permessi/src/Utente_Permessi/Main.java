package Utente_Permessi;

public class Main {
    public static void main(String[] args) {
        GestioneUtenti gestioneUtenti = new GestioneUtenti();

        Admin admin1 = new Admin("admin1", "admin1@example.com");
        Admin admin2 = new Admin("admin2", "admin2@example.com");
        Cliente cliente1 = new Cliente("cliente1", "cliente1@example.com");
        Cliente cliente2 = new Cliente("cliente2", "cliente2@example.com");

        gestioneUtenti.aggiungiUtente(admin1);
        gestioneUtenti.aggiungiUtente(admin2);
        gestioneUtenti.aggiungiUtente(cliente1);
        gestioneUtenti.aggiungiUtente(cliente2);

        gestioneUtenti.stampaPermessi();
    }
}