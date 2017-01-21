//package Core.wyszukiwarka;
//import Core.Exceptions.*;
//import dao.*;
//
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Stack;
//
//
//public class Dfs {
//
//	private Stack<ElementStosu> historia;
//	private List<Stack<ElementStosu>> wynik;
//	private Godzina limitCzasu;
//	private Stop przystanekDocelowy;
//	private Stop przystanekStartowy;
//	private Godzina poczatekCzasu;
//
//	public Dfs(Stop przystanekStartowy, Stop przystanekDocelowy, String czasStart, String dlugosc) throws GodzinaFormatException{
//		historia = new Stack<ElementStosu>();
//		wynik = new LinkedList<Stack<ElementStosu>>();
//		this.przystanekStartowy = przystanekStartowy;
//		this.przystanekDocelowy = przystanekDocelowy;
//		this.poczatekCzasu = new Godzina(czasStart);
//		this.limitCzasu = new Godzina(this.poczatekCzasu, dlugosc);
//	}
//
//	public List<Stack<ElementStosu>> getWynik() {
//		List<Odjazd> listaOdjazdow = null;
//		if(przystanekStartowy != null)listaOdjazdow = przystanekStartowy.getOdjazdy();
//		Iterator<Odjazd> it = null;
//		if(listaOdjazdow != null)it = listaOdjazdow.iterator();
//		if(it != null){
//			while(it.hasNext()){
//				Odjazd pomOdjazd = it.next();
//				if(pomOdjazd.getCzas().czyNieMniejszy(poczatekCzasu)){
//					dfs(new ElementStosu(pomOdjazd.getCzas(), pomOdjazd.getKurs().getLinia(), pomOdjazd, false));
//				}
//			}
//		}
//		return this.wynik;
//	}
//
//	private void dfs(ElementStosu akt){
//		if(akt.getCzas().czyWiekszy(limitCzasu))return;
//		historia.push(akt);
//		if(akt.czyWysiadam() && akt.getPrzystanek()== przystanekDocelowy){
//			Stack<ElementStosu> tempHistoria = new Stack<ElementStosu>();
//			Iterator<ElementStosu> it = historia.iterator();
//			while(it.hasNext()){
//				ElementStosu tempElt = it.next();
//				tempHistoria.add(tempElt);
//			}
//			wynik.add(tempHistoria);
//			historia.pop();
//			return;
//		}
//		if(akt.czyWysiadam() && !(akt.getPrzystanek()==przystanekDocelowy)){
//			List<Odjazd> x = akt.getPrzystanek().getOdjazdy();
//			Iterator<Odjazd> it1 = x.iterator();
//			while(it1.hasNext()){
//				Odjazd tempOdjazd = it1.next();
//				if(tempOdjazd.getCzas().czyNieMniejszy1(akt.getCzas()) && !this.czyWystepujeLinia(tempOdjazd.getKurs().getLinia())){
//					ElementStosu tempElt = new ElementStosu(tempOdjazd.getCzas(), tempOdjazd.getKurs().getLinia(), tempOdjazd, false);
//					dfs(tempElt);
//				}
//			}
//			/*
//			List<Stop> tempPrzystanki = akt.getPrzystanek().getZespol().getStops();
//			Iterator<Stop> it2 = tempPrzystanki.iterator();
//			while(it2.hasNext()){
//				Stop tempPrzystanek = it2.next();
//				if(!this.czyWystepujePrzystanek(tempPrzystanek)){
//					List<Odjazd> y = tempPrzystanek.getOdjazdy();
//					Iterator<Odjazd> it3 = y.iterator();
//					while(it3.hasNext()){
//						Odjazd tempOdjazd = it3.next();
//						if(tempOdjazd.getCzas().czyNieMniejszy5(akt.getCzas()) && !this.czyWystepujeLinia(tempOdjazd.getKurs().getLinia())){
//							ElementStosu tempElt = new ElementStosu(tempOdjazd.getCzas(), tempPrzystanek, tempOdjazd.getKurs().getLinia(), tempOdjazd, tempOdjazd.czyDojezdzaDoCelu(), false);
//							dfs(tempElt);
//						}
//					}
//				}
//			}
//			*/
//		}
//		Odjazd aktOdjazd = akt.getOdjazd();
//		Odjazd nast = aktOdjazd.getKurs().getNext(aktOdjazd.getId());
//		ElementStosu nastElt;
//		if(nast != null){
//			if(!this.czyWystepujePrzystanek(nast.getPrzystanek())){
//				nastElt = new ElementStosu(nast.getCzas(), nast.getKurs().getLinia(), nast, true);
//				dfs(nastElt);
//			}
//		}
//		historia.pop();
//	}
//
//	private boolean czyWystepujePrzystanek(Stop przystanek) {
//		boolean wystepuje = false;
//		Iterator<ElementStosu> it = historia.iterator();
//		while(it.hasNext()){
//			ElementStosu temp = it.next();
//			if(temp.getOdjazd().getPrzystanek() == przystanek)wystepuje = true;
//		}
//		return wystepuje;
//	}
//
//	private boolean czyWystepujeLinia(String linia) {
//		boolean wystepuje = false;
//		Iterator<ElementStosu> it = historia.iterator();
//		while(it.hasNext()){
//			ElementStosu temp = it.next();
//			if(temp.getLinia() == linia)wystepuje = true;
//		}
//		return wystepuje;
//	}
//}
