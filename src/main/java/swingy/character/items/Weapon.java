package swingy.character.items;

import javax.persistence.Entity;

import swingy.character.hero.Hero;

@Entity
public abstract class Weapon extends Items {
	protected int speedValue;

	public abstract void special();
	public abstract void special(Hero hero) ;
	public Weapon() {
	}
}
