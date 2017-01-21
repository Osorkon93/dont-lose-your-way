package DAO.Stop;

import java.util.List;

/**
 * Created by Mateusz on 27.12.2016.
 */
public interface StopDao {
    void initialize();
    List<Stop> getAllStops();
    Stop getStop(int id);
    Stop getStop(String name);
    void updateStop(int id, String name, List<String> lines, String street);
    void updateStop(int id, Stop stop);
}
