package swingy.character.randoms;

import java.util.ArrayList;
import java.util.List;

import swingy.character.items.DestroyerOfWold;
import swingy.character.items.Items;

public class WorldBoss extends Mob{
	protected String category;
	protected List<Items> lootTable;
	protected String	specialEffect;

	public WorldBoss(String name) {
		super(name);
		this.hostilityLevel = 10;
		this.hp = 1000;
		this.level = 29; //eloignement de la case par rapport au centre + 10
		this.armorValue = 10;
		this.attackValue = 20;
		this.category = "Boss";
		this.experienceDroped = 3000; //a modif en fonction du level de la MAP
		this.lootTable = new ArrayList<Items>();
		this.lootTable.add(new DestroyerOfWold());
		this.weapon = new DestroyerOfWold();
	}
}
