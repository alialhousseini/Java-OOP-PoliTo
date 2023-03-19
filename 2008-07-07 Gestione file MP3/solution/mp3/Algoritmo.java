package mp3;

public class Algoritmo {

	private String nome;
	private String estensione;
	private String programma;
	
    public Algoritmo(String nome, String estensione, String programma) {
		this.nome = nome;
		this.estensione = estensione;
		this.programma = programma;
	}

	public String getNome(){
        return nome;
    }

    public String getEstensione(){
        return estensione;
    }

    public String getProgramma(){
        return programma;
    }
    
    public String toString(){
    	return "A:"+nome+";"+estensione+";"+programma;
    }
}
