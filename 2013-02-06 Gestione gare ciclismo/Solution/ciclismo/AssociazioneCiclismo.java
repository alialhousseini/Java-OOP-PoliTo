package ciclismo;

import java.io.*;
import java.util.*;

public class AssociazioneCiclismo {

	HashMap atleti = new HashMap();
	HashMap gare = new HashMap();

	public void aggiungiAtleta(int tesseraAtleta, String nome, String cognome, String societa) {

		Atleta a = new Atleta(tesseraAtleta, nome, cognome, societa);
		atleti.put(tesseraAtleta, a);
	}

	public void aggiungiGara(String codiceGara, String descrittore, int peso) {

		Gara g = new Gara(codiceGara, descrittore, peso);
		gare.put(codiceGara, g);
	}

	public String getAtleta(int tessera) throws AtletaNonPresente {

		Atleta t = (Atleta) atleti.get(tessera);
		if (t == null)
			throw new AtletaNonPresente();
		return t.getCognome();
	}

	public String getFullAtleta(int tessera) throws AtletaNonPresente {

		Atleta t = (Atleta) atleti.get(tessera);
		if (t == null)
			throw new AtletaNonPresente();
		String stringa = t.getTesseraAtleta() + ", " + t.getNome() + ", " + t.getCognome() + ", " + t.getSocieta();
		return stringa;
	}

	public int getGara(String codiceGara) throws GaraNonPresente {

		Gara g = (Gara) gare.get(codiceGara);
		if (g == null)
			throw new GaraNonPresente();
		return g.getPeso();
	}

	public void aggiungiAtletaGara(int tesseraAtleta, String codiceGara)
			throws AtletaNonPresente, GaraNonPresente {

		Atleta a = (Atleta) atleti.get(tesseraAtleta);
		if (a == null)
			throw new AtletaNonPresente();
		Gara g = (Gara) gare.get(codiceGara);
		if (g == null)
			throw new GaraNonPresente();
		g.iscritti.add(a);
	}

	public int pettoraleAtletaGara(int tesseraAtleta, String codiceGara)
			throws AtletaNonPresente, GaraNonPresente, NonIscritto {

		Atleta a = (Atleta) atleti.get(tesseraAtleta);
		if (a == null)
			throw new AtletaNonPresente();
		Gara g = (Gara) gare.get(codiceGara);
		if (g == null)
			throw new GaraNonPresente();
		Atleta elemento = null;
		int i = 1;
		int numero = 0;
		for (Iterator iter = g.iscritti.iterator(); iter.hasNext();) {
			Atleta x = (Atleta) iter.next();
			if (x.getTesseraAtleta() == a.getTesseraAtleta()) {
				elemento = (Atleta) x;
				numero = i;
			}
			i++;
		}
		if (elemento == null)
			throw new NonIscritto();
		return numero;
	}

	public void tempoAtletaGara(int tesseraAtleta, String codiceGara, int tempo)
			throws AtletaNonPresente, GaraNonPresente, NonIscritto {

		Atleta a = (Atleta) atleti.get(tesseraAtleta);
		if (a == null)
			throw new AtletaNonPresente();
		Gara g = (Gara) gare.get(codiceGara);
		if (g == null)
			throw new GaraNonPresente();
		Atleta elemento = null;
		for (Iterator iter = g.iscritti.iterator(); iter.hasNext();) {
			Atleta x = (Atleta) iter.next();
			if (x.getTesseraAtleta() == a.getTesseraAtleta()) {
				elemento = (Atleta) x;
			}
		}
		if (elemento == null)
			throw new NonIscritto();
		g.tempi.put(tempo, elemento);
	}

	public int posizioneAtletaGara(int tesseraAtleta, String codiceGara)
			throws AtletaNonPresente, GaraNonPresente, NonIscritto {

		Collection classifica = new LinkedList();
		int b = 0;
		int pos = 0;
		Atleta a = (Atleta) atleti.get(tesseraAtleta);
		if (a == null)
			throw new AtletaNonPresente();
		Gara g = (Gara) gare.get(codiceGara);
		if (g == null)
			throw new GaraNonPresente();
		Atleta elemento = null;
		for (Iterator iter = g.iscritti.iterator(); iter.hasNext();) {
			Atleta x = (Atleta) iter.next();
			if (x.getTesseraAtleta() == a.getTesseraAtleta()) {
				elemento = (Atleta) x;
			}
		}
		if (elemento == null)
			throw new NonIscritto();
		classifica = g.tempi.values();
		for (Iterator it = classifica.iterator(); it.hasNext();) {
			Atleta x = (Atleta) it.next();
			b++;
			if (x.getTesseraAtleta() == a.getTesseraAtleta()) {
				pos = b;
			}
		}
		return pos;
	}

