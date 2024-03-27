package swingy.model.items;


import javax.persistence.Entity;

@Entity
public class SwordAndShield extends Weapon {
	public SwordAndShield() {
		super();
		this.type = "Weapon";
		this.name = "SwordAndShield";
		this.rank = "Basic";
		this.levelRequired = 1;
		this.speedValue = 3;
		this.attackModifier = 10;
		this.armorModifier = 15;
		this.specialEffects.add("Block");
	}

	public SwordAndShield(String rarity) {
		super();
		this.type = "Weapon";
		this.name = "SwordAndShield";
		this.rank = rarity;
		this.levelRequired = 1;
		this.speedValue = 3;
		if (rarity.contains("rare")) {
			this.attackModifier = 20;
		}
		if (rarity.contains("epic")) {
			this.attackModifier = 25;
		}
		else {
			this.attackModifier = 15;
		}
		this.specialEffects.add("Block");
	}
}
