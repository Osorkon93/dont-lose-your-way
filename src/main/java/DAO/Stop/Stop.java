package DAO.Stop;


import java.util.List;

public class Stop implements Comparable{

    private Integer id;
    private String name;
    private List<String> linesNames;
    private String street;

    public Stop(Integer id, String name, List<String> linesNames, String street){
        this.id=id;
        this.name=name;
        this.linesNames = linesNames;
        this.street=street;
    }

    public void addLine(String lineName){
        if(!linesNames.contains(lineName))
            linesNames.add(lineName);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLinesNames() {
        return linesNames;
    }

    public void setLinesNames(List<String> linesNames) {
        this.linesNames = linesNames;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) return -1;
        Stop stop = (Stop) o;
        return id.compareTo(stop.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stop stop = (Stop) o;

        if (id != null ? !id.equals(stop.id) : stop.id != null) return false;
        if (name != null ? !name.equals(stop.name) : stop.name != null) return false;
        if (linesNames != null ? !linesNames.equals(stop.linesNames) : stop.linesNames != null) return false;
        return street != null ? street.equals(stop.street) : stop.street == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (linesNames != null ? linesNames.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        return result;
    }
}
