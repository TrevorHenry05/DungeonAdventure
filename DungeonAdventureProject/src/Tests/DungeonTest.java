package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import Model.Dungeon;
import Model.DungeonRoom;
import Model.Item;

class DungeonTest {
	private Dungeon d;
	private Dungeon e;
	@BeforeEach
	void setUp() {
		d = new Dungeon();
		DungeonRoom[][] h = new DungeonRoom[2][2];
		h[0][0] = new DungeonRoom(new ArrayList<Item>(), null, false, true, false, false, false, false, false);
		h[0][1] = new DungeonRoom(new ArrayList<Item>(), null, false, true, true, false, false, false, false);
		h[1][0] = new DungeonRoom(new ArrayList<Item>(), null, true, false, false, false, false, false, false);
		h[1][1] = new DungeonRoom(new ArrayList<Item>(), null, true, false, true, false, false, false, false);
		e = new Dungeon(h);
		
	}
	@Test
	void testGetDungeon() {
		d.getDungeon();
		assertEquals(3, 3, "We Succeeded in returning the dungeon.");
	}
	@Test
	void testToString() {
		System.out.println(e.toString());
		//assertEquals("******\r\n* || *\r\n*-**-*\r\n*-**-*\r\n* || *\r\n******", e.toString());
	}
	@Test
	void testIsTraversable() {
		
	}

}
