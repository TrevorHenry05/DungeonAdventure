package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Model.Dungeon;
import Model.DungeonRoom;
import Model.DungeonSaveGame;
import Model.Hero;
import Model.Monster;
import View.View;

/**
 * A class that is the main logic of the dungeonAdventure game
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
public class DungeonAdventure {
	/**
	 * Contains main logic for the game. Entry point into the game that allows the user to play the game
	 * @param args
	 */
	public static void main(String[] args) {
		
		boolean keepPlaying = true, save = false;
		View view = new View();
		while(keepPlaying) {
			view.displayHowToPlay();
			Hero hero = null;
			Dungeon dungeon = null;
			
			
			if(view.displayMainMenu()) {
				//display hero creation prompt
				hero = view.heroName();
				//create dungeon
				dungeon = new Dungeon(view.displayRows(),view.displayColumns());
				//set heros current room
				hero.setCurrRoom(getHeroCurrRoom(hero,dungeon));
			} else {
				//get save object
				DungeonSaveGame dsg = loadSaveGame(view.displayLoadSave());
				//hero form save game
				hero = dsg.getHero();
				//dungeon from save game
				dungeon = dsg.getDungeon();
			}
			//v.displayText(dungeon.toString());
			
			//display hero info
			view.displayText("Hello " + hero.getCharacterName() + " the " + hero.getClassName() + ", are you ready to adventure the dungeon?");
			
			while(hero.isAlive() && (!(getHeroCurrRoom(hero,dungeon).isExit()) || !(hero.hasPillars()))) {
				DungeonRoom currRoom = getHeroCurrRoom(hero,dungeon);
				
				view.displayCurrRoom(hero);
				
				if(hero.getCurrRoom().isExit()) {
					view.displayText("You have found the exit, but you need to go back and find the other pillars of OO");
				}
				
				
				view.displayText(currRoom.removeItemsFromRoom(hero));		
				if(!hero.isAlive()) {
					break;
				} 
						
				
				if(currRoom.isMonster()) {
					Monster monster = currRoom.getMonster();
					view.displayText("The room you entered contains a " + monster.getMonsterType() + "\n");
					while(monster.isAlive() && hero.isAlive()) {
						encounter(hero, monster);
					}
					
					if(!monster.isAlive()) {
						view.displayText("You have slain the " + monster.getMonsterType() + "\n");
						currRoom.setMonster(null);
					} else if(!hero.isAlive()) {
						break;
					}
				}
				
				if(view.displayOptions(hero, dungeon)) {
					saveGame(dungeon,hero,view.displaySaveGame());
					save = true;
					break;
				}
				
				moveHero(hero,dungeon, view.displayMoveOptions(getHeroCurrRoom(hero,dungeon)));
						
			}
			
			if(save) {
				break;
			}
	
			if(!hero.isAlive()) {
				view.displayText("\nYou have died....");
			} else {
				view.displayText("\nGood job on obtaining the pillars of OO and escaping the dungeon");
			}
			
			view.displayText("\n\nEntire Dungeon:\n" + dungeon.toString());
			
			keepPlaying = view.displayKeepPlayingOptions();
		}
		
		view.displayText("\nThanks for playing!");
	}
	
	/**
	 * Uses the user's current x and y position within the array to determine which room they are in
	 * @param theHero
	 * @param theDungeon
	 * @return the DungeonRoom object the user is currently in
	 */
	public static DungeonRoom getHeroCurrRoom(final Hero theHero, final Dungeon theDungeon) {
		return theDungeon.getDungeon()[theHero.getCurrX()][theHero.getCurrY()];
	}
	
	/**
	 * Moves the hero in the direction the user selects. The user can enter "up" "down" "left" or "right" and the hero will move in that direction to the next DungeonRoom in that array
	 * position
	 * @param theHero users hero object
	 * @param theDungeon dungeon object that is currently being used
	 * @param theDirection the direction the user specified to move in
	 */
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
	
	/**
	 * Takes in the current Dungeon/Hero and creates a DungeonSaveGame and the name of the file the user wants, then creates a .ser file and
	 * saves the instance of the DungeonSaveGame to the file using serialization
	 * 
	 * @param theDungeon the current dungeon instance the user is playing
	 * @param theHero the current hero instance the user is using
	 * @param theFileName the file name the user wanted
	 * @throws InterruptedException 
	 */
	public static void saveGame(final Dungeon theDungeon, final Hero theHero, final String theFileName) {
		DungeonSaveGame dsg = new DungeonSaveGame(theDungeon, theHero);
		String currDirectory = System.getProperty("user.dir");
		String saveGameDir = currDirectory + "\\SaveGames";
		String saveFile = saveGameDir + "\\" + theFileName + ".ser";
		
		try {
		      File file = new File(saveFile);
		      if (!file.createNewFile()) {
		    	  View v = new View();
		    	  if(v.displayOverWrite().equalsIgnoreCase("no")) {
		    		saveGame(theDungeon,theHero,v.displaySaveGame()); 
		    		return;
		    	  } else {
		    		file.delete();
		    	  }	  
		      }
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		}
		
		try {   
            FileOutputStream file = new FileOutputStream(saveFile);
            ObjectOutputStream out = new ObjectOutputStream(file);
              
            out.writeObject(dsg);
              
            out.close();
            file.close();
  
        }
          
        catch(IOException ex) {
            System.out.println("IOException");
        }
	}
	
	/**
	 * Takes in the a user specified name of a save game they want to load, and loads the DungeonSaveGame state form the .ser file into a new DungeonSaveGame object.
	 * @param theFile use specified file name to load
	 * @return a DungeonSaveGame object that contains the Hero and Dungeon to be used
	 */
	public static DungeonSaveGame loadSaveGame(final String theFile) {
		DungeonSaveGame dsg = null;
		
		try
        {   
            FileInputStream file = new FileInputStream(theFile);
            ObjectInputStream in = new ObjectInputStream(file);
              
            dsg = (DungeonSaveGame)in.readObject();
              
            in.close();
            file.close();
        }
          
        catch(IOException e)
        {
            System.out.println("IOException");
        }
          
        catch(ClassNotFoundException e)
        {
            System.out.println("ClassNotFoundException");
        }
		
		return dsg;
	}
	
	/**
	 * Takes in a Hero object/Monster object and simulates a encounter between the Hero and Monster using User input until one is Dead
	 * @param theHero object the user is using
	 * @param theMonster object the user is fighting
	 */
	public static void encounter(final Hero theHero, final Monster theMonster) {
		double attacks = theHero.getAttackSpeed() / theMonster.getAttackSpeed();	
		View view = new View();
		
		//figure out number of hero attacks
		int heroAttacks = (int) Math.round(attacks);
		if(heroAttacks < 1) {
			theHero.setAttacks(1);
		} else {
			theHero.setAttacks(heroAttacks);
		}
		
		//Hero attacks
		while(theHero.getAttacks() > 0) {
			//Get type of attack user wants
			String attack = view.displayHeroAttacks();
			
			//if User wants a normal attack
			int monsterHealth = theMonster.getHitPoints();
			if(attack.equalsIgnoreCase("normal")) {
				//if normal succeeded
				if(theHero.attack(theMonster)) {
					view.displayText("The attack succeeded and you dealt " + (monsterHealth - theMonster.getHitPoints()) + " damage. Monsters current health " + theMonster.getHitPoints());
					monsterHealth = theMonster.getHitPoints();
					if(theMonster.isAlive()) {
						theMonster.heal();
						view.displayText("The monster attempted to heal and healed " + (theMonster.getHitPoints() - monsterHealth) + " health points. Monsters current health " + theMonster.getHitPoints() + "\n");
					} else {
						return;
					}
				//if normal failed
				} else {
					view.displayText("Your normal attack failed to land\n");
				}
			}
			
			//if user wants a special attack
			if(attack.equalsIgnoreCase("special")) {
				//if the hero is a priestess
				if(theHero.getClassName().equalsIgnoreCase("priestess")) {
					int heroHealth = theHero.getHitPoints();
					theHero.special(theHero);
					view.displayText("Your heal succeeded in healing " + (theHero.getHitPoints() - heroHealth) + " health points. Your current health is " + theHero.getHitPoints());				
				//else if hero is a thief
				}  else {
					//if special succeeded
					if(theHero.special(theMonster)) {
						view.displayText("The special attack succeeded and you dealt " + (monsterHealth - theMonster.getHitPoints()) + " damage. Monsters current health " + theMonster.getHitPoints());
						monsterHealth = theMonster.getHitPoints();
						//if monster still alive try heal
						if(theMonster.isAlive()) {
							theMonster.heal();
							view.displayText("The monster attempted to heal and healed " + (theMonster.getHitPoints() - monsterHealth) + " health points. Monsters current health " + theMonster.getHitPoints() + "\n");
						//else dead so return
						} else {
							return;
						}
					//else special failed
					} else {
						view.displayText("Your special attack failed to land");
					}
				}
			}
			
			theHero.setAttacks(theHero.getAttacks() - 1);			
		}
		
		//monsters attack
		view.displayText("\nIts the " + theMonster.getMonsterType() + " turn to attack");
		int heroHealth = theHero.getHitPoints();
		//if attack succeeded
		if(theMonster.attack(theHero)) {
			view.displayText("The " + theMonster.getMonsterType() + " succesfuly landed an attack.");
			view.displayText("You lost " + (heroHealth - theHero.getHitPoints()) + " health points. Your current health " + theHero.getHitPoints() + "\n");
		//else attack failed
		} else {
			view.displayText("The " + theMonster.getMonsterType() + " failed in landing an attack\n");
		}	
	}

}
