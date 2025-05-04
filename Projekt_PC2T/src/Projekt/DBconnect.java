package Projekt;

import java.sql.*;

public class DBconnect {

	private static final String URL = "jdbc:sqlite:students.db";
    private static Connection connection;

    public static Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL);
                System.out.println("Připojeno k databázi.");
            } catch (SQLException e) {
                System.out.println("Chyba při připojení: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Odpojeno od databáze.");
            } catch (SQLException e) {
                System.out.println("Chyba při odpojení: " + e.getMessage());
            }
        }
    }
}
