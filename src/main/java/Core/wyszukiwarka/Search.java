package Core.wyszukiwarka;


import DAO.Connection.Connection;
import DAO.Connection.ConnectionPart;
import DAO.Line.Line;
import DAO.Line.LineDao;
import DAO.Stop.Stop;
import DAO.Stop.StopDao;

import java.util.ArrayList;
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

    public List<Connection> searchConnection(String startingPoint, String endingPoint, Integer maxLineChanges, Integer numberOfConnectionsToSearch){
        return searchConnection(stopDao.getStop(startingPoint), stopDao.getStop(endingPoint), maxLineChanges, numberOfConnectionsToSearch);
    }

    public List<Connection> searchConnection(Stop startingPoint, Stop endingPoint, Integer maxLineChanges, Integer numberOfConnectionsToSearch){
        List<Connection> result = new ArrayList<Connection>();
        int emergencyCounter = 0;
        while(result.size()<=numberOfConnectionsToSearch && ((++emergencyCounter)<100) ) {
            if (maxLineChanges >= 0) {
                ConnectionPart output = checkDirectConnection(startingPoint, endingPoint, result);
                if (output != null) {
                    Connection temp = new Connection(0, "We found connection without any line changes.");
                    temp.addConnectionPart(output);
                    result.add(temp);
                }
            }

            if (maxLineChanges >= 1) {
                Map<Stop, List<Line>> reachedStops = getAllStopsWeCanReach(startingPoint); //TODO: parallel ?
                if (reachedStops != null)
                    for (Map.Entry<Stop, List<Line>> a : reachedStops.entrySet()) {
                        List<Connection> connectionsWeDoNotWant = new ArrayList<>();

                        if(result.size()>0){
                            for(Connection connection : result){ //jesli juz przechodzilismy przez ten przystanek to go nie chcemy
                                if(connection.getConnectionParts().size() == 2){
                                    ConnectionPart temp = connection.getConnectionParts().get(1);
                                    Connection temp2 = new Connection(maxLineChanges, "temp");
                                    temp2.addConnectionPart(temp);
                                    connectionsWeDoNotWant.add(temp2);
                                }
                            }
                        }


                        ConnectionPart secondPart = checkDirectConnection(a.getKey(), endingPoint, connectionsWeDoNotWant);
                        if (secondPart != null) {
                            Connection tempResult = new Connection(1, "We have found connection with one line changing.");
                            tempResult.addConnectionPart(checkDirectConnection(startingPoint, a.getKey())); //we can reach it, so it wont produce nullptr
                            tempResult.addConnectionPart(secondPart);
                            result.add(tempResult);
                            break;
                        }
                    }
            }

            if (maxLineChanges > 2) {
                //TODO
            }
        }
        return result;
    }


    private ConnectionPart checkDirectConnection(Stop startingPoint, Stop endingPoint, List<Connection>... foundConnection){
        for(String lineName : startingPoint.getLinesNames()){
            //System.out.println("line name: " + lineDao.getLine(lineName).get(0).getName());
            for(Line line : lineDao.getLine(lineName)){
                int starting = line.getStopOrderNo(startingPoint.getName());
                int stopping = line.getStopOrderNo(endingPoint.getName());
                if(starting<stopping && starting!=(-1) && stopping != (-1)){
                boolean flag = true;

                    for(List<Connection> temp : foundConnection)    //sprawdzamy czy juz wczesniej nie znalezlismy tego polaczenia
                        for(Connection existingConnection : temp){
                            if(existingConnection.getConnectionParts().size()==1){ //czyli to jest bezposrednie polaczenie, to chcemy sprawdzic
                                //System.out.println(existingConnection.getConnectionParts().get(0).getLine().getName() + " == " + lineName);
                                if(existingConnection.getConnectionParts().get(0).getLine().getName().toString().equals(lineName)){
                                    flag = false;
                                    break; //jesli to jest ta sama linia to olewamy.
                                }
                            }
                        }

                    if(flag) {
                        System.out.println("ZNALEZIONO POŁĄCZENIE: " + lineName + ", w strone: " + line.lastStop.getName());
                        return (new ConnectionPart(line, line.getStops().subList(starting, stopping + 1)));
                    }
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
