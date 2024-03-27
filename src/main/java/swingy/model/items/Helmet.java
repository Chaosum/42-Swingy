package swingy.model.items;
import javax.persistence.Entity;

@Entity
public class Helmet extends Items{
	public Helmet() {
		super();
		hpModifier = 20;
		this.name = "";
	}
	
}
