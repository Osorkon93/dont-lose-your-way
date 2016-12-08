package Core;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Storage{
    public static Preferences prefs;
    public static boolean darkTheme = false;
    public static boolean remember = false;
    public static String lastRouteFrom = "a";
    public static String lastRouteTo = "b";
    public static String tryb = "szybki";
    public static String wiele = "wiele";

    public static void setPreferences() throws BackingStoreException {
        // Define a node in which the preferences can be stored
        prefs = Preferences.userRoot().node("storage");

        //set the values
        prefs.putBoolean("darkTheme", darkTheme);
        prefs.putBoolean("remember", remember);
        if(remember){
            prefs.put("lastRouteFrom", lastRouteFrom);
            prefs.put("lastRouteTo", lastRouteTo);
        }
        prefs.put("tryb", tryb);
        prefs.put("wiele", wiele);

        prefs.flush();
    }

    public static void resetPreferences() throws BackingStoreException {
	    String [] keys = prefs.keys();
	    System.out.println(keys);
        // Delete example
	    prefs.remove("darkTheme");
	    prefs.remove("remember");
	    prefs.remove("tryb");
	    prefs.remove("wiele");

    }

    public static void loadPreferences() {
        // Define a node in which the preferences can be stored
        prefs = Preferences.userRoot().node("storage");

        //get the values - if not found second is default
        darkTheme = prefs.getBoolean("darkTheme", false);
        remember = prefs.getBoolean("remember", false);
        if(remember){
            lastRouteFrom = prefs.get("lastRouteFrom", "aa ");
            lastRouteTo = prefs.get("lastRouteTo", "bb ");
        }
        tryb = prefs.get("tryb", "szybki");
        wiele = prefs.get("wiele", "wiele");
    }
}