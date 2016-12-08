package controllers;

import Core.wyszukiwarka.Wyszukiwarka;
import Core.Storage;

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

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;


public class MainController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML Button go;
    @FXML TextField fromField, toField;


    @FXML
    public void pressedGo(ActionEvent e) {

        String from = fromField.getText();
        String to = toField.getText();
//        if ((from != null && !from.isEmpty()) && (to != null && !to.isEmpty())) {
//            System.out.println(from + " | " + to);
//        }

        //TODO: parse input

        // domyślnie na teraz, ale użytkownik może zmienić
        String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        Wyszukiwarka.main(new String[]{from, to, timeStamp, "15"});
        //TODO: zmienić czas z HH:mm na yyyyMMdd_HH:mm
        //jakby ktoś chciał jutro lub za tydzień
    }

    @FXML
    public void quit(ActionEvent e) throws BackingStoreException{
        Storage.setPreferences();//może być nie potrzebne (?)
        Platform.exit();
    }

    @FXML
    public void updateDatabase(ActionEvent e){
        //?
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
