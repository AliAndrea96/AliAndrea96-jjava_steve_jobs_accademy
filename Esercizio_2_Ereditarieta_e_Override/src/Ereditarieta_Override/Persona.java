package Ereditarieta_Override;

class Persona {
    protected String nome;
    protected String cognome;

    public Persona(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public void presentati() {
        System.out.println("Ciao, sono " + nome + " " + cognome);
    }
}