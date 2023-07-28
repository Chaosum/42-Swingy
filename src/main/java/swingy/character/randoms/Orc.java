package swingy.character.randoms;

public class Orc extends Mob {
	
	public Orc(int heroLevel, int advancement) {
		super("Orc");
		this.typeName = "humanoid";
		this.weaknesses.add("mage");
	}
}
