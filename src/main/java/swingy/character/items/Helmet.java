package swingy.character.items;
import javax.persistence.Entity;

@Entity
public class Helmet extends Items{
	protected String hMeterial;

	public Helmet() {
		super();
		hpModifier = 20;
		this.name = "";
	}
	
}
