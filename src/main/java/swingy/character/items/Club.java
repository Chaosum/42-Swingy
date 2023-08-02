package swingy.character.items;

import javax.persistence.Entity;

@Entity
public class Club extends Weapon{
	
	public Club() {
		this.type = "Weapon";
		this.name = "Club";
		this.rank = "basic";
		this.levelRequired = 1;
		this.speedValue = 1;
		this.attackModifier = 20;
	}

	public Club(String rarity) {
		super();
		this.type = "Weapon";
		this.name = "Club";
		this.rank = rarity;
		this.levelRequired = 1;
		this.speedValue = 1;
		this.armorModifier = 0;
		this.hpModifier = 0;
		if (rarity.contains("rare")) {
			this.attackModifier = 30;
		}
		if (rarity.contains("epic")) {
			this.attackModifier = 40;
		}
		else {
			this.attackModifier = 20;
		}
	}
}
