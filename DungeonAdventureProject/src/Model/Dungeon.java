package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Utility.Utility;

public class Dungeon implements Serializable {
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Multidimensional array of DungeonRooms that represents the dungeon
	 * @serial
	 */
	private DungeonRoom[][] myDungeon;

	/**
	 * Constructor for the Dungeon class
	 * @param theRows number of rows the dungeon should be
	 * @param theColumns number of columns the dungeon should be
	 */
	public Dungeon(final int theRows, final int theColumns) {
		myDungeon = createDungeon(theRows, theColumns);
	}
	
	/**
	 * Getter for myDungeon
	 * 
	 * @return the the MultiDimensional Array of DungeonRooms for the Dungeon
	 */
	public DungeonRoom[][] getDungeon() {
		return myDungeon;
	}
	
	/**
	 * Setter for myDungeon
	 * 
	 * @param theDungeon a multidimensional array of DungeonRoom's 
	 */
	public void setDungeon(final DungeonRoom[][] theDungeon) {
		myDungeon = theDungeon;
	}
	
	/**
	 *Builds a dungeon room by room from top left to bottom right, inserts potions, monsters, and pillars at random
	 *through out the dungeon. Then checks if the maze is Traversable and you are able to obtain all the pillars, if not creates a new dungeons
	 *until both are true
	 * 
	 * @return the created mutlidimensional array of DungeonRooms that represents the dungeon
	 */
	public static DungeonRoom[][] createDungeon(final int theRows, final int theColumns) {
		DungeonRoom[][] d = new DungeonRoom[theRows][theColumns];
		MonsterFactory mf =  new MonsterFactory();
		ItemFactory If = new ItemFactory();
		
		DungeonRoom room;
		Monster monster;
		List<Item> items;
		
		for(int rows = 0; rows < d.length; rows++) {
			for(int columns = 0; columns < d[rows].length; columns++) {
				boolean north = false, west = false, east = false, south = false;				
				monster = null;
				items = new ArrayList<Item>();
				room = null;
				
				if(!((rows == 0 && columns == 0) || ((rows == (d.length - 1)) && (columns == (d[rows].length - 1))))) {
					//add heals
					int chance = Utility.randomNumberGen(0, 100);
					if(chance < 10) {
						items.add(If.createItem("heal"));
					} else if (chance >= 10 && chance < 20) {
						items.add(If.createItem("heal"));
						items.add(If.createItem("heal"));
					}
					
					//add vision
					chance = Utility.randomNumberGen(0, 100);			
					if(chance < 15)  {
						items.add(If.createItem("vision"));
					}
					
					//add monster
					chance = Utility.randomNumberGen(0, 100);
					if(chance < 20) {
						chance = Utility.randomNumberGen(0, 100);
						if(chance < 33) {
							monster = mf.createMonster("ogre");
						} else if(chance >= 33 && chance < 66) {
							monster = mf.createMonster("skeleton");
						} else {
							monster = mf.createMonster("gremlin");
						}
					}
					
					north = isNorthDoor(d, rows, columns);
					west = isWestDoor(d, rows, columns);
					south = isSouthDoor(d, rows, columns);
					east = isEastDoor(d, rows, columns);
					
					if(!(north) && !(west) && !(south) && !(east)) {
						chance = Utility.randomNumberGen(0, 100);
						if (chance < 50) {
							south = true;
						} else {
							east = true;
						}
					}
					
					room = new DungeonRoom(items, monster, north, south, west, east, false, false, false);
				} else {
					if(rows == 0 && columns == 0) {
						south = isSouthDoor(d, rows, columns);
						east = isEastDoor(d, rows , columns);
						
						room = new DungeonRoom(items, monster, north, south, west, east, false, true, false);
					} else {
						north = isNorthDoor(d, rows, columns);
						west = isWestDoor(d,rows,columns);
						room = new DungeonRoom(items, monster, north, south, west, east, true, false, false);
					}
				}
					
				d[rows][columns] = room;	
			}
		}
		
		placePillars(d);
		
		if(isMazeTraversable(d) && isPillarsObtainable(d)) {
			return d;
		}

		return createDungeon(theRows, theColumns);
	}
	/**
	 * Determines if there is a door to the south in the room to the north of the current room
	 * 
	 * @param theDungeon a multidimensional array of DungeonRoom's 
	 * @param theI the I values of the room in the MultiDimensional Array
	 * @param theJ the J values of the room in the MultiDimensional Array
	 * @return false if the room is on the top row of the array, returns whether or not there is a door going south in the room above it
	 */
	public static boolean isNorthDoor(final DungeonRoom[][] theDungeon, final int theI, final int theJ) {
		if(theI < 1) {
			return false;
		}
		
		return theDungeon[theI - 1][theJ].isSouth();
	}
	
