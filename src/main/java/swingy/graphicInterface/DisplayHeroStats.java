package swingy.graphicInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;

import swingy.App;

public class DisplayHeroStats extends JPanel{
	public DisplayHeroStats() {
		try {
			Statement statement = App.modifyDatabase(App.connectToDatabase());
			ResultSet resultSet = statement.executeQuery("SELECT * FROM heros");
			resultSet.next();
			//voir avec ORM
		} catch (SQLException e) {
			System.out.println("createHero exception : " + e.getMessage());
		}
	}
}
	