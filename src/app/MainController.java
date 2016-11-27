package app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController implements Initializable{
	
	
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
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println("controller loaded");
	}





}
