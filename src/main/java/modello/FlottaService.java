package modello;

import java.util.ArrayList;
import java.util.List;

public class FlottaService {
    private List<Veicolo> veicoli;
    private List<Utente> utenti;
    private List<Noleggio> noleggiAttivi;

    public FlottaService() {
        this.veicoli = new ArrayList<>();
        this.utenti = new ArrayList<>();
        this.noleggiAttivi = new ArrayList<>();
    }
}
