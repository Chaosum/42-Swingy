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
		this.maxHp = 350;
		this.hp = 350 + this.hpBonus;
		this.attackValue = 15;
		this.typeName = "Paladin";
		this.specialAttack = "quick heal";
		this.specialType = "activ";
		this.currentCharge = 0;
		this.specialChargeCounter = 6;
		this.attackValue = 15;
		this.weapon = new SwordAndShield();
	}
	@Override
	public int special() {
		if (this.currentCharge < this.specialChargeCounter) {
			// not enouth charges
			return (0);
		}
		this.currentCharge = 0;
		this.hp = hp + ((maxHp + hpBonus) / 2);
		if (this.hp > this.maxHp + this.hpBonus)
		this.hp = this.maxHp + this.hpBonus;
		return (0);
	}
}
