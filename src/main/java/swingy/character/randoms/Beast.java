package swingy.character.randoms;

public class Beast extends Mob {

	public Beast(int heroLevel, int advancement) {
		super("Beast");
		this.typeName = "animal";
		this.weaknesses.add("archer");
	}
}
