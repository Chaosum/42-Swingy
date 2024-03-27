package swingy.model.items;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import swingy.model.hero.Hero;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Weapon extends Items {
	protected int speedValue;

	public void special() {}
	public void special(Hero hero) {}
	public Weapon() {
		super();
	}
}
