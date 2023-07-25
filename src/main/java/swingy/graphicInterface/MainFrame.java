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
		HeroSelection heroSlection = new HeroSelection();
		contentPane.add(heroSlection, BorderLayout.WEST);
		//CENTER
		//contentPane.add(displayHeroStats(), BorderLayout.CENTER); //display hero stats dans une boite de texte
		JButton playButton = new JButton("Play");
		contentPane.add(playButton, BorderLayout.SOUTH);
		//NORTH
		//contentPane.add(optionIcon(), BorderLayout.NORTH); //display une icone clickable qui ouvre une pop-up d'option
	}
}
