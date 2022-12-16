package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import Model.Dungeon;
import Model.DungeonRoom;
import Model.Item;
import Model.ItemFactory;
import Model.Monster;
import Model.MonsterFactory;

class DungeonTest {
	private Dungeon d1;
	private Dungeon d2;
	private Dungeon d3;
	private Dungeon d4;
	private DungeonRoom[][] dr;
	private DungeonRoom[][] dr1;
	
	@BeforeEach
	void setUp() {
		d1 = new Dungeon(4,  4);
		d2 = new Dungeon(4,  4);
		d3 = new Dungeon(4,  4);
		dr = new DungeonRoom[4][4];
		dr1 = new DungeonRoom[4][4];
		MonsterFactory mf =  new MonsterFactory();
		ItemFactory If = new ItemFactory();
		
		DungeonRoom room;
		Monster monster;
		List<Item> items;
		//create DungeonRoom array for Dungeon 1
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, true, false, true, false, true, false);
		dr[0][0] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("Ogre");
		items.add(If.createItem("heal")); 
		room = new DungeonRoom(items, monster, true, false, false, true, false, false, false);
		dr[1][0] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		room = new DungeonRoom(items, monster, false, true, false, true, false, false, false);
		dr[2][0] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		items.add(If.createItem("encapsulation"));
		room = new DungeonRoom(items, monster, true, false, false, true, false, false, false);
		dr[3][0] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("gremlin");
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		dr[0][1] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		room = new DungeonRoom(items, monster, false, true, true, true, false, false, false);
		dr[1][1] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		monster = null;
		room = new DungeonRoom(items, monster, true,  false, true, true, false, false, false);
		dr[2][1] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		dr[3][1] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, true, true, true, false, false, false);
		dr[0][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, true, false, true, true, false, false, false);
		dr[1][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		dr[2][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		dr[3][2] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("skeleton");
		items.add(If.createItem("inheritance"));
		items.add(If.createItem("heal"));
		
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		dr[0][3] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("skeleton");
		items.add(If.createItem("polymorphism"));
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		dr[1][3] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		items.add(If.createItem("abstraction"));
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		dr[2][3] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, true, false, true, false, true, false, false);
		dr[3][3] = room;

		d1.setDungeon(dr);
		
		//create DungeonRoom array for Dungeon 2
		DungeonRoom[][] d1 = dr;
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, false, false, true, false, false);
		d1[3][3] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		items.add(If.createItem("abstraction"));
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		d1[2][3] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		d1[3][2] = room;
		d2.setDungeon(d1);
		
		//create DungeonRoom array for Dungeon 3
		DungeonRoom[][] d2 = dr;
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, false, false, true, false, false);
		d2[3][3] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		d2[2][3] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("abstraction"));
		room = new DungeonRoom(items, monster, false, false, false, false, false, false, false);
		d2[3][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		d2[3][1] = room;
		d3.setDungeon(d2);
		
		
		//create d1 DungeonRoom array
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, true, false, true, false, true, false);
		dr1[0][0] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("Ogre");
		items.add(If.createItem("heal")); 
		room = new DungeonRoom(items, monster, true, false, false, true, false, false, false);
		dr1[1][0] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		room = new DungeonRoom(items, monster, false, true, false, true, false, false, false);
		dr1[2][0] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		room = new DungeonRoom(items, monster, true, false, false, true, false, false, false);
		dr1[3][0] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("gremlin");
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		dr1[0][1] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		room = new DungeonRoom(items, monster, false, true, true, true, false, false, false);
		dr1[1][1] = room;
		
		items = new ArrayList<Item>();
		items.add(If.createItem("heal"));
		monster = null;
		room = new DungeonRoom(items, monster, true,  false, true, true, false, false, false);
		dr1[2][1] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		dr1[3][1] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, true, true, true, false, false, false);
		dr1[0][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, true, false, true, true, false, false, false);
		dr1[1][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		dr1[2][2] = room;
		
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, true, false, false, false);
		dr1[3][2] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("skeleton");
		items.add(If.createItem("heal"));	
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		dr1[0][3] = room;
		
		items = new ArrayList<Item>();
		monster = mf.createMonster("skeleton");
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		dr1[1][3] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, false, true, false, false, false, false);
		dr1[2][3] = room;
		
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, true, false, true, false, true, false, false);
		dr1[3][3] = room;
	}
	
	@Test
	void testGetDungeon() {
		assertTrue(d1.getDungeon() == dr);
	}
	
	@Test
	void testSetDungeon() {
		d1.setDungeon(dr);
		assertTrue(d1.getDungeon() == dr);
	}
	
	@Test
	void testIsMazeTraversable() {
		assertTrue(Dungeon.isMazeTraversable(dr1));
		assertFalse(Dungeon.isMazeTraversable(d1.getDungeon()));
	}
	
	@Test
	void testToString() {
		
	}
	

}
