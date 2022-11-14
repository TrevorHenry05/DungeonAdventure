package View;

import java.util.Scanner;

import Model.Dungeon;
import Model.DungeonRoom;
import Model.Hero;
import Model.HeroFactory;
import Model.Monster;


public class View {
	
	private static final Scanner INPUT = new Scanner(System.in);
	
	public void displayDungeonNearHero(final Dungeon theDungeon, final Hero theHero) {
		StringBuilder sb = new StringBuilder();
		int x, y;
		if((theHero.getCurrX() - 1) >= 0) {
			x = theHero.getCurrX() - 1;
		} else {
			x = 0;
		}
		
		if((theHero.getCurrY() - 1) >= 0) {
			y = theHero.getCurrY() - 1;
		} else {
			y = 0;
		}
		
		for(int i = x; ((i < theDungeon.getDungeon().length) && (i < theHero.getCurrX() + 2)); i ++) {
			StringBuilder row1 = new StringBuilder();
			StringBuilder row2 = new StringBuilder();
			StringBuilder row3 = new StringBuilder();
			for(int j = y; ((j < theDungeon.getDungeon()[i].length) && (j < theHero.getCurrY() + 2)); j++) {
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
	
	public String displayHeroAttacks() {
		System.out.println("You have a chance to attack would you like to use normal attack(enter normal) or special attack(enter special): ");
		String attack = INPUT.next();
		while(!(attack.equalsIgnoreCase("normal") || attack.equalsIgnoreCase("special"))) {
			System.out.println("Please enter valid attack option:");
			attack = INPUT.next();
		}

		return attack;
	}

	
	public Hero start() {
		
		System.out.println("~~ Welcome to Dungeon Adventure ~~");
		System.out.println("What do you want your hero's name to be:");
		String name = INPUT.next();
			
		return heroSelection(name);	
	}
	
	public static Hero heroSelection(final String theName) {
		System.out.println("What hero class do you want to be (Warrior, Thief, or Priestess):");
		
		String heroType = INPUT.next();
		
		
		HeroFactory hf = new HeroFactory();		
		Hero hero;
		
		try {
			hero = hf.createHero(heroType, theName);
		} catch(IllegalArgumentException e) {
			System.out.println("Not a proper Hero type entered, try again.");
			hero = heroSelection(theName);
		}
		
		return hero;
	}
	
	public void displayCurrRoom(final Hero theHero) {
		System.out.println("\n\nCurrent Room:\n");
		System.out.println(theHero.getCurrRoom().toString());
	}
	
	public String displayMoveOptions(final DungeonRoom theRoom) {
		 StringBuilder sb = new StringBuilder();
		 sb.append("You look around the room and see that there are doors to go");
		 
		 if(theRoom.isNorth()) {
			 sb.append(" Up");
			 if(theRoom.isWest()) {
				 sb.append(" or");
			 }
		 }
		 
		 if(theRoom.isWest()) {
			 sb.append(" Left");
			 if(theRoom.isEast()) {
				 sb.append(" or");
			 }
		 }
		 
		 if(theRoom.isEast()) {
			 sb.append(" Right");
			 if(theRoom.isSouth()) {
				 sb.append(" or");
			 }
		 }
		 
		 if(theRoom.isSouth()) {
			 sb.append(" Down");
		 }
		 
		 System.out.println(sb.toString());
		 
		 return getMoveOption(theRoom);
	}
	
	public String getMoveOption(final DungeonRoom theRoom) {
		System.out.println("Which way would you like to go:");
		String direction = INPUT.next();
		
		if(!(direction.equalsIgnoreCase("up") || direction.equalsIgnoreCase("down") || direction.equalsIgnoreCase("left") || direction.equalsIgnoreCase("right"))) {
			System.out.println("Invalid direction please enter another");
			return getMoveOption(theRoom);
		}
		
		return direction;
		
	}
	
	public void displayText(final String theString) {
		System.out.println(theString);
	}
	
}
