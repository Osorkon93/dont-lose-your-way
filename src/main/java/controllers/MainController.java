package controllers;

//import Core.wyszukiwarka.Wyszukiwarka;

import Core.Storage;
import Core.wyszukiwarka.Search;
import DAO.Connection.Connection;
import DAO.Line.LineDao;
import DAO.Line.LineDaoImpl;
import DAO.Stop.StopDao;
import DAO.Stop.StopDaoImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.MpkConnector;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;


public class MainController implements Initializable{

    private MpkConnector mpk;
    private StopDao stopDao;
    private LineDao lineDao;

    @FXML Button go;
    @FXML TextField fromField, toField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateDatabase(new ActionEvent());
    }

    @FXML
    public void pressedGo(ActionEvent e) {

        if(stopDao.getStop(fromField.getText()) == null || stopDao.getStop(toField.getText()) == null) {
            System.out.println("We Cant find such stops");
            //todo jakis label
            return;
        }
        List<Connection> outputList = (new Search(lineDao, stopDao)).searchConnection(fromField.getText(), toField.getText(), 2, 3);
        for(Connection output : outputList) {
            if(output == null){
                System.out.println("Brak polaczenia :(");
            }else
            if (output.getNumberOfLineChanges() == 0) {
                System.out.println(output.getDescription() + " From: " + output.getConnectionParts().get(0).getStopsList().get(0).getName() +
                " to " + output.getConnectionParts().get(0).getStopsList().get(output.getConnectionParts().get(0).getStopsList().size()-1).getName() +
                ". Line: " + output.getConnectionParts().get(0).getLine().getName());
            }else
            if (output.getNumberOfLineChanges() == 1) {
                System.out.println(output.getDescription() + " From: " + output.getConnectionParts().get(0).getStopsList().get(0).getName() +
                        ", by: " + output.getConnectionParts().get(1).getStopsList().get(0).getName() +
                        ", to " + output.getConnectionParts().get(1).getStopsList().get(output.getConnectionParts().get(1).getStopsList().size() - 1).getName() +
                        ". Lines: " + output.getConnectionParts().get(0).getLine().getName() + "-" + output.getConnectionParts().get(0).getLine().getLastStop().getName() +
                        " -- " + output.getConnectionParts().get(1).getLine().getName() + "-" + output.getConnectionParts().get(1).getLine().getLastStop().getName()
                );
            }
        }

    }

    @FXML
    public void quit(ActionEvent e) throws BackingStoreException{
        Storage.setPreferences();//może być nie potrzebne (?)
        Platform.exit();
    }

    @FXML
    public void updateDatabase(ActionEvent e){
        mpk = new MpkConnector();
        try{
            mpk.downloadDatabase(mpk.getDatabaseUrl(), System.getProperty("user.home")+System.getProperty("file.separator", "\\")+".appDontLose"+System.getProperty("file.separator", "\\")+"database.sqlite");
        }catch (IOException ex){
            ex.printStackTrace();
        }
        System.out.println("DB ADRESS: " + System.getProperty("user.home")+System.getProperty("file.separator", "\\")+".appDontLose"+System.getProperty("file.separator", "\\")+"database.sqlite");
        stopDao = new StopDaoImpl(System.getProperty("user.home")+System.getProperty("file.separator", "\\")+".appDontLose"+System.getProperty("file.separator", "\\")+"database.sqlite");
        lineDao = new LineDaoImpl(
                System.getProperty("user.home")+System.getProperty("file.separator", "\\")+".appDontLose"+System.getProperty("file.separator", "\\")+"database.sqlite",
                stopDao
        );
        lineDao.initialize();
    }

    @FXML
    public void switchTheme(ActionEvent e) throws BackingStoreException{
        Storage.darkTheme = !Storage.darkTheme;
        Storage.setPreferences();
        System.out.println(Storage.darkTheme);//for debug
    }

    @FXML
    public void remember(ActionEvent e) throws BackingStoreException{
        Storage.remember = !Storage.remember;
        Storage.setPreferences();
    }

    @FXML
    public void showDocumentation(ActionEvent e){
        displayNewWindow("/view/Documentation.fxml", "Documentation");
    }

    @FXML
    public void about(ActionEvent e){
        displayNewWindow("/view/About.fxml", "About");
    }

    private void displayNewWindow (String pathToFXML, String title) {//for Documentation and About Window
        // czy powinno być w kontrolerze?
        try {
//            root = FXMLLoader.load(getClass().getResource("/view/Documentation.fxml"));
            //nowy sposób:
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(pathToFXML));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);

            Scene scene = new Scene(root);
            String currentTheme = Storage.darkTheme ? "/view/dark.css" : "/view/light.css";
            scene.getStylesheets().add(getClass().getResource(currentTheme).toExternalForm());

            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
