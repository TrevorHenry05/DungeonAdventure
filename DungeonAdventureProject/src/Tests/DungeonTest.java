package Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Model.Dungeon;
import Model.DungeonRoom;
import Model.Item;
import Model.ItemFactory;
import Model.Monster;
import Model.MonsterFactory;
/**
 * Test class for the Dungeon class
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
class DungeonTest {
	
	private Dungeon myDungeon1;
	private Dungeon myDungeon2;
	private Dungeon myDungeon3;
	private DungeonRoom[][] myDungeonArray1;
	private DungeonRoom[][] myDungeonArray2;
	private DungeonRoom[][] myDungeonArray3;
	
	@BeforeEach
	void setUp() {
		myDungeon1 = new Dungeon(4,  4);
		myDungeon2 = new Dungeon(4,  4);
		myDungeon3 = new Dungeon(4, 4);
		myDungeonArray1 = new DungeonRoom[4][4];
		myDungeonArray2 = new DungeonRoom[4][4];
		myDungeonArray3 = new DungeonRoom[2][2];
		
		MonsterFactory mf =  new MonsterFactory();
		ItemFactory If = new ItemFactory();
		
		DungeonRoom room;
		Monster monster;
		List<Item> items;
		
		//create DungeonRoom array for dr
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, true, false, true, false, true, false);
		myDungeonArray1[0][0] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("Ogre");
		items.add(If.createItem("heal")); 
		room = new DungeonRoom(items, monster, true, false, false, true, false, false, false);
		myDungeonArray1[1][0] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		room = new DungeonRoom(items, monster, false, true, false, true, false, false, false);
		myDungeonArray1[2][0] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		items.add(If.createItem("encapsulation"));
		room = new DungeonRoom(items, monster, true, false, false, true, false, false, false);
		myDungeonArray1[3][0] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("gremlin");
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		myDungeonArray1[0][1] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		room = new DungeonRoom(items, monster, false, true, true, true, false, false, false);
		myDungeonArray1[1][1] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		monster = null;
		room = new DungeonRoom(items, monster, true,  false, true, true, false, false, false);
		myDungeonArray1[2][1] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		myDungeonArray1[3][1] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, true, true, true, false, false, false);
		myDungeonArray1[0][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, true, false, true, true, false, false, false);
		myDungeonArray1[1][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		myDungeonArray1[2][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		myDungeonArray1[3][2] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("skeleton");
		items.add(If.createItem("inheritance"));
		items.add(If.createItem("heal"));
		
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		myDungeonArray1[0][3] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("skeleton");
		items.add(If.createItem("polymorphism"));
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		myDungeonArray1[1][3] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		items.add(If.createItem("abstraction"));
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		myDungeonArray1[2][3] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, false, false, true, false, false);
		myDungeonArray1[3][3] = room;

		myDungeon1.setDungeon(myDungeonArray1);
		
		
		//create dr1 DungeonRoom array
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, true, false, true, false, true, false);
		myDungeonArray2[0][0] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("Ogre");
		items.add(If.createItem("heal")); 
		room = new DungeonRoom(items, monster, true, false, false, true, false, false, false);
		myDungeonArray2[1][0] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		room = new DungeonRoom(items, monster, false, true, false, true, false, false, false);
		myDungeonArray2[2][0] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		room = new DungeonRoom(items, monster, true, false, false, true, false, false, false);
		myDungeonArray2[3][0] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("gremlin");
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		myDungeonArray2[0][1] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		room = new DungeonRoom(items, monster, false, true, true, true, false, false, false);
		myDungeonArray2[1][1] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		monster = null;
		room = new DungeonRoom(items, monster, true,  false, true, true, false, false, false);
		myDungeonArray2[2][1] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		myDungeonArray2[3][1] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, true, true, true, false, false, false);
		myDungeonArray2[0][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, true, false, true, true, false, false, false);
		myDungeonArray2[1][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		myDungeonArray2[2][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		myDungeonArray2[3][2] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("skeleton");
		items.add(If.createItem("heal"));	
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		myDungeonArray2[0][3] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("skeleton");
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		myDungeonArray2[1][3] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		myDungeonArray2[2][3] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, true, false, true, false, true, false, false);
		myDungeonArray2[3][3] = room;
		
		//create DungeonRoom Array for dr2 ( for reset isChecked )
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, true, false, true, false, true, true);
		myDungeonArray3[0][0] = room;
		
		room = new DungeonRoom(items, monster, false, true, true, false, false, false, true);
		myDungeonArray3[0][1] = room;
		
		room = new DungeonRoom(items, monster, true, false, false, true, false, false, true);
		myDungeonArray3[1][0] = room;
		
		room = new DungeonRoom(items, monster, true, false, true, false, true, false, true);
		myDungeonArray3[1][1] = room;
	}
	
	@Test
	void testGetDungeon() {
		assertTrue(myDungeon1.getDungeon() == myDungeonArray1);
	}
	
	@Test
	void testSetDungeon() {
		myDungeon1.setDungeon(myDungeonArray2);
		assertTrue(myDungeon1.getDungeon() == myDungeonArray2);
	}
	
	@Test
	void testIsMazeTraversable() {
		assertTrue(Dungeon.isMazeTraversable(myDungeonArray2));
		assertFalse(Dungeon.isMazeTraversable(myDungeon1.getDungeon()));
	}
	
	@Test
	void testIsPillarsObtainable() {
		assertFalse(Dungeon.isPillarsObtainable(myDungeonArray2));
		assertTrue(Dungeon.isPillarsObtainable(myDungeon2.getDungeon()));
	}
	
	@Test
	void testPlacePillars() {
		Dungeon.placePillars(myDungeonArray2);
		assertTrue(Dungeon.isPillarsObtainable(myDungeonArray2));
	}
	
	@Test
    void testResetRoomsChecked() {
		Dungeon.resetRoomsChecked(myDungeonArray3);
		assertFalse(myDungeonArray3[0][0].isChecked());
		assertFalse(myDungeonArray3[0][1].isChecked());
		assertFalse(myDungeonArray3[1][0].isChecked());
		assertFalse(myDungeonArray3[1][1].isChecked());
	}
	
	@Test
	void testIsNorthDoor() {
		assertTrue(Dungeon.isNorthDoor(myDungeonArray3, 1, 0));
		assertFalse(Dungeon.isNorthDoor(myDungeonArray3, 0, 0));
	}
	
	@Test
	void testIsWestDoor() {
		assertTrue(Dungeon.isWestDoor(myDungeonArray3, 1, 1));
		assertFalse(Dungeon.isWestDoor(myDungeonArray3, 0, 0));
	}
	
	@Test
	void testIsEastDoor() {
		assertFalse(Dungeon.isEastDoor(myDungeonArray3, 0, 1));
		assertFalse(Dungeon.isEastDoor(myDungeonArray3, 1, 1));
	}
	
	@Test
	void testIsSouthDoor() {
		assertFalse(Dungeon.isSouthDoor(myDungeonArray3, 1, 0));
		assertFalse(Dungeon.isSouthDoor(myDungeonArray3, 1, 1));
	}
	
	@Test
	void testCreateDungeon() {
		myDungeon3 = new Dungeon(6, 6);
		assertTrue(Dungeon.isMazeTraversable(myDungeon3.getDungeon()));
		assertTrue(Dungeon.isPillarsObtainable(myDungeon3.getDungeon()));
	}
	
	@Test
	void testToString() {
		StringBuilder sb = new StringBuilder();
		sb.append("******");
		sb.append(System.lineSeparator());
		sb.append("*i|| *");
		sb.append(System.lineSeparator());
		sb.append("*-**-*");
		sb.append(System.lineSeparator());
		sb.append("*-**-*");
		sb.append(System.lineSeparator());
		sb.append("* ||O*");
		sb.append(System.lineSeparator());
		sb.append("******");
		sb.append(System.lineSeparator());
		
		myDungeon3.setDungeon(myDungeonArray3);
		
		assertEquals(sb.toString(), myDungeon3.toString());
	}
	

}
