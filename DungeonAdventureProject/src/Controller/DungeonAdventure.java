package Controller;

import java.util.Scanner;

import Model.Dungeon;
import Model.DungeonRoom;
import Model.Hero;
import Model.Monster;
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
		System.out.println(dungeon.toString());
		//set heros current room
		hero.setCurrRoom(getHeroCurrRoom(hero,dungeon));
		//display hero info
		System.out.println("Hello " + hero.getCharacterName() + " the " + hero.getClassName() + ", are you ready to adventure the dungeon?");
		
		while(hero.isAlive() && (!(getHeroCurrRoom(hero,dungeon).isFinalRoom()) || !(hero.hasPillars()))) {
			DungeonRoom currRoom = getHeroCurrRoom(hero,dungeon);
			
			System.out.println("Current Room:\n");
			v.displayCurrRoom(hero);
			
			if(hero.getCurrRoom().isFinalRoom()) {
				System.out.println("You have found the exit, but you need to go back and find the other pillars of OO");
			}
			
			currRoom.removeItemsFromRoom(hero);
			if(!hero.isAlive()) {
				break;
			} 
					
			
			if(currRoom.isMonster()) {
				Monster monster = currRoom.getMonster();
				System.out.println("The room you entered contains a " + monster.getMonsterType());
				while(monster.isAlive() && hero.isAlive()) {
					//v.encounter(hero, monster);
					break;
				}
			}
			
			moveHero(hero,dungeon, v.displayMoveOptions(getHeroCurrRoom(hero,dungeon)));
					
		}

		if(!hero.isAlive()) {
			System.out.println("You have died....");
		} else {
			System.out.println("Good job on obtaining the pillars of OO and escaping the dungeon");
		}
		
		in.close();
	}
	
	public static DungeonRoom getHeroCurrRoom(final Hero theHero, final Dungeon theDungeon) {
		return theDungeon.getDungeon()[theHero.getCurrX()][theHero.getCurrY()];
	}

	public static void moveHero(final Hero theHero, final Dungeon theDungeon, final String theDirection) {
		
		if(theDirection.equalsIgnoreCase("up")) {
			theHero.setCurrX(theHero.getCurrX() - 1);
			theHero.setCurrRoom(theDungeon.getDungeon()[theHero.getCurrX()][theHero.getCurrY()]);
		}
		if(theDirection.equalsIgnoreCase("down")) {
			theHero.setCurrX(theHero.getCurrX() + 1);
			theHero.setCurrRoom(theDungeon.getDungeon()[theHero.getCurrX()][theHero.getCurrY()]);
		}
		if(theDirection.equalsIgnoreCase("left")) {
			theHero.setCurrY(theHero.getCurrY() - 1);
			theHero.setCurrRoom(theDungeon.getDungeon()[theHero.getCurrX()][theHero.getCurrY()]);
		} 
		if(theDirection.equalsIgnoreCase("right")) {
			theHero.setCurrY(theHero.getCurrY() + 1);
			theHero.setCurrRoom(theDungeon.getDungeon()[theHero.getCurrX()][theHero.getCurrY()]);
		} 
	}

}
