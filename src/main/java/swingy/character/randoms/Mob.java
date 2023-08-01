package swingy.character.randoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import swingy.character.Characters;
import swingy.character.hero.DeathException;
import swingy.character.hero.Hero;
import swingy.character.items.Items;
import swingy.character.items.Weapon;

public class Mob extends Characters{
	protected List<String> phrases;
	protected Weapon weapon;
	protected int experienceDroped;
	protected HashMap<Integer, Items> lootTable;

	protected Mob(String name) {
		super(name);
		this.lootTable = new HashMap<Integer, Items>();
		this.phrases = new ArrayList<String>();
		weapon = new Weapon();
	}

	protected int checkBlockPower() {
		int blockPower = 0;

		if (this.weapon.getSpecialEffects().contains("Block")) {
			blockPower++;
		}
		return (blockPower);
	}
	protected int checkResistances(int damages, Characters from) {
		int damagesModifier = 0;
		if (this.getWeaknesses().contains(from.getTypeName())) {
			damagesModifier = damagesModifier + (damages / 3);
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
			throw new DeathException(this.name + "was killed" + from.getName() + "\'s " + weapon.getName());
		}
	}
	public void dealDamages(Hero to) throws DeathException {
		int damages = this.attackValue + this.weapon.getAttackModifier();
		int damagesModifier = 0;
		if (this.weapon.getSpecialEffects().contains(to.getTypeName() + "Strength")) {
			damagesModifier += damages / 3;
		}
		damages += damagesModifier;
		if (this.weapon.getSpecialEffects().contains("Lifesteal")) {
			this.hp += damages / 2;
			if (this.hp > this.maxHp){
				this.hp = this.maxHp;
			}
		}
		try {
			to.takeDamages(damages, this.weapon, this);
		} catch (DeathException e) {
			throw new DeathException(e.getMessage());
		}
	}

	
	public int getExperienceDroped() {
		return (this.experienceDroped);
	}

	public List<String> getPhrases() {
		return (this.phrases);
	}
	public Weapon getWeapon() {
		return (this.weapon);
	}
}
