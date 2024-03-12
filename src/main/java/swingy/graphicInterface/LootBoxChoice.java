package swingy.graphicInterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swingy.character.hero.Hero;
import swingy.character.items.Items;
import swingy.character.randoms.Mob;

public class LootBoxChoice extends JDialog{
	private Hero hero;
	private Mob ennemy;
	private Items first;
	private Items second;
	private Items third;
	public boolean worthPrint;

	public LootBoxChoice(JFrame parentFrame, Game parent) {
		super(parentFrame, "Loot", true);
		this.hero = parent.getHero();
		this.ennemy = parent.getEnnemy();
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 200);
		setLocationRelativeTo(parentFrame);
		setUndecorated(true);
		setResizable(false);
		JPanel lootChoice = new JPanel();
		lootChoice.setLayout(new GridLayout(1, 3));
		HashMap<Integer, Items> lootTable = ennemy.getLootTable();
		Random rand = new Random();
		int randomNb = rand.nextInt(100);
		Set<Integer> dropRate = lootTable.keySet();
		int chosenDrop = 101;
		first = null;
		second = null;
		third = null;
		worthPrint = true;
		for (int key : dropRate) {
			if (randomNb <= key) {
				chosenDrop = key;
			}
		}
		if (chosenDrop != 101) {
			first = lootTable.get(chosenDrop);
		}
		randomNb = rand.nextInt(100);
		chosenDrop = 101;
		for (int key : dropRate) {
			if (randomNb <= key) {
				chosenDrop = key;
			}
		}
		if (chosenDrop != 101) {
			second = lootTable.get(chosenDrop);
		}
		randomNb = rand.nextInt(100);
		chosenDrop = 101;
		for (int key : dropRate) {
			if (randomNb <= key) {
				chosenDrop = key;
			}
		}
		if (chosenDrop != 101) {
			third = lootTable.get(chosenDrop);
		}
		JPanel firstLoot = itemSetUp(first);
		JButton selectFirstItem = new JButton("Choose");
		selectFirstItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (first != null){
					hero.equip(first);
				}
				dispose();
			}
		});
		
		JPanel secondLoot = itemSetUp(second);
		JButton selectSecondItem = new JButton("Choose");
		selectSecondItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (second != null) {
					hero.equip(second);
				}
				dispose();
			}
		});
		
		JPanel thirdLoot = itemSetUp(third);
		JButton selectThirdItem = new JButton("Choose");
		selectThirdItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (third != null) {
					hero.equip(third);
				}
				dispose();
			}
		});
		firstLoot.add(selectFirstItem);
		secondLoot.add(selectSecondItem);
		thirdLoot.add(selectThirdItem);
		lootChoice.add(firstLoot);
		lootChoice.add(secondLoot);
		lootChoice.add(thirdLoot);
		add(lootChoice, BorderLayout.CENTER);
		JButton noChoice = new JButton("I want nothing");
		noChoice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(noChoice, BorderLayout.SOUTH);
		if (first == null && second == null && third == null){
			worthPrint = false;
		}
	}
	
	private JPanel itemSetUp(Items loot) {
		JPanel itemSetUPanel = new JPanel();
		itemSetUPanel.setLayout(new BoxLayout(itemSetUPanel, BoxLayout.Y_AXIS));
		if (loot == null) {
			JLabel noLoot = new JLabel("Nothing");
			itemSetUPanel.add(noLoot);
		} else {
			try {
				
				JLabel itemClass = new JLabel(loot.getType() + " | lvl min : " + loot.getLevelRequired());
				JLabel itemCarac = new JLabel(loot.getName() + " | rank : " + loot.getRank());
				JLabel itemBonus = new JLabel("atk." + loot.getAttackModifier() + " | def." + loot.getArmorModifier() + " | hp." + loot.getHpModifier());
				itemSetUPanel.add(itemClass);
				itemSetUPanel.add(itemCarac);
				itemSetUPanel.add(itemBonus);
			} catch (Exception e) {
				System.out.println(loot);
			}
		}
		return (itemSetUPanel);
	}
	
}