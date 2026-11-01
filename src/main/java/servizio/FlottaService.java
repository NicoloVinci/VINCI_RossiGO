package servizio;

import eccezioni.*;
import it.kamaladafrica.codicefiscale.CodiceFiscale;
import modello.Stato;
import modello.Veicolo;

import java.util.*;

public class FlottaService {
    private Map<String, Veicolo> veicoli;
    private Map<String, Utente> utenti;
    private Map<String, Noleggio> noleggiAttivi;

    public FlottaService() {
        this.veicoli = new HashMap<>();
        this.utenti = new HashMap<>();
        this.noleggiAttivi = new HashMap<>();
    }

    public void registraVeicolo(Veicolo v) {
        this.veicoli.put(v.getCodice(), v);
    }

    public void registraUtente(Utente u) throws CodiceFiscaleNonValidoException {

        try {
            CodiceFiscale.of(u.getCodiceFiscale());
        } catch(Exception e) {
            throw new CodiceFiscaleNonValidoException("Codice fiscale non valido.", u.getCodiceFiscale());
        }
        this.utenti.put(u.getId(), u);
    }

    public void sbloccaVeicolo(String codice, String idUtente) throws VeicoloNonDisponibileException, BatteriaScaricaException, CreditoInsufficienteException, UtenteNonTrovatoException {
        Utente utente = utenti.get(idUtente);
        if(utente == null) {
            throw new UtenteNonTrovatoException("Utente inesistente.", utente);
        }
        if(utente.getCreditoInEuro() < 1.0) {
            throw new CreditoInsufficienteException("Credito non sufficiente per sbloccare il veicolo.", utente.getCreditoInEuro());
        }
        Veicolo veicolo = veicoli.get(codice);
        if(veicolo.getStato() == Stato.IN_RICARICA) {
            throw new VeicoloNonDisponibileException("Veicolo attualmente in ricarica, impossibile sbloccarlo.", veicolo);
        }
        if(veicolo.getStato() == Stato.IN_USO) {
            if(veicolo.getLivelloBatteriaPercentuale() >= 15.0) {
                veicolo.setStato(Stato.LIBERO);
            } else {
                mettiInRicarica(veicolo.getCodice());
                throw new BatteriaScaricaException("Batteria < 15%, il veicolo è in ricarica.", veicolo.getLivelloBatteriaPercentuale());
            }
        }
    }

    public void terminaNoleggio(String codice, int minutiUsati) throws VeicoloNonDisponibileException, CreditoInsufficienteException, BatteriaScaricaException, UtenteNonTrovatoException {
        Noleggio noleggio = noleggiAttivi.get(codice);
        noleggio.getVeicolo().setLivelloBatteriaPercentuale(noleggio.getVeicolo().getLivelloBatteriaPercentuale() - (minutiUsati * noleggio.getVeicolo().consumoBatteriaPerMinuto()));
        sbloccaVeicolo(noleggio.getVeicolo().getCodice(), noleggio.getUtente().getId());
        double costoTotale = noleggio.getVeicolo().calcolaCosto(minutiUsati) + 1.0;
        if(noleggio.getUtente().getCreditoInEuro() < costoTotale) {
            throw new CreditoInsufficienteException("Credito non sufficiente per sbloccare il veicolo.", noleggio.getUtente().getCreditoInEuro());
        } else {
            noleggiAttivi.remove(codice);
            noleggio.getUtente().setCreditoInEuro(noleggio.getUtente().getCreditoInEuro() - costoTotale);
        }
    }

    public void mettiInRicarica(String codice) {
        veicoli.get(codice).setStato(Stato.IN_RICARICA);
    }
}
