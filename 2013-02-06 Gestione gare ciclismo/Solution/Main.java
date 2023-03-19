

import java.util.List;

import ciclismo.AtletaNonPresente;
import ciclismo.AssociazioneCiclismo;
import ciclismo.GaraNonPresente;
import ciclismo.NonIscritto;

public class Main {

	public static void main(String[] args) throws AtletaNonPresente, GaraNonPresente, NonIscritto {
	
		AssociazioneCiclismo cm = new AssociazioneCiclismo();
		cm.aggiungiAtleta(120121, "gino", "bartali","Bianchi");
		cm.aggiungiAtleta(120010, "fausto", "coppi","Bianchi");
		cm.aggiungiAtleta(200200, "gino", "latoppa","Kelme");
		
		String s = cm.getAtleta(200200); //s == "latoppa" 
        System.out.println(s);
		cm.aggiungiGara("MiSr", "Milano Sanremo", 10);
		cm.aggiungiGara("PaRx", "Parigi Roubaix", 20);
		cm.aggiungiGara("Nix", "Altimocastro RevisondoliSulBrenoBasso", 0);
		cm.aggiungiGara("TFR", "Tour de France", 30);
		cm.aggiungiGara("GIT", "Giro d'Italia", 30);
		cm.aggiungiGara("VLT", "Vuelta", 20);
		
		int peso = cm.getGara("TFR"); // peso == 30
        System.out.println(peso);
		
		cm.aggiungiAtletaGara(120121, "PaRx");
		cm.aggiungiAtletaGara(120010, "PaRx");
		
		int pettorale = cm.pettoraleAtletaGara(120121, "PaRx"); //pettorale== 1
        System.out.println(pettorale);

		pettorale = cm.pettoraleAtletaGara(120010, "PaRx"); //pettorale== 2
        System.out.println(pettorale);
		
		cm.tempoAtletaGara(120121, "PaRx", 100);
		cm.tempoAtletaGara(120010, "PaRx", 99);
		
	    int posizione = cm.posizioneAtletaGara(120121, "PaRx"); // posizione == 2	
        System.out.println(posizione);
	    posizione = cm.posizioneAtletaGara(120010, "PaRx"); // posizione == 1
        System.out.println(posizione);
	    
        List<String> cl;
	    cl = cm.classificaGara("PaRx"); // s== 
        System.out.println(cl);
	                // "2 120010 coppi
	                //  1 120121 bartali"

	    cm.aggiungiAtletaGara(200200, "MiSr");
	    cm.aggiungiAtletaGara(120121, "MiSr");
	    cm.aggiungiAtletaGara(120010, "MiSr");
	    
		cm.tempoAtletaGara(120121, "MiSr", 100);
		cm.tempoAtletaGara(200200, "MiSr", 80);
		cm.tempoAtletaGara(120010, "MiSr", 99);

	    cl = cm.classificaGara("MiSr"); // s== 
        System.out.println(cl);
        // "1 200200 latoppa
	    //  3 120010 coppi 
        //  2 120121 bartali"

	    cl = cm.classificaGenerale();
        System.out.println(cl);
	      //  120010 coppi 19
	      //  120121 bartali 17
	      //  200200 latoppa 10
	          	    
	    cl = cm.classificaGeneralePesata();
        System.out.println(cl);
	      //  120010 coppi 290
	      //  120121 bartali 260
	      //  200200 latoppa 100 

	}

}
