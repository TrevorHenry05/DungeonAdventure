package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.DungeonRoom;
import Model.Hero;
import Model.Item;
import Model.ItemFactory;
import Model.Monster;
import Model.MonsterFactory;
import Model.Warrior;

class DungeonRoomTest {
	private DungeonRoom a;
	private DungeonRoom b;
	private DungeonRoom c;
	private DungeonRoom d;
	private MonsterFactory m = new MonsterFactory();
	private Hero h = new Warrior("John");
	private ItemFactory i = new ItemFactory();
	
	@BeforeEach
	void setUp() {
		a = new DungeonRoom(new ArrayList<Item>(), null, false, false, false, false, false, false, false);
		a.addItem(i.createItem("heal"));
		b = new DungeonRoom(new ArrayList<Item>(), null, true, true, true, true, true, true, false);
		c = new DungeonRoom(new ArrayList<Item>(), null, true, false, true, false, false, false, false);
		c.addItem(i.createItem("trap"));
		c.addItem(i.createItem("vision"));
		c.addItem(i.createItem("abstraction"));
		d = new DungeonRoom(new ArrayList<Item>(), m.createMonster("ogre"), false, true, false, true, false, false, false);
	}
	@Test
	void testIsMonster() {
		assertFalse(a.isMonster());
		assertTrue(d.isMonster());
		assertFalse(c.isMonster());
	}
	
	@Test
	void testSetMonster() {
		a.setMonster(m.createMonster("ogre"));
		assertTrue(a.isMonster());
		d.setMonster(null);
		assertFalse(d.isMonster());
	}
	
	@Test
	void testGetMonster() {
		Monster monster = m.createMonster("ogre");
		a.setMonster(monster);
		assertEquals(monster, a.getMonster());
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
	void testIsExit() {
		assertFalse(a.isExit());
		assertTrue(b.isExit());
	}
	
	@Test
	void testIsEntrance() {
		assertFalse(a.isEntrance());
		assertTrue(b.isEntrance());
	}
	
	@Test
	void testToString() {
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		
		sb.append("***");
		sb.append(System.lineSeparator());
		sb.append("*H*");
		sb.append(System.lineSeparator());
		sb.append("***");
		sb.append(System.lineSeparator());
		assertEquals(sb.toString(), a.toString());
		
		sb2.append("*-*");
		sb2.append(System.lineSeparator());
		sb2.append("|M*");
		sb2.append(System.lineSeparator());
		sb2.append("***");
		sb2.append(System.lineSeparator());
		assertEquals(sb2.toString(), c.toString());
	}
	@Test
	void testGetRoom() {
		char[][] p = a.getRoom();
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
	void testCreateRoom() {
		char[][] aRoom = a.getRoom();
		char[][] created = a.createRoom();
		assertTrue(aRoom[0][0] == created[0][0]);
		assertTrue(aRoom[0][1] == created[0][1]);
		assertTrue(aRoom[0][2] == created[0][2]);
		assertTrue(aRoom[1][0] == created[1][0]);
		assertTrue(aRoom[1][1] == created[1][1]);
		assertTrue(aRoom[1][2] == created[1][2]);
		assertTrue(aRoom[2][0] == created[2][0]);
		assertTrue(aRoom[2][1] == created[2][1]);
		assertTrue(aRoom[2][2] == created[2][2]);
	}
	
	@Test
	void testSetRoom() {
		a.setRoom();
		char[][] p = a.getRoom();
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
		StringBuilder sb = new StringBuilder();
		sb.append("You obtained a Heal potion\n");
		assertEquals(sb.toString(),a.removeItemsFromRoom(h));
		assertEquals(0, a.getItemsInRoom().size());
		assertEquals(1, h.getInventory().size());
	}
	
	@Test
	void testAddItem() {
		a.addItem(i.createItem("heal"));
		assertEquals(2, a.getItemsInRoom().size());
	}
	
	@Test
	void testIsChecked() {
		assertFalse(a.isChecked());
		assertFalse(b.isChecked());
		assertFalse(c.isChecked());
	}
	
	@Test
	void testSetRoomChecked() {
		a.setRoomChecked(true);
		b.setRoomChecked(true);
		c.setRoomChecked(true);
		assertTrue(a.isChecked());
		assertTrue(b.isChecked());
		assertTrue(c.isChecked());
	}
	
	@Test
	void testContainsPillar() {
		assertFalse(a.containsPillar());
		assertTrue(c.containsPillar());
	}

}
