package swingy.character.hero;

import swingy.character.items.Weapon;

public class ClassPaladin extends Hero {
	public ClassPaladin(String name) {
		super(name);
		this.className = "Paladin";
		this.strengths.add("undead");
		this.specialAttack = "quick heal";
		this.specialChargeCounter = 6;
		this.weapon = new Weapon("sword");
	}

	public void special() {
		this.hp = hp + (maxHp / 2);
		if (this.hp > this.maxHp)
			this.hp = this.maxHp;
	}
}
