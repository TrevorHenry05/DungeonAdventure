package View;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import Model.Dungeon;
import Model.DungeonRoom;
import Model.Hero;
import Model.HeroFactory;
import Model.Item;

/**
 * A Class that builds and displays propmpts to the user in the Console and collects input from the user through the console.
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
public class View {
	/**
	 * A Global scanner variable to be used
	 */
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
	 * The main menu for the game, prompts the user to create a new save file or load a saved file
     * @return a boolean, true if user picks create and false for load
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
	 * Display's the save game files that exist and prompts the user to enter a save game until they have entered a valid one.
	 * @return A string that represents the file the user wants to load
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
	 * Prompts user for a file name for the save and gets file name.
	 * @return String representation of the name of the save file the user specified
	 */
	public String displaySaveGame() {
		System.out.println("\nWhat do you want the name of the save to be?");
		return INPUT.next();
	}
	
	/**
	 * User has the option to overwrite file if they have entered a save file that already exists
	 * @return yes or no based of if the user wanted to overwrite the current file or not
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
	 * Takes in how many rows the user wants to be in the dungeon and stores it in the rows variable	
	 * @return the user chosen  number of rows that the dungeon will have
	 */	
	public int displayRows() {
		int rows;
		
		while(true) {
		    try {
		        System.out.println("\nHow many rows do you want the dungeon to be: ");
		        rows = Integer.parseInt(INPUT.next());
		        break;
		    }
		    catch(NumberFormatException e) {
		        System.out.println("You have not entered an Integer");
		    }
		}
		
		return rows;
	}
	/**
	 * Takes in how many columns the user wants to be in the dungeon and stores it in the cols variable	
	 * @return the user chosen number of columns the dungeon will have
	 */		
	public int displayColumns() {
		int cols;
		
		while(true) {
		    try {
		        System.out.println("\nHow many columns do you want the dungeon to be: ");
		        cols = Integer.parseInt(INPUT.next());
		        break;
		    }
		    catch(NumberFormatException e) {
		        System.out.println("You have not entered an Integer");
		    }
		}
		
		return cols;
	}
	
	/**
	 * User will enter the name of their hero, then calls the hero selection that obtains the class type they want to be.
	 * @return Hero object that the user has created
	 */
	public Hero heroName() {
		System.out.println("What do you want your hero's name to be:");
		String name = INPUT.next();
			
		return heroSelection(name);	
	}
	
	/**
	 * User enters the name of the class they wish to select and the Hero is created using the HeroFactory
	 * @param theName character name that the user wants to be called
	 * @return Hero object that the user has created
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
	 * Displays the text representation of the room the user is currently in
	 * @param theHero object
	 */
	public void displayCurrRoom(final Hero theHero) {
		System.out.println("\n\nCurrent Room:\n");
		System.out.println(theHero.getCurrRoom().toString());
	}
	
	/**
	 * Prompts the user with the only possible directions they can travel in the current room and calls the method that asks for 
	 * which direction they wish to move
	 * 
	 * @param theRoom object the Hero is in
	 * @return  a string representing the direction the user specified they wanted to move
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
	 * Prompts the user until a valid move option for the current room is entered.
	 * 
	 * @param theRoom object the Hero is in
	 * @param theOptions a String array of the only move options available for the current room
	 * @return direction string of the direction the user specifies to move
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
	 * Display the String that it is given to the console
	 * 
	 * @param theString a String that will be displayed to the user in the console
	 */
	public void displayText(final String theString) {
		System.out.println(theString);
	}
	
	/**
	 * Displays the options menu with the options "move", "inventory", and "save" and after the user specifies which option they want directs them to the prompt they want
	 * 
	 * @param theHero object
	 * @param theDungeon object
	 * @return a boolean, false if user entered move and true if user entered save
	 */
	public boolean displayOptions(final Hero theHero, final Dungeon theDungeon) {
		System.out.println("\nWould you like move on(move), or View inventory(inventory), or Save game and exit(save):");
		String choice = INPUT.next();
		
		while(!(choice.equalsIgnoreCase("move") || choice.equalsIgnoreCase("dungeon") || choice.equalsIgnoreCase("inventory") || choice.equalsIgnoreCase("save"))) {
			System.out.println("\nNot a valid option. Please enter a valid option\n");
			choice = INPUT.next();
		}
		
		if(choice.equalsIgnoreCase("inventory")) {
			System.out.println(theHero.toString());
			displayInventory(theHero, theDungeon);
			return displayOptions(theHero, theDungeon);
		} else if(choice.equalsIgnoreCase("dungeon")) {
			System.out.println("\nDungeon:\n" + theDungeon.toString());
			return displayOptions(theHero, theDungeon);
		} else if(choice.equalsIgnoreCase("move")) {
			return false;
		} else {
			return true;
		} 
	}
	
	/**
	 * The options for the hero to attack
	 * 
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
	 * The options for the hero to attack. The user can enter either "normal" or "special" for the attacks. If neither are entered, then the user will 
	 * see the "please enter valid attack option" and be prompted to enter another option
	 * @return a string representation of the attack that the user has specified
	 */
	public static void displayInventory(final Hero theHero, final Dungeon theDungeon) {		
		System.out.println("\nWould you like to use an item(use) or go back to options(options):");
		String choice = INPUT.next();
		
		while(!(choice.equalsIgnoreCase("use") || choice.equalsIgnoreCase("options"))) {
			System.out.println("\nNot a valid option. Please enter a valid option\n");
			choice = INPUT.next();
		}
		
		if(choice.equalsIgnoreCase("use")) {
			System.out.println("\nWould you like to use a heal potion(heal) or a vision potion(vision):");
			String item = INPUT.next();
			if(item.equalsIgnoreCase("heal") || item.equalsIgnoreCase("vision")) {
				Item useItem = theHero.removeItemFromInventory(item);
				if(useItem != null) {
					int heroHealth = theHero.getHitPoints();
					if(theHero.useItem(useItem)) {
						System.out.println("\nAfter using the vision potion you are able to see the surrounding rooms.");
						System.out.println(theHero.displayDungeonNearHero(theDungeon));
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
		} 
	}
	
	/**
	 * User can type whether they would like to quit the game or restart the game
	 * 
	 * @return true if they wish to continue or false if they wish to quit
	 */
	public boolean displayKeepPlayingOptions() {
		System.out.println("Do you want to restart(restart) or would you like to quit(quit)?");
		String answer = INPUT.next();
		
		while(!(answer.equalsIgnoreCase("restart") || answer.equalsIgnoreCase("quit"))) {
			System.out.println("Not a valid option please enter a new one");
			answer = INPUT.next();
		}
		
		return answer.equalsIgnoreCase("restart");
	}
	
}