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
import View.ViewGUI;

public class DungeonAdventureGUI {
	public static void main(String[] args) throws InterruptedException {
		boolean keepPlaying = true, save = false;
		
		ViewGUI v = new ViewGUI();
		v.start();
		
		while(keepPlaying) {
			Hero hero = null;
			Dungeon dungeon = null;
			
			v.displayHowToPlay();
			
			if(v.displayMainMenu()) {
				hero = v.heroName();
				dungeon = new Dungeon(v.displayRows(),v.displayColumns());
				hero.setCurrRoom(getHeroCurrRoom(hero,dungeon));
			} else {
				DungeonSaveGame dsg = loadSaveGame(v.displayLoadSave());
				//hero form save game
				hero = dsg.getHero();
				//dungeon from save game
				dungeon = dsg.getDungeon();
			}
			
			v.displayText("Hello " + hero.getCharacterName() + " the " + hero.getClassName() + ", are you ready to adventure the dungeon?", 1);
			
			while(hero.isAlive() && (!(getHeroCurrRoom(hero,dungeon).isExit()) || !(hero.hasPillars()))) {
				DungeonRoom currRoom = getHeroCurrRoom(hero,dungeon);
				
				v.displayCurrRoom(hero);
				
				if(hero.getCurrRoom().isExit()) {
					v.displayText("You have found the exit, but you need to go back and find the other pillars of OO", 2);
				}
				
				v.displayText(currRoom.removeItemsFromRoom(hero), 2);		
				if(!hero.isAlive()) {
					break;
				} 
				
				if(currRoom.isMonster()) {
					v.resetAllPanels();
					Monster monster = currRoom.getMonster();
					v.displayText("The room you entered contains a " + monster.getMonsterType(), 1);
					while(monster.isAlive() && hero.isAlive()) {
						encounter(hero, monster, v);
					}
					v.resetAllPanels();
					if(!monster.isAlive()) {
						v.displayText("You have slain the " + monster.getMonsterType(), 1);
						currRoom.setMonster(null);
					} else if(!hero.isAlive()) {
						break;
					}
				}
				
				if(v.displayOptions(hero, dungeon)) {
					saveGame(dungeon,hero,v.displaySaveGame(), v);
					save = true;
					break;
				}
				
				
				moveHero(hero,dungeon, v.displayMoveOptions(getHeroCurrRoom(hero,dungeon)));
			}
			if(save) {
				break;
			}
			
			if(!hero.isAlive()) {
				v.displayText("\nYou have died....", 1);
			} else {
				v.displayText("\nGood job on obtaining the pillars of OO and escaping the dungeon", 1);
			}
			
			v.displayText("\n\nEntire Dungeon:\n" + dungeon.toString(), 2);
			//keepPlaying = view.displayKeepPlayingOptions();
			v.resetAllPanels();
		}
	}
	

	/**
	 * Uses the user's current position to determine which room they are in
	 * @param theHero
	 * @param theDungeon
	 * @return HeroCurrRoom
	 */
	public static DungeonRoom getHeroCurrRoom(final Hero theHero, final Dungeon theDungeon) {
		return theDungeon.getDungeon()[theHero.getCurrX()][theHero.getCurrY()];
	}
	
