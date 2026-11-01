package modello;

public class Monopattino extends Veicolo {
    private double velocitaMassima;
    private final double tariffa = 0.25;
    private final boolean limitatoInZonaPedonale;

    public Monopattino(String codice, double livelloBatteriaPercentuale, Stato stato, boolean limitatoInZonaPedonale, double velocitaMassima) {
        super(codice, livelloBatteriaPercentuale, stato);
        this.limitatoInZonaPedonale = limitatoInZonaPedonale;
        this.velocitaMassima = velocitaMassima;
    }

    public boolean isLimitatoInZonaPedonale() {
        return limitatoInZonaPedonale;
    }

    @Override
    public double calcolaCosto(int minuti) {
        return minuti * tariffa;
    }

    @Override
    public double consumoBatteriaPerMinuto() {
        return 18.0;
    }
}
