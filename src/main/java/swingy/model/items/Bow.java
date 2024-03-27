package swingy.model.items;

import javax.persistence.Entity;

@Entity
public class Bow extends Weapon{
	public Bow() {
		super();
		this.type = "Weapon";
		this.name = "bow";
		this.rank = "Basic";
		this.levelRequired = 1;
		this.speedValue = 1;
		this.attackModifier = 30;
		this.specialEffects.add("Perforant");
	}

	public Bow(String rarity) {
		super();
		this.type = "Weapon";
		this.name = "Bow";
		this.rank = rarity;
		this.levelRequired = 1;
		this.speedValue = 1;
		if (rarity.contains("rare")) {
			this.attackModifier = 40;
		}
		if (rarity.contains("epic")) {
			this.attackModifier = 50;
		}
		else {
			this.attackModifier = 30;
		}
		this.specialEffects.add("Perforant");
	}
}
