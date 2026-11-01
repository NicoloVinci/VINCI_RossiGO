package modello;

public abstract class Veicolo {
    protected String codice;
    protected double livelloBatteriaPercentuale;
    protected Stato stato;

    public abstract double calcolaCosto(int minuti);
    public abstract double consumoBatteriaPerMinuto();

    public String getCodice() {
        return codice;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public double getLivelloBatteriaPercentuale() {
        return livelloBatteriaPercentuale;
    }

    public void setLivelloBatteriaPercentuale(double livelloBatteriaPercentuale) {
        this.livelloBatteriaPercentuale = livelloBatteriaPercentuale;
    }
}