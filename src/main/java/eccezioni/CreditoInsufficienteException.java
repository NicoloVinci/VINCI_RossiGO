package eccezioni;

public class CreditoInsufficienteException extends RossiGoException {
    private final double credito;

    public CreditoInsufficienteException(String message, double credito) {
        super(message);
        this.credito = credito;
    }
    public CreditoInsufficienteException(String message, double credito, Throwable cause) {
        super(message, cause);
        this.credito = credito;
    }

    public double getCredito() {
        return credito;
    }
}
