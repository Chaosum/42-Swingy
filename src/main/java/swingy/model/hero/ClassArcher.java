package swingy.model.hero;

import javax.persistence.Entity;

import swingy.model.items.Bow;
import swingy.model.randoms.Mob;

@Entity
public class ClassArcher extends Hero {

	public ClassArcher() {
		super();
	}

	public ClassArcher(String name) {
		super(name);
		this.maxHp = 250;
		this.hp = 250 + hpBonus;
		this.typeName = "Archer";
		this.specialAttack = "tracker";
		this.specialType = "passiv";
		this.weapon = new Bow();
		this.attackValue = 10;
		this.fleeChances = 75;
		this.critChance = 50;
		this.critModifier = 4;
		this.specialDescription = "Can see next case";
	}

	@Override
	public int special() {
		//reveal next case in map
		return (0);
	}
	@Override
	public int special(Mob mob) {
		return (0);
	}
}
