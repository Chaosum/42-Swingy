package swingy.graphicInterface;

import java.awt.BorderLayout;
import java.awt.MenuBar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
		this.setLocation(null);
		this.setLayout(new BorderLayout());
		JPanel contentPane = (JPanel) this.getContentPane();

		contentPane.add(createHeroList(), BorderLayout.WEST);
		JButton newHero = new JButton("Create new hero");
		contentPane.add(newHero, BorderLayout.WEST);
		//contentPane.add(displayHeroStats(), BorderLayout.CENTER); //display hero stats dans une boite de texte
		JButton playButton = new JButton("Play");
		contentPane.add(playButton, BorderLayout.CENTER);
		//contentPane.add(optionIcon(), BorderLayout.NORTH); //display une icone clickable qui ouvre une pop-up d'option
	}

	private JComboBox<String> createHeroList() {
		JComboBox<String> heroList = new JComboBox<String>();
		
		return (heroList);
	}

}
