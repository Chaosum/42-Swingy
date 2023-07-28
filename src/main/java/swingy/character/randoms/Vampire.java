package swingy.character.randoms;

public class Vampire extends Mob {

	public Vampire(int heroLevel, int advancement) {
		super("Vampire");
		this.typeName = "nocturn";
		this.weaknesses.add("Light");
	}
	
}
