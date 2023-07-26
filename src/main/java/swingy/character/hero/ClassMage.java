package swingy.character.hero;

import java.util.Random;

import javax.persistence.Entity;

import swingy.character.items.Wand;

@Entity
public class ClassMage extends Hero{
	public ClassMage() {
		super();
	}
	public ClassMage(String name) {
		super(name);
		this.typeName = "Mage";
		this.specialAttack = "fireball";
		this.specialType = "attack";
		this.currentCharge = 0;
		this.specialChargeCounter = 3;
		this.weapon = new Wand();
	}
	@Override
	public int special() {
		if (this.currentCharge < this.specialChargeCounter) {
			// not enouth charges
			return (0);
		}
		this.currentCharge = 0;
		Random rand = new Random();
		if (rand.nextInt(10) < this.critChance) {
			this.currentCharge = 3;
		}
		return (this.attackValue * 2);
	}
}
