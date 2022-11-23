package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
		
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, true, false, true, false, true, false);
		d[0][0] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("Ogre");
		items.add(If.createItem("heal")); 
		room = new DungeonRoom(items, monster, true, false, false, true, false, false, false);
		d[1][0] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		room = new DungeonRoom(items, monster, false, true, false, true, false, false, false);
		d[2][0] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		items.add(If.createItem("encapsulation"));
		room = new DungeonRoom(items, monster, true, false, false, true, false, false, false);
		d[3][0] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("gremlin");
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		d[0][1] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		monster = null;
		room = new DungeonRoom(items, monster, false, true, true, true, false, false, false);
		d[1][1] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		monster = null;
		room = new DungeonRoom(items, monster, true,  false, true, true, false, false, false);
		d[2][1] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		d[3][1] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, true, true, true, false, false, false);
		d[0][2] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("trap"));
		room = new DungeonRoom(items, monster, true, false, true, true, false, false, false);
		d[1][2] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("vision"));
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		d[2][2] = room;
		
		//second to last
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		d[3][2] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("skeleton");
		items.add(If.createItem("inheritance"));
		items.add(If.createItem("heal"));
		
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		d[0][3] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("skeleton");
		items.add(If.createItem("polymorphism"));
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		d[1][3] = room;
		//above last
		monster = null;
		items = new ArrayList<Item>();
		items.add(If.createItem("abstraction"));
		room = new DungeonRoom(items, monster, false, true, true, false, false, false, false);
		d[2][3] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, true, false, true, false, true, false, false);
		d[3][3] = room;
		

		return d;

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
