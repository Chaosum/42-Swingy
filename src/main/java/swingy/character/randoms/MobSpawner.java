package swingy.character.randoms;

import java.util.Random;

public class MobSpawner {

	public static Mob create(char mob, int advancement, int heroLevel) {
		Mob newClass = new Mob("default");
		if (mob == 'B'){
			newClass = new WorldBoss(heroLevel, advancement);
		}
		return (newClass);
	}

	public static Mob createRandom(char mob, int advancement, int heroLevel) {
		Mob newClass;
		Random rand = new Random();
		int randMob = rand.nextInt(100);
		if (randMob >= 70 || mob == 'V'){
			newClass = new Vampire(heroLevel, advancement);
			newClass.setName("V");
		}
		else if (randMob >= 50 || mob == 'Z') {
			newClass = new Zombie(heroLevel, advancement);
			newClass.setName("Z");
		}
		else if (randMob >= 30 || mob == 'A') {
			newClass = new Beast(heroLevel, advancement);
			newClass.setName("A");

		}
		else if (randMob >= 10 || mob == 'O') {
			newClass = new Orc(heroLevel, advancement);
			newClass.setName("O");

		}
		else {
			newClass = new Troll(heroLevel, advancement);
			newClass.setName("T");
		}
		return (newClass);
	}
}

