package Model;

import java.io.Serializable;

public class DungeonSaveGame implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Dungeon myDungeon;
	private final Hero myHero;
	
	public DungeonSaveGame(final Dungeon theDungeon, final Hero theHero) {
		myDungeon = theDungeon;
		myHero = theHero;
	}
	
	public Dungeon getDungeon() {
		return myDungeon;
	}
	
	public Hero getHero() {
		return myHero;
	}
}
