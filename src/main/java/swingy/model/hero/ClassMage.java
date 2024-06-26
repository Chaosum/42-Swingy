package swingy.model.hero;

import java.util.Random;

import javax.persistence.Entity;

import swingy.model.items.Wand;

@Entity
public class ClassMage extends Hero{
	public ClassMage() {
		super();
	}
	public ClassMage(String name) {
		super(name);
		this.maxHp = 300;
		this.hp = 300 + this.hpBonus;
		this.attackValue = 15;
		this.typeName = "Mage";
		this.specialAttack = "fireball";
		this.specialType = "activ";
		this.currentCharge = 3;
		this.specialChargeCounter = 3;
		this.weapon = new Wand();
	}
	@Override
	public int special() {
		int specialDmg = attackValue * 8;
		if (this.currentCharge < this.specialChargeCounter) {
			// not enouth charges
			return (0);
		}
		this.currentCharge = 0;
		Random rand = new Random();
		if (rand.nextInt(100) < this.critChance) {
			this.currentCharge = this.specialChargeCounter - 1;
		}
		return (specialDmg);
	}
}
