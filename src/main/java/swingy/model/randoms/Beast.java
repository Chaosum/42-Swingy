package swingy.model.randoms;

import swingy.model.items.LeatherArmor;
import swingy.model.items.Weapon;

public class Beast extends Mob {

	public Beast(int mapLevel, int advancement) {
		super("Beast");
		this.typeName = "animal";
		this.weaknesses.add("archer");

		phrases.add("Grrrrr! è.é");
		phrases.add("guiiiiiiiik ... x.x");
		this.level = mapLevel + ((3 * advancement) / 100);
		this.weapon = new Weapon();
		this.experienceDroped = level * 350;
		this.hp =  100 + (15 * level);
		this.maxHp = this.hp;
		this.attackValue = 20 + (5 * level);
		this.armorValue = 0 + (1 * level);
		this.critChance = 10;
		this.critModifier = 2;
		this.lootTable.put(30, new LeatherArmor());
		this.lootTable.put(15, new LeatherArmor("rare"));
		this.lootTable.put(5, new LeatherArmor("epic"));
	}
}
