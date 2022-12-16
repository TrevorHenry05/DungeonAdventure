package Model;

import java.io.Serializable;
import java.util.List;

public class DungeonRoom implements Serializable{
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * List of items currently in the room
	 */
	private List<Item> myItemsInRoom;
	/**
	 * Character array of what is in the room
	 */
	private char[][] myRoom;
	/**
	 * What the specific monster is
	 */
	private Monster myMonster;
	/**
	 * If there is a door heading north
	 */
	private final boolean myNorth;
	/**
	 * If there is a door heading south
	 */
	private final boolean mySouth;
	/**
	 * If there is a door heading west
	 */
	private final boolean myWest;
	/**
	 * If there is a door heading east
	 */
	private final boolean myEast;
	/**
	 * Where the entrance is in the dungeon
	 */
	private final boolean myEntrance;
	/**
	 * Where the exit is in the dungeon
	 */
	private final boolean myExit;
	/**
	 * What rooms have already been checked in the dungeon
	 */
	private boolean myRoomChecked;
	/**
	 * Constructor for the DungeonRoom class
	 * @param theItemsInRoom a list of Items in the room
	 * @param theMonster the Monster object that is in the room and null if none
	 * @param theNorth if there is a door in the north direction
	 * @param theSouth if there is a door in the south direction
	 * @param theWest if there is a door in the west direction
	 * @param theEast if there is a door in the east direction
	 * @param theExit if the room is the exit
	 * @param theEntrance if the room is the entrance
	 * @param theRoomChecked if the room has been checked during the isMazeTraversible and isPillarsCollectable DFS search
	 */
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
	
	/**
	 * Creating the room
	 */
	public void setRoom() {
		myRoom = createRoom();
	}
	
	/**
	 * Determines if the room has already been checked
	 * @param theRoomChecked
	 */
	public void setRoomChecked(final boolean theRoomChecked) {
		myRoomChecked = theRoomChecked;
	}
	
	/**
	 * getter for the monster
	 * @return myMonster
	 */
	public Monster getMonster() {
		return myMonster;
	}
	
	/**
	 * getter for the items in the room
	 * @return myItemsInRoom
	 */
	public List<Item> getItemsInRoom() {
		return myItemsInRoom;
	}
	
	/**
	 * Returns if the room is the exit
	 * @return myExit
	 */
	public boolean isExit() {
		return myExit;
	}
	
	/**
	 * Returns if the room is the entrance
	 * @return myEntrance
	 */
	public boolean isEntrance() {
		return myEntrance;
	}
	
	/**
	 * getter for the room method
	 * @return myRoom
	 */
	public char[][] getRoom() {
		return myRoom;
	}
	
	/**
	 * Returns if there is a door north
	 * @return myNorth
	 */
	public boolean isNorth() {
		return myNorth;
	}
	
	/**
	 * Returns if there is a door south
	 * @return mySouth
	 */
	public boolean isSouth() {
		return mySouth;
	}
	
	/**
	 * Returns if there is a door west
	 * @return myWest
	 */
	public boolean isWest() {
		return myWest;
	}
	
	/**
	 * Returns if there is a door east
	 * @return myEast
	 */
	public boolean isEast() {
		return myEast;
	}
	
	/**
	 * returns if room has been checked
	 * @return myRoomChecked
	 */
	public boolean isChecked() {
		return myRoomChecked;
	}
	
	/**
	 * Return if there is a monster in the room
	 * @return true or false if there is a monster
	 */
	public boolean isMonster() {
		if(getMonster() == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Places monster in room
	 * @param theMonster
	 */
	public void setMonster(final Monster theMonster) {
		myMonster = theMonster;
	}
	
	/**
	 * Places item in the room
	 * @param theItem
	 */
	public void addItem(final Item theItem) {
		getItemsInRoom().add(theItem);
		//update room representation after adding item
		myRoom = createRoom();
	}
	
	/**
	 * checks if a room contains any of the pillars
	 * @return true or false
	 */
	public boolean containsPillar() {
		for(Item item: getItemsInRoom()) {
			if(item.getType() == 'A' || item.getType() == 'I' || item.getType() == 'E' || item.getType() == 'P') {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * removes items from room if they have been collected by the user
	 * @param theHero
	 * @return new string of room with no items
	 */
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
	
	/**
	 * creates a text representation of the room
	 * @return room
	 */
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
	
	/**
	 * String builder for the room layout and possible items, monsters or pillars in room
	 */
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
