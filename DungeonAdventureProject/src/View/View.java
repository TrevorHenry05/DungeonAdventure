package View;

import java.util.Scanner;

import Model.Dungeon;
import Model.DungeonRoom;
import Model.Hero;
import Model.HeroFactory;
import Model.Item;


public class View {
	
	private static final Scanner INPUT = new Scanner(System.in);
	
	public void displayHowToPlay() {
		System.out.println("~~ Welcome to our Dungeon Adventure ~~\n");
		System.out.println("How to Play: Every time you enter a room you will be shown the items you picked up from the room or if there was a trap in the room.\n"
				+ "Then if there is a monster in the room it will begin combat with the monster. After monster is defeated you will be given two options to either move or look at inventory.\n"
				+ "As you move around the dungeon you are looking for the four pillars before you can exit (pillars: Abtraction, polymorphism, inheritance, and encapsulation).\n"
				+ "When you have found all four pillars if you make it to the exit you will have won the game. If at any point you get to 0 health or below you will have died and lose the game.");
		System.out.println("\nCombat: Based off the enemy you will be given a certain amount of attacks per round and every time your able to attack, you can either do a normal attack\n"
				+ "or a special that is unique for each class of hero, and normal and specials all have a chance to miss. After any attack that does damage to a monster they will have chance to heal.\n"
				+ "When your attacks have finished the monster will have one chance to attack you which will also have a chance to miss.");
		System.out.println("\nInventory: When you view the inventory you will be shown your health, heal/vision potions, and pillars found and you will be given the option either use an item or go back\n"
				+ "to the options menu. You are only able to use items if you have them.");
		System.out.println("\nDeveloper Options: When in the options menu enter dungeon to view the whole dungeon.\n");
	}
	
	public Hero heroName() {
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
		 sb.append("\nYou look around the room and see that there are doors to go");
		 
		 if(theRoom.isNorth()) {
			 sb.append(" Up");
			 if(theRoom.isWest() || theRoom.isEast() || theRoom.isSouth()) {
				 sb.append(" or");
			 }
		 }
		 
		 if(theRoom.isWest()) {
			 sb.append(" Left");
			 if(theRoom.isEast() || theRoom.isSouth()) {
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
		
		
		while(!(direction.equalsIgnoreCase("up") || direction.equalsIgnoreCase("down") || direction.equalsIgnoreCase("left") || direction.equalsIgnoreCase("right"))) {
			System.out.println("Invalid direction please enter another");
			direction = INPUT.next();
		}
		
		return direction;
		
	}
	
	public void displayText(final String theString) {
		System.out.println(theString);
	}
	
	public void displayOptions(final Hero theHero, final Dungeon theDungeon) {
		System.out.println("\nWould you like move on(move), or View inventory(inventory):");
		String choice = INPUT.next();
		
		if(choice.equalsIgnoreCase("inventory")) {
			System.out.println(theHero.toString());
			displayInventory(theHero, theDungeon);
		} else if(choice.equalsIgnoreCase("dungeon")) {
			System.out.println("\nDungeon:\n" + theDungeon.toString());
			displayOptions(theHero, theDungeon);
		} else if(choice.equalsIgnoreCase("move")) {
			return;
		} else {
			System.out.println("\nNot a valid option. Please enter a valid option\n");
			displayOptions(theHero, theDungeon);
		}
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
	
	public static void displayInventory(final Hero theHero, final Dungeon theDungeon) {		
		System.out.println("\nWould you like to use an item(use) or go back to options(options):");
		String choice = INPUT.next();
		View v = new View();
		
		if(choice.equalsIgnoreCase("use")) {
			System.out.println("\nWould you like to use a heal potion(heal) or a vision potion(vision):");
			String item = INPUT.next();
			if(item.equalsIgnoreCase("heal") || item.equalsIgnoreCase("vision")) {
				Item useItem = theHero.removeItemFromInventory(item);
				if(useItem != null) {
					int heroHealth = theHero.getHitPoints();
					if(theHero.useItem(useItem)) {
						System.out.println("\nAfter using the vision potion you are able to see the surrounding rooms.");
						v.displayDungeonNearHero(theDungeon, theHero);
						displayInventory(theHero, theDungeon);
					} else {
						System.out.println("The heal potion healed you for " + (theHero.getHitPoints() - heroHealth) + " . Current health is " + theHero.getHitPoints() + "\n");
						displayInventory(theHero, theDungeon);
					}
				} else {
					System.out.println("No item to use.");
					displayInventory(theHero, theDungeon);
				}
			} else {
				System.out.println("\nEnter a valid item to use");
				displayInventory(theHero, theDungeon);
			}
		} else if(choice.equalsIgnoreCase("options")) {
			
			v.displayOptions(theHero, theDungeon);
		} else {
			System.out.println("\nNot a valid option. Please enter a valid option\n");
			displayInventory(theHero, theDungeon);
		}
	}
	
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
					row2.append(theDungeon.getDungeon()[i][j].getRoom()[1][1]);
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
	
}
