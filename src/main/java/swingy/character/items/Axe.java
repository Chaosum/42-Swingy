package swingy.character.items;

public class Axe extends Weapon{
	public Axe() {
		this.type = "Axe";
		this.name = "Axe";
		this.rank = "basic";
		this.levelRequired = 1;
		this.speedValue = 1;
		this.attackModifier = 10;
	}

	public Axe(String rarity) {
		this.type = "Axe";
		this.name = "Axe";
		this.rank = rarity;
		this.levelRequired = 1;
		this.speedValue = 1;
		if (rarity.contains("rare")) {
			this.attackModifier = 20;
		}
		if (rarity.contains("epic")) {
			this.attackModifier = 25;
		}
		else {
			this.attackModifier = 10;
		}
	}
}
