package app;
	
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
//import com.lynden.gmapsfx.javascript.object.MapType;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import java.util.prefs.Preferences;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		new Storage();//Singleton
		Storage.loadPreferences();
		
		VBox root = (VBox)FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource(Storage.theme).toExternalForm());
//		scene.getStylesheets().add(Storage.theme);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}