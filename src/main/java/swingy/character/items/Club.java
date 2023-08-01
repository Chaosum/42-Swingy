package swingy.character.items;

public class Club extends Weapon{
	
	public Club() {
		this.type = "Club";
		this.name = "Club";
		this.rank = "basic";
		this.levelRequired = 1;
		this.speedValue = 1;
		this.attackModifier = 20;
	}

	public Club(String rarity) {
		this.type = "Club";
		this.name = "Club";
		this.rank = rarity;
		this.levelRequired = 1;
		this.speedValue = 1;
		if (rarity.contains("rare")) {
			this.attackModifier = 30;
		}
		if (rarity.contains("epic")) {
			this.attackModifier = 40;
		}
		else {
			this.attackModifier = 20;
		}
	}
}
