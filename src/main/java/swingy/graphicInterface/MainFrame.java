package swingy.graphicInterface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import swingy.character.hero.Hero;
import swingy.map.Map;

@Data
@EqualsAndHashCode(callSuper=false)
public class MainFrame extends JFrame {
	private static EntityManagerFactory entityManagerFactory;
	public static EntityManager entityManager;
	private DisplayHeroStats displayHeroStats;
	private HeroSelection heroSelection;
	private JPanel windowsPane;
	private Game game;

	public MainFrame() {
		super("Swingy");
		entityManagerFactory = Persistence.createEntityManagerFactory("swingypersistance");
		entityManager = entityManagerFactory.createEntityManager();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000 , 500);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		windowsPane = (JPanel) this.getContentPane();
		mainMenu();
	}
	public static void closeEntityManagerFactory() {
		if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
        }
    }
	
	public void mainMenu() {
		windowsPane.removeAll();
		//WEST
		heroSelection = new HeroSelection();
		windowsPane.add(heroSelection, BorderLayout.WEST);
		//CENTER
		displayHeroStats = new DisplayHeroStats(heroSelection.getHeroList().getSelectedValue());
		heroSelection.setDisplayHeroStats(displayHeroStats); // pour le listener
		windowsPane.add(displayHeroStats, BorderLayout.CENTER);
		//SOUTH
		JButton playButton =  new JButton("Play");
		windowsPane.add(playButton, BorderLayout.SOUTH);
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (heroSelection.getHeroList().getSelectedValue() != null) {
					enterTheDungeon();
				}
			}
		});
		//NORTH
		//windowsPane.add(optionIcon(), BorderLayout.NORTH); //display une icone clickable qui ouvre une pop-up d'option
		validate();
		repaint();
	}
	
	private void enterTheDungeon(){
		Hero hero = this.displayHeroStats.getHero();
		Map map = new Map(hero);
		windowsPane.removeAll();
		game = new Game(map, displayHeroStats, this);
		windowsPane.add(game, BorderLayout.CENTER);
		validate();
		repaint();
	}

}
