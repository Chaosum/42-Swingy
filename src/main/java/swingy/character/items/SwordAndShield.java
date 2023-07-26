package swingy.character.items;


import javax.persistence.Entity;

@Entity
public class SwordAndShield extends Weapon {
	public SwordAndShield() {
		this.type = "sword";
		this.name = "sword and shield";
		this.rank = "Basic";
		this.levelRequired = 1;
		this.speedValue = 3;
		this.attackModifier = 5;
		this.armorModifier = 10;
		this.specialEffects.add("Block");
	}
}
