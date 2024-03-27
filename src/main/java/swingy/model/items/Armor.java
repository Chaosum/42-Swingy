package swingy.model.items;

import javax.persistence.Entity;

@Entity
public class Armor extends Items{
	public Armor() {
		super();
		this.name = "";
	}
}
