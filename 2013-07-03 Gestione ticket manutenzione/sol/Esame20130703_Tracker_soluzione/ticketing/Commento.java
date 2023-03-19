package ticketing;

public class Commento {

    private Ticket ticket;
    private Utente u;
    private String testo;
    private long timestamp;
    
    public Commento(Ticket ticket, Utente u, String testo) {
        this.ticket = ticket;
        this.u = u;
        this.testo = testo;
        this.timestamp = System.currentTimeMillis();
    }
    public Utente getAutore() {
        return u;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public String getTesto() {
        return testo;
    }
    public long getTimestamp() {
        return timestamp;
    }
    
    
}
