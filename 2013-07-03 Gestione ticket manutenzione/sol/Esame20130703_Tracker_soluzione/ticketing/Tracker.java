package ticketing;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Tracker {
    private String url;
    private TreeMap<String,Utente> utenti = new TreeMap<String,Utente>();
    private TreeMap<String,Prodotto> prodotti = new TreeMap<String,Prodotto>();
    private TreeMap<Long,Ticket> tickets = new TreeMap<Long,Ticket>();
    private long idProd = 1;
    private long idTk = 1;

    public Tracker(String url){
        this.url = url;
        
    }
    
    public String getURL(){
        return url;
    }
    
    public void nuovoUtente(String nick, String nome, String email, String pwd) throws InvalidInformationException {
        if(nick==null || email==null || pwd==null){
             throw new InvalidInformationException();
        }
        Utente u = new Utente(nick,nome,email,pwd);
        utenti.put(nick, u);
    }
    
    public Utente getUtente(String nick){
        return utenti.get(nick);
    }
    
    public Collection<Utente> getUtenti(){
        return utenti.values();
    }
    
    
    public String nuovoProdotto(String nome, String descrizione) throws InvalidInformationException {
        if(nome==null || descrizione==null){
            throw new InvalidInformationException();
       }
        String id = "P" + idProd++;
        Prodotto p = new Prodotto(id,nome,descrizione);
        prodotti.put(id,p);
        return id;
    }
    
    public Prodotto getProdotto(String code){
        return prodotti.get(code);
    }

    public Collection<Prodotto> getProdotti(){
        return prodotti.values();
    }
    
    public Ticket nuovoTicket(String code, String nick, String label){
        Prodotto p = prodotti.get(code);
        Utente u = utenti.get(nick);
        Ticket tk = new Ticket(this,idTk++,p,u,label);
        tickets.put(tk.getCodice(), tk);
        p.addTicket(tk);
        u.addTicket();
        return tk;
    }
    
    public Ticket getTicket(long code){
        return tickets.get(code);
    }
    
    public List<Ticket> getTickets(){
        LinkedList<Ticket> res = new LinkedList<Ticket>(tickets.values());
        Collections.sort(res);
        return res;
    }
    
    public List<Prodotto> prodottiPerTicket(){
        LinkedList<Prodotto> risultato=new LinkedList<Prodotto>(prodotti.values());
        Collections.sort(risultato,new Comparator<Prodotto>(){
            public int compare(Prodotto a, Prodotto b){
                return - (int)(a.numeroTicket() - b.numeroTicket());
            }
        });
        return risultato;
    }
    
    public List<Utente> utentiPerTicket(){
        LinkedList<Utente> risultato=new LinkedList<Utente>(utenti.values());
        Collections.sort(risultato,new Comparator<Utente>(){
            public int compare(Utente a, Utente b){
                return - (int)(a.numeroTicket() - b.numeroTicket());
            }
        });
        return risultato;
    }
    
    
}
