package swingy.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import swingy.App;
import swingy.model.hero.Hero;

public class mainMenuController {
	public static EntityManagerFactory entityManagerFactory;
	public static EntityManager entityManager;
	ArrayList<Hero> heroList = new ArrayList<>();
	Hero currentHero = null;

	private void playerSelection(boolean console) {
		try {
			Statement statement = App.databaseConnector(App.connectToDatabase());
			ResultSet resultSet = statement.executeQuery("SELECT name FROM Hero");
			while (resultSet.next()) {
				String heroName = resultSet.getString("name");
				Hero hero = entityManager.find(Hero.class, heroName);
				heroList.add(hero);
			}
		} catch (SQLException e) {
			System.out.println("SQL error exception : " + e.getMessage());
		}
		if (console) {
			// heroSelectionConsoleView
		}
		else {
			// heroSelectionGUIView
		}
	}

	public mainMenuController(boolean console) {
		entityManagerFactory = Persistence.createEntityManagerFactory("swingypersistance");
		entityManager = entityManagerFactory.createEntityManager();
		playerSelection(console);
	}
}
