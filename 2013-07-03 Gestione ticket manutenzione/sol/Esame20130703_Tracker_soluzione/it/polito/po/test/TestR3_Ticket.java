package it.polito.po.test;

import java.util.List;

import ticketing.Prodotto;
import ticketing.Ticket;
import ticketing.Tracker;
import ticketing.Utente;
import junit.framework.TestCase;

public class TestR3_Ticket extends TestCase {

    Tracker t;
    String cp1;
    String cp2;
    String cp3;
    private static void aspetta() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // ignore exception
        }
    }


    public void setUp() throws Exception {
        t = new Tracker("http://www.polito.it/track");
        
        t.nuovoUtente("jsm", "John Smith", "john@smith.com", "secret");
        t.nuovoUtente("giove", "Giovanni Verdi", "giova@green.it", "facile");
        t.nuovoUtente("gv", "Giuseppe Verdi", "verdi@green.it", "aida");
        t.nuovoUtente("maro", "Mario Rossi", "rossi@gov.it", "reds");
        cp1 = t.nuovoProdotto("Bug tracking system", "Sistema di gestione dei ");
        cp2 = t.nuovoProdotto("Web Portal", "company main web portal");
        cp3 = t.nuovoProdotto("My Cloud", "Enterprise wide cloud system");
    }
    
    public void testTicket(){
        String nick = "jsm";
        String label = "No English version";

        long before = System.currentTimeMillis();
        aspetta();
        Ticket tk =  t.nuovoTicket(cp1, nick, label);
        aspetta();
        long after = System.currentTimeMillis();
      
        Utente u = t.getUtente(nick);
        Prodotto p = t.getProdotto(cp1);
        
        assertNotNull("Non e' stato creato alcun ticket",tk);
        
        long time = tk.getTimestamp();
        assertEquals(label,tk.getEtichetta());
        assertTrue( "Il time stamp non e' corretto",before <= time && time <= after );
        assertEquals(u,tk.getCreatore());
        assertEquals(p,tk.getProdotto());
    }

    
    public void testGetTicket(){
        Ticket t1 =  t.nuovoTicket(cp1, "jsm", "No English version");
        
        Ticket tk = t.getTicket(t1.getCodice());
        
        assertSame("Non viene restituito il ticket corretto",t1,tk);

    }
    
    public void testGetTickets(){
        t.nuovoTicket(cp1, "jsm", "No English version");
        t.nuovoTicket(cp1, "jsm", "Broken link in home page");
        t.nuovoTicket(cp2, "giove", "Titolo errato");

        List<Ticket> tickets = t.getTickets();
        
        assertNotNull("Non vengono restituiti ticket",tickets);
        assertEquals("Dovrebbero esserci 3 ticket",3,tickets.size());
    }

    public void testGetTicketsOrdine(){
        t.nuovoTicket(cp1, "jsm", "No English version");
        aspetta();
        t.nuovoTicket(cp1, "jsm", "Broken link in home page");
        aspetta();
        t.nuovoTicket(cp2, "giove", "Titolo errato");

        List<Ticket> tickets = t.getTickets();
        
        assertNotNull("Non vengono restituiti ticket",tickets);
        assertEquals("Dovrebbero esserci 3 ticket",3,tickets.size());
        
        assertTrue("Ordine non corretto",tickets.get(0).getTimestamp() > tickets.get(1).getTimestamp());
        assertTrue("Ordine non corretto",tickets.get(1).getTimestamp() > tickets.get(2).getTimestamp());
    }

}
