package swingy.character.items;

import swingy.character.hero.Hero;

public abstract class Weapon extends Items {
	protected int speedValue;

	public abstract void special();
	public abstract void special(Hero hero) ;
	public Weapon() {
	}
}
