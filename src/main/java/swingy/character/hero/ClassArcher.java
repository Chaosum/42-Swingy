package swingy.character.hero;

import javax.persistence.Entity;
import javax.persistence.Table;

import swingy.character.items.SwordAndShield;
import swingy.character.randoms.Mob;

@Entity
@Table(name = "heros")
public class ClassArcher extends Hero {
	public ClassArcher(String name) {
		super(name);
		this.typeName = "Archer";
		this.specialAttack = "tracker";
		this.specialType = "passiv";
		this.weapon = new SwordAndShield();
		this.fleeChances = 75;
		this.critChance = 50;
		this.critModifier = 4;
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
