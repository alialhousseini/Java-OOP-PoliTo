package ticketing;

import java.util.LinkedList;
import java.util.List;

public class Ticket implements Comparable<Ticket>{
    private Prodotto p;
    private Utente u;
    private String label;
    private long id;
    private long timestamp;
    private Tracker tracker;
    private LinkedList<Commento> commenti = new LinkedList<Commento>();

    public Ticket(Tracker tracker, long id, Prodotto p, Utente u, String label) {
        this.tracker = tracker;
        this.id=id;
        this.p = p;
        this.u = u;
        this.label = label;
        this.timestamp = System.currentTimeMillis();
    }
    public long getCodice(){
        return id;
    }
    public Utente getCreatore() {
        return u;
    }
    public Prodotto getProdotto() {
        return p;
    }
    public String getEtichetta() {
        return label;
    }
    public long getTimestamp() {
        return timestamp;
    }
    
    public Commento nuovoCommento(String nick, String testo){
        Utente u = tracker.getUtente(nick);
        Commento c = new Commento(this,u,testo);
        commenti.addFirst(c);
        return c;
    }

    public List<Commento> getCommenti(){
        return commenti;
    }
    public int compareTo(Ticket other) {
        
        return (int)(other.timestamp - this.timestamp);
    }
}
