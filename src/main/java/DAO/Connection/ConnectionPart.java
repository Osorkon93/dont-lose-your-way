package DAO.Connection;


import DAO.Line.Line;
import DAO.Stop.Stop;

import java.util.List;

public class ConnectionPart {

    private List<Stop> stopsList;
    private Line line;

    public ConnectionPart(Line line, List<Stop> stopsList){
        this.line = line;
        this.stopsList = stopsList;
    }

    public List<Stop> getStopsList() {
        return stopsList;
    }

    public void setStopsList(List<Stop> stopsList) {
        this.stopsList = stopsList;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

}
