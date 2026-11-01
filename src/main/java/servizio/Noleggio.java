package servizio;

import modello.Veicolo;

public class Noleggio {
    private Utente utente;
    private Veicolo veicolo;
    private int orarioInizio;
    private int orarioFine;

    public Noleggio(Utente utente, Veicolo veicolo, int orarioInizio, int orarioFine) {
        this.utente = utente;
        this.veicolo = veicolo;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
    }

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public Utente getUtente() {
        return utente;
    }
}
