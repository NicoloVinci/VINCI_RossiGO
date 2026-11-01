import com.github.lalyos.jfiglet.FigletFont;
import eccezioni.BatteriaScaricaException;
import eccezioni.CodiceFiscaleNonValidoException;
import eccezioni.CreditoInsufficienteException;
import eccezioni.VeicoloNonDisponibileException;
import modello.Bici;
import modello.Monopattino;
import modello.Stato;
import servizio.FlottaService;
import servizio.Utente;

import static java.lang.IO.println;

void main() {
    try {
        String titolo = FigletFont.convertOneLine("RossiGO");
        println(titolo);
    } catch (IOException e) {
        println("RossiGO");
    }
    FlottaService flotta = new FlottaService();
    try {
        flotta.registraVeicolo(new Bici("BK-0042", 100, Stato.LIBERO, 5, true));
        flotta.registraVeicolo(new Monopattino("SC-0107", 9, Stato.LIBERO, true, 32));
        flotta.registraVeicolo(new Bici("BK-0099", 80, Stato.IN_USO, 6, false));
    } catch (BatteriaScaricaException e) {
        println("Batteria scarica, attualmente il veicolo è in carica ed è al " + e.getLivelloBatteria());
    } catch (VeicoloNonDisponibileException e) {
        println("Veicolo attualmente non disponibile. Lo stato attuale è: " + e.getVeicolo().getStato());
    }

    try {
        println("Registrazione utente e sblocco Bici...");
        Utente mario = new Utente("U1", "Mario Rossi", "mario@email.it", "RSSMRA80A01H501U", 15.0);
        flotta.registraUtente(mario);

        flotta.sbloccaVeicolo("BK-0042", "U1");
        println("Bici BK-0042 sbloccata correttamente per Mario.\n");
    } catch (Exception e) {
        println("Errore inatteso: " + e.getMessage() + "\n");
    }

    try {
        println("Sblocco Monopattino per utente esistente...");
        Utente anna = new Utente("U2", "Anna Verdi", "anna@email.it", "VRDNNI95C41F205X", 20.0);
        flotta.registraUtente(anna);
        flotta.sbloccaVeicolo("SC-0107", "U2"); // Nota: qui la batteria è 9%, ma se vuoi testare lo sblocco metti un monopattino con batteria > 15%
        println("Monopattino SC-0107 sbloccato per Anna.\n");
    } catch (Exception e) {
        println("Errore: " + e.getMessage() + "\n");
    }

    try {
        println("Tentativo registrazione con Codice Fiscale errato...");
        Utente utenteErrato = new Utente("U3", "Luigi Bianchi", "luigi@email.it", "ERRATO12345", 10.0);
        flotta.registraUtente(utenteErrato);
        println("Registrazione riuscita per errore (non doveva passare).\n");
    } catch (CodiceFiscaleNonValidoException e) {
        println("Impossibile registrare l'utente: il codice fiscale fornito non è valido: " + e.getCodiceFiscale() + "\n");
    }

    try {
        println("Tentativo sblocco con credito a 0.50 €...");
        Utente utente = new Utente("U4", "Carla Neri", "carla@email.it", "NRCRL90A41L424K", 0.50);
        flotta.registraUtente(utente);

        flotta.sbloccaVeicolo("BK-0042", "U4");
        println("Sblocco riuscito nonostante il credito basso.\n");
    } catch (CreditoInsufficienteException e) {
        System.out.println("Sblocco rifiutato: credito insufficiente. Ricarica il tuo account per procedere. Attualmente hai: " + e.getCredito() + "\n");
    } catch (Exception e) {
        System.out.println("Altro errore: " + e.getMessage() + "\n");
    }

    try {
        println("Tentativo sblocco bici già occupata (BK-0099)...");
        flotta.sbloccaVeicolo("BK-0099", "U2");
        println("Sblocco riuscito su un mezzo occupato.\n");
    } catch (VeicoloNonDisponibileException e) {
        println("Spiacenti, questo mezzo è già in uso o in ricarica. Scegline un altro.\n");
    } catch (Exception e) {
        println("Altro errore: " + e.getMessage() + "\n");
    }

    try {
        println("Tentativo sblocco monopattino con batteria al 9% (SC-0107)...");
        flotta.sbloccaVeicolo("SC-0107", "U2");
        println("Sblocco riuscito con batteria scarica.\n");
    } catch (BatteriaScaricaException e) {
        println("Impossibile procedere: la batteria del mezzo è al " + e.getLivelloBatteria() + "%. Scegli un altro mezzo nelle vicinanze.\n");
    } catch (Exception e) {
        println("Altro errore: " + e.getMessage() + "\n");
    }
}
