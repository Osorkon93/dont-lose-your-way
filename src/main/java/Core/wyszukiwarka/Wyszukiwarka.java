//package Core.wyszukiwarka;
//
//import Core.Exceptions.*;
//import Core.Storage;
//import Core.comparators.CzasComparator;
//import Core.comparators.WygodaComparator;
//import dao.*;
//
//import java.util.*;
//import java.util.regex.PatternSyntaxException;
//
//public class Wyszukiwarka implements WyszukiwarkaPolaczen {
//
//	private Map<String, Stop> przystanki;
//	private Set<Polaczenie> polaczenia;
//	private Dfs dfs;
//
//	public Wyszukiwarka(){
//		przystanki = new HashMap<String, Stop>();
//	}
//
//	public static void main(String args[]){
//		try{
//			String wynik;
////			Scanner wejscie = new Scanner(System.in);
//
////			if(!wejscie.hasNextLine())throw new BrakDanychException();
////			String liniaWejscia = wejscie.nextLine();
////			wejscie.close();
//
////			String daneWejscia[] = liniaWejscia.split(" ");
//
//            String daneWejscia[] = args;
//			int index = 0;
//
//			Wyszukiwarka w = new Wyszukiwarka();
//			w.wczytajBaze();
//
//			String przystanekPoczatkowy = null;
//			String przystanekDocelowy = null;
//			for(int i=0; i<=1; i++){
//				String tempPrzystanek;
//				if(index >= daneWejscia.length)throw new BrakDanychException();
//				tempPrzystanek = daneWejscia[index];
//				index++;
//				boolean znalazlem = false;
//				while(index < daneWejscia.length && !znalazlem){
//					tempPrzystanek += " "+daneWejscia[index];
//					index++;
//					znalazlem = w.czyIstnieje(tempPrzystanek);
//				}
//				if(!znalazlem)throw new PrzystanekNotFoundException(tempPrzystanek);
//				if(i==0)przystanekPoczatkowy = tempPrzystanek;
//				else przystanekDocelowy = tempPrzystanek;
//			}
//
//			if(index >= daneWejscia.length)throw new BrakDanychException();
//			String czasPoczatkowy = daneWejscia[index];
//			index++;
//			if(index >= daneWejscia.length)throw new BrakDanychException();
//			String dlugosc = daneWejscia[index];
//			index++;
//			if(index < daneWejscia.length)throw new ZbedneDaneNaKoncuException(index, daneWejscia);
//
//			//
////            Storage.loadPreferences(); //na wszelki wypadek
//            String wiele = Storage.wiele;
//            String tryb = Storage.tryb;
//            //
//
//			if(tryb.equals("szybki")){
//				if(wiele.equals("wiele"))wynik = w.znajdzListeNajszybszych(przystanekPoczatkowy, przystanekDocelowy, czasPoczatkowy, dlugosc);
//				else wynik = w.znajdzNajszybszy(przystanekPoczatkowy, przystanekDocelowy, czasPoczatkowy, dlugosc);
//			}
//			else{
//				if(wiele.equals("wiele"))wynik = w.znajdzListeNajwygodniejszych(przystanekPoczatkowy, przystanekDocelowy, czasPoczatkowy, dlugosc);
//				else wynik = w.znajdzNajwygodniejszy(przystanekPoczatkowy, przystanekDocelowy, czasPoczatkowy, dlugosc);
//			}
//			System.out.print(wynik);
//		}catch(ZbedneDaneNaKoncuException w){
//			System.out.println("Na końcu wiersza wejścia występują zbędne dane! Usuń fragment: "+w.getZbedne());
////		}catch(NiepoprawneDaneWejsciaException w){
////			System.out.println("Dane wejscia niezgodne ze specyfikacją! Nierozpoznano fragmentu: "+w.getString());
//		}catch(PatternSyntaxException w){
//			System.out.println("Dane wejscia niezgodne ze specyfikacją!");
//		}catch(GodzinaFormatException w){
//			System.out.println("Niepoprawny format godziny! Postać "+w.getGodzina()+" jest niedopuszczalna!");
//		}catch(BrakDanychException w){
//			System.out.println("Nie wprowadzono wszystkich danych!");
////		}catch(FileNotFoundException w){
////			System.out.println("Plik pod wskazanym adresem nie istnieje! Wskazana ścieżka to: "+w.getLocalizedMessage());
////		}catch(NiepoprawnyRozkladException w){
////			System.out.println("Plik zawiera nieprawidłowy format danych! Nieprawidłowy rozkład linii "+w.getLinia()+" na trasie "+w.getNumerTrasy()+". w wierszu "+w.getNumerKursu());
////		}catch(BrakDanychWPlikuException w){
////			System.out.println("Plik zawiera niekompletne dane!");
////		}catch(TaSamaLiniaException w){
////			System.out.println("Plik wejściowy zawiera niepoprawne dane! Dane o linii "+w.getLinia()+ " występują w pliku więcej niż raz w pliku wejściowym!");
////		}catch(TenSamPrzystanekException w){
////			System.out.println("Plik wejściowy zawiera niepoprawne dane! Stop o nazwie "+w.getPrzystanek()+" występuje więcej niż raz w bazie przystanków!");
////		}catch(PrzystanekNotExistException w){
////			System.out.println("Stop "+w.getPrzystanek()+", podany w pliku wejściowym jako jeden z przystanków linii "+w.getLinia()+", nie istnieje w bazie!");
//		}catch(PrzystanekNotFoundException w){
//			System.out.println("Podany na wejściu przystanek ("+w.getPrzystanek()+") nie istnieje w bazie!");
//		}
//	}
//
//	public void wczytajBaze() {
//////szkic metody
////		//pętla po wszystkich przystankach
////		String nazwa;
////
////		Stop tempPrzystanek = new Stop(nazwa);	// FIXME: 06.12.2016
////		przystanki.put(nazwa, tempPrzystanek);
////
////		//petla po wszystkich liniach
////			String nazwaLinii;
////			Line tempLinia = new Line(nazwaLinii);    // FIXME: 06.12.2016
////			linie.put(nazwaLinii, tempLinia);
////			List<Trasa> tempTrasy = new LinkedList<Trasa>();
////			String tempLine = null;
////
////				//dla danej linii
////					List<Stop> tempPrzystanki = new ArrayList<Stop>();
////							//pętla po przystankach
////							String temp;
////							tempPrzystanki.add(przystanki.get(temp));
////
////					Trasa tempTrasa = new Trasa(tempPrzystanki);
////					tempTrasy.add(tempTrasa);
////					trasy.add(tempTrasa);
////
////
////
////
////					for(int j=0; j<2; j++){		//// FIXME: 06.12.2016 dodałem bez sensu tego for'a tylko po to aby zgadzaly sie nawiasy
////
////					//pętla
////						Kurs tempKurs = new Kurs(tempLinia, aktTrasa);    // FIXME: 06.12.2016
////						kursy.add(tempKurs);
////						List<Odjazd> tempOdjazdy = new ArrayList<Odjazd>();
////
////						String temp;
////							Godzina tempGodzina = new Godzina(temp);    // FIXME: 06.12.2016
////							Odjazd tempOdjazd = new Odjazd(tempKurs, j, tempGodzina);
////							tempOdjazdy.add(tempOdjazd);
////							Stop aktPrzystanek = null;
////							if(aktTrasa != null)aktPrzystanek = aktTrasa.getPrzystanek(j);
////							if(aktPrzystanek != null)aktPrzystanek.dodajOdjazd(tempOdjazd);
////							j++;
////						}
////						tempKurs.dodajOdjazdy(tempOdjazdy);
//
//	}
//
//	public String znajdzListeNajszybszych(String start, String stop, String czasStartu, String dlugosc) throws GodzinaFormatException{
//		znajdz(start, stop, czasStartu, dlugosc);
//		CzasComparator comparator = new CzasComparator();
//		polaczenia = new TreeSet<Polaczenie>(comparator);
//		zbierzDane();
//		return wynik();
//	}
//
//	public String znajdzNajszybszy(String start, String stop, String czasStartu, String dlugosc) throws GodzinaFormatException{
//		znajdz(start, stop, czasStartu, dlugosc);
//		CzasComparator comparator = new CzasComparator();
//		polaczenia = new TreeSet<Polaczenie>(comparator);
//		zbierzDane();
//		return wynikPojedynczy();
//	}
//
//	public String znajdzListeNajwygodniejszych(String start, String stop, String czasStartu, String dlugosc) throws GodzinaFormatException{
//		znajdz(start, stop, czasStartu, dlugosc);
//		WygodaComparator comparator = new WygodaComparator();
//		polaczenia = new TreeSet<Polaczenie>(comparator);
//		zbierzDane();
//		return wynik();
//	}
//
//	public String znajdzNajwygodniejszy(String start, String stop, String czasStartu, String dlugosc) throws GodzinaFormatException{
//		znajdz(start, stop, czasStartu, dlugosc);
//		WygodaComparator comparator = new WygodaComparator();
//		polaczenia = new TreeSet<Polaczenie>(comparator);
//		zbierzDane();
//		return wynikPojedynczy();
//	}
//
//	private boolean czyIstnieje(String przystanek){
//		return przystanki.containsKey(przystanek);
//	}
//
//	private void znajdz(String start, String cel, String czasStart, String dlugosc) throws GodzinaFormatException{
//		this.dfs = new Dfs(przystanki.get(start), przystanki.get(cel), czasStart, dlugosc);
//	}
//
//	private void zbierzDane(){
//		Iterator<Stack<ElementStosu>> itS = dfs.getWynik().iterator();
//		while(itS.hasNext()){
//			String tempOpis = new String("");
//			Stack<ElementStosu> pomStos = itS.next();
//			Iterator<ElementStosu> itE = pomStos.iterator();
//			ElementStosu pomElt = null;
//			ElementStosu remember = null;
//			boolean jedziemy = false;
//			int ktoryPrzystanek = 0;
//			int ileKursow = 0;
//			while(itE.hasNext()){
//				pomElt = itE.next();
//				if(jedziemy){
//					ktoryPrzystanek++;
//					if(pomElt.getOdjazd().getKurs() != remember.getOdjazd().getKurs()){
//						ktoryPrzystanek--;
//						tempOpis+=remember.getCzas().getString()+" "+remember.getPrzystanek().getNazwa()+" ("+ktoryPrzystanek+" przystanek)\n";
//						jedziemy = false;
//						ktoryPrzystanek=0;
//					}
//				}
//				if(!jedziemy){
//					tempOpis+="["+pomElt.getLinia()+"] "+pomElt.getCzas().getString()+" "+pomElt.getPrzystanek().getNazwa()+" => ";
//					jedziemy = true;
//					ileKursow++;
//				}
//				remember = pomElt;
//			}
//			Godzina tempCzasDojazdu = remember.getCzas();
//			tempOpis+=remember.getCzas().getString()+" "+remember.getPrzystanek().getNazwa()+" ("+ktoryPrzystanek+" przystanek)\n";
//			Polaczenie tempPolaczenie = new Polaczenie(tempOpis, tempCzasDojazdu, ileKursow-1);
//			polaczenia.add(tempPolaczenie);
//		}
//	}
//
//	private String wynik(){
//		String wynik = "Liczba znalezionych połączeń: "+polaczenia.size()+"\n";
//		Iterator<Polaczenie> it = polaczenia.iterator();
//		int indeks = 1;
//		while(it.hasNext()){
//			wynik += "Połączenie "+indeks+":\n" +
//			it.next().getOpis() +
//			"\n" +
//			"";
//			indeks++;
//		}
//		return wynik;
//	}
//
//	private String wynikPojedynczy(){
//		if(polaczenia.isEmpty()) return "Liczba znalezionych połączeń: 0\n";
//		else{
//			String wynik = "Liczba znalezionych połączeń: 1\n" +
//			"Połączenie 1:\n" +
//			polaczenia.iterator().next().getOpis() +
//			"\n" +
//			"";
//			return wynik;
//		}
//	}
//}
