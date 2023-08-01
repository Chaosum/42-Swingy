package swingy.character.items;

public class VampireTooths extends Weapon {
	
	public VampireTooths() {
		this.type = "bite";
		this.name = "VampireTooths";
		this.rank = "Basic";
		this.levelRequired = 1;
		this.speedValue = 2;
		this.attackModifier = 10;
		this.specialEffects.add("Perforant");
		this.specialEffects.add("Lifesteal");
	}

	public VampireTooths(String rarity) {
		this.type = "bite";
		this.name = "VampireTooths";
		this.rank = rarity;
		this.levelRequired = 1;
		this.speedValue = 2;
		if (rarity.contains("rare")) {
			this.attackModifier = 15;
		}
		if (rarity.contains("epic")) {
			this.attackModifier = 20;
		}
		else {
			this.attackModifier = 10;
		}
		this.specialEffects.add("Perforant");
		this.specialEffects.add("Lifesteal");
	}
}
