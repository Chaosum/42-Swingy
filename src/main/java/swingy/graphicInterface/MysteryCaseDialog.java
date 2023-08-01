package swingy.graphicInterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import swingy.character.hero.Hero;

public class MysteryCaseDialog extends JDialog {

	Hero hero;
	Game parent;

	public MysteryCaseDialog (JFrame parentFrame, Game parent) {
		super(parentFrame, "Mystery case", true);
		this.parent = parent;
		this.hero = parent.getHero();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 100);
		setLocationRelativeTo(parentFrame);
		setUndecorated(true);
		setResizable(false);
		JPanel choicePanel = new JPanel();
		choicePanel.setLayout(new GridLayout(1 , 3));
		JButton statsUpButton = new JButton("muscle up");
		statsUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				int randomNb = rand.nextInt(5);
				if (randomNb < 2)
					randomNb = 2;
				hero.setArmorValue(hero.getArmorValue() + randomNb);
				hero.setMaxHp(hero.getMaxHp() + randomNb);
				hero.setAttackValue(hero.getAttackValue() + randomNb);
				dispose();
			}
		});
		choicePanel.add(statsUpButton);
		JButton sleepButton = new JButton("Sleep");
		sleepButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int hpgain = (hero.getMaxHp() + hero.getHpBonus()) / 2;
				if (hpgain + hero.getHp() > hero.getMaxHp() + hero.getHpBonus()) {
					hero.setHp(hero.getMaxHp() + hero.getHpBonus());
				}
				else {
					hero.setHp(hpgain + hero.getHp());
				}
				dispose();
			}
		});
		choicePanel.add(sleepButton);
		JButton reforgeButton = new JButton("Reforge");
		reforgeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				if (rand.nextInt(100) >= 10) {
					hero.getWeapon().setAttackModifier(hero.getWeapon().getAttackModifier() + 15);
				}
				dispose();
			}
		});
		choicePanel.add(reforgeButton);
		add(choicePanel);
		setVisible(true);
	}
}
