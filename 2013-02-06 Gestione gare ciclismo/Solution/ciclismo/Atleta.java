package ciclismo;

public class Atleta {
	
	private int tesseraAtleta;
	private String nome;
	private String cognome;
	private String societa;
	private int punti = 0;
	private int puntiPesati = 0;

	Atleta(int tesseraAtleta, String nome, String cognome, String societa){
		
		this.tesseraAtleta=tesseraAtleta;
		this.nome=nome;
		this.cognome=cognome;
		this.societa=societa;
	}
	
	public int getTesseraAtleta(){
		return tesseraAtleta;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getCognome(){
		return cognome;
	}
	
	public String getSocieta(){
		return societa;
	}
	
	public int getPunti(){
		return punti;
	}

	public void setPunti(int punti){
		this.punti = punti;
	}
	
	public void addPunti(int punti){
		this.punti += punti;
	}
	
	public int getPuntiPesati(){
		return puntiPesati;
	}

	public void setPuntiPesati(int puntiPesati){
		this.puntiPesati = puntiPesati;
	}
	
	public void addPuntiPesati(int puntiPesati){
		this.puntiPesati += puntiPesati;
	}

}
