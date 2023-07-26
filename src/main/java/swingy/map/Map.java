package swingy.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.Data;
import swingy.character.hero.Hero;
import swingy.character.hero.SpecialIsReadyException;

@Data
public class Map {
	protected int mapLevel;
	protected int size;
	protected List<String> map;
	protected int difficulty;
	protected Hero currentHero;
	protected int posX;
	protected int posY;
	protected int advancement;


	public Map(Hero hero){
		this.currentHero = hero;
		this.mapLevel = currentHero.getLevel();
		this.size = ((this.mapLevel - 1) * 5) + 10 - (this.mapLevel % 2);
		initPosition();
		this.difficulty = 60 - (10 * this.mapLevel % 2);
		this.map = new ArrayList<String> ();
		Random rand = new Random();
		int i = 0;
		while (i < this.size) {
			int j = 0;
			while (j < this.size) {
				if (j == 0 || j == this.size - 1){
					this.map.set(i, this.map.get(i) + "B");
				}
				int event = rand.nextInt(100);
				if (event <= 100 - this.difficulty - 1) {
					this.map.set(i, this.map.get(i) + ".");
				}
				else if (event <= (100 - this.difficulty) / 2) {
					this.map.set(i, this.map.get(i) + "?");
				}
				else {
					this.map.set(i, this.map.get(i) + "M");
				}
				j++;
			}
			i++;
		}
		this.map.set(size / 2 + 1, this.map.get(size / 2 + 1) + ".");
	}

	protected void	initPosition() {
		this.posX =(size / 2) + 1;
		this.posY =(size / 2) + 1;
		this.advancement = 0;
	}

	public void moveUp() {
		this.posY -=1;
		this.upDatePos();
	}
	public void moveDown() {
		this.posY +=1;
		this.upDatePos();
	}
	public void moveLeft() {
		this.posX -=1;
		this.upDatePos();
	}
	public void moveRight() {
		this.posX +=1;
		this.upDatePos();
	}
	public void upDatePos() {   
		int xAdvancement = posX - (size / 2) + 1; 
		int yAdvancement = posY - (size / 2) + 1;
		this.advancement = (xAdvancement < 0 ? xAdvancement * -1: xAdvancement) + (yAdvancement < 0 ? yAdvancement * -1: yAdvancement);
		StringBuilder newString = new StringBuilder(map.get(posY));
		char event = map.get(posY).charAt(posX);
		if (event == 'M' || event == 'Z' || event == 'V' || event == 'S'
			|| event == 'D' || event == 'L' || event == 'O') {
			//fight here en fonction de l'avancement definir le level / le type de mob
			//if (win) set char a .
			try {
				this.currentHero.chargeUp();
			} catch (SpecialIsReadyException e) {
				// agir en fonction -> rendre le bouton disponible
			}
			newString.setCharAt(posX, '.');
			map.set(posY, newString.toString());
		}
		else if (event == '?') {
			//evenement ici -> random event (heal ou malus a voir)
			try {
				this.currentHero.chargeUp();
			} catch (SpecialIsReadyException e) {
				// agir en fonction -> rendre le bouton disponible
			}
			newString.setCharAt(posX, '.');
			map.set(posY, newString.toString());
		}
		else if (event == 'B') {
			//fight boss
			//fin de la map si win
		}
	}
}
