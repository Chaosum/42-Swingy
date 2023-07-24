package swingy.character.items;

import java.util.Random;

import swingy.character.hero.Hero;
import swingy.character.hero.SpecialIsReadyException;

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

	public void special() {}
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
