package dao;

import java.util.List;


public class Kurs {
	/*
	konkretny przejazd danej linii komunikacyjnej po danej trasie (związany z fizycznym środkiem komunikacji o określonym identyfikatorze)
	ponadto n-ty element listy odjazdy to odjazd z n-tego przystanku Trasy
	 */
	
	private String linia;
	private Trasa trasa;
	private List<Odjazd> odjazdy;
	
	public Kurs(String tempLinia, Trasa trasa) {
		this.linia = tempLinia;
		this.trasa = trasa;
	}

	public Trasa getTrasa() {
		return this.trasa;
	}
	
	public String getLinia() {
		return linia;
	}
	
	public void dodajOdjazdy(List<Odjazd> tempOdjazdy) {
		odjazdy = tempOdjazdy;
	}
	
	public Odjazd getNext(int id) {
		if (id+1<odjazdy.size())return odjazdy.get(id+1);
		else return null;
	}
}
