package swingy.character.hero;

public class HeroCreator {

	public static Hero create(String className, String name) throws WrongClassException {
		Hero newClass;
		if (className.toLowerCase() == "paldin"){
			newClass = new ClassPaladin(name);
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
}
