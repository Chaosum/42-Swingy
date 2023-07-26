package swingy.character.items;

import java.util.Random;

import javax.persistence.Entity;

import swingy.character.hero.Hero;
import swingy.character.hero.SpecialIsReadyException;

@Entity
public class Wand extends Weapon {
	public Wand() {
		this.type = "wand";
		this.name = "wand";
		this.rank = "Basic";
		this.levelRequired = 1;
		this.speedValue = 2;
		this.attackModifier = 3;
		this.specialEffects.add("Passiv");
	}
	@Override
	public void special(Hero hero){
		Random rand = new Random();
		if (rand.nextInt(10) < 1) {
			//display succes message
			try {
				hero.chargeUp();
			} catch (SpecialIsReadyException e) {
				// gerer special up
			}
		}
	}
}
