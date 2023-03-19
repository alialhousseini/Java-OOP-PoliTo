import ticketing.*;

public class Esempio {

	public static void main(String[] args) throws InvalidInformationException {

	    Tracker t = new Tracker("http://www.polito.it/track");
	    
	    t.nuovoUtente("jsm", "John Smith", "john@smith.com", "secret");
        t.nuovoUtente("giove", "Giovanni Verdi", "giova@green.it", "facile");

        String code = t.nuovoProdotto("Web Portal", "company main web portal");
        
        Ticket t1 =  t.nuovoTicket(code, "jsm", "No English version");
        aspettaCentoMillisecondi();
        Ticket t2 =  t.nuovoTicket(code, "jsm", "Broken link in home page");
        aspettaCentoMillisecondi();
        Ticket t3 =  t.nuovoTicket(code, "giove", "Titolo errato");

        System.out.println("Il ticket " + t2.getCodice() + " ( " + t2.getEtichetta() + " )");
        System.out.println("relativo al prodotto " + t2.getProdotto().getNome() );
        long elapsed = System.currentTimeMillis() - t2.getTimestamp();
        System.out.println("E' stato creato " + elapsed + " ms fa");
        System.out.println("da " + t2.getCreatore().getNickname() + "(" + t2.getCreatore().getName() + ")");
        
        t3.nuovoCommento("giove", "sulla pagina del personale");
        t3.nuovoCommento("jsm", "ed anche su quella dei progetti");
        t3.nuovoCommento("giove", "Dovrebbe essere in maiuscolo e grassetto");
        
        System.out.println("Classifica utenti per ticket creati:");
        int i = 0;
        for(Utente u : t.utentiPerTicket()){
            System.out.println(++i + " " + u.getName() + ", " + u.numeroTicket() + " ticket");
        }
        
        
	}
	
	private static void aspettaCentoMillisecondi() {
	    try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // ignore exception
        }
	}
}
