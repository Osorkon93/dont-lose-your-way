package dao;

public class Odjazd {
	/*
	konkretny odjazd danego kursu linii komunikacyjnej z j-tego przystanku trasy(liczymy od zera) o danej godzinie
	 */
	
	private Kurs kurs;
	private int id;
	private Godzina godzina;

	public Odjazd(Kurs tempKurs, int j, Godzina tempGodzina) {
		this.kurs = tempKurs;
		this.id = j;
		this.godzina = tempGodzina;
	}

	public Godzina getCzas(){
		return this.godzina;
	}

	public Kurs getKurs() {
		return kurs;
	}

	public int getId(){
		return id;
	}

	public Przystanek getPrzystanek() {
		return this.kurs.getTrasa().getPrzystanek(id);
	}
}
