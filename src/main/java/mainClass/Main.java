package mainClass;

import Core.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        new Storage();//Singleton
        Storage.loadPreferences();
        VBox root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Main.fxml"));
        Scene scene = new Scene(root);
//      scene.getStylesheets().add(getClass().getClassLoader().getResource(Storage.theme).toExternalForm());
//		scene.getStylesheets().add(Core.Storage.theme);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}