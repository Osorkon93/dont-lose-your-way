package DAO.Line;

import DAO.Stop.Stop;
import DAO.Stop.StopDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineDaoImpl implements  LineDao{

    private List<Line> allLines = null;
    private String dbAbsolutePath = "";
    private StopDao stopDaoImpl;

    public LineDaoImpl (String dbAbsolutePath, StopDao stopDaoImpl){
        this.dbAbsolutePath = dbAbsolutePath;
        this.stopDaoImpl = stopDaoImpl;
    }

    @Override
    public void initialize() {
        stopDaoImpl.getAllStops();
        allLines = new ArrayList<>();
        Connection connection = null;
        ResultSet resultSetLines = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbAbsolutePath);
            statement = connection.createStatement();
            resultSetLines = statement.executeQuery(
                    "SELECT DISTINCT LineName FROM StopDepartures"
            );
            while (resultSetLines.next()){
                Statement statement1 = connection.createStatement();
                ResultSet resultSetLastStops = statement1.executeQuery(
                        "SELECT DISTINCT LastStopId, LastStopName FROM StopDepartures WHERE LineName=" + resultSetLines.getInt("LineName")
                );
                while (resultSetLastStops.next()) {
                    Line tempLine = new Line(resultSetLines.getInt("LineName"));
                    Statement statement2 = connection.createStatement();
                    ResultSet resultSetStops = statement2.executeQuery(
                            "SELECT DISTINCT VariantID, No, StopId, StopName, StreetName, PointID FROM StopDepartures WHERE LineName=" + tempLine.getName() +
                                    " and LastStopId =" + resultSetLastStops.getInt("LastStopId") + " ORDER BY No ASC"
                    );

                    while (resultSetStops.next()){
                        Stop tempStop = stopDaoImpl.getStop(resultSetStops.getInt("StopID"));
                        tempStop.addLine(tempLine.getName().toString());
                        stopDaoImpl.updateStop(tempStop.getId(), tempStop);
                        tempStop.setVariantID(resultSetStops.getInt("VariantID"));
                        tempStop.setPointID(resultSetStops.getInt("PointID"));
                        tempLine.addStop(tempStop);
                    }
                    tempLine.setLastStop(tempLine.getStops().get(tempLine.getStops().size()-1));
                    allLines.add(tempLine);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
                if (resultSetLines != null) resultSetLines.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //printAllLines();
    }

    @Override
    public List<Line> getAllLines() {
        if(allLines==null) initialize();
        return allLines;
    }

    @Override
    public List<Line> getLine(String name) {
        List<Line> temp = new ArrayList<>();
        for(Line line : allLines) {
            if (line.getName().toString().equals(name.trim())) {
                temp.add(line);
            }
        }
        return temp;
        //return allLines.stream().filter(line -> line.getName().equals(name)).collect(Collectors.toCollection(ArrayList::new)); //Zawsze powinny być 2 linie, np 18 jadąca na Czerwone Maki i z powrotem
    }

    public void printAllLines(){
        for(Line line : allLines){
            System.out.println(" ");
            System.out.print("Linia: " + line.getName() + ", przystanki: ");
            for(Stop stop : line.getStops()){
                System.out.print(" | " + stop.getName() + " | ");
            }
            System.out.println( " " );
        }
    }
}
