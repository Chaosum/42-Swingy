package swingy.model.randoms;

import swingy.model.items.Weapon;

public class Zombie extends Mob {

	public Zombie(int mapLevel, int advancement) {
		super("Zombie");
		this.typeName = "undead";
		this.weaknesses.add("paladin");

		phrases.add("Braiiiins!");
		phrases.add("Braiiiins!!!");
		this.level = mapLevel + ((3 * advancement) / 100);
		this.weapon = new Weapon();
		this.experienceDroped = level * 350;
		this.hp =  100 + (15 * level);
		this.maxHp = this.hp;
		this.attackValue = 15 + (5 * level);
		this.armorValue = 10 + (2 * level);
		this.critChance = 10;
		this.critModifier = 2;
	}
}
