package Model;

public class Dungeon {
	private final DungeonRoom[][] myDungeon;
	
	public Dungeon() {
		myDungeon = createDungeon();
	}
	
	public DungeonRoom[][] getDungeon() {
		return myDungeon;
	}
	
	public DungeonRoom[][] createDungeon() {
		return null;
	}
	
	public boolean isMazeTraversible(final DungeonRoom[][] theDungeon) {
		return false;
	}
}
