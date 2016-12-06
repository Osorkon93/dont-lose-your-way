package Core.Expections;

import java.io.IOException;


public class TaSamaLiniaException extends IOException {

	private String nazwa;

	public TaSamaLiniaException(String nazwaLinii) {
		this.nazwa = nazwaLinii;
	}

	public String getLinia(){
		return this.nazwa;
	}
}
