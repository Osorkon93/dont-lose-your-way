package controllers;


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
import Core.Storage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;

//import javafx.event.EventHandler;

public class MainController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//		System.out.println("controller loaded");
    }

    @FXML Button go;
    @FXML TextField fromField, toField;


    @FXML
    public void pressedGo(ActionEvent e){
//		System.out.println("pressedGo");

        String from = fromField.getText();
        String to = toField.getText();
        if ((from != null && !from.isEmpty()) && (to != null && !to.isEmpty())) {
            System.out.println(from +" | "+ to);
        }

        //TODO: parse input



    }

    @FXML
    public void quit(ActionEvent e) throws BackingStoreException{
        // if 'remember' parameter true save last search
        if(Storage.remember == true){
            //save
        }
        Storage.setPreferences();
        Platform.exit();
    }

    @FXML
    public void updateDatabase(ActionEvent e){


    }

    @FXML
    public void switchTheme(ActionEvent e) throws BackingStoreException{
        if(Storage.theme.equals("view/application.css")){
            Storage.theme = "dark.css";
        }
        else if(Storage.theme.equals("dark.css")){
            Storage.theme = "view/application.css";
        }
        Storage.setPreferences();
        System.out.println(Storage.theme);
    }

    @FXML
    public void remember(ActionEvent e) throws BackingStoreException{
        Storage.remember = Storage.remember ? false : true;
        Storage.setPreferences();
    }

    @FXML
    public void showDocumentation(ActionEvent e){
        Parent root;
        try{
            root = FXMLLoader.load(getClass().getResource("Documentation.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Documentation");

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource(Storage.theme).toExternalForm());

            stage.setScene(scene);
            stage.show();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void about(ActionEvent e){
        Parent root;
        try{
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/About.fxml"));
            Stage stage = new Stage();
            stage.setTitle("About");

            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource(Storage.theme).toExternalForm());

            stage.setScene(scene);
            stage.show();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }


}
