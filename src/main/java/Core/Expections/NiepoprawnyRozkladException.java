package Core.Expections;

import java.io.IOException;


public class NiepoprawnyRozkladException extends IOException {
	
	private String nazwaLinii;
	private int numerTrasy;
	private int numerWiersza;

	public NiepoprawnyRozkladException(String nazwaLinii, int i, int j) {
		this.nazwaLinii = nazwaLinii;
		this.numerTrasy = i;
		this.numerWiersza = j;
	}

	public String getLinia() {
		return nazwaLinii;
	}

	public int getNumerTrasy() {
		return numerTrasy;
	}

	public int getNumerKursu() {
		return numerWiersza;
	}
}
