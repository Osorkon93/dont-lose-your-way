package mainClass;

import Core.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

//import com.lynden.gmapsfx.javascript.object.MapType;


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

//        MpkConnector mpk = new MpkConnector();
//        try{
//            mpk.downloadDatabase(mpk.getDatabaseUrl(), System.getProperty("user.home")+System.getProperty("file.separator", "\\")+".appDontLose"+System.getProperty("file.separator", "\\")+"database.sqlite");
//        }catch (IOException ex){
//            ex.printStackTrace();
//        }
//        System.out.println("DB ADRESS: " + System.getProperty("user.home")+System.getProperty("file.separator", "\\")+".appDontLose"+System.getProperty("file.separator", "\\")+"database.sqlite");
//        StopDao stopDao = new StopDaoImpl(System.getProperty("user.home")+System.getProperty("file.separator", "\\")+".appDontLose"+System.getProperty("file.separator", "\\")+"database.sqlite");
//        LineDao lineDao = new LineDaoImpl(
//                System.getProperty("user.home")+System.getProperty("file.separator", "\\")+".appDontLose"+System.getProperty("file.separator", "\\")+"database.sqlite",
//                stopDao
//        );
//        lineDao.initialize();
//
//        Connection output = (new Search(lineDao, stopDao)).searchConnection("Norymberska", "Solvay", 3);
//        System.out.println(output.getDescription() + " Z: " + output.getConnectionParts().get(0).getStopsList().get(0).getName() +
//                ", przez: " + output.getConnectionParts().get(1).getStopsList().get(0).getName() +
//                ", do " +  output.getConnectionParts().get(1).getStopsList().get( output.getConnectionParts().get(1).getStopsList().size()-1).getName() +
//                ". Liniami: " + output.getConnectionParts().get(0).getLine().getName() + "-" + output.getConnectionParts().get(0).getLine().getLastStop().getName() +
//                " -- " + output.getConnectionParts().get(1).getLine().getName() + "-" + output.getConnectionParts().get(1).getLine().getLastStop().getName()
//        );
    }

    public static void main(String[] args) {
        launch(args);
    }

}