package swingy.graphicInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;

import lombok.Data;
import lombok.EqualsAndHashCode;
import swingy.character.hero.Hero;

@Data
@EqualsAndHashCode(callSuper=false)
public class DisplayHeroStats extends JScrollPane{
	protected Hero hero;
	protected String previousSelectedName;
	public DisplayHeroStats(String selectedValue) {
		super();
		setLayout(new ScrollPaneLayout());
		setPreferredSize(new Dimension(500, 200));
		previousSelectedName = "";
		upDateHeroListStat(selectedValue);
	}

	public void upDateHeroListStat(String selectedValue) {
		String selectedName = selectedValue;
		if (selectedName != null) {
			String heroName = selectedName.substring(0, selectedName.indexOf(' '));
			hero = MainFrame.entityManager.createQuery("SELECT h FROM Hero h WHERE h.name = :heroName", Hero.class).setParameter("heroName", heroName).getSingleResult();
		}
		else {
			this.hero = new Hero();
		}
		setViewportView(setUpHeroStats());
		revalidate();
		repaint();
	}

	private JScrollPane setUpHeroStats() { // personnalisé l'UI
		JPanel stats = new JPanel(new GridBagLayout());
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Bordure de couleur noire, épaisseur de 2 pixels
		stats.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		//hero level
		JLabel heroLevel = new JLabel("level : " + this.hero.getLevel());
		heroLevel.setFont(new Font("Arial", Font.BOLD, 28));
		GridBagConstraints levelConstraints = new GridBagConstraints();
		levelConstraints.anchor = GridBagConstraints.WEST;
		levelConstraints.insets = new Insets(20, 0, 10, 0);
		levelConstraints.gridx = 0; // colonne 0
		levelConstraints.gridy = 0; // line 0
		stats.add(heroLevel, levelConstraints);

		JLabel heroClass = new JLabel("Class : " + this.hero.getTypeName());
		heroClass.setFont(new Font("Arial", Font.BOLD, 28));
		GridBagConstraints classConstraints = new GridBagConstraints();
		classConstraints.anchor = GridBagConstraints.WEST;
		classConstraints.insets = new Insets(20, -100, 10, 0);
		classConstraints.gridx = 1; // colonne 0
		classConstraints.gridy = 0; // line 0
		stats.add(heroClass, classConstraints);
	
		//hero Xp
		JLabel heroExperience = new JLabel("nextLevel : " + this.hero.getNextLevelXp() + "xp");
		heroExperience.setFont(new Font("Arial", Font.BOLD, 24));
		GridBagConstraints experienceConstraints = new GridBagConstraints();
		experienceConstraints.anchor = GridBagConstraints.WEST;
		experienceConstraints.insets = new Insets(10, 100, 10, 0);
		experienceConstraints.gridx = 0; // colonne 0
		experienceConstraints.gridy = 1; // line 0
		stats.add(heroExperience, experienceConstraints);
		//Hero Stats
		JLabel heroAttack = new JLabel("Attack Power: " + this.hero.getAttackValue());
		heroAttack.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints attackConstraints = new GridBagConstraints();
		attackConstraints.anchor = GridBagConstraints.WEST;
		attackConstraints.insets = new Insets(20, 0, 10, 0);
		attackConstraints.gridx = 0;
		attackConstraints.gridy = 2;
		stats.add(heroAttack, attackConstraints);
		JLabel heroDefense = new JLabel("Defense : " + this.hero.getArmorValue());
		heroDefense.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints defenseConstraints = new GridBagConstraints();
		defenseConstraints.anchor = GridBagConstraints.WEST;
		defenseConstraints.insets = new Insets(20, 0, 10, 0);
		defenseConstraints.gridx = 0;
		defenseConstraints.gridy = 3;
		stats.add(heroDefense, defenseConstraints);
		JLabel heroHp = new JLabel("Hp: " + (this.hero.getHp() + this.hero.getHpBonus()) );
		heroHp.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints hpConstraints = new GridBagConstraints();
		hpConstraints.anchor = GridBagConstraints.WEST;
		hpConstraints.insets = new Insets(20, 0, 10, 0);
		hpConstraints.gridx = 0;
		hpConstraints.gridy = 4;
		stats.add(heroHp, hpConstraints);
		//hero items
		JLabel heroWeapon = new JLabel("Weapon : " + (this.hero.getWeapon().getName() == null? "": this.hero.getWeapon().getName()));
		heroWeapon.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints weaponConstraints = new GridBagConstraints();
		weaponConstraints.anchor = GridBagConstraints.WEST;
		weaponConstraints.insets = new Insets(20, -100, 10, 0);
		weaponConstraints.gridx = 1; // colonne 0
		weaponConstraints.gridy = 2; // line 0
		stats.add(heroWeapon, weaponConstraints);
		JLabel heroArmor = new JLabel("Armor : " + (this.hero.getArmor().getName() == null? "": this.hero.getArmor().getName()));
		heroArmor.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints armorConstraints = new GridBagConstraints();
		armorConstraints.anchor = GridBagConstraints.WEST;
		armorConstraints.insets = new Insets(20, -100, 10, 0);
		armorConstraints.gridx = 1; // colonne 0
		armorConstraints.gridy = 3; // line 0
		stats.add(heroArmor, armorConstraints);
		JLabel heroHelmet = new JLabel("Helmet : " + (this.hero.getHelmet().getName() == null? "": this.hero.getHelmet().getName()));
		heroHelmet.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints helmetConstraints = new GridBagConstraints();
		helmetConstraints.anchor = GridBagConstraints.WEST;
		helmetConstraints.insets = new Insets(20, -100, 10, 0);
		helmetConstraints.gridx = 1; // colonne 0
		helmetConstraints.gridy = 4; // line 0
		stats.add(heroHelmet, helmetConstraints);
		JScrollPane heroStats = new JScrollPane(stats);
		return (heroStats);
	}
}
