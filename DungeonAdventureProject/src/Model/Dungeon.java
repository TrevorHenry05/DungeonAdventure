package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dungeon {
	private final DungeonRoom[][] myDungeon;
	private final Random r = new Random();
	
	public Dungeon() {
		myDungeon = createDungeon();
	}
	
	public DungeonRoom[][] getDungeon() {
		return myDungeon;
	}
	
	public Dungeon(DungeonRoom[][] d) {
		myDungeon = d;
	}
	public DungeonRoom[][] createDungeon() {
		DungeonRoom[][] d = new DungeonRoom[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				List<Item> items = new ArrayList<Item>();
				if (r.nextDouble() < 0.1) {
					items.add(new Item('H', "Health Potion", true));
				}
				if (r.nextDouble() < 0.1) {
					items.add(new Item('V', "Vision Potion", true));
				}
				d[i][j] = new DungeonRoom(items, null, i > 0, i < 4, j > 0, j < 4);
			}
		}
//		items = new ArrayList<Item>();
//		room = new DungeonRoom(items, monster, false, false, true, true, false);
//		d[3][2] = room;
//		
//		items = new ArrayList<Item>();
//		monster = mf.createMonster("skeleton");
//		items.add(If.createItem("inheritance"));
//		items.add(If.createItem("heal"));
//		
//		room = new DungeonRoom(items, monster, false, false, true, false, false);
//		d[0][3] = room;
//		
//		items = new ArrayList<Item>();
//		items.add(If.createItem("polymorphism"));
//		room = new DungeonRoom(items, monster, false, false, true, false, false);
//		d[1][3] = room;
//		
//		monster = null;
//		items = new ArrayList<Item>();
//		items.add(If.createItem("abstraction"));
//		room = new DungeonRoom(items, monster, false, true, true, false, false);
//		d[2][3] = room;
//		
//		monster = null;
//		items = new ArrayList<Item>();
//		room = new DungeonRoom(items, monster, true, false, true, false, true);
//		d[3][3] = room;
		

		return d;

}
	
	public boolean isMazeTraversible(final DungeonRoom[][] theDungeon) {
		
		
		
		return false;
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
