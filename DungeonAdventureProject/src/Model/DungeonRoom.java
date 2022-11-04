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
		myMonster = theMonster;
		myNorth = theNorth;
		mySouth = theSouth;
		myWest = theWest;
		myEast = theEast;
		myRoom = createRoom();
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
	
	public boolean isMonster() {
		if(getMonster() == null) {
			return false;
		}
		
		return true;
	}
	
	public char[][] createRoom() {
		char[][] room = new char[3][3];
		
		//add corners of room
		room[0][0] = '*';
		room[0][2] = '*';
		room[2][0] = '*';
		room[2][2] = '*';
			
		//add what items are in the room
		if(getItemsInRoom().length > 1) {
			room[1][1] = 'M';
		} else if( getItemsInRoom().length == 0) {
			room[1][1] = ' ';
		} else {
			room[1][1] = getItemsInRoom()[0].getType();
		}
		
		//Add north wall or door
		if(isNorth()) {
			room[0][1] = '-';
		} else {
			room[0][1] = '*';
		}
		
		//Add west wall or door
		if(isWest()) {
			room[1][0] = '|';
		} else {
			room[1][0] = '*';
		}
		
		//Add east wall or door
		if(isEast()) {
			room[1][2] = '|';
		} else {
			room[1][2] = '*';
		}
		
		//Add south wall or door
		if(isSouth()) {
			room[2][1] = '-';
		} else {
			room[2][1] = '*';
		}
		
		return room;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < getRoom().length; i++) {
			for(int j = 0; j < getRoom()[i].length; j++) {
				sb.append(getRoom()[i][j]);
			}
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
	
	
}
