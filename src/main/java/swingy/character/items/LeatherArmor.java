package swingy.character.items;

public class LeatherArmor extends Armor{

	public LeatherArmor() {
		this.name = "LeatherArmor";
		this.rank = "basic";
		this.type = "LeatherArmor";
		this.levelRequired = 1;
		this.attackModifier = 0;
		this.hpModifier = 0;
		this.armorModifier = 5;
	}

	public LeatherArmor(String rarity) {
		this.name = "LeatherArmor";
		this.rank = "basic";
		this.type = "LeatherArmor";
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
