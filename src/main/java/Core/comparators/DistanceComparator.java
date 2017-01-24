package Core.comparators;

import DAO.Connection.Connection;
import DAO.Connection.ConnectionPart;

import java.util.Comparator;


public class DistanceComparator implements Comparator<Connection>{

    @Override
    public int compare(Connection o1, Connection o2) {
        Integer o1StopCount=0;
        Integer o2StopCount=0;
        for(ConnectionPart connectionPart : o1.getConnectionParts()){
            o1StopCount += connectionPart.getStopsList().size();
        }
        for(ConnectionPart connectionPart : o2.getConnectionParts()){
            o2StopCount += connectionPart.getStopsList().size();
        }
        return o1StopCount.compareTo(o2StopCount);
    }
}
