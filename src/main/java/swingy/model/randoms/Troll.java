package swingy.model.randoms;

import swingy.model.items.Club;

public class Troll extends Mob{

	public Troll(int mapLevel, int advancement) {
		super("Troll");
		this.typeName = "miniboss";

		phrases.add("!!!!!");
		phrases.add("...");
		this.level = mapLevel + ((5 * advancement) / 100);
		this.weapon = new Club();
		this.experienceDroped = level * 700;
		this.hp =  200 + (30 * level);
		this.maxHp = this.hp;
		this.attackValue = 10 + (10 * level);
		this.armorValue = 5 + (2 * level);
		this.critChance = 10;
		this.critModifier = 2;
		this.lootTable.put(30, new Club());
		this.lootTable.put(15, new Club("rare"));
		this.lootTable.put(5, new Club("epic"));
		this.lootTable.put(1, new Club("legendary"));
	}
}
