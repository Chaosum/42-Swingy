package swingy.graphicInterface;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class MainFrame extends JFrame {

	public MainFrame() {
		super("Swingy");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		JPanel contentPane = (JPanel) this.getContentPane();
		//WEST
		HeroSelection heroSelection = new HeroSelection();
		contentPane.add(heroSelection, BorderLayout.WEST);
		//CENTER
		DisplayHeroStats displayHeroStats = new DisplayHeroStats(heroSelection.getHeroList()); // a voir pour mettre un listener sur le heroselection
		contentPane.add(displayHeroStats, BorderLayout.CENTER); //display hero stats dans une boite de texte
		JButton playButton = new JButton("Play");
		contentPane.add(playButton, BorderLayout.SOUTH);
		//NORTH
		//contentPane.add(optionIcon(), BorderLayout.NORTH); //display une icone clickable qui ouvre une pop-up d'option
	}
}
