package Core.wyszukiwarka;

import dao.Godzina;
import dao.Linia;
import dao.Odjazd;
import dao.Przystanek;

public class ElementStosu {
	
	private Godzina czas;
	private Przystanek przystanek;
	private Linia linia;
	private Odjazd odjazd;
	private boolean destination;
	private boolean czyWysiadam;
	
	public ElementStosu(Godzina czas, Przystanek przystanek, Linia linia, Odjazd odjazd, boolean czyDojezdza, boolean b) {
		this.czas = czas;
		this.przystanek = przystanek;
		this.linia = linia;
		this.odjazd = odjazd;
		this.destination = czyDojezdza;
		this.czyWysiadam = b;
	}
	
	public Godzina getCzas(){
		return this.czas;
	}
	
	public Przystanek getPrzystanek(){
		return this.przystanek;
	}
	
	public Linia getLinia(){
		return this.linia;
	}
	
	public Odjazd getOdjazd(){
		return this.odjazd;
	}
	
	public boolean czyDocelowy(){
		return destination;
	}
	
	public boolean czyWysiadam(){
		return czyWysiadam;
	}
}
