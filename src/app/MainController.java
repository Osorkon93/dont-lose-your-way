package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController implements Initializable{
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println("controller loaded");
	}
	
	@FXML Button go;
	@FXML TextField fromField, toField;
	
	
	@FXML
	public void pressedGo(ActionEvent e){
		System.out.println("pressedGo");
		
		String from = fromField.getText();
		String to = toField.getText();
		if ((from != null && !from.isEmpty()) && (to != null && !to.isEmpty())) {
			System.out.println(from +" | "+ to);
		}
		
		//TODO: parse input
		

		
	}
	
	@FXML
	public void quit(ActionEvent e){
		
		
	}
	
	@FXML
	public void updateDatabase(ActionEvent e){

		
	}
	
	@FXML
	public void switchTheme(ActionEvent e){

		
	}
	
	@FXML
	public void remember(ActionEvent e){

		
	}
	
	@FXML
	public void showDocumentation(ActionEvent e){
		Parent root;
		try{
			root = FXMLLoader.load(getClass().getResource("Documentation.fxml"));
			Stage stage = new Stage();
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Documentation");
			stage.setScene(new Scene(root));
			stage.show();
		}catch(IOException ex) {
            ex.printStackTrace();
		}
		
	}
	
	@FXML
	public void about(ActionEvent e){
		Parent root;
		try{
			root = FXMLLoader.load(getClass().getResource("About.fxml"));
			Stage stage = new Stage();
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("About");
			stage.setScene(new Scene(root));
			stage.show();
		}catch(IOException ex) {
            ex.printStackTrace();
		}
	}


}
