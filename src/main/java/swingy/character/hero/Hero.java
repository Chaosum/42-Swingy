package swingy.character.hero;

import java.util.Random;

import swingy.character.Characters;
import swingy.character.items.Armor;
import swingy.character.items.Helmet;
import swingy.character.items.Weapon;

public class Hero extends Characters {
	protected int		experience;
	protected int		nextLevelXp;
	protected String	title;
	protected Weapon	weapon;
	protected Armor		armor;
	protected Helmet	helmet;
	protected String	specialAttack;
	protected int		currentCharge;
	protected int		specialChargeCounter;
	
	protected Hero(String name) {
		super(name);
		this.experience = 0;
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

	public void chargeUp(){
		this.setCurrentCharge(this.currentCharge + 1);
		if (this.getCurrentCharge() == this.specialChargeCounter) {
			//activer l'option special
		}
	}

	private int checkBlockPower() {
		int blockPower = 0;

		if (this.getWeapon().getSpecialEffects().contains("Block")) {
			blockPower++;
		}
		if (this.getArmor().getSpecialEffects().contains("Block")) {
			blockPower++;
		}
		if (this.getHelmet().getSpecialEffects().contains("Block")) {
			blockPower++;
		}
		return (blockPower);
	}
	private int checkResistances(int damages, Weapon weapon, Characters from) {
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
	public void takeDamages(int damages, Weapon weapon, Characters from) throws DeathException {
		int blockPower = checkBlockPower();
		if ( blockPower > 0) {
			Random rand = new Random();
			if (rand.nextInt(10) < blockPower){
				//damages are parried
				return ;
			}
		}
		damages = checkResistances(damages, weapon, from);
		this.hp = this.hp - damages;
		if (this.hp <= 0) {
			throw new DeathException(this.name + this.title + "died from" + from.getName() + "\'s " + weapon.getName());
		}
	}

	//Getters et setters
	public String getTitle() {
		return (this.title);
	}
	protected void	setExperience(String newTitle) {
		this.title = newTitle;
	}
	public int getExperience() {
		return (this.experience);
	}
	protected void	setExperience(int value) {
		this.experience = value;
	}
	public int getNextLevelXp() {
		return (this.nextLevelXp);
	}
	public void setNextLevelXp() {
		this.nextLevelXp = ((this.level * 1000) + ((level - 1) * (level - 1)) * 450) - this.experience;
	}
	public Weapon getWeapon() {
		return (this.weapon);
	}
	public void setWeapon(Weapon newWeapon) {
		this.weapon = newWeapon;
	}
	public Armor getArmor() {
		return (this.armor);
	}
	public void setArmor(Armor newArmor) {
		this.armor = newArmor;
	}
	public Helmet getHelmet() {
		return (this.helmet);
	}
	public void setHelmet (Helmet newHelmet) {
		this.helmet = newHelmet;
	}
	public String getSpecialAttack() {
		return (this.specialAttack);
	}
	public int getSpecialChargeCounter() {
		return (this.specialChargeCounter);
	}
	public void setSpecialChargeCounter(int value) {
		this.specialChargeCounter = value;
	}
	public int getCurrentCharge() {
		return (this.currentCharge);
	}
	public void setCurrentCharge(int value) {
		this.currentCharge = value;
	}
}