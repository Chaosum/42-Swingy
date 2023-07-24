package swingy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * superuser psql swingy et swingyroot
 *
 */
public class App {
public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/";
        String username = "swingy";
        String password = "swingyroot";
        String databaseName = "my_new_database";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT 1 FROM pg_database WHERE datname = '" + databaseName + "'");
                if (resultSet.next()) {
                    System.out.println("La base de données '" + databaseName + "' existe déjà.");
                }
                else {
                    statement.executeUpdate("CREATE DATABASE " + databaseName);
                    System.out.println("La base de données '" + databaseName + "' a été créée avec succès.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la création de la base de données : " + e.getMessage());
        }
    }
}
