package Core.Exceptions;

import java.io.IOException;


public class ZbedneDaneNaKoncuException extends IOException {

	private String[] daneWejscia;
	private int index;
	
	public ZbedneDaneNaKoncuException(int index, String[] daneWejscia) {
		this.daneWejscia = daneWejscia;
		this.index = index;
	}
	
	public String getZbedne(){
		String wynik = "";
		int i = index;
		while(i < daneWejscia.length){
			wynik+=daneWejscia[i];
			if(i != daneWejscia.length - 1)
				wynik+=" ";
			i++;
		}
		return wynik;
	}
}
