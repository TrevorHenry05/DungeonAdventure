package Model;

public class DungeonRoom {
	
	private Item[] myItemsInRoom;
	private char[][] myRoom;
	private Monster myMonster;
	private final boolean myNorth;
	private final boolean mySouth;
	private final boolean myWest;
	private final boolean myEast;
	
	public DungeonRoom(final Item[] theItemsInRoom, final Monster theMonster, final boolean theNorth, final boolean theSouth, final boolean theWest, final boolean theEast) {
		myItemsInRoom = theItemsInRoom;
		myRoom = createRoom();
		myMonster = theMonster;
		myNorth = theNorth;
		mySouth = theSouth;
		myWest = theWest;
		myEast = theEast;
	}
	
	public Monster getMonster() {
		return myMonster;
	}
	
	public Item[] getItemsInRoom() {
		return myItemsInRoom;
	}
	
	public char[][] getRoom() {
		return myRoom;
	}
	
	public boolean isNorth() {
		return myNorth;
	}
	
	public boolean isSouth() {
		return mySouth;
	}
	
	public boolean isWest() {
		return myWest;
	}
	
	public boolean isEast() {
		return myEast;
	}
	
	public char[][] createRoom() {
		return null;
	}
}
