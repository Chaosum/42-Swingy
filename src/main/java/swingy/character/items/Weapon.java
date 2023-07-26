package swingy.character.items;

import javax.persistence.Entity;

import swingy.character.hero.Hero;

@Entity
public class Weapon extends Items {
	protected int speedValue;

	public void special() {}
	public void special(Hero hero) {}
	public Weapon() {
	}
}
