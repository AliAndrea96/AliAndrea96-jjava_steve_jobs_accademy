package Sistema_autenticazione_utenti;

class Professore extends Utente implements Autenticabile {
    private String materia;

    public Professore(String username, String email, String materia) {
        super(username, email);
        this.materia = materia;
    }

    @Override
    public void presentati() {
        System.out.println("Professore " + username + ", insegna: " + materia);
    }

    @Override
    public void autentica(String password) throws AutenticazioneException {
        if (!password.equals("prof2024")) {
            throw new AutenticazioneException("Password errata per Professore " + username);
        }
        System.out.println("Autenticazione Professore riuscita!");
    }
}