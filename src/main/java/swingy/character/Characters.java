package swingy.character;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;
import swingy.character.hero.DeathException;
import swingy.character.items.Weapon;

@Entity
@Table(name = "Hero")
@Data
public abstract class Characters {
	@Id
	protected String name;
	protected int	hp;
	protected int	maxHp;
	protected int	level;
	protected int attackValue;
	protected int armorValue;
	protected String typeName;
	@ElementCollection
	@CollectionTable(name = "character_weaknesses", joinColumns = @JoinColumn(name = "character_name"))
	@Column(name = "weakness")
	protected List<String> weaknesses;
	protected int	critChance;
	protected int	critModifier;


	public abstract void	takeDamages(int damages, Weapon weapon, Characters from) throws DeathException;
	
	protected Characters() {
		weaknesses = new ArrayList<String>();
		this.name = "";
		this.hp = 100;
		this.maxHp = 100;
		this.level = 1;
		this.attackValue = 0;
		this.armorValue = 0;
		this.critChance = 10;
		this.critModifier = 2;
	}
	protected Characters(String name) {
		weaknesses = new ArrayList<String>();
		this.name = name;
		this.hp = 100;
		this.maxHp = 100;
		this.level = 1;
		this.attackValue = 0;
		this.armorValue = 0;
		this.critChance = 10;
		this.critModifier = 2;
	}
}
