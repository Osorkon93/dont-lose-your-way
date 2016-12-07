package mainClass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Core.Storage;
import services.MpkConnector;

import java.io.IOException;

//import com.lynden.gmapsfx.javascript.object.MapType;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        new Storage();//Singleton
        Storage.loadPreferences();
        VBox root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Main.fxml"));
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getClassLoader().getResource(Storage.theme).toExternalForm());
//		scene.getStylesheets().add(Core.Storage.theme);
        primaryStage.setScene(scene);
        primaryStage.show();

        MpkConnector mpk = new MpkConnector();
//        mpk.downloadDatabase(mpk.getDatabaseUrl(), System.getProperty("user.home") + "\\app\\database.sqlite");
    }

    public static void main(String[] args) {
        launch(args);
    }

}