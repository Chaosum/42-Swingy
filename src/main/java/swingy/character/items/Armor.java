package swingy.character.items;

import javax.persistence.Entity;

@Entity
public class Armor extends Items{
	protected String aMeterial;
	public Armor() {
		super();
		this.name = "";
	}
}
