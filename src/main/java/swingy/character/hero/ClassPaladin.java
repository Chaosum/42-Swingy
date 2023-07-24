package swingy.character.hero;

import swingy.character.items.SwordAndShield;

public class ClassPaladin extends Hero {
	public ClassPaladin(String name) {
		super(name);
		this.typeName = "Paladin";
		this.specialAttack = "quick heal";
		this.currentCharge = 0;
		this.specialChargeCounter = 6;
		this.weapon = new SwordAndShield();
	}

	public void special() {
		if (this.currentCharge < 6) {
			// not enouth charges
			return;
		}
		this.currentCharge = 0;
		this.hp = hp + (maxHp / 2);
		if (this.hp > this.maxHp)
		this.hp = this.maxHp;
	}
}
