package Finestra;


public class Studente {
    private String nome;
    private String cognome;
    private String dataNascita;
    private String matricola;

    public Studente(String nome, String cognome, String dataNascita, String matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.matricola = matricola;
    }

    @Override
    public String toString() {
        return nome + " " + cognome + ", nato il " + dataNascita + ", matricola: " + matricola;
    }
}