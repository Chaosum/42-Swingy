package swingy.graphicInterface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swingy.character.hero.Hero;

public class EndOfTheGame extends JDialog {
	MainFrame parentParent;
	Game parent;

	Hero hero;

	public EndOfTheGame (JFrame parentFrame, MainFrame parentParent, Game parent, boolean victory) {
		this.parentParent = parentParent;
		this.parent = parent;
		this.hero = parent.getHero();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 100);
		setLocationRelativeTo(parentFrame);
		setUndecorated(true);
		setResizable(false);
		if (victory){
			winCase();
		}
		else {
			loseCase();
		}
		setVisible(true);
	}

	private void 	winCase() {
		JPanel winScreen = new JPanel();
		winScreen.setLayout(new BoxLayout(winScreen, BoxLayout.Y_AXIS));
		JLabel youWinLabel = new JLabel("You win !");
		youWinLabel.setFont(new Font("Arial", Font.BOLD, 28));
		JButton nextLevelButton = new JButton("Next level");
		nextLevelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveHero();
				dispose();
				parentParent.enterTheDungeon(hero);
			}
		});
		JButton mainMenuButton = new JButton("Main menu");
		mainMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveHero();
				dispose();
				parentParent.mainMenu();
			}
		});
		winScreen.add(youWinLabel);
		winScreen.add(nextLevelButton);
		winScreen.add(mainMenuButton);
		add(winScreen);
	}

	private	void	saveHero() {
		//save hero here
	}

	private void 	loseCase() {
		JPanel winScreen = new JPanel();
		winScreen.setLayout(new BoxLayout(winScreen, BoxLayout.Y_AXIS));
		JLabel youLoseLabel = new JLabel("You lose !");
		youLoseLabel.setFont(new Font("Arial", Font.BOLD, 28));
		JButton retryButton = new JButton("Retry ?");
		retryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveHero();
				dispose();
				parentParent.enterTheDungeon(hero);
			}
		});
		JButton mainMenuButton = new JButton("Main menu");
		mainMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				parentParent.mainMenu();
			}
		});
		winScreen.add(youLoseLabel);
		winScreen.add(retryButton);
		winScreen.add(mainMenuButton);
		add(winScreen);
	}

}
