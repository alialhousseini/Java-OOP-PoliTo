package ticketing;

import java.util.LinkedList;

public class Prodotto{
    private String id,  nome,  descrizione;
    private LinkedList<Ticket> tickets = new LinkedList<Ticket>();
    
    public Prodotto(String id, String nome, String descrizione) {
        this.id=id;
        this.nome = nome;
        this.descrizione=descrizione;
    }

    public String getNome(){
        return nome;
    }

    public String getDescrizione(){
        return descrizione;
    }

    public String getCodice(){
        return id;
    }
    
    public long numeroTicket(){
        return tickets.size();
    }
    
    void addTicket(Ticket t){
        tickets.addFirst(t);
    }
}
