package swingy.graphicInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	private JPanel rightZone;

	public Game(Map map, DisplayHeroStats displayHeroStats) {
		this.map = map;
		this.hero = map.getHero();
		this.displayHeroStats = displayHeroStats;
		setLayout(new BorderLayout());
		playerZone();
		mapZone();
		add(leftZone, BorderLayout.WEST);
		add(rightZone, BorderLayout.CENTER);
	}

	private void upDateRightZone() {
		remove(rightZone);
		mapZone();
		add(rightZone, BorderLayout.CENTER);
		validate();
		repaint();
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
		goLeftButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if we are not in event
				map.moveLeft();
				upDateRightZone();
			}
		});
		goLeftPanel.add(goLeftButton);
		goLeftPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

		JPanel goRightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton goRightButton = new JButton("→");
		goRightButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if we are not in event
				map.moveRight();
				upDateRightZone();
			}
		});
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
		goTopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if we are not in event
				map.moveUp();
				upDateRightZone();
			}
		});
		goBotButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if we are not in event
				map.moveDown();
				upDateRightZone();
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

	private void mapZone() {
		rightZone = new JPanel();
		int size = map.getSize();
		rightZone.setLayout(new GridLayout(size, size));
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
					rightZone.add(mapCase);
				}
				else if (trackerCase(i, j)) {
					JLabel mapCase = new JLabel("" + map.getMap().get(i).charAt(j));
					mapCase.setHorizontalAlignment(SwingConstants.CENTER);
					mapCase.setVerticalAlignment(SwingConstants.CENTER);
					mapCase.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
					rightZone.add(mapCase);
				}
				else if (map.getExploredMap().get(i).charAt(j) != ' '){
					JLabel mapCase = new JLabel("" + map.getExploredMap().get(i).charAt(j));
					mapCase.setHorizontalAlignment(SwingConstants.CENTER);
					mapCase.setVerticalAlignment(SwingConstants.CENTER);
					mapCase.setOpaque(true);
					mapCase.setBackground(Color.lightGray);
					mapCase.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
					rightZone.add(mapCase);
				}
				else {
					rightZone.add(new JLabel(" "));
				}
				i++;
			}
			j++;
		}
		rightZone.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
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

}
