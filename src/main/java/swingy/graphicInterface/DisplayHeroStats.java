package swingy.graphicInterface;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JList;
import javax.swing.JPanel;

import swingy.character.hero.Hero;

public class DisplayHeroStats extends JPanel{
	public DisplayHeroStats(JList<String> heroList) {
		String selectedName = heroList.getSelectedValue();
		String heroName = selectedName.substring(0, selectedName.indexOf(' '));
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("swingypersistance");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Hero hero = entityManager.createQuery("SELECT h FROM Heros h WHERE h.name = :heroName", Hero.class).setParameter("heroName", heroName).getSingleResult();
		entityManager.close();
		entityManagerFactory.close();
		//recup les infos de hero et les utiliser pour l'interface
		hero.getLevel();
	}
}
	