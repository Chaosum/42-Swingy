package swingy.graphicInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
	private Hero hero;
	private String previousSelectedName;
	private JScrollPane scrollPane;
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
			scrollPane = setUpHeroStats(24);
		}
		else {
			scrollPane = new JScrollPane();
		}
		setViewportView(scrollPane);
		revalidate();
		repaint();
	}

	private JScrollPane setUpHeroStats(int fontSize) { // personnalisé l'UI
		JPanel stats = new JPanel();
		stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2); // Bordure de couleur noire, épaisseur de 2 pixels
		stats.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		//hero level
		JLabel heroLevel = new JLabel("level : " + this.hero.getLevel());
		heroLevel.setFont(new Font("Arial", Font.BOLD, fontSize));
		stats.add(heroLevel);

		JLabel heroClass = new JLabel("Class : " + this.hero.getTypeName());
		heroClass.setFont(new Font("Arial", Font.BOLD, fontSize));
		stats.add(heroClass);
	
		//hero Xp
		JLabel heroExperience = new JLabel("nextLevel : " + this.hero.getNextLevelXp() + "xp");
		heroExperience.setFont(new Font("Arial", Font.BOLD, fontSize));
		stats.add(heroExperience);
		//Hero Stats
		JLabel heroAttack = new JLabel("Attack Power: " + this.hero.getAttackValue());
		heroAttack.setFont(new Font("Arial", Font.BOLD, fontSize));
		stats.add(heroAttack);
		JLabel heroDefense = new JLabel("Defense : " + this.hero.getArmorValue());
		heroDefense.setFont(new Font("Arial", Font.BOLD, fontSize));
		stats.add(heroDefense);
		JLabel heroHp = new JLabel("Hp: " + (this.hero.getMaxHp() + this.hero.getHpBonus()) );
		heroHp.setFont(new Font("Arial", Font.BOLD, fontSize));
		stats.add(heroHp);
		//hero items
		JLabel heroWeapon = new JLabel("Weapon : " + (this.hero.getWeapon().getName() == null? "": this.hero.getWeapon().getName()));
		heroWeapon.setFont(new Font("Arial", Font.BOLD, fontSize));
		stats.add(heroWeapon);
		JLabel heroArmor = new JLabel("Armor : " + (this.hero.getArmor().getName() == null? "": this.hero.getArmor().getName()));
		heroArmor.setFont(new Font("Arial", Font.BOLD, fontSize));
		stats.add(heroArmor);
		JLabel heroHelmet = new JLabel("Helmet : " + (this.hero.getHelmet().getName() == null? "": this.hero.getHelmet().getName()));
		heroHelmet.setFont(new Font("Arial", Font.BOLD, fontSize));
		stats.add(heroHelmet);
		JScrollPane heroStats = new JScrollPane(stats);
		return (heroStats);
	}
}
