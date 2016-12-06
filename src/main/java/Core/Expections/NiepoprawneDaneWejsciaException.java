package Core.Expections;

import java.io.IOException;


public class NiepoprawneDaneWejsciaException extends IOException{
	
	private String wejscie;
	
	public NiepoprawneDaneWejsciaException(String porzadek) {
		this.wejscie = porzadek;
	}
	
	public String getString(){
		return this.wejscie;
	}
}
