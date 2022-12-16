package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Berserker;
import Model.Dev;
import Model.Dungeon;
import Model.DungeonRoom;
import Model.Hero;
import Model.Item;
import Model.ItemFactory;
import Model.Monster;
import Model.Priestess;
import Model.Thief;
import Model.Warrior;

/**
 * Test class for the Hero class
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */

class HeroTest {
	private Hero myDev;
	private Hero myWarrior;
	private Hero myPriestess;
	private Hero myThief;
	private Hero myBerserker;
	private ItemFactory myItemFactory;
	private Item myItem;
	@BeforeEach
	void setUp() {
		myDev = new Dev("devtest");
		myItemFactory = new ItemFactory();
		myItem = myItemFactory.createItem("heal");
		myWarrior = new Warrior("Wart");
		myWarrior.setCurrX(3);
		myWarrior.setCurrY(2);
		myPriestess = new Priestess("Parvati");
		myThief = new Thief("Trevor");
		myBerserker = new Berserker("Brian");
	}
	@Test
	void testReturnName() {
		assertTrue(myBerserker.getCharacterName().equals("Brian"));
	}
	@Test
	void testgetCurrX() {
		assertEquals(3, myWarrior.getCurrX());
	}
	@Test
	void getBlockChance() {
		assertEquals(0.3, myPriestess.getBlockChance());
	}
	@Test
	void testGetHitPoints() {
		assertEquals(110, myBerserker.getHitPoints());
	}
	@Test
	void testGetAttacks() {
		assertEquals(0, myBerserker.getAttacks());
	}
	@Test
	void testGetInventory() {
		assertEquals(0, myThief.getInventory().size());
	}
	@Test
	void testGetClassName() {
		assertTrue(myPriestess.getClassName().equals("Priestess"));
	}
	@Test
	void testGetCurrY() {
		assertEquals(2, myWarrior.getCurrY());
	}
	@Test
	void testGetSetCurrRoom() {
		myPriestess.setCurrRoom(new DungeonRoom(new ArrayList<Item>(), null, false, false, false, false, false, false, false));
		assertTrue(myPriestess.getCurrRoom() != null);
	}
	@Test
	void testSetAttacks() {
		myPriestess.setAttacks(2);
		assertEquals(2, myPriestess.getAttacks());
	}
	@Test
	void testAddItemToInventory() {
		myThief.addItemToInventory(new Item('H', "Heal", true));
		assertEquals(1, myThief.getInventory().size());
	}
	
	@Test
	void testRemoveUseItems() {
		myThief.addItemToInventory(new Item('H', "Heal", true));
		myThief.addItemToInventory(new Item('A', "Abstraction", false));
		myThief.removeItemFromInventory("Heal");
		assertEquals(1, myThief.getInventory().size());
		assertTrue(myItem.isUsable());
		
	}

	@Test
	void testUseItem() {
		Item i = myItemFactory.createItem("vision");
		assertTrue(myThief.useItem(i));
		i = myItemFactory.createItem("heal");
		assertFalse(myThief.useItem(i));

	}

	@Test
	void testAttack() {
		myThief.setHitPoints(100);
		assertEquals(true, myDev.attack(myThief));
		assertFalse(myThief.isAlive());
		
	}

	@Test
	void testDisplayDungeonNearHero() {
		Dungeon dungeon = new Dungeon(4,4);
		DungeonRoom[][] da = new DungeonRoom[2][2];
		Monster monster;
		List<Item> items;
		DungeonRoom room;
		monster = null;
		items = new ArrayList<Item>();
		room = new DungeonRoom(items, monster, false, true, false, true, false, true, true);
		da[0][0] = room;
		
		room = new DungeonRoom(items, monster, false, true, true, false, false, false, true);
		da[0][1] = room;
		
		room = new DungeonRoom(items, monster, true, false, false, true, false, false, true);
		da[1][0] = room;
		
		room = new DungeonRoom(items, monster, true, false, true, false, true, false, true);
		da[1][1] = room;
		dungeon.setDungeon(da);
		
		myDev.setCurrX(0);
		myDev.setCurrY(0);
		
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
		
		assertEquals(sb.toString(), myDev.displayDungeonNearHero(dungeon));
	}
	
	@Test
	void testSpecial() {
		assertTrue(myDev.special(myThief));
		assertFalse(myThief.isAlive());
	}
	
	@Test
	void testToString() {
		myDev.addItemToInventory(myItemFactory.createItem("heal"));
		myDev.addItemToInventory(myItemFactory.createItem("vision"));
		
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + myDev.getCharacterName());
		sb.append(System.lineSeparator());
		sb.append("Hit Points: " + myDev.getHitPoints());
		sb.append(System.lineSeparator());
		sb.append("Total Heal Potions: " + 1);
		sb.append(System.lineSeparator());
		sb.append("Total Vision Pottions: " + 1);
		sb.append(System.lineSeparator());
		sb.append("Pillars Found:\n");
		
		assertEquals(sb.toString(), myDev.toString());
	}
	
	@Test
	void testHasPillars() {
		myThief.addItemToInventory(new Item('A', "Pillar of Abstraction", true));
		myThief.addItemToInventory(new Item('E', "Pillar of Encapsulation", true));
		myThief.addItemToInventory(new Item('I', "Pillar of Inheritance", true));
		myThief.addItemToInventory(new Item('P', "Pillar of Polymorphism", true));
		assertTrue(myThief.hasPillars());
		
		
	}
	
}
	
