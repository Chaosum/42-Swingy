package swingy.view.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityManager;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import swingy.model.hero.Hero;

public class ConfirmChoiseDialog extends JDialog {

	private HeroSelection parentPanel;
	
	public ConfirmChoiseDialog(JFrame parentFrame, HeroSelection parentPanel) {
		this.parentPanel = parentPanel;
		setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 200);
		setLocationRelativeTo(parentFrame);
		setResizable(false);
		setModal(true);
		JLabel title = new JLabel("Confirm delete choice : ");
		add(title, BorderLayout.NORTH);
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(cancel, BorderLayout.WEST);
		JButton confirm = new JButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteHero();
				dispose();
			}
		});
		add(confirm, BorderLayout.CENTER);

	}

	private void deleteHero(){
		String heroNameWithTitle = parentPanel.getHeroList().getSelectedValue();
		String heroName = heroNameWithTitle.split(" ")[0];
		EntityManager entityManager = MainFrame.entityManager;
		try {
			
			entityManager.getTransaction().begin();
			Hero heroToDelete = entityManager.find(Hero.class, heroName);
			if (heroToDelete != null) {
				entityManager.remove(heroToDelete);
			}
			else {
				System.out.println("Hero not found");
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Deleting failed");
		} finally {
			parentPanel.upDateHeroList();
		}
	}
}
