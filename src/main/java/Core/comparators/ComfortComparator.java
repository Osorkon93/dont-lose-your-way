package Core.comparators;

import DAO.Connection.Connection;
import DAO.Connection.ConnectionPart;

import java.util.Comparator;


public class ComfortComparator implements Comparator<Connection>{

    @Override
    public int compare(Connection o1, Connection o2) {
        Integer o1Comfort=0;
        Integer o2Comfort=0;
        for(ConnectionPart connectionPart : o1.getConnectionParts()){
            o1Comfort += connectionPart.getLine().getComfortLevel();
        }
        for(ConnectionPart connectionPart : o2.getConnectionParts()){
            o2Comfort += connectionPart.getLine().getComfortLevel();
        }
        return o1Comfort.compareTo(o2Comfort);
    }
}
