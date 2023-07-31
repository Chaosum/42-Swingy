package swingy.graphicInterface;

import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import swingy.character.hero.Hero;
import swingy.character.randoms.Mob;

public class LootBoxChoice extends JDialog{
	Game parent;
	Hero hero;
	Mob ennemy;

	public LootBoxChoice(JFrame parentFrame, Game parent) {
		super(parentFrame, "Loot", true);
		this.parent = parent;
		this.hero = parent.getHero();
		this.ennemy = parent.getEnnemy();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 100);
		setLocationRelativeTo(parentFrame);
		setUndecorated(true);
		setResizable(false);
		JPanel lootChoice = new JPanel();
		lootChoice.setLayout(new GridLayout(1, 3));
		Random rand = new Random();
		int randomNb = rand.nextInt(100);
		//voir loot tables
		
	}
}
