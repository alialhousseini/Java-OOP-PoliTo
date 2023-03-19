package it.polito.po.test;

import java.util.Collection;

import ticketing.InvalidInformationException;
import ticketing.Prodotto;
import ticketing.Tracker;
import junit.framework.TestCase;

public class TestR2_Prodotti extends TestCase {

    public void testProdotto() throws InvalidInformationException{
        Tracker t = new Tracker("http://www.polito.it/track");

        String code = t.nuovoProdotto("Bug tracking system", "Sistema di gestione dei ");
        
        assertNotNull("Codice nullo",code);
        
        assertEquals("Il codice non inizia con 'P'",'P',code.charAt(0));
        assertTrue("Il codice non contiene un carattere numerico dopo la 'P'",Character.isDigit(code.charAt(1)));
    }

    public void testGetProdotto() throws InvalidInformationException{
        Tracker t = new Tracker("http://www.polito.it/track");

        String nome = "Bug tracking system";
        String descrizione = "Sistema di gestione dei ";
        String code = t.nuovoProdotto(nome, descrizione);
        
        assertNotNull("Codice nullo",code);
        
        Prodotto p = t.getProdotto(code);
        
        assertNotNull("Prodotto inesistente", p);
        
        assertEquals(nome,p.getNome());
        assertEquals(descrizione,p.getDescrizione());
        assertEquals(code,p.getCodice());
    }

    public void testGetProdotti() throws InvalidInformationException{
        Tracker t = new Tracker("http://www.polito.it/track");

        t.nuovoProdotto("Bug tracking system", "Sistema di gestione dei ");
        t.nuovoProdotto("Web Portal", "company main web portal");
        t.nuovoProdotto("My Cloud", "Enterprise wide cloud system");
        
        Collection<Prodotto> prodotti = t.getProdotti();
        assertNotNull("Non risultano prodotti",prodotti);
        
        assertEquals("Non ci sono i tre prodotti attesi",3,prodotti.size());
    }

    public void testProdottiErrore() {
        Tracker t = new Tracker("http://www.polito.it/track");

        try {
            t.nuovoProdotto(null, "Sistema di gestione dei ");
            fail("Attesa eccezione per nome nullo");
        } catch (InvalidInformationException e) {
            //ok
        }
        try {
            t.nuovoProdotto("Web Portal", null);
            fail("Attesa eccezione per descrizione nulla");
        } catch (InvalidInformationException e) {
            //ok
        }

        Collection<Prodotto> prodotti = t.getProdotti();
        assertNotNull("Non risultano prodotti",prodotti);
        
        assertEquals("Non dovrebbero esserci prodotti",0,prodotti.size());
    }
}
