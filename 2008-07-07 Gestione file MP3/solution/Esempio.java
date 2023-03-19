import java.util.Iterator;

import mp3.*;

public class Esempio {

    public static void main(String[] args) {
        
        Archivio arch = new Archivio("Mia musica");
        
        arch.registraAlgoritmo("Mpeg layer 3","mp3","winamp");
        arch.registraAlgoritmo("Ogg Vorbis","ogg","winamp");
        arch.registraAlgoritmo("Windows Media Audio","wma","wmplayer");
        
        System.out.println("Ci sono " + arch.getAlgoritmi().size() + " algoritmi registrati.");

        Cartella classica = arch.aggiungiCartella("/var/classica");
        Cartella rock = arch.aggiungiCartella("/var/rock");
        
        classica.aggiungiBrano("albinoni.mp3","Adagio","Albinoni");
        rock.aggiungiBrano("one.mp3","One","U2");
        rock.aggiungiBrano("Bloody sunday.mp3","Bloody Sunday","U2");
        rock.aggiungiBrano("time.ogg","Time","Pink Floyd");
        
        System.out.println(arch.getBraniArtista("U2"));
        
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
    }
}
