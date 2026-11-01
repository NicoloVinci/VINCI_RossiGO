package eccezioni;

import modello.Veicolo;

public class VeicoloNonDisponibileException extends RossiGoException {
    private final Veicolo veicolo;

    public VeicoloNonDisponibileException(String message, Veicolo veicolo) {
      super(message);
      this.veicolo = veicolo;
    }
    public VeicoloNonDisponibileException(String message, Veicolo veicolo, Throwable cause) {
        super(message, cause);
        this.veicolo = veicolo;
    }

    public Veicolo getVeicolo() {
      return veicolo;
    }
}
