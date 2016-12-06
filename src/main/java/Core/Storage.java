package Core;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Storage{
    public static Preferences prefs;
    public static String theme = "view/application.css";
    public static boolean remember = false;
    public static String lastRouteFrom = "a";
    public static String lastRouteTo = "b";


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
        //set the values
//	    prefs.put("theme", "application.css");
//	    prefs.putBoolean("remember", false);
//	    prefs.put("lastRouteFrom", "a");
//	    prefs.put("lastRouteTo", "b");

//	    String [] keys = prefs.keys();
//	    System.out.println(keys);
        prefs.flush();
        // Delete example
//	    prefs.remove("theme");


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

//	    System.out.println(theme);
//	    System.out.println(remember);
//	    System.out.println(lastRouteTo);
//	    System.out.println(lastRouteFrom);

    }
}