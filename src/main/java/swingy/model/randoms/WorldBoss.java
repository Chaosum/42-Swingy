package swingy.model.randoms;

import swingy.model.items.DestroyerOfWorld;

public class WorldBoss extends Mob{
	protected String category;

	public WorldBoss(int mapLevel, int advancement) {
		super("WorldBoss");
		this.level = mapLevel + 5;
		this.hp = 300 + (50 * level);
		this.maxHp = this.hp;
		this.armorValue = 10 + (5 * mapLevel);
		this.attackValue = (5 * level);
		this.category = "Boss";
		this.experienceDroped = 3000 * level;
		this.lootTable.put(15, new DestroyerOfWorld());
		this.weapon = new DestroyerOfWorld();
	}
}
