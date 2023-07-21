package swingy.character;

import java.util.List;

public class Character {
	protected String name;
	protected int	hp;
	protected int	maxHp;
	protected int	level;
	protected int attackValue;
	protected int armorValue;
	protected List<String> weaknesses;
	protected List<String> strengths;

	protected Character() {
		this.name = "NaN";
		this.hp = 100;
		this.maxHp = 100;
		this.level = 1;
		this.attackValue = 0;
		this.armorValue = 0;
	}
	protected Character(String name) {
		this.name = name;
		this.hp = 0;
		this.level = 0;
		this.attackValue = 0;
		this.armorValue = 0;
	}
	//Getters et setters
	public String	getName() {
		return (this.name);
	}
	public	void	setName(String newName) {
		this.name = newName;
	}
	public int	getHp() {
		return (this.hp);
	}
	public	void	setHp(int value) {
		this.hp = value;
	}
	public int	getMaxHp() {
		return (this.hp);
	}
	public	void	setMaxHp(int value) {
		this.hp = value;
	}
	public	int	getLevel() {
		return (this.level);
	}
	public	void	setLevel(int newLevel) {
		this.level = newLevel;
	}
	public int	getAttackValue() {
		return (this.attackValue);
	}
	public	void	setAttackValue(int newAttackValue) {
		this.attackValue = newAttackValue;
	}
	public int	getArmorValue() {
		return (this.armorValue);
	}
	public	void	setArmorValue(int newArmorValue) {
		this.armorValue = newArmorValue;
	}
	public List<String> getWeaknesses() {
		return (this.weaknesses);
	}
	public void	setWeaknesses(List<String> newList) {
		this.weaknesses = newList;
	}
	public List<String> getStrengths() {
		return (this.strengths);
	}
	public void	setStrengths(List<String> newList) {
		this.strengths = newList;
	}
}
