package DAO.Connection;


import java.util.ArrayList;
import java.util.List;

public class Connection {


    private int numberOfLineChanges;
    private String description;
    private List<ConnectionPart> connectionParts;

    public Connection(int numberOfLineChanges, String description) {
        this.numberOfLineChanges = numberOfLineChanges;
        this.description = description;
        connectionParts = new ArrayList<>();
    }

    public Connection(int numberOfLineChanges) {
        this.numberOfLineChanges = numberOfLineChanges;
        connectionParts = new ArrayList<>();
    }

    public List<ConnectionPart> getConnectionParts() {
        return connectionParts;
    }

    public void setConnectionParts(List<ConnectionPart> connectionParts) {
        this.connectionParts = connectionParts;
    }

    public void addConnectionPart(ConnectionPart connectionPart) {
        connectionParts.add(connectionPart);
    }

    public int getNumberOfLineChanges(){
        return numberOfLineChanges;
    }

    public void setNumberOfLineChanges(int numberOfLineChanges) {
        this.numberOfLineChanges = numberOfLineChanges;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}