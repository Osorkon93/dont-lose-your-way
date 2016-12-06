package dao;

public class Linia {
	
	private String nazwa;
	
	public Linia(String nazwaLinii) {
		this.nazwa = nazwaLinii;
	}

	public void wypiszTest() {
		System.out.println(nazwa);
	}

	public String getNazwa() {
		return nazwa;
	}
}