	public List<String> classificaGara(String codiceGara)
			throws GaraNonPresente, AtletaNonPresente, NonIscritto {

		Collection classifica = new LinkedList();
		List<String> ris = new ArrayList();
		int pett;
		String stringa;
		Gara g = (Gara) gare.get(codiceGara);
		if (g == null)
			throw new GaraNonPresente();
		classifica = g.tempi.values();
		for (Iterator iter = classifica.iterator(); iter.hasNext();) {
			Atleta x = (Atleta) iter.next();
			pett = pettoraleAtletaGara(x.getTesseraAtleta(), g.getcodiceGara());
			stringa = String.valueOf(pett);
			stringa += " ";
			stringa += x.getTesseraAtleta();
			stringa += " ";
			stringa += (String) x.getCognome();
			ris.add(stringa);
		}
		return ris;
	}

	public List<String> classificaGenerale() throws AtletaNonPresente {

		String stringa;
		Collection elenco = new LinkedList();
		Collection classifica = new LinkedList();
		Collection classificaFinale = new ArrayList();
		SortedMap puntiTot = new TreeMap();
		Collection valoriClassificaFinale = new ArrayList();
		List<String> ris = new ArrayList();
		elenco = gare.values();
		for (Iterator iter = elenco.iterator(); iter.hasNext();) {
			Gara x = (Gara) iter.next();
			classifica = x.tempi.values();
			int i = 10;
			for (Iterator it = classifica.iterator(); it.hasNext();) {
				Atleta a = (Atleta) it.next();
				if (a.getPunti() == 0) {
					a.setPunti(i);
				} else {
					a.addPunti(i);
				}
				i--;
			}
		}
		classificaFinale = atleti.values();
		for (Iterator iter = classificaFinale.iterator(); iter.hasNext();) {
			Atleta a = (Atleta) iter.next();
			puntiTot.put(a.getPunti(), a);
		}
		valoriClassificaFinale = ((TreeMap<Integer, Atleta>) puntiTot).descendingMap().values();
		for (Iterator<Atleta> it = valoriClassificaFinale.iterator(); it.hasNext();) {
			Atleta a = (Atleta) it.next();
			stringa = String.valueOf(a.getTesseraAtleta());
			stringa += " ";
			stringa += a.getCognome();
			stringa += " ";
			stringa += String.valueOf(a.getPunti());
			ris.add(stringa);
		}
		return ris;
	}

	public List<String> classificaGeneralePesata() throws AtletaNonPresente {

		String stringa;
		Collection elenco = new LinkedList();
		Collection classifica = new LinkedList();
		Collection classificaFinale = new ArrayList();
		SortedMap puntiPesatiTot = new TreeMap();
		Collection valoriClassificaFinale = new ArrayList();
		List<String> ris = new ArrayList();
		elenco = gare.values();
		for (Iterator iter = elenco.iterator(); iter.hasNext();) {
			Gara x = (Gara) iter.next();
			classifica = x.tempi.values();
			int i = 10;
			for (Iterator it = classifica.iterator(); it.hasNext();) {
				Atleta a = (Atleta) it.next();
				if (a.getPuntiPesati() == 0) {
					a.setPuntiPesati(i * x.getPeso());
				} else {
					a.addPuntiPesati(i * x.getPeso());
				}
				i--;
			}
		}
		classificaFinale = atleti.values();
		for (Iterator iter = classificaFinale.iterator(); iter.hasNext();) {
			Atleta a = (Atleta) iter.next();
			puntiPesatiTot.put(a.getPuntiPesati(), a);
		}
		valoriClassificaFinale = ((TreeMap<Integer, Atleta>) puntiPesatiTot).descendingMap().values();
		for (Iterator it = valoriClassificaFinale.iterator(); it.hasNext();) {
			Atleta a = (Atleta) it.next();
			stringa = String.valueOf(a.getTesseraAtleta());
			stringa += " ";
			stringa += a.getCognome();
			stringa += " ";
			stringa += String.valueOf(a.getPuntiPesati());
			ris.add(stringa);
		}
		return ris;
	}

	public void loadGara(String fileName) throws IOException {

		try{
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String idG = reader.readLine().trim();
			String line;
			while ((line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ";");
				try{
					String idAtleta = st.nextToken().trim();
					String tempo = st.nextToken().trim();
					StringTokenizer st2 = new StringTokenizer(tempo, ":");
					String t1 = st2.nextToken().trim();
					String t2 = st2.nextToken().trim();
					String t3 = st2.nextToken().trim();
					this.tempoAtletaGara(Integer.parseInt(idAtleta), idG, Integer.parseInt(t2));
				}
				catch (AtletaNonPresente e) {
					e.printStackTrace();
				}
				catch (GaraNonPresente e) {
					e.printStackTrace();
				}
				catch (NonIscritto e) {
					e.printStackTrace();
				}
			}
		}	
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}