package hangman;

import java.sql.*;

public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/hangman";
    private static final String USER = "root";
    private static final String PASSWORD = "1qazxsw2";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void setUserInfo(String name, String userName, String password) throws SQLException {
        String query = "INSERT INTO userinfo VALUES ('" + name + "', '" + userName + "', '" + password + "');";
        
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
