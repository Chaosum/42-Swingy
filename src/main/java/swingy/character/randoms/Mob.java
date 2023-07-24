package swingy.character.randoms;

import java.util.ArrayList;
import java.util.List;

import swingy.character.Characters;
import swingy.character.hero.DeathException;
import swingy.character.items.Weapon;

public class Mob extends Characters{
	protected int hostilityLevel;
	protected List<String> phrases;
	protected Weapon weapon;
	protected int experienceDroped;

	protected Mob(String name) {
		super(name);
		this.hostilityLevel = 0;
		this.phrases = new ArrayList<String>();
	}

	public void takeDamages(int damages, Weapon weapon, Characters from) throws DeathException {
		this.hp = this.hp - damages;
		if (this.hp <= 0) {
			throw new DeathException(this.name + "was killed" + from.getName() + "\'s " + weapon.getName());
		}
	}
	public int getExperienceDroped() {
		return (this.experienceDroped);
	}
	public int getHostilityLevel() {
		return (this.hostilityLevel);
	}

	public List<String> getPhrases() {
		return (this.phrases);
	}
	public Weapon getWeapon() {
		return (this.weapon);
	}
}
