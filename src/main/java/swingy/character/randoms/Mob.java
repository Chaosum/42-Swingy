package swingy.character.randoms;

import java.util.ArrayList;
import java.util.List;

import swingy.character.Character;
import swingy.character.items.Weapon;

public class Mob extends Character{
	protected String typeName;
	protected int hostilityLevel;
	protected List<String> phrases;
	protected Weapon weapon;

	protected Mob(String name) {
		super(name);
		this.hostilityLevel = 0;
		this.phrases = new ArrayList<String>();
	}

	public String getTypename() {
		return (this.typeName);
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
