package Model;

import java.util.ArrayList;
import java.util.List;
//import java.util.List;

public class Driver {
	public static void main(final String[] theArgs) {
//		List<Item> items = new ArrayList<Item>();
//		DungeonRoom room = new DungeonRoom(items);
		Dungeon dungeon = new Dungeon();
		System.out.println(dungeon.toString());
	}

}
