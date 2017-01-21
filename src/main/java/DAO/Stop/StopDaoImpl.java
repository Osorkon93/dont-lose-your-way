package DAO.Stop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StopDaoImpl implements StopDao{

    private List<Stop> allStops = null;
    private String dbAbsolutePath = "";

    public StopDaoImpl(String dbAbsolutePath){
        this.dbAbsolutePath = dbAbsolutePath;
    }

    @Override
    public void initialize(){
        allStops = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbAbsolutePath);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT * FROM STOPS"
            );
            while (resultSet.next()){
                //System.out.println("STOP NAME:" + resultSet.getString("NAME"));
                allStops.add(new Stop(
                        resultSet.getInt("id"),
                        resultSet.getString("NAME"),
                        new ArrayList<>(),
                        ""
                ));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Stop> getAllStops() {
        if(allStops == null) {
            initialize();
        }
        return allStops;
    }

    @Override
    public Stop getStop(int id) {
        if(allStops==null) initialize();
        for(Stop stop : allStops){
            if(stop.getId()==id) return stop;
        }
        return null;
    }

    @Override
    public Stop getStop(String name) {
        if(allStops==null) initialize();
        for(Stop stop : allStops){
            if(stop.getName().equals(name)) return stop;
        }
        return null;
    }

    @Override
    public void updateStop(int id, String name, List<String> lines, String street){
        allStops.set(
                allStops.indexOf(getStop(id)),
                new Stop(id, name, lines, street)
        );
    }

    @Override
    public void updateStop(int id, Stop stop){
        allStops.set(
                allStops.indexOf(getStop(id)),
                stop
        );
    }
}
