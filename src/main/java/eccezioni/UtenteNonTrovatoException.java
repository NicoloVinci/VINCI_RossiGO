package eccezioni;

import modello.Utente;

public class UtenteNonTrovatoException extends RossiGoException {
    private final Utente utente;

    public UtenteNonTrovatoException(String message, Utente utente) {
        super(message);
        this.utente = utente;
    }
    public UtenteNonTrovatoException(String message, Utente utente, Throwable cause) {
        super(message, cause);
        this.utente = utente;
    }

    public Utente getUtente() {
        return utente;
    }
}
