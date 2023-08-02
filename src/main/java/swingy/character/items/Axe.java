package swingy.character.items;

import javax.persistence.Entity;

@Entity
public class Axe extends Weapon{
	public Axe() {
		super();
		this.type = "Weapon";
		this.name = "Axe";
		this.rank = "basic";
		this.levelRequired = 1;
		this.speedValue = 1;
		this.attackModifier = 10;
	}

	public Axe(String rarity) {
		super();
		this.type = "Weapon";
		this.name = "Axe";
		this.rank = rarity;
		this.levelRequired = 1;
		this.speedValue = 1;
		if (rarity.contains("rare")) {
			this.attackModifier = 20;
		}
		if (rarity.contains("epic")) {
			this.attackModifier = 25;
		}
		else {
			this.attackModifier = 10;
		}
	}
}
