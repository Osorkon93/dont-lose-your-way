package Core;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Storage{
    public static Preferences prefs;
    public static String theme = "view/application.css";
    public static boolean remember = false;
    public static String lastRouteFrom = "a";
    public static String lastRouteTo = "b";
    public static String porzadek = "szybki";
    public static String tryb = "w";

    public static void setPreferences() throws BackingStoreException {
        // Define a node in which the preferences can be stored
        prefs = Preferences.userRoot().node("storage");

        //set the values
        prefs.put("theme", theme);
        prefs.putBoolean("remember", remember);
        if(remember){
            prefs.put("lastRouteFrom", lastRouteFrom);
            prefs.put("lastRouteTo", lastRouteTo);
        }
        prefs.put("porzadek", porzadek);
        prefs.put("tryb", tryb);

        prefs.flush();
    }

    public static void resetPreferences() throws BackingStoreException {
	    String [] keys = prefs.keys();
	    System.out.println(keys);
        // Delete example
	    prefs.remove("theme");
	    prefs.remove("remember");
	    prefs.remove("porzadek");
	    prefs.remove("tryb");

    }

    public static void loadPreferences() {
        // Define a node in which the preferences can be stored
        prefs = Preferences.userRoot().node("storage");

        //get the values - if not found second is default
        theme = prefs.get("theme", "view/application.css");
        remember = prefs.getBoolean("remember", false);
        if(remember){
            lastRouteFrom = prefs.get("lastRouteFrom", "aa ");
            lastRouteTo = prefs.get("lastRouteTo", "bb ");
        }
        porzadek = prefs.get("porzadek", "szybki");
        tryb = prefs.get("tryb", "w");
    }
}