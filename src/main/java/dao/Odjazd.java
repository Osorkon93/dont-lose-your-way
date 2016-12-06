package dao;

public class Odjazd {
	
	private Kurs kurs;
	private int id;
	private Godzina godzina;

	public Odjazd(Kurs tempKurs, int j, Godzina tempGodzina) {
		this.kurs = tempKurs;
		this.id = j;
		this.godzina = tempGodzina;
	}

	public boolean czyDojezdzaDoCelu(){
		return (id < kurs.getTrasa().getIdDocelowe());
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
