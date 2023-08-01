package swingy.character.items;

import javax.persistence.Entity;

@Entity
public class DestroyerOfWold extends Weapon{
	public DestroyerOfWold() {
		this.type = "sword";
		this.name = "DestroyerOfWold";
		this.rank = "Basic";
		this.levelRequired = 1;
		this.speedValue = 1;
		this.attackModifier = 20;
		this.specialEffects.add("Perforant");
	}
}
