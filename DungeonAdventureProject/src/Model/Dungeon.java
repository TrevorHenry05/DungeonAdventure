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
	
	public Dungeon() {
		myDungeon = createDungeon();
	}
	
	public DungeonRoom[][] getDungeon() {
		return myDungeon;
	}
	

	public DungeonRoom[][] createDungeon() {
		DungeonRoom[][] d = new DungeonRoom[4][4];
		MonsterFactory mf =  new MonsterFactory();
		ItemFactory If = new ItemFactory();
		
		DungeonRoom room;
		Monster monster;
		List<Item> items;
		
		for(int i = 0; i < d.length; i++) {
				for(int j = 0; j<d[i].length; j++) {
					boolean north, west, east, south;
					
					
					monster = null;
					items = new ArrayList<Item>();
					room = null;
			int chance = Utility.randomNumberGen(0, 100);		
					if(chance<10) {
						items.add(If.createItem("heal")); 
					} else if(chance >= 10) {
						items.add(If.createItem("heal")); 
						items.add(If.createItem("heal")); 
					}
					
					chance = Utility.randomNumberGen(0, 100);
					if(chance< 10) {
						items.add(If.createItem("vision")); 
				
						
					
					}
					chance = Utility.randomNumberGen(0, 100);
					if(chance< 5) {
						chance = Utility.randomNumberGen(0, 100);
						if(chance<33) {
						monster=mf.createMonster("ogre"); 
				}else if(chance >= 33 && chance < 66) {
					monster=mf.createMonster("skeleton");
				} else {
					monster=mf.createMonster("gremlin");
			}
		}	
	}
}	
	
			
			
		
	

		return d;

	}
	
	private static boolean isNorthDoor(final DungeonRoom[][] theDungeon, final int theI, final int theJ) {
		if(theI < 1) {
					return false;
		}
	
		return theDungeon[theI-1][theJ].isSouth();
	
	}
	
	private static boolean isWestDoor(final DungeonRoom[][] theDungeon, final int theI, final int theJ) {
		if(theJ < 1) {
					return false;
		}
		return theDungeon[theI-1][theJ].isEast();
	}
	private static boolean isEastDoor(final DungeonRoom[][] theDungeon, final int theI, final int theJ) {
			if(theJ < 1) {
						return false;
			}
			int chance = Utility.randomNumberGen(0,  100);
			if(chance < 70) {
				return true;
			}
	}
	private static boolean isSouthDoor(final DungeonRoom[][] theDungeon, final int theI, final int theJ) {
		if(theI > theDungeon.length -2) {
					return false;
		}
		int chance = Utility.randomNumberGen(0,  100);
		if(chance < 70) {
			return true;
		}
}
	
  public static boolean isMazeTraversible(final DungeonRoom[][] theDungeon) {
		return isMazeTraversible(theDungeon, 0, 0, "");
	}
	
	public static boolean isMazeTraversible(final DungeonRoom[][] theDungeon, final int theX, final int theY, final String theDir) {
		DungeonRoom room = theDungeon[theX][theY];
		boolean north = false, south = false, east = false, west = false;
		
		if(room.isExit()) {
			return true;
		} else if (room.isChecked()) {
			return false;
		} else {
			room.setRoomChecked(true);
			if(!(theDir.equalsIgnoreCase("north")) && room.isNorth()) {				
				north = isMazeTraversible(theDungeon, theX -  1, theY, "south");
			}
			
			if(!(theDir.equalsIgnoreCase("east")) && room.isEast()) {
				east = isMazeTraversible(theDungeon, theX, theY + 1, "west");
			}
			
			if(!(theDir.equalsIgnoreCase("west")) && room.isWest()) {
				west = isMazeTraversible(theDungeon, theX, theY - 1, "east");
			}
			
			if(!(theDir.equalsIgnoreCase("south")) && room.isSouth()) {
				south = isMazeTraversible(theDungeon, theX + 1, theY, "north");
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
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
}

