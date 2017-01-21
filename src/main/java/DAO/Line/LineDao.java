package DAO.Line;

import java.util.List;

public interface LineDao {
    void initialize();
    List<Line> getAllLines();
    List<Line> getLine(String name);
}
