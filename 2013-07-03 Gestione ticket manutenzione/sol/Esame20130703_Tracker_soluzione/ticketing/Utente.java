package ticketing;

public class Utente {
    private String nick,  nome,  email,  pwd;
    private long countTickets;

    public Utente(String nick, String nome, String email, String pwd) {
        this.nick = nick;
        this.nome = nome;
        this.email = email;
        this.pwd = pwd;
    }

    public String getNickname(){
        return nick;
    }

    public String getName(){
        return nome;
    }
    
    public String getEmail(){
        return email;
    }
    
    public boolean authenticate(String pwd){
        return this.pwd.equals(pwd);
    }

    public long numeroTicket(){
        return countTickets;
    }
    
    public void addTicket(){
        countTickets++;
    }
    
}
