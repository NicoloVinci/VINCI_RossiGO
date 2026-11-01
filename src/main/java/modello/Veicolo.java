package modello;

public abstract class Veicolo {
    String codice;
    int livelloBatteriaPercentuale;
    Stato stato;

    public abstract double calcolaCosto(int minuti);
    public abstract double consumoBatteriaPerMinuto();
}