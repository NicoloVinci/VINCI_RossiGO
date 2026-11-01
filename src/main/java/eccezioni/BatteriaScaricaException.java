package eccezioni;

public class BatteriaScaricaException extends RossiGoException {
    private final double livelloBatteria;
    public BatteriaScaricaException(String message, double livelloBatteria) {
        super(message);
        this.livelloBatteria = livelloBatteria;
    }

    public BatteriaScaricaException(String message, Throwable cause, double livelloBatteria) {
        super(message, cause);
        this.livelloBatteria = livelloBatteria;
    }

    public double getLivelloBatteria() {
        return livelloBatteria;
    }
}
