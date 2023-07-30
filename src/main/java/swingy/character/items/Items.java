package swingy.character.items;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.Data;

@Entity
@Data
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // La clé primaire auto-générée
	protected String name;
	protected String rank;
	protected String type;
	protected int levelRequired;
	protected int attackModifier;
	protected int hpModifier;
	protected int armorModifier;
	@ElementCollection
    @CollectionTable(name = "SpecialEffects", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "specialEffect")
	protected List<String> specialEffects;

	protected Items() {
		this.specialEffects = new ArrayList<String>();
	}

	public String getName() {
		return (this.name);
	}
	public String getRank() {
		return (this.rank);
	}
	public String getType() {
		return (this.type);
	}
	public int getLevelRequired() {
		return (this.levelRequired);
	}
	public int getAttackModifier() {
		return (this.attackModifier);
	}
	public int getHpModifier() {
		return (this.hpModifier);
	}
	public int getArmorModifier() {
		return (this.armorModifier);
	}
	public List<String> getSpecialEffects() {
		return (this.specialEffects);
	}
}
