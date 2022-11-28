package View;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import Model.Dungeon;
import Model.DungeonRoom;
import Model.Hero;
import Model.HeroFactory;
import Model.Item;


public class View {
	
	private static final Scanner INPUT = new Scanner(System.in);
	/**
	 * Displays the text instructions for the user to play the game
	 */
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
	/**
	 * Allows the user to either create a new game or load a previously saved game
	 * @return true or false
	 */
	public boolean displayMainMenu() {
		System.out.println("Would you like to create a new game(create) or load a save game(load):");
		String answer = INPUT.next();
		
		while(!(answer.equalsIgnoreCase("create") || answer.equalsIgnoreCase("load"))) {
			System.out.println("PLease enter a valid option");
			answer = INPUT.next();
		}
		
		if(answer.equalsIgnoreCase("create")) {
			return true;
		}
		
		return false;
	}
	/**
	 * Display's the loaded save game files that exist and prompts the user to select a game
	 * @return save
	 */
	public String displayLoadSave() {
		String currDirectory = System.getProperty("user.dir");
		String saveGameDir = currDirectory + "\\SaveGames";
		File folder = new File(saveGameDir);
		File[] listOfFiles = folder.listFiles();
		System.out.println("\nList of save Games:");

		for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
				String file = listOfFiles[i].getName();
			    System.out.println(file.substring(0, file.length() - 4));
			  } 
		}
		
		System.out.println("\nEnter the name of a save you want to load:");
		String save = INPUT.next();
		save = saveGameDir + "\\" + save + ".ser";
		File tmpDir = new File(save);
		while(!tmpDir.exists()) {
			System.out.println("Enter a valid save game:");
			save = INPUT.next();
			save = saveGameDir + "\\" + save + ".ser";
			tmpDir = new File(save);
		}
		
		
		return save;
	}
	/**
	 * User will enter what they want their save to be named
	 * @return SaveGame
	 */
	public String displaySaveGame() {
		System.out.println("\nWhat do you want the name of the save to be?");
		return INPUT.next();
	}
	/**
	 * User has the option to overwrite file if they have entered a new name that already exists
	 * @return OverWrite
	 */
	public String displayOverWrite() {
		System.out.println("\nWould you like to overwrite this file(yes or no):");
		String answer = INPUT.next();
		
		while(!(answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no"))) {
			System.out.println("Not a valid input");
			answer = INPUT.next();
		}
		
		return answer;
	}
	/**
	 * User will enter the name of their hero
	 * @return heroName
	 */
	public Hero heroName() {
		System.out.println("What do you want your hero's name to be:");
		String name = INPUT.next();
			
		return heroSelection(name);	
	}
	/**
	 * User enters the name of the class they wish to select and the class is taken from the HeroFactory database
	 * @param theName
	 * @return
	 */
	public static Hero heroSelection(final String theName) {
		System.out.println("What hero class do you want to be (Warrior, Thief, berserker, or Priestess):");
		
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
	/**
	 * Displays the room the user is currently in
	 * @param theHero
	 */
	public void displayCurrRoom(final Hero theHero) {
		System.out.println("\n\nCurrent Room:\n");
		System.out.println(theHero.getCurrRoom().toString());
	}
	/**
	 * Prompts the user with the only possible directions they can travel in the current room
	 * @param theRoom
	 * @return getMoveOption
	 */
	public String displayMoveOptions(final DungeonRoom theRoom) {
		 StringBuilder sb = new StringBuilder();
		 sb.append("\nYou look around the room and see that there are doors to go");
		 String[] options = new String[4];
		 int i = 0;
		 
		 if(theRoom.isNorth()) {
			 options[i++] = "up";
			 sb.append(" Up");
			 if(theRoom.isWest() || theRoom.isEast() || theRoom.isSouth()) {
				 sb.append(" or");
			 }
		 }
		 
		 if(theRoom.isWest()) {
			 options[i++] = "left";
			 sb.append(" Left");
			 if(theRoom.isEast() || theRoom.isSouth()) {
				 sb.append(" or");
			 }
		 }
		 
		 if(theRoom.isEast()) {
			 options[i++] = "right";
			 sb.append(" Right");
			 if(theRoom.isSouth()) {
				 sb.append(" or");
			 }
		 }
		 
		 if(theRoom.isSouth()) {
			 options[i] = "down";
			 sb.append(" Down");
		 }
		 
		 System.out.println(sb.toString());
		 
		 return getMoveOption(theRoom, options);
	}
	/**
	 * Prompts the user by asking which way they would like to travel
	 * @param theRoom
	 * @param theOptions
	 * @return direction
	 */
	public String getMoveOption(final DungeonRoom theRoom, final String[] theOptions) {
		System.out.println("Which way would you like to go:");
		String direction = INPUT.next();
		direction.toLowerCase();
		
		
		while(!(Arrays.asList(theOptions).contains(direction))) {
			System.out.println("Invalid direction please enter another");
			direction = INPUT.next();
			direction.toLowerCase();
		}
		
		return direction;
		
	}
	/**
	 * Displays the options the user has which includes moving, viewing inventory and saving game and exiting
	 * @param theString
	 */
	public void displayText(final String theString) {
		System.out.println(theString);
	}
	
	public boolean displayOptions(final Hero theHero, final Dungeon theDungeon) {
		System.out.println("\nWould you like move on(move), or View inventory(inventory), or Save game and exit(save):");
		String choice = INPUT.next();
		
		if(choice.equalsIgnoreCase("inventory")) {
			System.out.println(theHero.toString());
			displayInventory(theHero, theDungeon);
			return false;
		} else if(choice.equalsIgnoreCase("dungeon")) {
			System.out.println("\nDungeon:\n" + theDungeon.toString());
			return displayOptions(theHero, theDungeon);
		} else if(choice.equalsIgnoreCase("move")) {
			return false;
		} else if(choice.equalsIgnoreCase("save")) {
			return true;
		} else {
			System.out.println("\nNot a valid option. Please enter a valid option\n");
			return displayOptions(theHero, theDungeon);
		}
	}
	/**
	 * The options for the hero to attack
	 * @return
	 */
	public String displayHeroAttacks() {
		System.out.println("You have a chance to attack would you like to use normal attack(enter normal) or special attack(enter special): ");
		String attack = INPUT.next();
		while(!(attack.equalsIgnoreCase("normal") || attack.equalsIgnoreCase("special"))) {
			System.out.println("Please enter valid attack option:");
			attack = INPUT.next();
		}

		return attack;
	}
	/**
	 * Displays the current inventory of items the user has
	 * @param theHero
	 * @param theDungeon
	 */
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
	/**
	 * User can type whether they would like to quit the game or restart the game
	 * @return true or false
	 */
	public boolean displayKeepPlayingOptions() {
		System.out.println("Do you want to restart(restart) or would you like to quit(quit)?");
		String answer = INPUT.next();
		
		while(!(answer.equalsIgnoreCase("restart") || answer.equalsIgnoreCase("quit"))) {
			System.out.println("Not a valid option please enter a new one");
			answer = INPUT.next();
		}
		
		if(answer.equalsIgnoreCase("restart")) {
			return true;
		}
		
		return false;
	}
	/**
	 * Displays where the hero is on the map currently and updates when the user moves
	 * @param theDungeon
	 * @param theHero
	 */
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
