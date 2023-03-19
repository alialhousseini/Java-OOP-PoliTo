package mp3;

public class Brano {

	private String nome;
	private String titolo;
	private String artista;
	private Cartella cartella;
	private Algoritmo algoritmo;
	
    public Brano(Cartella cartella,String nome, String titolo, String artista,Algoritmo algoritmo) {
    	this.cartella = cartella;
		this.nome = nome;
		this.titolo = titolo;
		this.artista = artista;
		this.algoritmo = algoritmo;

    }

	public Cartella getCartella(){
        return cartella;
    }
    
    /**
     * 
     * @return nome del file
     */
    public String getNome(){
        return nome;
    }
    
    public String getTitolo(){
        return titolo;
    }
    
    public String getArtista(){
        return artista;
    }
    
    public Algoritmo getAlgoritmo(){
        return algoritmo;
    }
    
    public String comandoPlay() {
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("cd "+this.cartella.getPath()+"\n");
    	
    	if (this.algoritmo==null)
    		sb.append("Algoritmo sconosciuto");
    	else
    		sb.append(this.algoritmo.getProgramma());
    	
    	sb.append(" " + this.nome);
    	
    	return sb.toString();
    	
        //return "cd "+this.cartella.getPath()+"\n"+this.algoritmo.getProgramma()+ " " + this.nome;
    }

    public String toString(){
    	return "{B:"+nome+";"+titolo+";"+artista+";"+cartella+";"+algoritmo+"}";
    }
}
