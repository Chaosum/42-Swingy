package swingy.graphicInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import swingy.character.hero.DeathException;
import swingy.character.hero.Hero;
import swingy.character.hero.VictoryException;
import swingy.character.randoms.Mob;
import swingy.character.randoms.MobSpawner;
import swingy.map.Map;

public class Game extends JPanel implements KeyListener{
	private Hero	hero;
	private Map		map;
	private JPanel	leftZone;
	private JPanel	centerZone;
	private JPanel	rightZone;
	private boolean	inCombat;
	private boolean flee;
	private boolean	victory;
	private boolean	boss;
	private Mob		ennemy;
	private JCheckBox useSpecialButton;
	private MainFrame parent;
	private Game current;

	public Game(Map map, MainFrame parent) {
		current = this;
		this.parent = parent;
		this.map = map;
		this.hero = map.getHero();
		this.inCombat = false;
		this.victory = false;
		this.boss = false;
		this.flee = false;

		setLayout(new BorderLayout());
		requestFocusInWindow();
		playerZone();
		mapZone();
		eventZone();
		add(leftZone, BorderLayout.WEST);
		add(centerZone, BorderLayout.CENTER);
		add(rightZone, BorderLayout.EAST);
		addKeyListener(this);
		setFocusable(true);
	}

	private void upDateEventZone() {
		remove(rightZone);
		rightZone.removeAll();
		eventZone();
		add(rightZone, BorderLayout.EAST);
		validate();
		repaint();
	}


	private void updateCenterZone() {
		remove(centerZone);
		centerZone.removeAll();
		mapZone();
		add(centerZone, BorderLayout.CENTER);
		validate();
		repaint();
	}

	private void updatePlayerZone() {
		remove(leftZone);
		leftZone.removeAll();
		playerZone();
		add(leftZone, BorderLayout.WEST);
		validate();
		repaint();
	}

	private void playerZone() {
		leftZone = new JPanel();
		leftZone.setLayout(new BoxLayout(leftZone, BoxLayout.Y_AXIS));
		//leftZone.add(heroStatsZone());
		leftZone.add(actionSection());
		leftZone.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}

	private JScrollPane heroStatsZone() {
		JPanel stats = new JPanel();
		stats.setLayout(new BoxLayout(stats, BoxLayout.Y_AXIS));
		stats.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		JLabel name = new JLabel(hero.getName() + " " + hero.getTitle());
		name.setFont(new Font("Arial", Font.BOLD, 20));
		JLabel heroLvl = new JLabel( hero.getTypeName() + " lvl. " + hero.getLevel());
		JLabel exeperience = new JLabel("xp : " + hero.getExperience() + " / " + hero.getNextLevelXp() + hero.getExperience() + " | needed: " + hero.getNextLevelXp());
		JLabel heroHp = new JLabel( "Hp : " + hero.getHp() + " / " + hero.getMaxHp());
		JLabel special = new JLabel("Spacial : " + hero.getSpecialDescription());
		JLabel heroStats = new JLabel("Attack : " + hero.getAttackValue() + " " + hero.getTitle() + "Armor : " + hero.getArmorValue());
		JLabel spacer = new JLabel(" ");
		JLabel heroWeapon = new JLabel("Weapon : " + hero.getWeapon().getName());
		JLabel heroWeaponStats = new JLabel("attack : " + hero.getWeapon().getAttackModifier() + " | Armor : " + hero.getWeapon().getArmorModifier() + " | Hp :" + hero.getWeapon().getHpModifier());
		JLabel heroArmor = new JLabel("Armor : " + hero.getArmor().getName());
		JLabel heroArmorStats = new JLabel("attack : " + hero.getArmor().getAttackModifier() + " | Armor : " + hero.getArmor().getArmorModifier() + " | Hp :" + hero.getArmor().getHpModifier());
		JLabel heroHelmet = new JLabel("Helmet : " + hero.getHelmet().getName());
		JLabel heroHelmetStats = new JLabel("attack : " + hero.getHelmet().getAttackModifier() + " | Armor : " + hero.getHelmet().getArmorModifier() + " | Hp :" + hero.getHelmet().getHpModifier());
		
		stats.add(name);
		stats.add(heroLvl);
		stats.add(exeperience);
		stats.add(heroHp);
		stats.add(special);
		stats.add(heroStats);
		stats.add(spacer);
		stats.add(heroWeapon);
		stats.add(heroWeaponStats);
		stats.add(heroArmor);
		stats.add(heroArmorStats);
		stats.add(heroHelmet);
		stats.add(heroHelmetStats);

		JScrollPane scrollPane = new JScrollPane(stats);
		return (scrollPane);
	}
	
