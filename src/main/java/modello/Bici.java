package modello;

public class Bici extends Veicolo {
    private final int marce;
    private final double tariffa = 0.15;
    private final boolean haCestino;

    public Bici(String codice, double livelloBatteriaPercentuale, Stato stato,  int marce,  boolean haCestino) {
        super(codice, livelloBatteriaPercentuale, stato);
        this.haCestino = haCestino;
        this.marce = marce;
    }

    public boolean isHaCestino() {
        return haCestino;
    }

    @Override
    public double calcolaCosto(int minuti) {
        return minuti * tariffa;
    }

    @Override
    public double consumoBatteriaPerMinuto() {
        return 5.0;
    }
}
