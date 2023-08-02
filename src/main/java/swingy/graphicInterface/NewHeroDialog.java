package swingy.graphicInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import swingy.character.hero.Hero;
import swingy.character.hero.HeroCreator;
import swingy.character.hero.WrongClassException;

public class NewHeroDialog extends JDialog {
	@Pattern(regexp = "^[a-zA-Z][a-z]*$", message = "Le texte peu commencer par une majuscule et contenir uniquement des lettres minuscules.")
	@Length(min = 2, max = 20, message = "Le texte doit contenir entre 2 et 20 caractères.")
	protected String heroName;
	protected JTextField name;
	protected JCheckBox paladin;
	protected JCheckBox mage;
	protected JCheckBox archer;
	protected JLabel errorLabel;
	protected HeroSelection parentPanel;

	public NewHeroDialog(JFrame parentFrame, HeroSelection parentPanel) {
		super(parentFrame, "New Hero", false); // true indique que la boîte de dialogue est modale
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300, 200);
		setLocationRelativeTo(parentFrame);
		setResizable(false);
		setModal(true);
		errorLabel = new JLabel("");
		this.parentPanel = parentPanel;
		JPanel newHeroForm = formPanel();
		add(newHeroForm);
	}

	private JPanel formPanel() {
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new BoxLayout( formPanel, BoxLayout.Y_AXIS));
		name = new JTextField("Name");
		name.setMaximumSize(new Dimension(1000, 40));
		ButtonGroup classChoice = new ButtonGroup();
		paladin = new JCheckBox("paladin");
		mage = new JCheckBox("mage");
		archer = new JCheckBox("archer");
		classChoice.add(paladin);
		classChoice.add(mage);
		classChoice.add(archer);
		paladin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mage.setSelected(false);
				archer.setSelected(false);
			}
		});
		mage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paladin.setSelected(false);
				archer.setSelected(false);
			}
		});
		archer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paladin.setSelected(false);
				mage.setSelected(false);
			}
		});
		JButton create = new JButton("Submit");
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				heroName = name.getText();
				ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
				Validator validator = factory.getValidator();
				Set<ConstraintViolation<NewHeroDialog>> violations = validator.validate(NewHeroDialog.this);
				if (violations.isEmpty()) {
					try {
						if (paladin.isSelected()) {
							System.out.println("Test creation classe");
							Hero newHero = HeroCreator.create("paladin", heroName);
							HeroCreator.saveHeroToDatabase(newHero, false);
						}
						else if (mage.isSelected()) {
							Hero newHero = HeroCreator.create("mage", heroName);
							HeroCreator.saveHeroToDatabase(newHero, false);
						}
						else if (archer.isSelected()) {
							Hero newHero = HeroCreator.create("archer", heroName);
							HeroCreator.saveHeroToDatabase(newHero, false);
						}
						else {
							throw new WrongClassException();
						}
						dispose();
						parentPanel.upDateHeroList();
					} catch (WrongClassException error) {
						errorLabel.setText("Wrong class");
						revalidate();
						repaint();
					} catch (EntityExistsException error) {
						errorLabel.setText("Name already taken");
						revalidate();
						repaint();
					}
				} else { // Les contraintes ne sont pas valides, traitez les violations
					StringBuilder errorMessage = new StringBuilder();
					for (ConstraintViolation<NewHeroDialog> violation : violations) {
						errorMessage.append(violation.getMessage()).append("\n");
					}
					errorLabel.setText("Wrong Name format [a-zA-Z][a-z]");
					revalidate();
					repaint();
				}
			}
		});
		paladin.setAlignmentX(javax.swing.Box.CENTER_ALIGNMENT); 
		mage.setAlignmentX(javax.swing.Box.CENTER_ALIGNMENT); 
		archer.setAlignmentX(javax.swing.Box.CENTER_ALIGNMENT); 
		formPanel.add(errorLabel);
		formPanel.add(name);
		formPanel.add(paladin);
		formPanel.add(mage);
		formPanel.add(archer);
		formPanel.add(create);
		return (formPanel);
	}
}