	/**
	 * Moves the hero in the direction the user selects
	 * @param theHero
	 * @param theDungeon
	 * @param theDirection
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
	 * The method to load a game that has been saved
	 * @param theFile
	 * @return dsg
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
	 * Takes in the current Dungeon/Hero and creates a DungeonSaveGame and the name of the file the user wants, then creates a .ser file and
	 * saves the instance of the DungeonSaveGame to the file using serialization
	 * 
	 * @param theDungeon the current dungeon instance the user is playing
	 * @param theHero the current hero instance the user is using
	 * @param theFileName the file name the user wanted
	 * @throws InterruptedException 
	 */
	public static void saveGame(final Dungeon theDungeon, final Hero theHero, final String theFileName, final ViewGUI theView) throws InterruptedException {
		DungeonSaveGame dsg = new DungeonSaveGame(theDungeon, theHero);
		String currDirectory = System.getProperty("user.dir");
		String saveGameDir = currDirectory + "\\SaveGames";
		String saveFile = saveGameDir + "\\" + theFileName + ".ser";
		
		try {
		      File file = new File(saveFile);
		      if (!file.createNewFile()) {
		    	  if(theView.displayOverWrite().equalsIgnoreCase("no")) {
		    		saveGame(theDungeon,theHero,theView.displaySaveGame(), theView); 
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
	 * The main method to represent any encounters with the monsters the user comes across
	 * @param theHero
	 * @param theMonster
	 * @throws InterruptedException 
	 */
	public static void encounter(final Hero theHero, final Monster theMonster, final ViewGUI theView) throws InterruptedException {
		double attacks = theHero.getAttackSpeed() / theMonster.getAttackSpeed();	
		
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
			String attack = theView.displayHeroAttacks();
			
			//if User wants a normal attack
			int monsterHealth = theMonster.getHitPoints();
			if(attack.equalsIgnoreCase("normal")) {
				//if normal succeeded
				if(theHero.attack(theMonster)) {
					theView.displayText("The attack succeeded and you dealt " + (monsterHealth - theMonster.getHitPoints()) + " damage. Monsters current health " + theMonster.getHitPoints(), 2);
					monsterHealth = theMonster.getHitPoints();
					if(theMonster.isAlive()) {
						theMonster.heal();
						theView.displayText("The monster attempted to heal and healed " + (theMonster.getHitPoints() - monsterHealth) + " health points. Monsters current health " + theMonster.getHitPoints(), 2);
					} else {
						return;
					}
				//if normal failed
				} else {
					theView.displayText("Your normal attack failed to land", 2);
				}
			}
			
			//if user wants a special attack
			if(attack.equalsIgnoreCase("special")) {
				//if the hero is a priestess
				if(theHero.getClassName().equalsIgnoreCase("priestess")) {
					int heroHealth = theHero.getHitPoints();
					theHero.special(theHero);
					theView.displayText("Your heal succeeded in healing " + (theHero.getHitPoints() - heroHealth) + " health points. Your current health is " + theHero.getHitPoints(), 2);				
				//else if hero is a thief
				}  else {
					//if special succeeded
					if(theHero.special(theMonster)) {
						theView.displayText("The special attack succeeded and you dealt " + (monsterHealth - theMonster.getHitPoints()) + " damage. Monsters current health " + theMonster.getHitPoints(), 2);
						monsterHealth = theMonster.getHitPoints();
						//if monster still alive try heal
						if(theMonster.isAlive()) {
							theMonster.heal();
							theView.displayText("The monster attempted to heal and healed " + (theMonster.getHitPoints() - monsterHealth) + " health points. Monsters current health " + theMonster.getHitPoints(), 2);
						//else dead so return
						} else {
							return;
						}
					//else special failed
					} else {
						theView.displayText("Your special attack failed to land", 2);
					}
				}
			}
			
			theHero.setAttacks(theHero.getAttacks() - 1);			
		}
		
		//monsters attack
		theView.displayText("\nIts the " + theMonster.getMonsterType() + " turn to attack", 2);
		int heroHealth = theHero.getHitPoints();
		//if attack succeeded
		if(theMonster.attack(theHero)) {
			theView.displayText("The " + theMonster.getMonsterType() + " succesfuly landed an attack.", 2);
			theView.displayText("You lost " + (heroHealth - theHero.getHitPoints()) + " health points. Your current health " + theHero.getHitPoints(), 2);
		//else attack failed
		} else {
			theView.displayText("The " + theMonster.getMonsterType() + " failed in landing an attack" , 2);
		}
		
		theView.resetPanel2();
	}
}
