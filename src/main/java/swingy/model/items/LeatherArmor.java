package swingy.model.items;

import javax.persistence.Entity;

@Entity
public class LeatherArmor extends Armor{

	public LeatherArmor() {
		super();
		this.name = "LeatherArmor";
		this.rank = "basic";
		this.type = "Armor";
		this.levelRequired = 1;
		this.attackModifier = 0;
		this.hpModifier = 0;
		this.armorModifier = 5;
	}

	public LeatherArmor(String rarity) {
		super();
		this.name = "LeatherArmor";
		this.rank = "basic";
		this.type = "Armor";
		this.levelRequired = 1;
		this.attackModifier = 0;
		this.hpModifier = 0;
		if (rarity.contains("rare")) {
			this.armorModifier = 10;
		}
		else if (rarity.contains("epic")) {
			this.armorModifier = 15;
		}
		else {
			this.armorModifier = 5;
		}
	}
	
}
