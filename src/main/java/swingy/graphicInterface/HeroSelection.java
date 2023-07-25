package swingy.graphicInterface;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import swingy.App;

@Data
@EqualsAndHashCode(callSuper=false)
public class HeroSelection extends JPanel {
	protected JScrollPane scrollPane;
	protected JList<String> heroList;
	public HeroSelection () {
		super();
		setLayout(new BoxLayout( this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(createLabel("Select a hero:"));// Ajouter la JList à la JScrollPane
		heroList = createHeroList();
		scrollPane = new JScrollPane(heroList);
		scrollPane.setPreferredSize(new Dimension(200, (int) (this.getHeight() * 0.7)));
		add(scrollPane);
		add(Box.createRigidArea(new Dimension(0, 10)));//spacer
		JButton newHero = setUpButton("New hero");//new button
		add(newHero);
		add(Box.createRigidArea(new Dimension(0, 10)));//spacer
	}

	private JButton setUpButton(String name) {
		JButton newHero = new JButton(name);
		
		newHero.setAlignmentX(Component.CENTER_ALIGNMENT);
		newHero.setPreferredSize(new Dimension(100, 30));

		newHero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Bouton New hero cliqué !");
				callNewHeroDialog();
			}
		});
		return (newHero);
	}

	private void callNewHeroDialog() {
		NewHeroDialog newHeroDialog = new NewHeroDialog((JFrame) SwingUtilities.getWindowAncestor(HeroSelection.this), this);
		newHeroDialog.setVisible(true);
	}

	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setAlignmentX(Component.CENTER_ALIGNMENT); // Centre le texte horizontalement
		return label;
	}

	public void upDateHeroList() {
		JList<String> heroList = createHeroList();
		heroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(heroList);
		revalidate();
		repaint();
	}

	private JList<String> createHeroList() {
		DefaultListModel<String> heroListModel = new DefaultListModel<>(); 
		JList<String> heroList = new JList<>(heroListModel);
		heroList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		try {
			Statement statement = App.modifyDatabase(App.connectToDatabase());
			ResultSet resultSet = statement.executeQuery("SELECT * FROM heros");
			while (resultSet.next()) {
				String name = resultSet.getString("name") + " " + resultSet.getString("title");
				heroListModel.addElement(name);
			}
		} catch (SQLException e) {
			System.out.println("createHero exception : " + e.getMessage());
		}
		return (heroList);
	}
}
