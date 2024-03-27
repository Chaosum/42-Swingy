package swingy.model.items;

import javax.persistence.Entity;

@Entity
public class DestroyerOfWorld extends Weapon{
	public DestroyerOfWorld() {
		super();
		this.type = "Weapon";
		this.name = "DestroyerOfWorld";
		this.rank = "Basic";
		this.levelRequired = 1;
		this.speedValue = 1;
		this.attackModifier = 20;
		this.specialEffects.add("Perforant");
	}

	public DestroyerOfWorld(String rarity) {
		super();
		this.type = "Weapon";
		this.name = "DestroyerOfWorld";
		this.rank = rarity;
		this.levelRequired = 1;
		this.speedValue = 1;
		if (rarity.contains("rare")) {
			this.attackModifier = 30;
		}
		if (rarity.contains("epic")) {
			this.attackModifier = 40;
		}
		else {
			this.attackModifier = 20;
		}
		this.specialEffects.add("Perforant");
	}
}
