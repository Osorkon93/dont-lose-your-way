package Core.Expections;

import java.io.IOException;


public class GodzinaFormatException extends IOException {
	
	private String nazwaGodziny;
	
	public GodzinaFormatException(String dlugosc) {
		nazwaGodziny = dlugosc;
	}
	
	public String getGodzina(){
		return nazwaGodziny;
	}
}
