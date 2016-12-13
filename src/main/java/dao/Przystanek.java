package dao;

import java.util.LinkedList;


public class Przystanek {
	
	private String nazwa;
	private LinkedList<Odjazd> odjazdy = new LinkedList<Odjazd>();
	
	public Przystanek(String nazwa){
		this.nazwa = nazwa;
	}

	public void dodajOdjazd(Odjazd tempOdjazd){
		odjazdy.add(tempOdjazd);
	}
	
	public LinkedList<Odjazd> getOdjazdy(){
		return this.odjazdy;
	}
	
	public String getNazwa(){
		return nazwa;
	}

}
