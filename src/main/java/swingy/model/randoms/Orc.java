package swingy.model.randoms;

import swingy.model.items.Axe;
import swingy.model.items.BronzeHelmet;

public class Orc extends Mob {
	
	public Orc(int mapLevel, int advancement) {
		super("Orc");
		this.typeName = "humanoid";
		this.weaknesses.add("mage");

		phrases.add("Lok'tar Ogar !");
		phrases.add("arg...");
		this.level = mapLevel + ((3 * advancement) / 100);
		this.weapon = new Axe();
		this.experienceDroped = level * 350;
		this.hp =  100 + (15 * level);
		this.maxHp = this.hp;
		this.attackValue = 10 + (5 * level);
		this.armorValue = 8 + (2 * level);
		this.critChance = 10;
		this.critModifier = 2;
		this.lootTable.put(40, new Axe());
		this.lootTable.put(35, new Axe("rare"));
		this.lootTable.put(30, new BronzeHelmet());
		this.lootTable.put(15, new BronzeHelmet("rare"));
		this.lootTable.put(5, new BronzeHelmet("epic"));

	}
}
