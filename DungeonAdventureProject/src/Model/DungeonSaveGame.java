package Model;

import java.io.Serializable;

public class DungeonSaveGame implements Serializable {
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The Dungeon Object to be saved
	 */
	private final Dungeon myDungeon;
	/**
	 * The Hero Object to be saved
	 */
	private final Hero myHero;
	/**
	 * Constructor for saving the game and what information must be stored
	 * @param theDungeon object we want to save
	 * @param theHero object we want to save
	 */
	public DungeonSaveGame(final Dungeon theDungeon, final Hero theHero) {
		myDungeon = theDungeon;
		myHero = theHero;
	}
	
	/**
	 * getter for the created dungeon class of saved game
	 * @return myDungeon the Dungeon object that is stored in the DungeonSaveGame object
	 */
	public Dungeon getDungeon() {
		return myDungeon;
	}
	
	/**
	 * getter for the hero of saved game
	 * @return myHero the Hero object that is stored in the DungeonSaveGame object
	 */
	public Hero getHero() {
		return myHero;
	}
}
