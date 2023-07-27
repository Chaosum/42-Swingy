package swingy.character.hero;

import javax.persistence.Entity;

import swingy.character.items.SwordAndShield;

@Entity
public class ClassPaladin extends Hero {
	public ClassPaladin() {
		super();
	}
	public ClassPaladin(String name) {
		super(name);
		this.typeName = "Paladin";
		this.specialAttack = "quick heal";
		this.specialType = "activ";
		this.currentCharge = 0;
		this.specialChargeCounter = 6;
		this.weapon = new SwordAndShield();
	}
	@Override
	public int special() {
		if (this.currentCharge < this.specialChargeCounter) {
			// not enouth charges
			return (0);
		}
		this.currentCharge = 0;
		this.hp = hp + (maxHp / 2);
		if (this.hp > this.maxHp)
		this.hp = this.maxHp;
		return (0);
	}
}
