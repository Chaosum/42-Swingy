package swingy.character;

import java.util.List;

import lombok.Data;
import swingy.character.hero.DeathException;
import swingy.character.items.Weapon;

@Data
public abstract class Characters {
	protected String name;
	protected int	hp;
	protected int	maxHp;
	protected int	level;
	protected int attackValue;
	protected int armorValue;
	protected String typeName;
	protected List<String> weaknesses;
	protected int	critChance;
	protected int	critModifier;


	public abstract void	takeDamages(int damages, Weapon weapon, Characters from) throws DeathException;
	
	protected Characters() {
		this.name = "NaN";
		this.hp = 100;
		this.maxHp = 100;
		this.level = 1;
		this.attackValue = 0;
		this.armorValue = 0;
		this.critChance = 10;
		this.critModifier = 2;
	}
	protected Characters(String name) {
		this.name = name;
		this.hp = 0;
		this.level = 0;
		this.attackValue = 0;
		this.armorValue = 0;
	}
}
