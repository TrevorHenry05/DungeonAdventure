package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.DungeonRoom;
import Model.Hero;
import Model.Item;
import Model.ItemFactory;
import Model.MonsterFactory;
import Model.Warrior;

class DungeonRoomTest {
	private DungeonRoom a;
	private DungeonRoom b;
	private DungeonRoom c;
	private MonsterFactory m = new MonsterFactory();
	private Hero d = new Warrior("John");
	private ItemFactory i = new ItemFactory();
	@BeforeEach
	void setUp() {
		a = new DungeonRoom(new ArrayList<Item>(), null, false, false, false, false);
		a.addItem(i.createItem("heal"));
		b = new DungeonRoom(new ArrayList<Item>(), m.createMonster("ogre"), true, true, true, true, true);
		c = new DungeonRoom(new ArrayList<Item>(), null, true, false, true, false, false);
		c.addItem(i.createItem("trap"));
		c.addItem(i.createItem("vision"));
		c.addItem(i.createItem("abstraction"));
	}
	@Test
	void testIsMonster() {
		assertFalse(a.isMonster());
		assertTrue(b.isMonster());
		assertFalse(c.isMonster());
	}
	@Test
	void testGetItems() {
		assertEquals(1, a.getItemsInRoom().size());
	}
	@Test
	void testIsNorth() {
		assertFalse(a.isNorth());
		assertTrue(b.isNorth());
		assertTrue(c.isNorth());
	}
	@Test
	void testIsSouth() {
		assertFalse(a.isSouth());
		assertTrue(b.isSouth());
		assertFalse(c.isSouth());
	}
	@Test
	void testIsWest() {
		assertFalse(a.isWest());
		assertTrue(b.isWest());
	}
	@Test
	void testIsEast() {
		assertFalse(a.isEast());
		assertTrue(b.isEast());
	}
	@Test
	void testIsFinalRoom() {
		assertFalse(a.isFinalRoom());
		assertTrue(b.isFinalRoom());
	}
	@Test
	void testToString() {
		//a.addItem(new Item('H', "Heal", true));
		//System.out.println(a.getItemsInRoom().get(0).getType());
		assertEquals("***\r\n*H*\r\n***\r\n", a.toString());
		assertEquals("*-*\r\n|M|\r\n*-*\r\n", b.toString());
		assertEquals("*-*\r\n|S*\r\n***\r\n", c.toString());
	}
	@Test
	void testGetRoom() {
		char[][] p = a.getRoom();
		//assertTrue(a.getRoom().equals(new char[][]{{'*', '*', '*'}, {'*', 'H', '*'}, {'*', '*', '*'}}));
		assertTrue(p[0][0] == '*');
		assertTrue(p[0][1] == '*');
		assertTrue(p[0][2] == '*');
		assertTrue(p[1][0] == '*');
		assertTrue(p[1][1] == 'H');
		assertTrue(p[1][2] == '*');
		assertTrue(p[2][0] == '*');
		assertTrue(p[2][1] == '*');
		assertTrue(p[2][2] == '*');
	}
	@Test
	void testRemoveItemFromRoom() {
		c.removeItemsFromRoom(d);
		assertEquals(0, c.getItemsInRoom().size());
		assertEquals(2, d.getInventory().size());
	}

}
