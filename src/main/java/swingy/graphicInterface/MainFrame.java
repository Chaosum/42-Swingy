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
	protected DisplayHeroStats displayHeroStats;
	protected HeroSelection heroSelection;

	public MainFrame() {
		super("Swingy");
		entityManagerFactory = Persistence.createEntityManagerFactory("swingypersistance");
		entityManager = entityManagerFactory.createEntityManager();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000 , 500);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		JPanel contentPane = (JPanel) this.getContentPane();
		//WEST
		heroSelection = new HeroSelection();
		contentPane.add(heroSelection, BorderLayout.WEST);
		//CENTER
		displayHeroStats = new DisplayHeroStats(heroSelection.getHeroList().getSelectedValue()); // a voir pour mettre un listener sur le heroselection
		heroSelection.setDisplayHeroStats(displayHeroStats);
		contentPane.add(displayHeroStats, BorderLayout.CENTER); //display hero stats dans une boite de texte
		//SOUTH
		JButton playButton =  new JButton("Play");
		contentPane.add(playButton, BorderLayout.SOUTH);
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				enterTheDungeon();
			}
		});
		//NORTH
		//contentPane.add(optionIcon(), BorderLayout.NORTH); //display une icone clickable qui ouvre une pop-up d'option
	}
	public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

	private void enterTheDungeon(){
		Hero hero = this.displayHeroStats.getHero();
		Map map = new Map(hero);
	}
}