	private JPanel actionSection() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		JButton attackButton = new JButton("Attack");
		attackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inCombat == true) {
					try {
						combatRound();
						upDateEventZone();
						updatePlayerZone();
						if(inCombat == false) {
							updateCenterZone();
						}
					} catch (DeathException ex) {
						victory = false;
						new EndOfTheGame((JFrame) SwingUtilities.getWindowAncestor(Game.this), current, victory);
					}
				}
			}
		});
		attackButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.add(attackButton);
		JButton runButton = new JButton("Run");
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inCombat == true && boss == false) {
					flee = true;
					try {
						combatRound();
						upDateEventZone();
						updatePlayerZone();
						if(inCombat == false) {
							updateCenterZone();
						}
					} catch (DeathException ex) {
						victory = false;
						new EndOfTheGame((JFrame) SwingUtilities.getWindowAncestor(Game.this), current, victory);
					}
				}
			}
		});
		runButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.add(runButton);
		panel.add(specialZoneSetUp());
		JPanel direction = new JPanel();
		direction.setLayout(new BorderLayout());
		JPanel goLeftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton goLeftButton = new JButton("←");
		goLeftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inCombat == false) {
					map.moveLeft();
					upDateEventZone();
					updateCenterZone();
				}
			}
		});
		goLeftPanel.add(goLeftButton);
		goLeftPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

		JPanel goRightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton goRightButton = new JButton("→");
		goRightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inCombat == false) {
					map.moveRight();
					upDateEventZone();
					updateCenterZone();
				}
			}
		});
		goRightPanel.add(goRightButton);
		goRightPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		JPanel goBotPanel = new JPanel();
		goBotPanel.setLayout(new BoxLayout(goBotPanel, BoxLayout.Y_AXIS));
		goBotPanel.setAlignmentX(CENTER_ALIGNMENT);
		JButton goTopButton = new JButton("↑");
		JButton actionButton = new JButton("+");
		JButton goBotButton = new JButton("↓");
		goTopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inCombat == false) {
					map.moveUp();
					upDateEventZone();
					updateCenterZone();
				}
			}
		});
		goBotButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inCombat == false) {
					map.moveDown();
					upDateEventZone();
					updateCenterZone();
				}
			}
		});
		goBotPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
		goBotPanel.add(goTopButton);
		goBotPanel.add(Box.createRigidArea(new Dimension(0, 5))); // spacer
		goBotPanel.add(actionButton);
		goBotPanel.add(Box.createRigidArea(new Dimension(0, 5))); // spacer
		goBotPanel.add(goBotButton);

		direction.add(goLeftPanel, BorderLayout.WEST);
		direction.add(goRightPanel, BorderLayout.EAST);
		direction.add(goBotPanel, BorderLayout.CENTER);

		direction.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		panel.add(direction);


		return (panel);
	}

	private void eventZone() {
		rightZone = new JPanel();
		rightZone.setPreferredSize(new Dimension(200, 500));
		rightZone.setLayout(new BoxLayout(rightZone, BoxLayout.Y_AXIS));
		if (inCombat == false) {
			mapEvent();
		}
		else if (inCombat == true) {
			combatEvent();
		}
		rightZone.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		add(rightZone, BorderLayout.EAST);
	}

	private void	combatRound() throws DeathException {
		int heroSpeed = hero.getWeapon().getSpeedValue();
		int mobSpeed = ennemy.getWeapon().getSpeedValue();
		boolean specialButton = false;
		if (hero.getSpecialType().contains("activ")) {
			specialButton = useSpecialButton.isSelected();
		}

		if (flee == true) {
			Random rand = new Random();
			int randomInt = rand.nextInt(100);
			if (randomInt <= hero.getFleeChances()) {
				inCombat = false;
				mapEvent();
				return ;
			}
			else {
				heroSpeed = 0;
			}
		}
		try {
			if (heroSpeed >= mobSpeed && heroSpeed > 0) {
				hero.dealDamages(ennemy, specialButton);
			}
			else if (mobSpeed > heroSpeed) {
				ennemy.dealDamages(hero);
			}
			if (heroSpeed < mobSpeed && heroSpeed > 0) {
				hero.dealDamages(ennemy, specialButton);
			}
			else if (mobSpeed <= heroSpeed) {
				ennemy.dealDamages(hero);
			}
			if (flee == false) {
				hero.chargeUp();
			}
		} catch (VictoryException e) {
			inCombat = false;
			victory = true;
			lootMob();
			if (boss) {
				new EndOfTheGame((JFrame) SwingUtilities.getWindowAncestor(Game.this), this, victory);
			}
		}
	}
	
	private void lootMob() {
		hero.gainExperience(ennemy.getExperienceDroped());
		LootBoxChoice lootbox = new LootBoxChoice((JFrame) SwingUtilities.getWindowAncestor(Game.this), this);
		//afficher les items loot avec un random rand pour voir si on les loot puis pop-up avec choix d'equiper ou pas
	}

	private void	combatEvent() {
		JLabel name = new JLabel(ennemy.getName());
		rightZone.add(name);
		JLabel type = new JLabel(ennemy.getTypeName() + "/ lvl. " + ennemy.getLevel());
		rightZone.add(type);
		JLabel hp = new JLabel("Hp : " + ennemy.getHp() + " / " + ennemy.getMaxHp());
		rightZone.add(hp);
		JLabel stats = new JLabel("Attack = " + ennemy.getAttackValue() + " | " + "Armor = " + ennemy.getArmorValue() );
		rightZone.add(stats);
		JLabel speed = new JLabel("Attack speed = " + ennemy.getWeapon().getSpeedValue());
		rightZone.add(speed);
	}

	private void mapEvent() {
		char event = map.getMap().get(map.getPosX()).charAt(map.getPosY());
		int xAdvancement = map.getPosX() - (map.getSize() / 2); 
		int yAdvancement = map.getPosY() - (map.getSize() / 2);
		int advancement = (xAdvancement < 0 ? xAdvancement * -1: xAdvancement) + (yAdvancement < 0 ? yAdvancement * -1: yAdvancement);
		if (victory == false && flee == false) {
			if (event == 'M') {
				ennemy = MobSpawner.createRandom(event, advancement, map.getMapLevel());
				inCombat = true;
				combatEvent();

			}
			else if (event == 'B') {
				ennemy = MobSpawner.create("worldBoss", map.getMapLevel());
				inCombat = true;
				boss = true;
				combatEvent();
			}
			else if (event == '?') {
				new MysteryCaseDialog((JFrame) SwingUtilities.getWindowAncestor(Game.this), this);
			}
		}
		if (flee == false && (inCombat == false || victory == true)) {
			victory = false;
			String left = map.getMap().get(map.getPosX()).substring(0, map.getPosY());
			String right = map.getMap().get(map.getPosX()).substring(map.getPosY() + 1);
			map.getMap().set(map.getPosX(), left + "." + right);
			map.upDateExploredMap();
		}
		else if (flee == true) {
			flee = false;
			int prevposX = map.getPosX();
			int prevPosY = map.getPosY();
			map.setPosX(map.getPrevPosX());
			map.setPosY(map.getPrevPosY());
			map.setPrevPosX(prevposX);
			map.setPrevPosY(prevPosY);
			map.upDateExploredMap();
		}
		if (victory && boss){
			new EndOfTheGame((JFrame) SwingUtilities.getWindowAncestor(Game.this), this, victory);
		}
	}

	private JPanel specialZoneSetUp() {
		JPanel specialZone = new JPanel();
		specialZone.setLayout(new BorderLayout());
		specialZone.add(Box.createRigidArea(new Dimension(0, 20)));
		if (hero.getSpecialType().equals("activ")) {
			activTypeSpecial(specialZone);
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
			useSpecialButton = new JCheckBox("Use special");
			specialZone.add(useSpecialButton, BorderLayout.CENTER);
		}
		else {
			useSpecialButton = new JCheckBox("Use special");
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

	private void mapZone() {
		centerZone = new JPanel();
		int size = map.getSize();
		centerZone.setLayout(new GridLayout(size, size));
		int j = 0;
		while(j < size) {
			int i = 0;
			while (i < size) {
				if (i == map.getPosX() && j == map.getPosY()) {
					JLabel mapCase = new JLabel("X");
					mapCase.setHorizontalAlignment(SwingConstants.CENTER);
					mapCase.setVerticalAlignment(SwingConstants.CENTER);
					mapCase.setOpaque(true);
					mapCase.setBackground(new Color(204,255,204));
					mapCase.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
					centerZone.add(mapCase);
				}
				else if (trackerCase(i, j) && map.getExploredMap().get(i).charAt(j) == ' ') {
					JLabel mapCase = new JLabel("" + map.getMap().get(i).charAt(j));
					mapCase.setHorizontalAlignment(SwingConstants.CENTER);
					mapCase.setVerticalAlignment(SwingConstants.CENTER);
					mapCase.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
					centerZone.add(mapCase);
				}
				else if (map.getExploredMap().get(i).charAt(j) != ' '){
					JLabel mapCase = new JLabel("" + map.getExploredMap().get(i).charAt(j));
					mapCase.setHorizontalAlignment(SwingConstants.CENTER);
					mapCase.setVerticalAlignment(SwingConstants.CENTER);
					mapCase.setOpaque(true);
					mapCase.setBackground(Color.lightGray);
					mapCase.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
					centerZone.add(mapCase);
				}
				else {
					centerZone.add(new JLabel(" "));
				}
				i++;
			}
			j++;
		}
		centerZone.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}

	private boolean trackerCase(int x, int y) {
		int top = map.getPosY() - 1;
		int bot = map.getPosY() + 1;
		int left = map.getPosX() - 1;
		int right = map.getPosX() + 1;
		if (hero.getSpecialAttack().contains("tracker")
			&& ((x == map.getPosX() && (y == top || y == bot))
			|| (y == map.getPosY() && (x == left || x == right)))
			&& map.getExploredMap().get(x).charAt(y) != '.') {
			return (true);
		}
		return (false);
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_UP) {
			if(inCombat == false) {
				map.moveUp();
				upDateEventZone();
				updateCenterZone();
			}
		} else if (keyCode == KeyEvent.VK_DOWN) {
			if(inCombat == false) {
				map.moveDown();
				upDateEventZone();
				updateCenterZone();
			}
		} else if (keyCode == KeyEvent.VK_LEFT) {
			if(inCombat == false) {
				map.moveLeft();
				upDateEventZone();
				updateCenterZone();
			}
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			if(inCombat == false) {
				map.moveRight();
				upDateEventZone();
				updateCenterZone();
			}
		} else if (keyCode == KeyEvent.VK_SPACE) {
			if(inCombat == true) {
				try {
					combatRound();
					upDateEventZone();
					updatePlayerZone();
					if(inCombat == false) {
						updateCenterZone();
					}
				} catch (DeathException ex) {
					new EndOfTheGame((JFrame) SwingUtilities.getWindowAncestor(Game.this), this, victory);
				}
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	//getters et setters
	public Hero getHero() {
		return (this.hero);
	}
	public Mob getEnnemy() {
		return (this.ennemy);
	}
	public MainFrame getParent() {
		return (this.parent);
	}
}
