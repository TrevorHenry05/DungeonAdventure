package Model;

import java.io.Serializable;
import java.util.List;

public class DungeonRoom implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Item> myItemsInRoom;
	private char[][] myRoom;
	private Monster myMonster;
	private final boolean myNorth;
	private final boolean mySouth;
	private final boolean myWest;
	private final boolean myEast;
	private final boolean myEntrance;
	private final boolean myExit;
	private boolean myRoomChecked;
	
	public DungeonRoom(final List<Item> theItemsInRoom, final Monster theMonster, final boolean theNorth, final boolean theSouth, final boolean theWest, final boolean theEast, final boolean theExit, final boolean theEntrance, final boolean theRoomChecked) {
		myItemsInRoom = theItemsInRoom;		
		myMonster = theMonster;
		myNorth = theNorth;
		mySouth = theSouth;
		myWest = theWest;
		myEast = theEast;
		myExit = theExit;
		myEntrance = theEntrance;
		myRoomChecked = theRoomChecked;
		myRoom = createRoom();
	}
	
	public void setRoom() {
		myRoom = createRoom();
	}
	
	public void setRoomChecked(final boolean theRoomChecked) {
		myRoomChecked = theRoomChecked;
	}
	
	public Monster getMonster() {
		return myMonster;
	}
	
	public List<Item> getItemsInRoom() {
		return myItemsInRoom;
	}
	
	public boolean isExit() {
		return myExit;
	}
	
	public boolean isEntrance() {
		return myEntrance;
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
	
	public boolean isChecked() {
		return myRoomChecked;
	}
	
	public boolean isMonster() {
		if(getMonster() == null) {
			return false;
		}
		
		return true;
	}
	
	public void setMonster(final Monster theMonster) {
		myMonster = theMonster;
	}
	
	public void addItem(final Item theItem) {
		getItemsInRoom().add(theItem);
		//update room representation after adding item
		myRoom = createRoom();
	}
	
	public boolean containsPillar() {
		for(Item item: getItemsInRoom()) {
			if(item.getType() == 'A' || item.getType() == 'I' || item.getType() == 'E' || item.getType() == 'P') {
				return true;
			}
		}
		
		return false;
	}
	
	public String removeItemsFromRoom(final Hero theHero) {		
		StringBuilder sb = new StringBuilder();
		
		for(Item item: getItemsInRoom()) {
			if(item.getType() == 'X') {
				int currHeroHealth = theHero.getHitPoints();
				theHero.useItem(item);				
				sb.append("You encoutered a trap!\n");
				sb.append("You lost " + (currHeroHealth - theHero.getHitPoints()) + " health to the trap.\n");
			}
			if (item.getType() == 'A' || item.getType() == 'I' || item.getType() == 'E' || item.getType() == 'P') {
				sb.append("You obtained the " + item.getDescription() + "!\n");
				theHero.addItemToInventory(item);
				if(theHero.hasPillars()) {
					sb.append("You have found all the pillars, now just make it to the exit!\n");
				}
			} 
			if(item.getType() == 'H' || item.getType() == 'V') {
			sb.append("You obtained a " + item.getDescription() + " potion\n");
			theHero.addItemToInventory(item);
			}
			
		}
		
		getItemsInRoom().clear();
		setRoom();
		
		return sb.toString();
	}
	
	
	public char[][] createRoom() {
		char[][] room = new char[3][3];
		
		//add corners of room
		room[0][0] = '*';
		room[0][2] = '*';
		room[2][0] = '*';
		room[2][2] = '*';
		
		if(isEntrance()) {
			room[1][1] = 'i';
		} else if(isExit()) {
			room[1][1] = 'O';
		} else {			
			//add what items are in the room
			if(getItemsInRoom().size() == 1) {		
				room[1][1] = getItemsInRoom().get(0).getType();
			} else if( getItemsInRoom().size() == 0) {
				room[1][1] = ' ';
			} else {
				room[1][1] = 'M';
			}
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
