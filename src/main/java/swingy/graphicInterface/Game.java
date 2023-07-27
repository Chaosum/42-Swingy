package swingy.graphicInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import swingy.character.hero.Hero;
import swingy.map.Map;

@Data
@EqualsAndHashCode(callSuper=false)
public class Game extends JPanel{
	private Hero hero;
	private Map map;
	private DisplayHeroStats displayHeroStats;
	private JPanel leftZone;
	public Game(Map map, DisplayHeroStats displayHeroStats) {
		this.map = map;
		this.hero = map.getHero();
		this.displayHeroStats = displayHeroStats;
		setLayout(new BorderLayout());
		playerZone();
		add(leftZone, BorderLayout.WEST);
	}

	private void playerZone() {
		leftZone = new JPanel();
		leftZone.setLayout(new BoxLayout(leftZone, BoxLayout.Y_AXIS));
		leftZone.add(displayHeroStats.setUpHeroStats(14)); // Crée custom
		leftZone.add(actionSection());
		leftZone.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}

	private JPanel actionSection() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		JButton attackButton = new JButton("Attack");
		attackButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.add(attackButton);
		JButton runButton = new JButton("Run");
		runButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.add(runButton);
		panel.add(specialZoneSetUp());
		JPanel direction = new JPanel();
		direction.setLayout(new BorderLayout());
		JPanel goLeftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton goLeftButton = new JButton("←");
		goLeftPanel.add(goLeftButton);
		goLeftPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

		JPanel goRightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton goRightButton = new JButton("→");
		goRightPanel.add(goRightButton);
		goRightPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

		//JPanel goTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//goTopPanel.add(goTopButton);
		JPanel goBotPanel = new JPanel();
		goBotPanel.setLayout(new BoxLayout(goBotPanel, BoxLayout.Y_AXIS));
		goBotPanel.setAlignmentX(CENTER_ALIGNMENT);
		JButton goTopButton = new JButton("↑");
		JButton actionButton = new JButton("@");
		JButton goBotButton = new JButton("↓");
		goBotPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
		goBotPanel.add(goTopButton);
		goBotPanel.add(Box.createRigidArea(new Dimension(0, 5))); // spacer
		goBotPanel.add(actionButton);
		goBotPanel.add(Box.createRigidArea(new Dimension(0, 5))); // spacer
		goBotPanel.add(goBotButton);
		//JPanel actionPanel = new JPanel(new FlowLayout());
		direction.add(goLeftPanel, BorderLayout.WEST);
		direction.add(goRightPanel, BorderLayout.EAST);
		//direction.add(goTopPanel, BorderLayout.NORTH);
		//direction.add(actionPanel, BorderLayout.CENTER);
		direction.add(goBotPanel, BorderLayout.CENTER);

		direction.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.add(direction);


		return (panel);
	}

	private JPanel specialZoneSetUp() {
		JPanel specialZone = new JPanel();
		specialZone.setLayout(new BorderLayout());
		specialZone.add(Box.createRigidArea(new Dimension(0, 20)));
		if (hero.getSpecialType().equals("activ")) {
			activTypeSpecial(specialZone); // listener a ajouté quand combat fait
		}
		else if (hero.getSpecialType().equals("passiv")) {
			passivTypeSpecial(specialZone);
		}
		specialZone.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		return (specialZone);
	}

	private void	activTypeSpecial(JPanel specialZone) {
		JLabel chargeCounter = new JLabel ("Charges : " + this.hero.getCurrentCharge() + " / " + this.hero.getSpecialChargeCounter());
		specialZone.add(chargeCounter, BorderLayout.NORTH);
		if (hero.isSpecialUp()) {
			JCheckBox useSpecialButton = new JCheckBox("Use special");
			specialZone.add(useSpecialButton, BorderLayout.CENTER);
		}
		else {
			JCheckBox useSpecialButton = new JCheckBox("Use special");
			useSpecialButton.setEnabled(false);
			specialZone.add(useSpecialButton, BorderLayout.CENTER);
		}
	}

	private void	passivTypeSpecial(JPanel specialZone) {
		specialZone.setLayout(new BoxLayout(specialZone, BoxLayout.Y_AXIS));
		String[] descriptions = this.hero.getSpecialDescription().split("\n");
		JLabel specialDescription = new JLabel ("PASSIV : " + this.hero.getSpecialAttack());
		specialZone.add(specialDescription);
		JLabel spacerLabel = new JLabel (" ");
		specialZone.add(spacerLabel);
		int i = 0;
		while (i < descriptions.length) {
			JLabel descriptionLine = new JLabel (descriptions[i]);
			specialZone.add(descriptionLine);
			i++;
		}
	}

}
