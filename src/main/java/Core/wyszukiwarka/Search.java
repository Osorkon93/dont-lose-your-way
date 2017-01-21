package Core.wyszukiwarka;


import DAO.Connection.Connection;
import DAO.Connection.ConnectionPart;
import DAO.Line.Line;
import DAO.Line.LineDao;
import DAO.Stop.Stop;
import DAO.Stop.StopDao;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Search {

    private LineDao lineDao;
    private StopDao stopDao;

    public Search(LineDao lineDao, StopDao stopDao){
        this.lineDao = lineDao;
        this.stopDao = stopDao;
    }

    public Connection searchConnection(String startingPoint, String endingPoint, Integer maxLineChanges){
        return searchConnection(stopDao.getStop(startingPoint), stopDao.getStop(endingPoint), maxLineChanges);
    }

    public Connection searchConnection(Stop startingPoint, Stop endingPoint, Integer maxLineChanges){
        Connection result = null;
        if(maxLineChanges >= 0){
            ConnectionPart output = checkDirectConnection(startingPoint, endingPoint);
            if(output != null) {
                result = new Connection(0, "We found connection without any line changes.");
                return result;
            }
        }

        if(maxLineChanges >= 1){
            Map<Stop, List<Line>> reachedStops = getAllStopsWeCanReach(startingPoint); //TODO: parallel ?
            if(reachedStops!=null)
                for(Map.Entry<Stop, List<Line>> a : reachedStops.entrySet()){
                    ConnectionPart secondPart = checkDirectConnection(a.getKey(), endingPoint);
                if(secondPart!=null){
                    result = new Connection(1, "We have found connection with one line changing.");
                    result.addConnectionPart(checkDirectConnection(startingPoint, a.getKey())); //we can reach it, so it wont produce nullptr
                    result.addConnectionPart(secondPart);
                    return result;
                }
            }
        }



        if(maxLineChanges > 2){
            //TODO
        }
        System.out.println("We couldn't find connection :( ");
        return new Connection(maxLineChanges, "Sorry, we couldn't find connection");
    }


    private ConnectionPart checkDirectConnection(Stop startingPoint, Stop endingPoint){
        for(String lineName : startingPoint.getLinesNames()){
            //System.out.println("line name: " + lineDao.getLine(lineName).get(0).getName());
            for(Line line : lineDao.getLine(lineName)){
                int starting = line.getStopOrderNo(startingPoint.getName());
                int stopping = line.getStopOrderNo(endingPoint.getName());
                if(starting<stopping && starting!=(-1) && stopping != (-1)){
                    System.out.println("ZNALEZIONO POŁĄCZENIE: " + lineName + ", w strone: " + line.lastStop.getName());
                    return (new ConnectionPart(line, line.getStops().subList(starting, stopping+1)));
                }
            }
        }
        return null;
    }

    private Map<Stop, List<Line>> getAllStopsWeCanReach(Stop startingStop){
        Map<Stop, List<Line>> output = new TreeMap<>();
        for(String lineName : startingStop.getLinesNames()){
            List<Line> tempLine = lineDao.getLine(lineName);
            if(tempLine.size()>0)
                for(Stop tempStop : tempLine.get(0).getStops()){
                    output.put(tempStop, tempLine);
                }
        }
        return output;
    }

}
