package swingy.model.items;

import javax.persistence.Entity;

@Entity
public class BronzeHelmet extends Helmet{
	public BronzeHelmet() {
		super();
		this.name = "BronzeHelmet";
		this.rank = "basic";
		this.type = "Helmet";
		this.levelRequired = 1;
		this.attackModifier = 0;
		this.hpModifier = 50;
		this.armorModifier = 5;
	}

	public BronzeHelmet(String rarity) {
		super();
		this.name = "BronzeHelmet";
		this.rank = rarity;
		this.type = "Helmet";
		this.levelRequired = 1;
		this.attackModifier = 0;
		if (rarity.contains("rare")) {
			this.hpModifier = 50;
			this.armorModifier = 10;
		}
		else if (rarity.contains("epic")) {
			this.hpModifier = 75;
			this.armorModifier = 15;
		}
		else {
			this.hpModifier = 100;
			this.armorModifier = 5;
		}
	}
}
