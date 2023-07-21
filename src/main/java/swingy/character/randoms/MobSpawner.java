package swingy.character.randoms;

public class MobSpawner {
		public static Mob create(String className, String name) throws WrongMobException {
		//Mob newClass;
		//if (className.toLowerCase() == "paldin"){
		//	newClass = new ClassPaladin(name);
		//	return (newClass);
		//}
		//if (className.toLowerCase() == "mage"){
		//	newClass = new ClassMage(name);
		//	return (newClass);
		//}
		//if (className.toLowerCase() == "archer"){
		//	newClass = new ClassArcher(name);
		//	return (newClass);
		//}
		//else
			throw new WrongMobException( "Error : \"" +  className + "\" is not a correct or available class.");
	}
}
