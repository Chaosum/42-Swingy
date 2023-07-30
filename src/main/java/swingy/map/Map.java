package swingy.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.Data;
import swingy.character.hero.Hero;

@Data
public class Map {
	private int mapLevel;
	private int size;
	private List<String> map;
	private List<String> exploredMap;
	private int difficulty;
	private Hero hero;
	private int posX;
	private int posY;
	private int prevPosX;
	private int prevPosY;
	private int advancement;

	public Map(Hero hero){
		this.hero = hero;
		this.mapLevel = hero.getLevel();
		this.size = ((this.mapLevel - 1) * 5) + 10 - (this.mapLevel % 2);
		initPosition();
		this.difficulty = 60 - (10 * this.mapLevel / 2);
		this.map = new ArrayList<String> (this.size);
		Random rand = new Random();
		int i = 0;
		while (i < this.size) {
			int j = 0;
			map.add("");
			while (j < this.size) {
				if ((j == 0 || j == this.size - 1) || (i == 0 || i == this.size - 1)) {
					this.map.set(i, this.map.get(i) + "B");
				}
				else {
					int event = rand.nextInt(100);
					if (event > (100 - this.difficulty - 1)) {
						this.map.set(i, this.map.get(i) + ".");
					}
					else if (event >= (100 - this.difficulty) / 3) {
						this.map.set(i, this.map.get(i) + "M");
					}
					else {
						this.map.set(i, this.map.get(i) + "?");
					}
				}
				j++;
			}
			i++;
		}
		String left = this.map.get(size / 2).substring(0, size / 2);
		String right = this.map.get(size / 2).substring((size / 2) + 1);
		map.set(size / 2, left + "." + right);
		System.out.println(map);
		initExploredMap();
	}

	private void	initPosition() {
		this.posX =(size / 2);
		this.posY =(size / 2);
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.advancement = 0;
	}

	private void initExploredMap() {
		exploredMap = new ArrayList<String>(this.size);

		int i = 0;
		while (i < this.size) {
			int j = 0;
			exploredMap.add("");
			while (j < this.size) {
				if (i == posX && j == posY) {
					this.exploredMap.set(i, this.exploredMap.get(i) + ".");
				}
				else {
					this.exploredMap.set(i, this.exploredMap.get(i) + " ");
				}
				j++;
			}
			i++;
		}
		System.out.println(this.exploredMap);
	}

	public void upDateExploredMap() {
		if (this.exploredMap.get(posX).charAt(posY) != '.') {
			String left = this.exploredMap.get(posX).substring(0, posY);
			String right = this.exploredMap.get(posX).substring(posY + 1);
			this.exploredMap.set(posX, left + "." + right);

		}
		else if (this.exploredMap.get(prevPosX).charAt(prevPosY) != '.') { //flee case
			String left = this.exploredMap.get(prevPosX).substring(0, prevPosY);
			char middle = this.map.get(prevPosX).charAt(prevPosY);
			String right = this.exploredMap.get(prevPosX).substring(prevPosY + 1);
			this.exploredMap.set(prevPosX, left + middle + right);
		}
	}

	public void moveUp() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.posY -=1;
		if (posY < 0)
			posY++;
	}
	public void moveDown() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.posY +=1;
		if (posY /size > 0)
			posY--;
	}
	public void moveLeft() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.posX -=1;
		if (posX < 0)
			posX++;
	}
	public void moveRight() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.posX +=1;
		if (posX / size > 0)
			posX--;
	}
	public char upDatePos() {
		char event = map.get(posY).charAt(posX);
		//String left = this.map.get(posX).substring(0, posY);
		//String right = this.map.get(posX).substring(posY + 1);
		//map.set(posY, left + "." + right);
		return (event);
	}
}
