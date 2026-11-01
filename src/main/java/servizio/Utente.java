package servizio;

public class Utente {
    private String id;
    private String nome;
    private String email;
    private String codiceFiscale;
    private double creditoInEuro;

    public Utente(String id, String nome, String email, String codiceFiscale, double creditoInEuro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.codiceFiscale = codiceFiscale;
        this.creditoInEuro = creditoInEuro;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getId() {
        return id;
    }

    public double getCreditoInEuro() {
        return creditoInEuro;
    }

    public void setCreditoInEuro(double creditoInEuro) {
        this.creditoInEuro = creditoInEuro;
    }
}