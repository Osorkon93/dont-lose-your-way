package controllers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GmapsfxController implements Initializable, MapComponentInitializedListener {

    @FXML
    private GoogleMapView mapView;

    @FXML
    private TextField addressTextField;

    private GoogleMap map;
    private StringProperty address = new SimpleStringProperty();
    //private GeocodingService geocodingService = new GeocodingService();
    private static GmapsfxController instance;

    public static GmapsfxController getInstance() {
        return instance;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        instance = this;
        mapView.addMapInializedListener(this);
        //address.bind(addressTextField.textProperty());
    }


    @Override
    public void mapInitialized() {
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(50.06100, 19.9350))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(false)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(12);

        map = mapView.createMap(mapOptions);
    }


//    public void setStopOnMap(String Name){
//        geocodingService.geocode(Name, new GeocodingServiceCallback() {
//            @Override
//            public void geocodedResultsReceived(GeocodingResult[] geocodingResults, GeocoderStatus geocoderStatus) {
//                for(GeocodingResult geocodingResult : geocodingResults){
//                    System.out.println(geocodingResult.toString());
//                }
//            }
//        });
//    }
}