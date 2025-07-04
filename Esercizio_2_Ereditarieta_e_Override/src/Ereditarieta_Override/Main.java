package Ereditarieta_Override;

public class Main {
    public static void main(String[] args) {

        Persona personaGenerica = new Persona("Andrea", "Alì");
        Studente studente = new Studente("Gigi", "D'Alessio", "12345");
        Professore professore = new Professore("Francesco", "Totti", "Italiano");


        Persona[] persone = {personaGenerica, studente, professore};

        for (Persona persona : persone) {
            persona.presentati();
        }
    }
}