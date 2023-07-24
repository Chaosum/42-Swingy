package swingy.character.randoms;

public class MobSpawner {
		public static Mob create(String className, String name) throws WrongMobException {
		Mob newClass;
		if (className.toLowerCase() == "worldBoss"){
			newClass = new WorldBoss(name);
			return (newClass);
		}
		else
			throw new WrongMobException( "Error : \"" +  className + "\" is not a correct or available class.");
	}
}
