package app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Connection connection = null;
        String url = "jdbc:mysql://localhost:3307/db";
        String username = "user";
        String password = "password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            DatabaseManipulation dbManipulation = new DatabaseManipulation(connection);
            System.out.println("Connected to database");
            System.out.println("What would you like to do?");
            System.out.println("1. Create table");
            System.out.println("2. Get object by ID");
            System.out.println("3. Add to the table new row");
            System.out.println("4: Get all employee");
            int choice = scanner.nextInt();
            switch (choice){
                case 1: dbManipulation.createTable();
                break;
                case 2:
                    System.out.println("Enter ID:");
                    int id = scanner.nextInt();
                    Object objectFromDB = dbManipulation.getObjectById(id);
                    System.out.println(objectFromDB.toString());
                    break;
                case 3:
                    dbManipulation.addRowFromUserInput();
                case 4:
                    Object listOfEmployee = dbManipulation.getAllObjectsInTable();
                    System.out.println(listOfEmployee.toString());
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Disconnected from the database");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}