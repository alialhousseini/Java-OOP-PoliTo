package ciclismo;

import java.util.*;

public class Gara {
	
	private String codiceGara;
	private String descrittore;
	private int peso;
	
	List iscritti = new ArrayList();
	SortedMap tempi = new TreeMap();

	Gara(String codiceGara, String descrittore, int peso){
		
		this.codiceGara=codiceGara;
		this.descrittore=descrittore;
		this.peso=peso;
	}
	
	public String getcodiceGara(){
		return codiceGara;
	}
	
	public String getDescrittore(){
		return descrittore;
	}
	
	public int getPeso(){
		return peso;
	}

}
