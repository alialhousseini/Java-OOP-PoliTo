package it.polito.po.test;

import java.util.List;

import ticketing.Prodotto;
import ticketing.Ticket;
import ticketing.Tracker;
import ticketing.Utente;
import junit.framework.TestCase;

public class TestR5_Stats extends TestCase {

    Tracker t;
    String cp1;
    String cp2;
    String cp3;
    static final String nick1 = "jsm";
    static final String nick2 = "giove";
    static final String nick3 = "gv";
    static final String nick4 = "maro";

    private static void aspetta() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // ignore exception
        }
    }

    public void setUp() throws Exception {
        t = new Tracker("http://www.polito.it/track");
        
        t.nuovoUtente(nick1, "John Smith", "john@smith.com", "secret");
        t.nuovoUtente(nick2, "Giovanni Verdi", "giova@green.it", "facile");
        t.nuovoUtente(nick3, "Giuseppe Verdi", "verdi@green.it", "aida");
        t.nuovoUtente(nick4, "Mario Rossi", "rossi@gov.it", "reds");
        cp1 = t.nuovoProdotto("Bug tracking system", "Sistema di gestione dei ");
        cp2 = t.nuovoProdotto("Web Portal", "company main web portal");
        cp3 = t.nuovoProdotto("My Cloud", "Enterprise wide cloud system");
        
        t.nuovoTicket(cp1, nick1, "No English version");
        aspetta();
        Ticket tk = t.nuovoTicket(cp2, nick1, "Broken link in home page");
        aspetta();
        t.nuovoTicket(cp2, nick2, "Titolo errato");
        
        tk.nuovoCommento(nick2, "sulla pagina del personale");
        aspetta();
        tk.nuovoCommento(nick1, "ed anche su quella dei progetti");
        aspetta();
        tk.nuovoCommento(nick2, "Dovrebbe essere in maiuscolo e grassetto");
    }
    
    public void testNumTicket(){
        Prodotto p1 = t.getProdotto(cp1);
        Prodotto p2 = t.getProdotto(cp2);
        assertEquals(1,p1.numeroTicket());
        assertEquals(2,p2.numeroTicket());
    }

    public void testProdottiPerTicket(){
        List<Prodotto> prodotti = t.prodottiPerTicket();
        
        assertNotNull("Non c'e' nessun prodotto",prodotti);
        long prev = Long.MAX_VALUE;
        for(Prodotto p : prodotti){
            assertTrue("Ordine non corretto: " + p.getNome() + " ha " + p.numeroTicket() + ", il preced " + prev, 
                        p.numeroTicket() <= prev);
            prev = p.numeroTicket();
        }
    }

    public void testNumTicketUtente(){
        Utente u1 = t.getUtente(nick1);
        Utente u2 = t.getUtente(nick2);
        Utente u3 = t.getUtente(nick3);
        assertEquals(2,u1.numeroTicket());
        assertEquals(1,u2.numeroTicket());
        assertEquals(0,u3.numeroTicket());
    }

    public void testUtentiPerTicket(){
        List<Utente> utenti = t.utentiPerTicket();
        
        assertNotNull("Non c'e' nessun utente",utenti);
        long prev = Long.MAX_VALUE;
        for(Utente u : utenti){
            assertTrue("Ordine non corretto:" + u.getNickname() + " ha " + u.numeroTicket() + " il preced " + prev, 
                        u.numeroTicket() <= prev);
            prev = u.numeroTicket();
        }
    }
}
