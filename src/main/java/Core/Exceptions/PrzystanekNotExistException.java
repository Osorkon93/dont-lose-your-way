package Core.Exceptions;

import java.io.IOException;


public class PrzystanekNotExistException extends IOException {
	private String nazwaLinii;
	private String nazwaPrzystanku;

	public PrzystanekNotExistException(String nazwaLinii, String nazwaPrzystanku) {
		this.nazwaLinii = nazwaLinii;
		this.nazwaPrzystanku = nazwaPrzystanku;
	}

	public String getLinia(){
		return this.nazwaLinii;
	}
	
	public String getPrzystanek(){
		return this.nazwaPrzystanku;
	}
}
