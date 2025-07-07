package Finestra;


public class Professore {
    private String nome;
    private String cognome;
    private String dataNascita;
    private String numeroId;

    public Professore(String nome, String cognome, String dataNascita, String numeroId) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.numeroId = numeroId;
    }

    @Override
    public String toString() {
        return nome + " " + cognome + ", nato il " + dataNascita + ", ID: " + numeroId;
    }
}
