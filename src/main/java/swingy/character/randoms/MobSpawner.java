package swingy.character.randoms;

import java.util.Random;

public class MobSpawner {

	public static Mob create(String className, int heroLevel) {
		Mob newClass = new Mob("default");
		if (className.toLowerCase().contains("worldboss")){
			newClass = new WorldBoss("Bolas");
		}
		return (newClass);
	}

	public static Mob createRandom(char mob, int advancement, int heroLevel) {
		Mob newClass;
		Random rand = new Random();
		int randMob = rand.nextInt(100);
		if (randMob >= 70){
			newClass = new Vampire(heroLevel, advancement);
		}
		else if (randMob >= 50) {
			newClass = new Zombie(heroLevel, advancement);
		}
		else if (randMob >= 30) {
			newClass = new Beast(heroLevel, advancement);
		}
		else if (randMob >= 10) {
			newClass = new Orc(heroLevel, advancement);
		}
		else {
			newClass = new Troll(heroLevel, advancement);
		}
		return (newClass);
	}
}

