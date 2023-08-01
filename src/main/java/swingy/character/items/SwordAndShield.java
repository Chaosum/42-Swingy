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
		this.attackModifier = 10;
		this.armorModifier = 15;
		this.specialEffects.add("Block");
	}
}
