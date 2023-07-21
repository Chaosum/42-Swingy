package swingy.character.hero;

import swingy.character.Character;
import swingy.character.items.Armor;
import swingy.character.items.Helmet;
import swingy.character.items.Weapon;

public class Hero extends Character {
	protected int		experience;
	protected int		nextLevelXp;
	protected String	className;
	protected String	title;

	protected Weapon	weapon;
	protected Armor		armor;
	protected Helmet	helmet;
	protected String	specialAttack;
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

	//Getters et setters
	public String getClassName() {
		return (this.className);
	}
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
}