package dao;

public class Polaczenie {
	
	private Godzina czasKoncowy;
	private String opis;
	private int liczbaPrzesiadek;

	public Polaczenie(String tempOpis, Godzina tempCzasDojazdu, int i) {
		this.opis = tempOpis;
		this.czasKoncowy = tempCzasDojazdu;
		this.liczbaPrzesiadek = i;
	}

	public Godzina getCzasKoncowy() {
		return czasKoncowy;
	}

	public String getOpis() {
		return opis;
	}
	
	public int getLiczbaPrzesiadek(){
		return liczbaPrzesiadek;
	}
}
