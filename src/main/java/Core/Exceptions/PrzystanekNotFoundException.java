package Core.Exceptions;

import java.io.IOException;


public class PrzystanekNotFoundException extends IOException {
	
	private String przystanek;

	public PrzystanekNotFoundException(String tempPrzystanek) {
		this.przystanek = tempPrzystanek;
	}
	
	public String getPrzystanek(){
		return this.przystanek;
	}
}
