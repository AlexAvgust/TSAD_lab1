package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatabaseManipulation implements DatabaseOperations {

    private final Connection connection;

    public DatabaseManipulation(Connection connection){
        this.connection = connection;
    }
    public void addRowFromUserInput() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter name:");
        String name = scanner.next();

        System.out.println("Enter position:");
        String position = scanner.next();

        System.out.println("Enter salary:");
        double salary = scanner.nextDouble();

        addRow(name, position, salary);
    }
    @Override
    public void addRow(String value1, String value2, Double value3) throws SQLException {
        String sql = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, value1);
            preparedStatement.setString(2, value2);
            preparedStatement.setDouble(3, value3);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Row added successfully.");
            } else {
                System.out.println("Failed to add row.");
            }
        }catch (SQLException e) {
          System.out.println(e.getErrorCode());
        }
    }

    public void createTable (){
        try {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS employees ( " +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "position TEXT, " +
                    "salary DECIMAL(10, 2))";

            statement.executeUpdate(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());

        }
    }

    public Employee getObjectById(int id){
          Employee employee = null;
          String sql = "SELECT * FROM employees WHERE id = ?";
          try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
              preparedStatement.setInt(1,id);
              ResultSet resultSet = preparedStatement.executeQuery();

              if(resultSet.next()){
                  int employeeId = resultSet.getInt("id");
                  String name = resultSet.getString("name");
                  String position = resultSet.getString("position");
                  Double salary = resultSet.getDouble("salary");

                  employee = new Employee(id,name,position,salary);
              }
          }catch (SQLException e) {
              System.out.println(e.getErrorCode());
          }
          return employee;
    }

    public List<Employee> getAllObjectsInTable(){
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                Double salary = resultSet.getDouble("salary");

                Employee employee = new Employee(id, name, position, salary);
                employees.add(employee);
            }
        }catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }

        return employees;
    }
}
