package Core.wyszukiwarka;
import dao.*;

public class ElementStosu {
	
	private Godzina czas;
	private Przystanek przystanek;
	private String linia;
	private Odjazd odjazd;
	private boolean czyWysiadam;
	
	public ElementStosu(Godzina czas, Przystanek przystanek, String linia, Odjazd odjazd, boolean b) {
		this.czas = czas;
		this.przystanek = przystanek;
		this.linia = linia;
		this.odjazd = odjazd;
		this.czyWysiadam = b;
	}
	
	public Godzina getCzas(){
		return this.czas;
	}
	
	public Przystanek getPrzystanek(){
		return this.przystanek;
	}
	
	public String getLinia(){
		return this.linia;
	}
	
	public Odjazd getOdjazd(){
		return this.odjazd;
	}
	
	public boolean czyWysiadam(){
		return czyWysiadam;
	}
}
