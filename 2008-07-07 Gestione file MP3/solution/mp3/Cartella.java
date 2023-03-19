package mp3;

import java.util.Collection;
import java.util.LinkedList;

public class Cartella {
	
	private String path;
	private LinkedList<Brano> brani = new LinkedList<Brano>();
	private Archivio archivio;

    public String getPath(){
        return path;
    }
    
    public Brano aggiungiBrano(String nomeFile, String titolo, String artista){

    	if (nomeFile==null) return null;
    	if (titolo==null) return null;
    	if (artista==null) return null;
    	
    	
    	//Manca il controllo se quel brano già esiste
    	if(1==0){
        	for(Brano b:brani){
        		
        		if (b.getArtista().equals(artista) && b.getTitolo().equals(titolo) && b.getArtista().equals(artista)){
        			System.out.println("STAI duplicando il brano!!!!");
        		}
        		
        	}
    	}
    	
    	//Verifico che tipo di algoritmo mi serve
    	Algoritmo a = archivio.getAlgoritmoFromNomeFile(nomeFile);
    	
    	Brano b = new Brano(this,nomeFile,titolo,artista,a);
    	brani.add(b);
    	
    	return b;
    	
    }
    
    @SuppressWarnings("unchecked")
	public Collection getBrani(){
        return brani;
    }

	public Cartella(Archivio a,String path) {

		this.archivio = a;
		this.path = path;
	}

	void setPath(String path) {
		this.path = path;
	}
	
	public String  toString() {
		return "C:"+path;
	}
}
