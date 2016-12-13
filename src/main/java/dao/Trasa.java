package dao;

import java.util.Iterator;
import java.util.List;


public class Trasa {
	/*
	lista przystanków, po których przejeżdża pewna linia komunikacyjna
	 */
	
	private List<Przystanek> przystanki;

	public Trasa(List<Przystanek> tempPrzystanki) {
		przystanki = tempPrzystanki;
	}

	public Przystanek getPrzystanek(int j) {
		if(przystanki != null) return przystanki.get(j);
		else return null;
	}

	public int getLiczbaPrzystankow() {
		return przystanki.size();
	}
}
