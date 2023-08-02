package swingy.character.hero;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import swingy.graphicInterface.MainFrame;

public class HeroCreator {

	public static Hero create(String className, String name) throws WrongClassException {
		Hero newClass;
		if (className.toLowerCase() == "paladin"){
			newClass = new ClassPaladin(name);
			System.out.println("Test creation classe2" + className);
			return (newClass);
		}
		if (className.toLowerCase() == "mage"){
			newClass = new ClassMage(name);
			return (newClass);
		}
		if (className.toLowerCase() == "archer"){
			newClass = new ClassArcher(name);
			return (newClass);
		}
		else
			throw new WrongClassException( "Error : \"" +  className + "\" is not a correct or available class.");
	}

	public static void saveHeroToDatabase(Hero newHero, boolean upDate) throws EntityExistsException {
		EntityManager entityManager = MainFrame.entityManager;
		try {
			entityManager.getTransaction().begin();
			if (upDate == false) {
				entityManager.persist(newHero); // Cette ligne enregistre le nouvel héro dans la base de données
			}
			else {
				entityManager.merge(newHero);
			}
			entityManager.getTransaction().commit();
		} catch (EntityExistsException e) {
			throw e;
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
}
