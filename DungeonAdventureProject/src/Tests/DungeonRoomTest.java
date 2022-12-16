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
/**
 * Test class for the DungeonRoom class
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
class DungeonRoomTest {
	
	private DungeonRoom myDungeonRoom1;
	private DungeonRoom myDungeonRoom2;
	private DungeonRoom myDungeonRoom3;
	private DungeonRoom myDungeonRoom4;
	private MonsterFactory myMonsterfactory;
	private Hero myHero;
	private ItemFactory myItemFactory;
	
	@BeforeEach
	void setUp() {
		myItemFactory= new ItemFactory();
		myHero = new Warrior("John");
		myMonsterfactory = new MonsterFactory();
		myDungeonRoom1 = new DungeonRoom(new ArrayList<Item>(), null, false, false, false, false, false, false, false);
		myDungeonRoom1.addItem(myItemFactory.createItem("heal"));
		myDungeonRoom2 = new DungeonRoom(new ArrayList<Item>(), null, true, true, true, true, true, true, false);
		myDungeonRoom3 = new DungeonRoom(new ArrayList<Item>(), null, true, false, true, false, false, false, false);
		myDungeonRoom3.addItem(myItemFactory.createItem("trap"));
		myDungeonRoom3.addItem(myItemFactory.createItem("vision"));
		myDungeonRoom3.addItem(myItemFactory.createItem("abstraction"));
		myDungeonRoom4 = new DungeonRoom(new ArrayList<Item>(), myMonsterfactory.createMonster("ogre"), false, true, false, true, false, false, false);
	}
	
	@Test
	void testIsMonster() {
		assertFalse(myDungeonRoom1.isMonster());
		assertTrue(myDungeonRoom4.isMonster());
		assertFalse(myDungeonRoom3.isMonster());
	}
	
	@Test
	void testSetMonster() {
		myDungeonRoom1.setMonster(myMonsterfactory.createMonster("ogre"));
		assertTrue(myDungeonRoom1.isMonster());
		myDungeonRoom4.setMonster(null);
		assertFalse(myDungeonRoom4.isMonster());
	}
	
	@Test
	void testGetMonster() {
		Monster monster = myMonsterfactory.createMonster("ogre");
		myDungeonRoom1.setMonster(monster);
		assertEquals(monster, myDungeonRoom1.getMonster());
	}
	
	@Test
	void testGetItems() {
		assertEquals(1, myDungeonRoom1.getItemsInRoom().size());
	}
	@Test
	void testIsNorth() {
		assertFalse(myDungeonRoom1.isNorth());
		assertTrue(myDungeonRoom2.isNorth());
		assertTrue(myDungeonRoom3.isNorth());
	}
	@Test
	void testIsSouth() {
		assertFalse(myDungeonRoom1.isSouth());
		assertTrue(myDungeonRoom2.isSouth());
		assertFalse(myDungeonRoom3.isSouth());
	}
	@Test
	void testIsWest() {
		assertFalse(myDungeonRoom1.isWest());
		assertTrue(myDungeonRoom2.isWest());
	}
	@Test
	void testIsEast() {
		assertFalse(myDungeonRoom1.isEast());
		assertTrue(myDungeonRoom2.isEast());
	}
	
	@Test
	void testIsExit() {
		assertFalse(myDungeonRoom1.isExit());
		assertTrue(myDungeonRoom2.isExit());
	}
	
	@Test
	void testIsEntrance() {
		assertFalse(myDungeonRoom1.isEntrance());
		assertTrue(myDungeonRoom2.isEntrance());
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
		assertEquals(sb.toString(), myDungeonRoom1.toString());
		
		sb2.append("*-*");
		sb2.append(System.lineSeparator());
		sb2.append("|M*");
		sb2.append(System.lineSeparator());
		sb2.append("***");
		sb2.append(System.lineSeparator());
		assertEquals(sb2.toString(), myDungeonRoom3.toString());
	}
	@Test
	void testGetRoom() {
		char[][] p = myDungeonRoom1.getRoom();
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
		char[][] aRoom = myDungeonRoom1.getRoom();
		char[][] created = myDungeonRoom1.createRoom();
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
		myDungeonRoom1.setRoom();
		char[][] p = myDungeonRoom1.getRoom();
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
		assertEquals(sb.toString(),myDungeonRoom1.removeItemsFromRoom(myHero));
		assertEquals(0, myDungeonRoom1.getItemsInRoom().size());
		assertEquals(1, myHero.getInventory().size());
	}
	
	@Test
	void testAddItem() {
		myDungeonRoom1.addItem(myItemFactory.createItem("heal"));
		assertEquals(2, myDungeonRoom1.getItemsInRoom().size());
	}
	
	@Test
	void testIsChecked() {
		assertFalse(myDungeonRoom1.isChecked());
		assertFalse(myDungeonRoom2.isChecked());
		assertFalse(myDungeonRoom3.isChecked());
	}
	
	@Test
	void testSetRoomChecked() {
		myDungeonRoom1.setRoomChecked(true);
		myDungeonRoom2.setRoomChecked(true);
		myDungeonRoom3.setRoomChecked(true);
		assertTrue(myDungeonRoom1.isChecked());
		assertTrue(myDungeonRoom2.isChecked());
		assertTrue(myDungeonRoom3.isChecked());
	}
	
	@Test
	void testContainsPillar() {
		assertFalse(myDungeonRoom1.containsPillar());
		assertTrue(myDungeonRoom3.containsPillar());
	}

}
