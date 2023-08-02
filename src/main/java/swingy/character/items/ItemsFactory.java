package swingy.character.items;

public class ItemsFactory {
	
	public static Weapon createWeapon(String name, String rank) {
		Weapon newWeapon = new Weapon();
		
		if (name.contains("Axe")) {
			newWeapon = new Axe(rank);
		}
		else if (name.contains("Bow")) {
			newWeapon = new Bow(rank);
		}
		else if (name.contains("Club")) {
			newWeapon = new Club(rank);
		}
		else if (name.contains("DestroyerOfWorld")) {
			newWeapon = new DestroyerOfWorld(rank);
		}
		else if (name.contains("SwordAndShield")) {
			newWeapon = new SwordAndShield(rank);
		}
		else if (name.contains("VampireTooths")) {
			newWeapon = new VampireTooths(rank);
		}
		else if (name.contains("Wand")) {
			newWeapon = new Wand(rank);
		}
		return (newWeapon);
	}

	public static Helmet createHelmet(String name, String rank) {
		Helmet newHelmet = new Helmet();

		if (name.contains("BronzeHelmet")) {
			newHelmet = new BronzeHelmet(rank);
		}
		return (newHelmet);
	}

	public static Armor createArmor(String name, String rank) {
		Armor newArmor = new Armor();

		if (name.contains("LeatherArmor")) {
			newArmor = new LeatherArmor(rank);
		}
		return (newArmor);
	}
}
