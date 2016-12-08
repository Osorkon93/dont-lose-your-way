package dao;

import java.util.List;


public class Kurs {
	/*
	zbiór
	 */
	
	private Linia linia;//delete
	private Trasa trasa;
	private List<Odjazd> odjazdy;
	
	public Kurs(Linia tempLinia, Trasa trasa) {
		this.linia = tempLinia;
		this.trasa = trasa;
	}

	public Trasa getTrasa() {
		return this.trasa;
	}
	
	public Linia getLinia() {
		return linia;
	}
	
	public void dodajOdjazdy(List<Odjazd> tempOdjazdy) {
		odjazdy = tempOdjazdy;
	}
	
	public Odjazd getNext(int id) { //
		if (id+1<odjazdy.size())return odjazdy.get(id+1);
		else return null;
	}
}
