package Model;

import java.io.Serializable;
/**
 * Uses serialization to save a .ser file that stores the user's current progress in the dungeon
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
public class DungeonSaveGame implements Serializable {
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	
	private final Dungeon myDungeon;
	private final Hero myHero;
	/**
	 * Constructor for saving the game and what information must be stored
	 * @param theDungeon
	 * @param theHero
	 */
	public DungeonSaveGame(final Dungeon theDungeon, final Hero theHero) {
		myDungeon = theDungeon;
		myHero = theHero;
	}
	
	/**
	 * getter for the created dungeon class of saved game
	 * @return myDungeon
	 */
	public Dungeon getDungeon() {
		return myDungeon;
	}
	
	/**
	 * getter for the hero of saved game
	 * @return myHero
	 */
	public Hero getHero() {
		return myHero;
	}
}
