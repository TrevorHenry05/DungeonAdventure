package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Utility.Utility;

public class Dungeon implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private final DungeonRoom[][] myDungeon;
	private final Random r = new Random();
	
	public Dungeon() {
		myDungeon = createDungeon();
	}
	
	public DungeonRoom[][] getDungeon() {
		return myDungeon;
	}
	


	private static DungeonRoom[][] createDungeon() {
		DungeonRoom[][] d = new DungeonRoom[7][7];
		MonsterFactory mf =  new MonsterFactory();
		ItemFactory If = new ItemFactory();
		
		DungeonRoom room;
		Monster monster;
		List<Item> items;
		
		for(int i = 0; i < d.length; i++) {
			for(int j = 0; j < d[i].length; j++) {
				boolean north = false, west = false, east = false, south = false;				
				monster = null;
				items = new ArrayList<Item>();
				room = null;
				
				if(!((i == 0 && j == 0) || ((i == (d.length - 1)) && (j == (d[i].length - 1))))) {
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
					if(chance < 10)  {
						items.add(If.createItem("vision"));
					}
					
					//add monster
					chance = Utility.randomNumberGen(0, 100);
					if(chance < 15) {
						chance = Utility.randomNumberGen(0, 100);
						if(chance < 33) {
							monster = mf.createMonster("ogre");
						} else if(chance >= 33 && chance < 66) {
							monster = mf.createMonster("skeleton");
						} else {
							monster = mf.createMonster("gremlin");
						}
					}
					
					north = isNorthDoor(d, i, j);
					west = isWestDoor(d, i, j);
					south = isSouthDoor(d, i, j);
					east = isEastDoor(d, i, j);
					
					room = new DungeonRoom(items, monster, north, south, west, east, false, false, false);
				} else {
					if(i == 0 && j == 0) {
						south = isSouthDoor(d, i, j);
						east = isEastDoor(d, i , j);
						
						room = new DungeonRoom(items, monster, north, south, west, east, false, true, false);
					} else {
						north = isNorthDoor(d, i, j);
						west = isWestDoor(d,i,j);
						room = new DungeonRoom(items, monster, north, south, west, east, true, false, false);
					}


				}
					
				d[i][j] = room;	
			}
		}
		
		placePillars(d);
		
		if(isMazeTraversable(d) && isPillarsObtainable(d)) {
			return d;
		}

		return createDungeon();
	}
	
	private static boolean isNorthDoor(final DungeonRoom[][] theDungeon, final int theI, final int theJ) {
		if(theI < 1) {
			return false;
		}
		
		return theDungeon[theI - 1][theJ].isSouth();
	}
	
	private static boolean isWestDoor(final DungeonRoom[][] theDungeon, final int theI, final int theJ) {
		if(theJ < 1) {
			return false;
		}
		
		return theDungeon[theI][theJ - 1].isEast();
	}
	
	private static boolean isEastDoor(final DungeonRoom[][] theDungeon, final int theI, final int theJ) {
		if(theJ > theDungeon[theI].length - 2) {
			return false;
		}
		
		int chance = Utility.randomNumberGen(0, 100);
		if(chance < 70) {
			return true;
		}
		
		return false;
	}
	
	private static boolean isSouthDoor(final DungeonRoom[][] theDungeon, final int theI, final int theJ) {
		if(theI > theDungeon.length - 2) {
			return false;
		}
		
		int chance = Utility.randomNumberGen(0, 100);
		if(chance < 70) {
			return true;
		}	
		return false;
	}
	
	public static void resetRoomsChecked(final DungeonRoom[][] theDungeon) {
		for(int i = 0; i < theDungeon.length; i++) {
			for(int j = 0; j < theDungeon[i].length; j++) {
				theDungeon[i][j].setRoomChecked(false);
			}
		}
	}
	
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
	
	public static boolean isMazeTraversable (final DungeonRoom[][] theDungeon) {
		resetRoomsChecked(theDungeon);
		return isMazeTraversable(theDungeon, 0, 0, "");
	}
	
	public static boolean isMazeTraversable(final DungeonRoom[][] theDungeon, final int theX, final int theY, final String theDir) {
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
	
	public static boolean isPillarsObtainable(final DungeonRoom[][] theDungeon) {
		resetRoomsChecked(theDungeon);
		return isPillarsObtainable(theDungeon, 0, 0, "", 0);
	}
	
	public static boolean isPillarsObtainable(final DungeonRoom[][] theDungeon, final int theX, final int theY, final String theDir, final int theCount) {
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
			if (i < myDungeon.length - 1) {
				sb.append(System.lineSeparator());
			}
		}
		
		return sb.toString();
	}
}
