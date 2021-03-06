package controllers;

//import Core.wyszukiwarka.Wyszukiwarka;

import Core.Storage;
import Core.wyszukiwarka.Search;
import DAO.Connection.Connection;
import DAO.Line.LineDao;
import DAO.Line.LineDaoImpl;
import DAO.Stop.Stop;
import DAO.Stop.StopDao;
import DAO.Stop.StopDaoImpl;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.MpkConnector;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;


public class MainController implements Initializable{

    private MpkConnector mpk;
    private StopDao stopDao;
    private LineDao lineDao;
    private List<Connection> outputList;
    private List<Marker> markers = new ArrayList<>();

    @FXML Button go;
    @FXML TextField fromField, toField;
    @FXML TextArea stopOutput1;
    @FXML TextArea stopOutput2;
    @FXML TextArea stopOutput3;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            updateDatabase(new ActionEvent());
        } catch ( Exception e){
            e.printStackTrace();
        }
        stopOutput1.setWrapText(true);
        stopOutput2.setWrapText(true);
        stopOutput3.setWrapText(true);
    }

    @FXML
    public void pressedGo(ActionEvent e) {

        stopOutput1.setText("");
        stopOutput2.setText("");
        stopOutput3.setText("");

        if(stopDao.getStop(fromField.getText()) == null || stopDao.getStop(toField.getText()) == null) {
            System.out.println("We Cant find such stops");
            //todo jakis label
            return;
        }
        outputList = (new Search(lineDao, stopDao)).searchConnection(fromField.getText(), toField.getText(), 2, 3);

        List<String> outputStrings = new ArrayList<>();
        for(Connection output : outputList) {
            if(output == null){
                System.out.println("Brak polaczenia :(");
            }else
            if (output.getNumberOfLineChanges() == 0) {
                String string = (output.getDescription() + "\n\nFrom: " + output.getConnectionParts().get(0).getStopsList().get(0).getName() +
                "\nTo: " + output.getConnectionParts().get(0).getStopsList().get(output.getConnectionParts().get(0).getStopsList().size()-1).getName() +
                ".\nLine: " + output.getConnectionParts().get(0).getLine().getName() + "-" + output.getConnectionParts().get(0).getLine().getLastStop().getName());
                System.out.println(string);
                outputStrings.add(string);
//                try {
//                    mpk.getStopDeparturesTimes(output.getConnectionParts().get(0).getStopsList().get(0), output.getConnectionParts().get(0).getLine());
//                } catch (Exception e1){
//                    e1.printStackTrace();
//                }
            }else
            if (output.getNumberOfLineChanges() == 1) {
                String string = (output.getDescription() + "\n\nFrom: " + output.getConnectionParts().get(0).getStopsList().get(0).getName() +
                        "\nBy: " + output.getConnectionParts().get(1).getStopsList().get(0).getName() +
                        "\nTo " + output.getConnectionParts().get(1).getStopsList().get(output.getConnectionParts().get(1).getStopsList().size() - 1).getName() +
                        ".\nLines: \n" + output.getConnectionParts().get(0).getLine().getName() + "-" + output.getConnectionParts().get(0).getLine().getLastStop().getName() +
                        "\n" + output.getConnectionParts().get(1).getLine().getName() + "-" + output.getConnectionParts().get(1).getLine().getLastStop().getName()
                );
                System.out.println(string);
                outputStrings.add(string);
            }
        }


        if(outputStrings.size()==0) stopOutput1.setText("We couldn't find any connection.");
        if(outputStrings.size()>0) stopOutput1.setText(outputStrings.get(0));
        if(outputStrings.size()>1) stopOutput2.setText(outputStrings.get(1));
        if(outputStrings.size()>2) stopOutput3.setText(outputStrings.get(2));

    }


    public void markOnMap(MouseEvent e){
        try {
            TitledPane pane = (TitledPane) e.getSource();

            Integer i=-1;
            if(pane.getId().equals("result1")) i=0;
            if(pane.getId().equals("result2")) i=1;
            if(pane.getId().equals("result3")) i=2;
            if(i == -1 || outputList==null || outputList.size()==0) return;

            GoogleMap googleMap = GmapsfxController.getMap();
            for(Marker marker : markers){
                System.out.println("Removing marker");
                googleMap.removeMarker(marker);
            }

            markers = new ArrayList<>();
            if(outputList.get(i).getConnectionParts().size() >= 1){
                Stop stop = outputList.get(i).getConnectionParts().get(0).getStopsList().get(0);
                List<Double> temp = mpk.getStopLatLng(stop);
                LatLong latLong = new LatLong(temp.get(1), temp.get(0));
                Marker marker = new Marker(new MarkerOptions().position(latLong));
                googleMap.addMarker(marker);
                markers.add(marker);

                stop = outputList.get(i).getConnectionParts().get(0).getStopsList().get(
                        outputList.get(i).getConnectionParts().get(0).getStopsList().size()-1
                );
                temp = mpk.getStopLatLng(stop);
                latLong = new LatLong(temp.get(1), temp.get(0));
                marker = new Marker(new MarkerOptions().position(latLong));
                googleMap.addMarker(marker);
                markers.add(marker);
            }
            if(outputList.get(i).getConnectionParts().size() == 2){
                Stop stop = outputList.get(i).getConnectionParts().get(1).getStopsList().get(
                        outputList.get(i).getConnectionParts().get(1).getStopsList().size()-1
                );
                List<Double> temp = mpk.getStopLatLng(stop);
                LatLong latLong = new LatLong(temp.get(1), temp.get(0));
                Marker marker = new Marker(new MarkerOptions().position(latLong));
                googleMap.addMarker(marker);
                markers.add(marker);
            }
            googleMap.setZoom(googleMap.getZoom()-1);
            googleMap.setZoom(googleMap.getZoom()+1);
        } catch (Exception e1){
            e1.printStackTrace();
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

    public void reportBug(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Bug report dialog");
        alert.setHeaderText("Please describe bug below and click ok");

        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 0);

        alert.getDialogPane().setContent(expContent);
        alert.showAndWait();
    }
}
