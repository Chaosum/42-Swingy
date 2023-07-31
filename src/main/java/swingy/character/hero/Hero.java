package swingy.character.hero;

import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import swingy.character.Characters;
import swingy.character.items.Armor;
import swingy.character.items.Helmet;
import swingy.character.items.Weapon;
import swingy.character.randoms.Mob;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Hero extends Characters {
	protected int		experience;
	protected int		nextLevelXp;
	protected String	title;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "weapon_id")
	protected Weapon	weapon;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "armor_id")
	protected Armor		armor;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "helmet_id")
	protected Helmet	helmet;
	protected int		hpBonus;
	protected String	specialAttack;
	protected int		currentCharge;
	protected int		specialChargeCounter;
	protected String	specialType;
	protected int		fleeChances;
	protected String	specialDescription;
	
	public Hero () {
		super();
		title = "";
		this.fleeChances = 0;
		this.experience = 0;
		setNextLevelXp();
		this.weapon = new Weapon();
		this.helmet = new Helmet();
		this.armor = new Armor();
		this.specialDescription = "";
	}

	protected Hero(String name) {
		super(name);
		this.weapon = new Weapon();
		this.helmet = new Helmet();
		this.armor = new Armor();
		title = "";
		this.fleeChances = 50;
		this.experience = 0;
		this.specialDescription = "";
		setNextLevelXp();
	}

	public void gainExperience(int value) {
		setExperience(this.experience += value);
		while (this.experience >= this.nextLevelXp) {
			levelUp();
		}
	}

	//indiquer au joueur qu'il a ganger un level et faire les modifs en fonction (hp etc)
	public void levelUp() {
		this.experience = this.experience - this.nextLevelXp;
		this.level++;
		this.hp += 5;
		this.level += 1;
		this.attackValue += 1;
		setNextLevelXp();
		//display level up
	}

	public void chargeUp() {
		this.setCurrentCharge(this.currentCharge + 1);
	}
	public int special() {
		return (0);
	}
	public int special(Mob mob) {
		return (1);
	}

	protected int checkBlockPower() {
		int blockPower = 0;

		if (this.weapon.getSpecialEffects().contains("Block")) {
			blockPower++;
		}
		if (this.armor.getSpecialEffects().contains("Block")) {
			blockPower++;
		}
		if (this.helmet.getSpecialEffects().contains("Block")) {
			blockPower++;
		}
		return (blockPower);
	}
	protected int checkResistances(int damages, Characters from) {
		int damagesModifier = 0;
		if (this.getWeaknesses().contains(from.getTypeName())) {
			damagesModifier = damagesModifier + (damages / 3);
		}
		if (this.armor.getSpecialEffects().contains(from.getTypeName() + "Resistance")) {
			damagesModifier = damagesModifier - (damages / 3); 
		}
		if (this.helmet.getSpecialEffects().contains(from.getTypeName() + "Resistance")) {
			damagesModifier = damagesModifier - (damages / 3); 
		}
		if (this.weapon.getSpecialEffects().contains(from.getTypeName() + "Resistance")) {
			damagesModifier = damagesModifier - (damages / 3); 
		}
		return (damages + damagesModifier);
	}
	@Override
	public void takeDamages(int damages, Weapon weapon, Characters from) throws DeathException {
		int blockPower = checkBlockPower();
		if ( blockPower > 0) {
			Random rand = new Random();
			if (rand.nextInt(10) < blockPower){
				//damages are parried
				return ;
			}
		}
		damages = checkResistances(damages, from);
		if (!weapon.getSpecialEffects().contains("Perforant")) {
			damages -= this.armorValue;
		}
		if (damages <= 0) {
			//no dmg deal
			return ;
		}
		this.hp = this.hp - damages;
		if (this.hp <= 0) {
			throw new DeathException(this.name + this.title + "died from" + from.getName() + "\'s " + weapon.getName());
		}
	}

	public void dealDamages(Mob to, boolean special) throws VictoryException {
		int damages = this.attackValue + this.weapon.getAttackModifier();
		Random rand = new Random(); // definit si crit
		if (rand.nextInt(100) < this.critChance) {
			damages = damages * this.critModifier;
		}
		if (this.specialType.contains("activ") && special == true) {
			damages += this.special();
		}
		if (this.weapon.getSpecialEffects().contains("passiv")) {
			this.weapon.special(this);
		}
		int damagesModifier = 0;

		if (this.weapon.getSpecialEffects().contains(to.getTypeName() + "Strength")) {
			damagesModifier += damages / 3;
		}
		if (this.helmet.getSpecialEffects().contains(to.getTypeName() + "Strength")) {
			damagesModifier += damages / 3;
		}
		if (this.armor.getSpecialEffects().contains(to.getTypeName() + "Strength")) {
			damagesModifier += damages / 3;
		}
		damages += damagesModifier;
		try {
			to.takeDamages(damages, this.weapon, this);
		} catch (DeathException e) {
			throw new VictoryException(e.getMessage());
		}
	}

	public boolean isSpecialUp() {
		if (this.currentCharge < this.specialChargeCounter) {
			return (false);
		}
		return (true);
	}

	//Getters et setters
	public void setNextLevelXp() {
		this.nextLevelXp = ((this.level * 1000) + ((level - 1) * (level - 1)) * 450) - this.experience;
	}
}