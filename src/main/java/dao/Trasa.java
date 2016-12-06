package dao;

import java.util.Iterator;
import java.util.List;


public class Trasa {
	
	private List<Przystanek> przystanki;
	private int idDocelowy = -1;

	public Trasa(List<Przystanek> tempPrzystanki) {
		przystanki = tempPrzystanki;
	}

	public Przystanek getPrzystanek(int j) {
		if(przystanki != null) return przystanki.get(j);
		else return null;
	}
	
	public void ustawIdDocelowy(Przystanek przystanek){
		Iterator<Przystanek> it = przystanki.iterator();
		int i = 0;
		while(it.hasNext()){
			Przystanek tempPrzystanek = it.next();
			if(tempPrzystanek == przystanek)idDocelowy = i;
			i++;
		}
	}

	public int getIdDocelowe() {
		return idDocelowy;
	}

	public int getLiczbaPrzystankow() {
		return przystanki.size();
	}
}
