package swingy.character.hero;

import javax.persistence.Entity;

import swingy.character.items.Bow;
import swingy.character.randoms.Mob;

@Entity
public class ClassArcher extends Hero {

	public ClassArcher() {
		super();
	}

	public ClassArcher(String name) {
		super(name);
		this.typeName = "Archer";
		this.specialAttack = "tracker";
		this.specialType = "passiv";
		this.weapon = new Bow();
		this.fleeChances = 75;
		this.critChance = 50;
		this.critModifier = 4;
		this.specialDescription = "Highter chance to run\nHighter crit chance\nHighter crit modifier\nCan see next case";
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
