package View;

import java.util.Scanner;

import Model.Dungeon;
import Model.Hero;
import Model.HeroFactory;
import Model.Monster;


public class View {
	
	public void displayDungeonNearHero(final Dungeon theDungeon, final Hero theHero) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = theHero.getCurrX() - 1; ((i < theDungeon.getDungeon().length) && (i < theHero.getCurrX() + 2)); i ++) {
			StringBuilder row1 = new StringBuilder();
			StringBuilder row2 = new StringBuilder();
			StringBuilder row3 = new StringBuilder();
			for(int j = theHero.getCurrY() - 1; ((j < theDungeon.getDungeon()[i].length) && (j < theHero.getCurrY() + 2)); j++) {
				row1.append(theDungeon.getDungeon()[i][j].getRoom()[0][0]);
				row1.append(theDungeon.getDungeon()[i][j].getRoom()[0][1]);
				row1.append(theDungeon.getDungeon()[i][j].getRoom()[0][2]);
				if((i == theHero.getCurrX()) && (j == theHero.getCurrY())) {
					row2.append(theDungeon.getDungeon()[i][j].getRoom()[1][0]);
					row2.append(theDungeon.getDungeon()[i][j].getRoom()[1][1]);
					row2.append(theDungeon.getDungeon()[i][j].getRoom()[1][2]);
				} else {
					row2.append(theDungeon.getDungeon()[i][j].getRoom()[1][0]);
					row2.append(' ');
					row2.append(theDungeon.getDungeon()[i][j].getRoom()[1][2]);
				}
				
				row3.append(theDungeon.getDungeon()[i][j].getRoom()[2][0]);
				row3.append(theDungeon.getDungeon()[i][j].getRoom()[2][1]);
				row3.append(theDungeon.getDungeon()[i][j].getRoom()[2][2]);				
			}
			
			sb.append(row1.toString());
			sb.append(System.lineSeparator());
			sb.append(row2.toString());
			sb.append(System.lineSeparator());
			sb.append(row3.toString());
			sb.append(System.lineSeparator());
		}
		
		System.out.println(sb.toString());
	}
	
	public static void incounter(final Hero theHero, final Monster theMonster) {
		
	}
	
	public Hero start() {
		Scanner in = new Scanner(System.in);
		HeroFactory hf = new HeroFactory();
		
		System.out.println("~~ Welcome to Dungeon Adventure ~~");
		System.out.println("What do you want your hero's name to be:");
		String name = in.next();
		System.out.println("What hero class do you want to be (Warrior, Thief, or Priestess):");
		String hero = in.next();
		in.close();
		
		return hf.createHero(hero, name);		
	}
	
	public void displayCurrRoom(final Hero theHero, final Dungeon theDungeon) {
		System.out.println(theDungeon.getDungeon()[theHero.getCurrX()][theHero.getCurrY()].toString());
	}
	
}
