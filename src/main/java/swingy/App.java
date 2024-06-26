package swingy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import swingy.view.GUI.MainFrame;

/**
 * superuser psql swingy et swingyroot
 *
 */
public class App {
	public static Connection connectToDatabase() throws SQLException{
		String url = "jdbc:postgresql://localhost:5432/";
		String username = "swingy";
		String password = "swingyroot";
		
		return (DriverManager.getConnection(url, username, password));
	}

	public static Statement databaseConnector(Connection connection) throws SQLException{
		return (connection.createStatement());
	}
	private static void createDatabase() throws SQLException {
		String databaseName = "swingy";

		Statement statement = databaseConnector(connectToDatabase());
		ResultSet resultSet = statement.executeQuery("SELECT 1 FROM pg_database WHERE datname = '" + databaseName + "'");
		if (resultSet.next()) {
			System.out.println("La base de données '" + databaseName + "' existe déjà.");
			resultSet = statement.executeQuery("SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'Hero')");
			if (resultSet.next()) {
				boolean tableExists = resultSet.getBoolean(1);
				if (tableExists) {
					System.out.println("La table 'Hero' existe déjà.");
				} else {
					statement.executeUpdate("CREATE TABLE Hero (name VARCHAR(20) PRIMARY KEY, title VARCHAR(50))");
					System.out.println("La table 'Hero' a été créée avec succès.");
				}
			}
		}
		else {
			statement.executeUpdate("CREATE DATABASE " + databaseName);
			System.out.println("La base de données '" + databaseName + "' a été créée avec succès.");
			statement.executeUpdate("CREATE TABLE Hero (name VARCHAR(20) PRIMARY KEY, title VARCHAR(50))");
		}
	}
	public static void main(String[] args) {
		System.out.println(args[0] );
		if (args[0] == null && (args[0] != "console" || args[0] != "GUI")) {
			System.out.println("Wrong args : Expect console | GUI");
			return ;
		}
		try {
			createDatabase();
		} catch (SQLException e) {
			System.out.println("Erreur lors de la création de la base de données : " + e.getMessage());
		}
		try {
			boolean console = args[0] == "console";
			MainFrame window = new MainFrame(console);
			window.setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			MainFrame.entityManager.close();
			MainFrame.entityManagerFactory.close();
		}
	}
}

