package mp3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Archivio {

	private String nome;
	
	private HashMap<String,Cartella> cartelle = new HashMap<String,Cartella>();
	private TreeMap<String,Algoritmo> algoritmi = new TreeMap<String,Algoritmo>();
	
    public Archivio(String nome){
        this.nome=nome;
    }

    public String getNome(){
        return nome;
    }
    
    public Cartella aggiungiCartella(String path){
        
    	if(path==null)return null;
        
        Cartella c = new Cartella(this,path);
        cartelle.put(path,c);
        
        return c;
    }
    
    @SuppressWarnings("unchecked")
	public Collection getCartelle(){
        return cartelle.values();
    }
    
    @SuppressWarnings("unchecked")
	public Collection getBraniArtista(String artista){
    	
    	Collection braniArtista = new LinkedList<Brano>();
    	
    	//Per tutte le cartelle che ho devo recuperare i brani contenuti
    	for(Cartella c:cartelle.values()){
    		
    		for (Iterator i = c.getBrani().iterator(); i.hasNext();) {
                Brano b = (Brano) i.next();
                
    			//Verifico se il brano è dell'Artista
    			if(b.getArtista().equals(artista)){

    	        	//Se si li aggiungo in una collectione che li contiene tutti
    				braniArtista.add(b);
    			}

    		}

    	}
        return braniArtista;
    }

    public Algoritmo registraAlgoritmo(String nome, String estensione, String programma){
    	
    	if(nome==null) return null;
    	if(estensione==null) return null;
    	if(programma==null) return null;
    	
    	estensione = estensione.replace(".","");
    	
    	Algoritmo a = new Algoritmo(nome,estensione,programma);
    	algoritmi.put(estensione.toUpperCase(), a);
    	
    	return a;
    	
    }
    
    public Algoritmo getAlgoritmo(String estensione){
        if(estensione==null) return null;
        
        Algoritmo a = algoritmi.get(estensione.toUpperCase());
        return a;
        
    }

    @SuppressWarnings("unchecked")
	public Collection getAlgoritmi() {
        return algoritmi.values();
    }

    public void leggi(String nomeFile){
    	
    	try {
			
    		BufferedReader br =	new BufferedReader(new FileReader(nomeFile));
    		String linea;
    		Cartella lastCartella = null;
    		
    		//Leggo le righe finchè ce ne sono
    		while((linea = br.readLine())!=null){
    			
    			//Verifico che tipo di riga è
    			try {
    				
    				//token stringa
    				StringTokenizer st = new StringTokenizer(linea,";");
    				
    				String tipoRiga = st.nextToken().trim();
    				
    				if(tipoRiga.toUpperCase().equals("A")){
    				
    					//E' un ALGORITMO
    					String nome = st.nextToken().trim();
    					String estensione = st.nextToken().trim().replace(".","");
    					String programma = st.nextToken().trim();
    					
    					this.registraAlgoritmo(nome, estensione, programma);
    				}
    				else if(tipoRiga.toUpperCase().equals("C")){
    					
    					//E' una CARTELLA
    					String path = st.nextToken().trim();
    					
    					lastCartella = this.aggiungiCartella(path);

    				}
    				else if(tipoRiga.toUpperCase().equals("B")){
    					
    					//E' un BRANO
    					String mioNomeFile = st.nextToken().trim();
    					String titolo = st.nextToken().trim();
    					String artista = st.nextToken().trim();
    					
    					lastCartella.aggiungiBrano(mioNomeFile, titolo, artista);

    				}
    				
    			} catch (Exception e) {
    				
    			}
    			
    		}
    		
    		//Chiudo reader
    		br.close();
    		

    	} catch (IOException e) {
			System.out.println("Errore I/O : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Errore Generico : " + e.getMessage());
		}
        
    }
    
    Algoritmo getAlgoritmoFromNomeFile(String nomeFile){
    	
    	if (nomeFile==null) return null;
    	
    	//Splitto il nome file per .
    	StringTokenizer st = new StringTokenizer(nomeFile,".");
    	String estensione=null;
    	Algoritmo a = null;

    	if (st.countTokens()>1){
    		
        	while(st.hasMoreTokens()){
        		estensione = st.nextToken();
        	}
        	
        	a = algoritmi.get(estensione.toUpperCase());

    	}
    	
    	return a;

    }
}
