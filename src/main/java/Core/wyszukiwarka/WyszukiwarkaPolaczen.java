package Core.wyszukiwarka;

/**
 * Interfejs Wyszukiwarki. Ponadto Wyszukiwarka powinna mieć bezargumentowy 
 * konstruktor publiczny. 
 */
public interface WyszukiwarkaPolaczen {
	/**
	 * Wczytuje dane z pliku o podanej nazwie. Po wywołaniu tej metody można 
	 * rozpocząć wyszukiwanie.
	 * @param fileName nazwa (ścieżka) do pliku
	 * @throws Exception
	 */
	public void wczytajDane(String fileName) throws Exception;
	
	/**
	 * Znajduje najkrótsze połączenie spełniające podane kryteria i zwraca opis wyniku.
	 * @param start nazwa przystanku początkowego
	 * @param cel nazwa przystanku docelowego
	 * @param czasStart początek wyszukiwania
	 * @param dlugosc dlugosc przedzialu wyszukiwania
	 * @return opis znalezionego polaczenia
	 * @throws Exception
	 */
	public String znajdzNajkrotsze(String start, String cel, String czasStart, String dlugosc) throws Exception ;

	/**
	 * Znajduje najwygodniejsze połączenie spełniające podane kryteria i zwraca opis wyniku.
	 * @param start nazwa przystanku początkowego
	 * @param cel nazwa przystanku docelowego
	 * @param czasStart początek wyszukiwania
	 * @param dlugosc dlugosc przedzialu wyszukiwania
	 * @return opis znalezionego polaczenia
	 * @throws Exception
	 */
	public String znajdzNajwygodniejsze(String start, String cel,
                                        String czasStart, String dlugosc) throws Exception ;
	
	/**
	 * Znajduje wszystkie połączenia spełniające podane kryteria, sortuje je w porządku
	 * szybkim i zwraca  opis wyniku.
	 * @param start nazwa przystanku początkowego
	 * @param cel nazwa przystanku docelowego
	 * @param czasStart początek wyszukiwania
	 * @param dlugosc dlugosc przedzialu wyszukiwania
	 * @return opis znalezionego polaczenia
	 * @throws Exception
	 */
	public String znajdzOdNajkrotszego(String start, String stop,
                                       String czasStartu, String dlugosc) throws Exception ;
	
	/**
	 * Znajduje wszystkie połączenia spełniające podane kryteria, sortuje je w porządku
	 * wygodnym i zwraca  opis wyniku.
	 * @param start nazwa przystanku początkowego
	 * @param cel nazwa przystanku docelowego
	 * @param czasStart początek wyszukiwania
	 * @param dlugosc dlugosc przedzialu wyszukiwania
	 * @return opis znalezionego polaczenia
	 * @throws Exception
	 */
	public String  znajdzOdNajwygodniejszego(String start, String stop,
                                             String czasStartu, String dlugosc) throws Exception ;
}
