package swingy.graphicInterface;

import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import swingy.App;

@Data
@EqualsAndHashCode(callSuper=false)
public class MainFrame extends JFrame {
	public MainFrame() {
		super("Swingy");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500, 500);
		//this.setLocation(null);
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
		try {
			Statement statement = App.modifyDatabase(App.connectToDatabase());
			ResultSet resultSet = statement.executeQuery("SELECT * FROM heros");
			String name = resultSet.getString("name") + " " + resultSet.getString("title");
			heroList.addItem(name);
			while (resultSet.next()) {
				name = resultSet.getString("name") + " " + resultSet.getString("title");
				heroList.addItem(name);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return (heroList);
	}

}