	/**
	 * Determines if there is a door to the east of the room to the west of the current room
	 * 
	 * @param theDungeon a multidimensional array of DungeonRoom's 
	 * @param theI the I values of the room in the MultiDimensional Array
	 * @param theJ the J values of the room in the MultiDimensional Array
	 * @return false if the door is in the first column of the array, returns whether or not there is a door going east in the room to the left of it
	 */
	public static boolean isWestDoor(final DungeonRoom[][] theDungeon, final int theI, final int theJ) {
		if(theJ < 1) {
			return false;
		}
		
		return theDungeon[theI][theJ - 1].isEast();
	}
	
	/**
	 * Decides whether the current room should have a door to the east at a 70% chance
	 * 
	 * @param theDungeon a multidimensional array of DungeonRoom's 
	 * @param theI the I values of the room in the MultiDimensional Array
	 * @param theJ the J values of the room in the MultiDimensional Array
	 * @return true if the random number (chance) is above 70
	 */
	public static boolean isEastDoor(final DungeonRoom[][] theDungeon, final int theI, final int theJ) {
		if(theJ > theDungeon[theI].length - 2) {
			return false;
		}
		
		int chance = Utility.randomNumberGen(0, 100);
		if(chance < 70) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Determines whether the current room should have a door to the south at a 70% chance
	 * 
	 * @param theDungeon a multidimensional array of DungeonRoom's 
	 * @param theI the I values of the room in the MultiDimensional Array
	 * @param theJ the J values of the room in the MultiDimensional Array
	 * @return true if the random number (chance) is above 70
	 */
	public static boolean isSouthDoor(final DungeonRoom[][] theDungeon, final int theI, final int theJ) {
		if(theI > theDungeon.length - 2) {
			return false;
		}
		
		int chance = Utility.randomNumberGen(0, 100);
		if(chance < 70) {
			return true;
		}	
		return false;
	}
	
	/**
	 * Resets all the rooms in the dungeon myRoomChecked instance variable back to false
	 * 
	 * @param theDungeon a multidimensional array of DungeonRoom's 
	 */
	public static void resetRoomsChecked(final DungeonRoom[][] theDungeon) {
		for(int i = 0; i < theDungeon.length; i++) {
			for(int j = 0; j < theDungeon[i].length; j++) {
				theDungeon[i][j].setRoomChecked(false);
			}
		}
	}
	
	/**
	 * Places pillars through out the dungeon randomly and make sure no two pillars are in the same room
	 * 
	 * @param theDungeon a multidimensional array of DungeonRoom's 
	 */
	public static void placePillars(final DungeonRoom[][] theDungeon) {
		ItemFactory If = new ItemFactory();
		int iMax = theDungeon.length - 2;
		int jMax = theDungeon[1].length - 2;
		
		List<Item> list = new ArrayList<Item>();
		list.add(If.createItem("abstraction"));
		list.add(If.createItem("encapsulation"));
		list.add(If.createItem("inheritance"));
		list.add(If.createItem("polymorphism"));
		
		for(Item item: list) {
			int i = Utility.randomNumberGen(1, iMax);
			int j = Utility.randomNumberGen(1, jMax);
			while(theDungeon[i][j].containsPillar()) {
				i = Utility.randomNumberGen(1, iMax);
				j = Utility.randomNumberGen(1, jMax);
			}
			
			theDungeon[i][j].addItem(item);
		}
		
	}
	
	/**
	 * Resets the checked instance fields and then calls the helper method that checks if you can traverse the maze from start to finish
	 * 
	 * @param theDungeon a multidimensional array of DungeonRoom's 
	 * @return Helper for isMazeTraversable method
	 */
	public static boolean isMazeTraversable(final DungeonRoom[][] theDungeon) {
		resetRoomsChecked(theDungeon);
		return isMazeTraversable(theDungeon, 0, 0, "");
	}
	
	/**
	 * Traverses the dungeon to check if you can traverse to the exit door
	 * 
	 * @param theDungeon a multidimensional array of DungeonRoom's 
	 * @param theX current x values of the Room
	 * @param theY current Y values of the Room
	 * @param theDir that we came into the room from
	 * @return true if the maze is able to go from start room to exit room, or false if the maze is unable to be completed
	 */
	private static boolean isMazeTraversable(final DungeonRoom[][] theDungeon, final int theX, final int theY, final String theDir) {
		DungeonRoom room = theDungeon[theX][theY];
		boolean north = false, south = false, east = false, west = false;
		
		if(room.isExit()) {
			return true;
		} else if (room.isChecked()) {
			return false;
		} else {
			room.setRoomChecked(true);
			if(!(theDir.equalsIgnoreCase("north")) && room.isNorth()) {				
				north = isMazeTraversable(theDungeon, theX -  1, theY, "south");
			}
			
			if(!(theDir.equalsIgnoreCase("east")) && room.isEast()) {
				east = isMazeTraversable(theDungeon, theX, theY + 1, "west");
			}
			
			if(!(theDir.equalsIgnoreCase("west")) && room.isWest()) {
				west = isMazeTraversable(theDungeon, theX, theY - 1, "east");
			}
			
			if(!(theDir.equalsIgnoreCase("south")) && room.isSouth()) {
				south = isMazeTraversable(theDungeon, theX + 1, theY, "north");
			}
			
			
		}
		
		return (north || south || east || west);
	}
	
	/**
	 * Resets if the room checked instance variables of rooms in dungeon and then calls helper method that checks if you are able to get
	 * to all rooms that contain pillars
	 * 
	 * @param theDungeon a multidimensional array of DungeonRoom's 
	 * @return Helper for isPillarsObtainable method
	 */
	public static boolean isPillarsObtainable(final DungeonRoom[][] theDungeon) {
		resetRoomsChecked(theDungeon);
		return isPillarsObtainable(theDungeon, 0, 0, "", 0);
	}
	
	/**
	 * Traverses the maze room by room to finds out if you are able to obtain all pillars in the maze.
	 * 
	 * @param theDungeon a multidimensional array of DungeonRoom's 
	 * @param theX value of the room in the Dungeon
	 * @param theY value of the room in the Dungeon
	 * @param theDir that we came into the room from
	 * @param theCount of pillars found so far in the DFS search
	 * @return false if the pillars are not obtainable and returns true if you can get to all pillars in the maze.
	 */
	private static boolean isPillarsObtainable(final DungeonRoom[][] theDungeon, final int theX, final int theY, final String theDir, final int theCount) {
		DungeonRoom room = theDungeon[theX][theY];
		boolean north = false, south = false, east = false, west = false;
		int count = theCount;
		
		if(theCount == 4) {
			return true;
		} else if (room.isChecked()) {
			return false;
		} else {
			room.setRoomChecked(true);
			if(room.containsPillar()) {
				count++;
			}
			if(!(theDir.equalsIgnoreCase("north")) && room.isNorth()) {				
				north = isPillarsObtainable(theDungeon, theX -  1, theY, "south", count);
			}
			
			if(!(theDir.equalsIgnoreCase("east")) && room.isEast()) {
				east = isPillarsObtainable(theDungeon, theX, theY + 1, "west", count);
			}
			
			if(!(theDir.equalsIgnoreCase("west")) && room.isWest()) {
				west = isPillarsObtainable(theDungeon, theX, theY - 1, "east", count);
			}
			
			if(!(theDir.equalsIgnoreCase("south")) && room.isSouth()) {
				south = isPillarsObtainable(theDungeon, theX + 1, theY, "north", count);
			}
			
			
		}
		
		return (north || south || east || west);
	}
	
	/**
	 * Builds a text representation of a dungeon instance using string builder.
	 * 
	 * @return the text representation of a dungeon instance
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < getDungeon().length; i ++) {
			StringBuilder row1 = new StringBuilder();
			StringBuilder row2 = new StringBuilder();
			StringBuilder row3 = new StringBuilder();
			for(int j = 0; j < getDungeon()[i].length; j++) {
				row1.append(getDungeon()[i][j].getRoom()[0][0]);
				row1.append(getDungeon()[i][j].getRoom()[0][1]);
				row1.append(getDungeon()[i][j].getRoom()[0][2]);
				
				row2.append(getDungeon()[i][j].getRoom()[1][0]);
				row2.append(getDungeon()[i][j].getRoom()[1][1]);
				row2.append(getDungeon()[i][j].getRoom()[1][2]);
				
				row3.append(getDungeon()[i][j].getRoom()[2][0]);
				row3.append(getDungeon()[i][j].getRoom()[2][1]);
				row3.append(getDungeon()[i][j].getRoom()[2][2]);				
			}
			
			sb.append(row1.toString());
			sb.append(System.lineSeparator());
			sb.append(row2.toString());
			sb.append(System.lineSeparator());
			sb.append(row3.toString());
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
}
