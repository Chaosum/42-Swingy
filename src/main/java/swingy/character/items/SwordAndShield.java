package swingy.character.items;

import swingy.character.hero.Hero;

public class SwordAndShield extends Weapon {
	public SwordAndShield() {
		this.type = "sword";
		this.name = "sword and shield";
		this.rank = "Basic";
		this.levelRequired = 1;
		this.speedValue = 3;
		this.attackModifier = 5;
		this.armorModifier = 5;
		this.specialEffects.add("Block");
	}
	public void special() {
	}
	public void special(Hero hero) {
	}
}
