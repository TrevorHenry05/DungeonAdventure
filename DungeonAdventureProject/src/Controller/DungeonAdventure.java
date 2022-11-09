package Controller;

import java.util.Scanner;

import Model.Dungeon;
import Model.Hero;
import View.View;

public class DungeonAdventure {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		View v = new View();
		
		//display hero creation prompt
		Hero hero = v.start();
		//create dungeon
		Dungeon dungeon = new Dungeon();
		//set heros current room
		hero.setCurrRoom(dungeon.getDungeon()[hero.getCurrX()][hero.getCurrY()]);
		//display hero info
		System.out.println("Hello " + hero.getCharacterName() + " the " + hero.getClassName() + " ,are you ready to adventure the dungeon?");
		
		while(hero.isAlive()) {
			System.out.println("Dungeon:\n");
			v.displayDungeonNearHero(dungeon, hero);
			System.out.println("Current Room:\n");
			v.displayCurrRoom(hero, dungeon);
			
		}
		
		
		in.close();
	}

	

}
