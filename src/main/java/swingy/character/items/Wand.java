package swingy.character.items;

import java.util.Random;

import javax.persistence.Entity;

import swingy.character.hero.Hero;

@Entity
public class Wand extends Weapon {
	public Wand() {
		super();
		this.type = "Weapon";
		this.name = "Wand";
		this.rank = "Basic";
		this.levelRequired = 1;
		this.speedValue = 2;
		this.attackModifier = 20;
		this.specialEffects.add("Passiv");
	}

	public Wand(String rarity) {
		super();
		this.type = "Weapon";
		this.name = "Wand";
		this.rank = rarity;
		this.levelRequired = 1;
		this.speedValue = 2;
		if (rarity.contains("rare")) {
			this.attackModifier = 25;
		}
		if (rarity.contains("epic")) {
			this.attackModifier = 30;
		}
		else {
			this.attackModifier = 35;
		}
		this.specialEffects.add("Passiv");
	}

	@Override
	public void special(Hero hero){
		Random rand = new Random();
		if (rand.nextInt(10) < 1) {
				hero.chargeUp();
		}
	}
}
