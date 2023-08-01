package swingy.character.randoms;

import swingy.character.items.VampireTooths;

public class Vampire extends Mob {

	public Vampire(int mapLevel, int advancement) {
		super("Vampire");
		this.typeName = "nocturn";
		this.weaknesses.add("Light");

		phrases.add("I wonder the taste of your blood");
		phrases.add("I did not liked it.");
		this.level = mapLevel + ((3 * advancement) / 100);
		this.weapon = new VampireTooths();
		this.experienceDroped = level * 450;
		this.hp =  100 + (15 * level);
		this.maxHp = this.hp;
		this.attackValue = 15 + (5 * level);
		this.armorValue = 5 + (1 * level);
		this.critChance = 10;
		this.critModifier = 2;
		this.lootTable.put(15, new VampireTooths());
		this.lootTable.put(5, new VampireTooths("rare"));
		this.lootTable.put(1, new VampireTooths("epic"));

	}
	
}
