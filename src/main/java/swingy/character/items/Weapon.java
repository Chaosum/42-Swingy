package swingy.character.items;

public abstract class Weapon extends Items {
	protected int speedValue;
	protected String specialEffect;

	protected abstract void special();
	public Weapon() {
	}
}
