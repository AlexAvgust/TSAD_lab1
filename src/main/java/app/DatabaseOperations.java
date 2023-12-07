package app;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseOperations {
    void createTable() throws SQLException;

    void addRow(String value1, String value2, Double value3) throws SQLException;

    Employee getObjectById(int id) throws SQLException;


}
