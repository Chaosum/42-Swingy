package swingy.character.randoms;

public class Zombie extends Mob {

	public Zombie(int heroLevel, int advancement) {
		super("Zombie");
		this.typeName = "undead";
		this.weaknesses.add("paladin");
	}
}
