package swingy.character.items;

import javax.persistence.Entity;

import swingy.character.hero.Hero;

@Entity
public class Bow extends Weapon{
	public Bow() {
		this.type = "bow";
		this.name = "bow";
		this.rank = "Basic";
		this.levelRequired = 1;
		this.speedValue = 1;
		this.attackModifier = 15;
		this.specialEffects.add("Perforant");
	}
	public void special() {}
	public void special(Hero hero) {}
}
