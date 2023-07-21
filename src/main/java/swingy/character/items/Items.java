package swingy.character.items;

import java.util.ArrayList;
import java.util.List;

public class Items {
	protected String name;
	protected String rank;
	protected String type;
	protected int levelRequired;
	protected int attackModifier;
	protected int hpModifier;
	protected int armorModifier;
	protected List<String> specialEffects;

	protected Items() {
		this.specialEffects = new ArrayList<String>();
	}

	public String getName() {
		return (this.name);
	}
	public String getRank() {
		return (this.rank);
	}
	public String getType() {
		return (this.type);
	}
	public int getLevelRequired() {
		return (this.levelRequired);
	}
	public int getAttackModifier() {
		return (this.attackModifier);
	}
	public int getHpModifier() {
		return (this.hpModifier);
	}
	public int getArmorModifier() {
		return (this.armorModifier);
	}
	public List<String> getSpecialEffects() {
		return (this.specialEffects);
	}
}
