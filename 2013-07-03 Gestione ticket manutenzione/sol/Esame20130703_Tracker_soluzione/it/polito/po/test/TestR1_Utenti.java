package it.polito.po.test;

import java.util.Collection;

import ticketing.InvalidInformationException;
import ticketing.Tracker;
import ticketing.Utente;
import junit.framework.TestCase;

public class TestR1_Utenti extends TestCase {

    
    public void testTracker(){
        
        String url = "https://www.polito.it/track";
        Tracker t = new Tracker(url);
        
        assertEquals(url,t.getURL());
    }
    
    public void testUtente() throws InvalidInformationException{
        
        Tracker t = new Tracker("https://www.polito.it/track");
        
        String nick = "jsm";
        String nome = "John Smith";
        String email = "john@smith.com";
        String pwd = "secret";
        t.nuovoUtente(nick, nome, email, pwd);

        Utente u = t.getUtente(nick);
        
        assertNotNull("Utente non trovato",u);
        
        assertEquals(nick,u.getNickname());
        assertEquals(nome,u.getName());
        assertEquals(email,u.getEmail());
        
    }
    
    public void testUtenteAuthentication() throws InvalidInformationException{
        
        Tracker t = new Tracker("https://www.polito.it/track");
        
        String nick = "jsm";
        String nome = "John Smith";
        String email = "john@smith.com";
        String pwd = "secret";
        t.nuovoUtente(nick, nome, email, pwd);

        Utente u = t.getUtente(nick);
        
        assertNotNull("Utente non trovato",u);

        assertTrue("Autenticazione fallita",u.authenticate(pwd));
        
    }
    
    public void testUtenti() throws InvalidInformationException{
        Tracker t = new Tracker("http://www.polito.it/track");
        
        t.nuovoUtente("jsm", "John Smith", "john@smith.com", "secret");
        t.nuovoUtente("giove", "Giovanni Verdi", "giova@green.it", "facile");
        t.nuovoUtente("gv", "Giuseppe Verdi", "verdi@green.it", "aida");
        t.nuovoUtente("maro", "Mario Rossi", "rossi@gov.it", "reds");
        
        Collection<Utente> utenti = t.getUtenti();
        
        assertNotNull("Utenti non trovati",utenti);
        
        assertEquals("Mancano degli utenti",4,utenti.size());
    }

    
    public void testNomeOpzionale() throws InvalidInformationException{
        
        Tracker t = new Tracker("https://www.polito.it/track");
        
        String nick = "jsm";
        String nome = null;
        String email = "john@smith.com";
        String pwd = "secret";
        t.nuovoUtente(nick, nome, email, pwd);

        Utente u = t.getUtente(nick);
        
        assertNotNull("Utente senza nome non trovato",u);        
    }
  
    
    public void testError() {
        Tracker t = new Tracker("http://www.polito.it/track");
        
        
        try {
            t.nuovoUtente(null, "John Smith", "john@smith.com", "secret");
            fail("Attesa eccezione per nickname mancante");
        } catch (InvalidInformationException e) {
            // ok
        }
        try {
            t.nuovoUtente("giove", "Giovanni Verdi", null, "facile");
            fail("Attesa eccezione per email mancante");
        } catch (InvalidInformationException e) {
            // ok
        }
        try {
            t.nuovoUtente("gv", "Giuseppe Verdi", "verdi@green.it", null);
            fail("Attesa eccezione per password mancante");
        } catch (InvalidInformationException e) {
            // ok
        }
    }

}
