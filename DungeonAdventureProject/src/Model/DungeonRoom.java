package Model;

import java.util.List;

public class DungeonRoom {
	
	private List<Item> myItemsInRoom;
	private char[][] myRoom;
	private Monster myMonster;
	private final boolean myNorth;
	private final boolean mySouth;
	private final boolean myWest;
	private final boolean myEast;
	private final boolean myFinalRoom;
	private static char[] PILLARS = new char[]{'A', 'E', 'I', 'P'};
	
	public DungeonRoom(final List<Item> theItemsInRoom, final Monster theMonster, final boolean theNorth, final boolean theSouth, final boolean theWest, final boolean theEast, final boolean theFinalRoom) {
		myItemsInRoom = theItemsInRoom;		
		myMonster = theMonster;
		myNorth = theNorth;
		mySouth = theSouth;
		myWest = theWest;
		myEast = theEast;
		myFinalRoom = theFinalRoom;
		createRoom();
		checkForMonster();
	}
	public DungeonRoom(final List<Item> theItemsInRoom, final Monster theMonster, final boolean theNorth, final boolean theSouth, final boolean theWest, final boolean theEast) {
//		myItemsInRoom = theItemsInRoom;		
//		myMonster = theMonster;
//		myNorth = theNorth;
//		mySouth = theSouth;
//		myWest = theWest;
//		myEast = theEast;
//		myFinalRoom = false;
//		createRoom();
//		checkForMonster();
		this(theItemsInRoom, theMonster, theNorth, theSouth, theWest, theEast, false);
	}
//	public void setRoom() {
//		myRoom = createRoom();
//	}
	private void checkForMonster() {
		if (this.isMonster()) {
			myItemsInRoom.add(new Item('M', myMonster.getMonsterType(), false, false));
		}
	}
	public Monster getMonster() {
		return myMonster;
	}
	
	public List<Item> getItemsInRoom() {
		return myItemsInRoom;
	}
	
	public boolean isFinalRoom() {
		return myFinalRoom;
	}
	
	public char[][] getRoom() {
		createRoom();
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
	
	public void addItem(final Item theItem) {
		myItemsInRoom.add(theItem);
	}
	
	public void removeItemsFromRoom(final Hero theHero) {	
		for(Item item: getItemsInRoom()) {
//			if(item.getType() == 'X') {
//				theHero.useItem(item);
//				System.out.println("You encoutered a trap!");
//			}
//			if (item.getType() == 'A' || item.getType() == 'I' || item.getType() == 'E' || item.getType() == 'P') {
//				System.out.println("You obtained the " + item.getDescription() + "!");
//				theHero.addItemToInventory(item);
//				if(theHero.hasPillars()) {
//					System.out.println("You have found all the pillars, now just make it to the exit!");
//				}
//			} 
//			if(item.getType() == 'H') {
//				System.out.println("You obtained a " + item.getDescription() + " potion");
//				theHero.addItemToInventory(item);
//			}
			if (item.isCollectable()) {
				theHero.addItemToInventory(item);
				for (char i : PILLARS) {
					if  (i == item.getType()) {
						System.out.println("You obtained the " + item.getDescription() + "!");
						break;
					}
					if (i == 'P') {
						System.out.println("You obtained " + item.getDescription());
					}
				}
			} else {
				if (item.isUsable()) {
					theHero.useItem(item);
				}
			}
			if (theHero.hasPillars()) {
				System.out.println("You have found all the pillars! Now just make it to the exit!");
			}
		}
		myItemsInRoom.clear();
		createRoom();
	}
	
	public boolean isMonster() {
		if(getMonster() == null) {
			return false;
		}
		
		return true;
	}
	
	public void createRoom() {
		char[][] room = new char[3][3];
		
		//add corners of room
		room[0][0] = '*';
		room[0][2] = '*';
		room[2][0] = '*';
		room[2][2] = '*';
			
		//add what items are in the room
		if(myItemsInRoom.size() == 1) {		
			room[1][1] = myItemsInRoom.get(0).getType();
		} else if(myItemsInRoom.size() == 0) {
			room[1][1] = ' ';
		} else {
			room[1][1] = 'S';
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
		
		myRoom = room;
	}
	
	public String toString() {
		createRoom();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < myRoom.length; i++) {
			for(int j = 0; j < myRoom[i].length; j++) {
				sb.append(myRoom[i][j]);
			}
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
	
	
}
