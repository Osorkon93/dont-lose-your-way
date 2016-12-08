package Core.wyszukiwarka;

/**
 * Interfejs Wyszukiwarki. Ponadto Wyszukiwarka powinna mieć bezargumentowy 
 * konstruktor publiczny. 
 */
public interface WyszukiwarkaPolaczen {

	/**
	 * Wczytuje dane z bazy danych. Po wywołaniu tej metody można
	 * rozpocząć wyszukiwanie.
	 */
	void wczytajBaze() throws Exception;

	/**
	 * Znajduje wszystkie połączenia i zwraca ich listę posortowaną w porządku szybkim.
	 * @param start nazwa przystanku początkowego
	 * @param stop nazwa przystanku docelowego
	 * @param czasStartu początek wyszukiwania
	 * @param dlugosc dlugosc przedzialu wyszukiwania
	 * @return opis znalezionego polaczenia
	 * @throws Exception
	 */
	String znajdzListeNajszybszych(String start, String stop, String czasStartu, String dlugosc) throws Exception ;

	/**
	 * Znajduje wszystkie połączenia i zwraca tylko najszybsze
	 * @param start nazwa przystanku początkowego
	 * @param stop nazwa przystanku docelowego
	 * @param czasStartu początek wyszukiwania
	 * @param dlugosc dlugosc przedzialu wyszukiwania
	 * @return opis znalezionego polaczenia
	 * @throws Exception
	 */
	String znajdzNajszybszy(String start, String stop, String czasStartu, String dlugosc) throws Exception ;

	/**
	 * Znajduje wszystkie połączenia i zwraca ich listę posortowaną w porządku wygodnym.
	 * @param start nazwa przystanku początkowego
	 * @param stop nazwa przystanku docelowego
	 * @param czasStartu początek wyszukiwania
	 * @param dlugosc dlugosc przedzialu wyszukiwania
	 * @return opis znalezionego polaczenia
	 * @throws Exception
	 */
	String znajdzListeNajwygodniejszych(String start, String stop, String czasStartu, String dlugosc) throws Exception ;

	/**
	 * Znajduje wszystkie połączenia i zwraca tylko najwygodniejsze
	 * @param start nazwa przystanku początkowego
	 * @param stop nazwa przystanku docelowego
	 * @param czasStartu początek wyszukiwania
	 * @param dlugosc dlugosc przedzialu wyszukiwania
	 * @return opis znalezionego polaczenia
	 * @throws Exception
	 */
	String znajdzNajwygodniejszy(String start, String stop, String czasStartu, String dlugosc) throws Exception ;


}
