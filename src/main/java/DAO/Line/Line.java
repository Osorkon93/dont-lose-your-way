package DAO.Line;

import DAO.Stop.Stop;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Mateusz on 27.12.2016.
 */
public class Line {

    private Integer name;
    private List<Stop> stops;
    private Stop lastStop;
    private Integer comfortLevel=0;


    public Line(Integer name){
        this.name = name;
        this.stops = new ArrayList<>();
    }

    public Integer getComfortLevel(){
        return comfortLevel;
    }

    public void setComfortLevel(Integer comfortLevel){
        this.comfortLevel=comfortLevel;
    }

    public Stop getLastStop(){
        return lastStop;
    }

    public void setLastStop(Stop lastStop){
        this.lastStop = lastStop;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void addStop(Stop stop) {
        this.stops.add(stop);
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public boolean containsStop(String stopName){
        for(Stop stop : stops){
            if(stop.getName().equals(stopName))
                return true;
        }
        return false;
    }

    public int getStopOrderNo (String stopName) {
        for(int i=0; i<stops.size(); i++){
            if(stops.get(i).getName().equals(stopName))
                return i;
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return (name.equals(line.name) && stops.equals(line.stops) && lastStop.equals(line.lastStop));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, stops, lastStop);
    }
}
