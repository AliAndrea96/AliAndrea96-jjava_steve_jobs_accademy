package Sistema_autenticazione_utenti;

public interface Autenticabile {
    void autentica(String password) throws AutenticazioneException;
}