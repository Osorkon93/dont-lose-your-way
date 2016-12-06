package Core.Exceptions;

import java.io.IOException;


public class TenSamPrzystanekException extends IOException {

	private String nazwaPrzystanku;

	public TenSamPrzystanekException(String temp) {
		this.nazwaPrzystanku = temp;
	}
	
	public String getPrzystanek(){
		return this.nazwaPrzystanku;
	}
}
