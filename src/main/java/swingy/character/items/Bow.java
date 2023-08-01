package swingy.character.items;

import javax.persistence.Entity;

@Entity
public class Bow extends Weapon{
	public Bow() {
		this.type = "bow";
		this.name = "bow";
		this.rank = "Basic";
		this.levelRequired = 1;
		this.speedValue = 1;
		this.attackModifier = 30;
		this.specialEffects.add("Perforant");
	}
}
