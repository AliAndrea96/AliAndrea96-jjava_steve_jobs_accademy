package Sistema_autenticazione_utenti;

class Studente extends Utente implements Autenticabile {
    private String matricola;

    public Studente(String username, String email, String matricola) {
        super(username, email);
        this.matricola = matricola;
    }

    @Override
    public void presentati() {
        System.out.println("Studente " + username + ", matricola: " + matricola);
    }

    @Override
    public void autentica(String password) throws AutenticazioneException {
        if (!password.equals("studente123")) {
            throw new AutenticazioneException("Password errata per Studente " + username);
        }
        System.out.println("Autenticazione Studente riuscita!");
    }
}