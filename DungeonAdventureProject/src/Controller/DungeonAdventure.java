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

public class DungeonAdventure {

	public static void main(String[] args) {
		
		boolean keepPlaying = true, save = false;
		View v = new View();
		while(keepPlaying) {
			v.displayHowToPlay();
			Hero hero = null;
			Dungeon dungeon = null;
			
			
			if(v.displayMainMenu()) {
				//display hero creation prompt
				hero = v.heroName();
				//create dungeon
				dungeon = new Dungeon();
				//set heros current room
				hero.setCurrRoom(getHeroCurrRoom(hero,dungeon));
			} else {
				//get save object
				DungeonSaveGame dsg = loadSaveGame(v.displayLoadSave());
				//hero form save game
				hero = dsg.getHero();
				//dungeon from save game
				dungeon = dsg.getDungeon();
			}
			v.displayText(dungeon.toString());
			
			//display hero info
			v.displayText("Hello " + hero.getCharacterName() + " the " + hero.getClassName() + ", are you ready to adventure the dungeon?");
			
			while(hero.isAlive() && (!(getHeroCurrRoom(hero,dungeon).isExit()) || !(hero.hasPillars()))) {
				DungeonRoom currRoom = getHeroCurrRoom(hero,dungeon);
				
				v.displayCurrRoom(hero);
				
				if(hero.getCurrRoom().isExit()) {
					v.displayText("You have found the exit, but you need to go back and find the other pillars of OO");
				}
				
				
				v.displayText(currRoom.removeItemsFromRoom(hero));		
				if(!hero.isAlive()) {
					break;
				} 
						
				
				if(currRoom.isMonster()) {
					Monster monster = currRoom.getMonster();
					v.displayText("The room you entered contains a " + monster.getMonsterType() + "\n");
					while(monster.isAlive() && hero.isAlive()) {
						encounter(hero, monster);
					}
					
					if(!monster.isAlive()) {
						v.displayText("You have slain the " + monster.getMonsterType() + "\n");
						currRoom.setMonster(null);
					} else if(!hero.isAlive()) {
						break;
					}
				}
				
				if(v.displayOptions(hero, dungeon)) {
					saveGame(dungeon,hero,v.displaySaveGame());
					save = true;
					break;
				}
				
				moveHero(hero,dungeon, v.displayMoveOptions(getHeroCurrRoom(hero,dungeon)));
						
			}
			
			if(save == true) {
				break;
			}
	
			if(!hero.isAlive()) {
				v.displayText("\nYou have died....");
			} else {
				v.displayText("\nGood job on obtaining the pillars of OO and escaping the dungeon");
			}
			
			v.displayText("\n\nEntire Dungeon:\n" + dungeon.toString());
			
			keepPlaying = v.displayKeepPlayingOptions();
		}
		
		v.displayText("\nThanks for playing!");
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
	
	public static void encounter(final Hero theHero, final Monster theMonster) {
		double attacks = theHero.getAttackSpeed() / theMonster.getAttackSpeed();	
		View v = new View();
		
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
			String attack = v.displayHeroAttacks();
			
			//if User wants a normal attack
			int monsterHealth = theMonster.getHitPoints();
			if(attack.equalsIgnoreCase("normal")) {
				//if normal succeeded
				if(theHero.attack(theMonster)) {
					v.displayText("The attack succeeded and you dealt " + (monsterHealth - theMonster.getHitPoints()) + " damage. Monsters current health " + theMonster.getHitPoints());
					monsterHealth = theMonster.getHitPoints();
					if(theMonster.isAlive()) {
						theMonster.heal();
						v.displayText("The monster attempted to heal and healed " + (theMonster.getHitPoints() - monsterHealth) + " health points. Monsters current health " + theMonster.getHitPoints() + "\n");
					} else {
						return;
					}
				//if normal failed
				} else {
					v.displayText("Your normal attack failed to land\n");
				}
			}
			
			//if user wants a special attack
			if(attack.equalsIgnoreCase("special")) {
				//if the hero is a priestess
				if(theHero.getClassName().equalsIgnoreCase("priestess")) {
					int heroHealth = theHero.getHitPoints();
					theHero.special(theHero);
					v.displayText("Your heal succeeded in healing " + (theHero.getHitPoints() - heroHealth) + " health points. Your current health is " + theHero.getHitPoints());				
				//else if hero is a thief
				}  else {
					//if special succeeded
					if(theHero.special(theMonster)) {
						v.displayText("The special attack succeeded and you dealt " + (monsterHealth - theMonster.getHitPoints()) + " damage. Monsters current health " + theMonster.getHitPoints());
						monsterHealth = theMonster.getHitPoints();
						//if monster still alive try heal
						if(theMonster.isAlive()) {
							theMonster.heal();
							v.displayText("The monster attempted to heal and healed " + (theMonster.getHitPoints() - monsterHealth) + " health points. Monsters current health " + theMonster.getHitPoints() + "\n");
						//else dead so return
						} else {
							return;
						}
					//else special failed
					} else {
						v.displayText("Your special attack failed to land");
					}
				}
			}
			
			theHero.setAttacks(theHero.getAttacks() - 1);			
		}
		
		//monsters attack
		v.displayText("\nIts the " + theMonster.getMonsterType() + " turn to attack");
		int heroHealth = theHero.getHitPoints();
		//if attack succeeded
		if(theMonster.attack(theHero)) {
			v.displayText("The " + theMonster.getMonsterType() + " succesfuly landed an attack.");
			v.displayText("You lost " + (heroHealth - theHero.getHitPoints()) + " health points. Your current health " + theHero.getHitPoints() + "\n");
		//else attack failed
		} else {
			v.displayText("The " + theMonster.getMonsterType() + " failed in landing an attack\n");
		}	
	}

}
