package modello;

public abstract class Veicolo {
    protected String codice;
    protected double livelloBatteriaPercentuale;
    protected Stato stato;

    public abstract double calcolaCosto(int minuti);
    public abstract double consumoBatteriaPerMinuto();
}