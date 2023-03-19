import java.util.Iterator;

import mp3.Archivio;
import mp3.Brano;
import mp3.Cartella;


public class MioTest {
	
    public static void main(String[] args) {
        
        Archivio arch = new Archivio("Mia musica");
        
        System.out.println(arch.getNome());
        
       arch.leggi("prova.txt");
        
        System.out.println(arch.getCartelle());

        System.out.println(arch.getAlgoritmi());

        
        for (Iterator cartelle = arch.getCartelle().iterator(); cartelle.hasNext();) {
            Cartella c = (Cartella) cartelle.next();
            System.out.println("Cartella " + c.getPath());
            for (Iterator brani = c.getBrani().iterator(); brani.hasNext();) {
                Brano b = (Brano) brani.next();
                System.out.println("\t" + b.getArtista() + " : " + b.getTitolo() + " - " + 
                            b.getAlgoritmo().getNome() );
                System.out.println(b.comandoPlay());
            }
        }
        
        System.out.println(arch.getBraniArtista("Ligabue"));
    }

}
