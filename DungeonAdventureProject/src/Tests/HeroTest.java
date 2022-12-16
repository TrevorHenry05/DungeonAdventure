package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Berserker;
import Model.Dev;
import Model.DungeonRoom;
import Model.Item;
import Model.ItemFactory;
import Model.Monster;
import Model.MonsterFactory;
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
	private Dev d;
	private Warrior w;
	private Priestess p;
	private Thief t;
	private Berserker b;
	private ItemFactory f;
	private Item myItem;
	@BeforeEach
	void setUp() {
		d = new Dev("devtest");
		f = new ItemFactory();
		myItem = f.createItem("heal");
		w = new Warrior("Wart");
		w.setCurrX(3);
		w.setCurrY(2);
		p = new Priestess("Parvati");
		t = new Thief("Trevor");
		b = new Berserker("Brian");
	}
	@Test
	void testReturnName() {
		assertTrue(b.getCharacterName().equals("Brian"));
	}
	@Test
	void testgetCurrX() {
		assertEquals(3, w.getCurrX());
	}
	@Test
	void getBlockChance() {
		assertEquals(0.3, p.getBlockChance());
	}
	@Test
	void testGetHitPoints() {
		assertEquals(110, b.getHitPoints());
	}
	@Test
	void testGetAttacks() {
		assertEquals(0, b.getAttacks());
	}
	@Test
	void testGetInventory() {
		assertEquals(0, t.getInventory().size());
	}
	@Test
	void testGetClassName() {
		assertTrue(p.getClassName().equals("Priestess"));
	}
	@Test
	void testGetCurrY() {
		assertEquals(2, w.getCurrY());
	}
	@Test
	void testGetSetCurrRoom() {
		p.setCurrRoom(new DungeonRoom(new ArrayList<Item>(), null, false, false, false, false, false, false, false));
		assertTrue(p.getCurrRoom() != null);
	}
	@Test
	void testSetAttacks() {
		p.setAttacks(2);
		assertEquals(2, p.getAttacks());
	}
	@Test
	void testAddItemToInventory() {
		t.addItemToInventory(new Item('H', "Heal", true));
		assertEquals(1, t.getInventory().size());
	}
	
	@Test
	void testRemoveUseItems() {
		t.addItemToInventory(new Item('H', "Heal", true));
		t.addItemToInventory(new Item('A', "Abstraction", false));
		t.removeItemFromInventory("Heal");
		assertEquals(1, t.getInventory().size());
		assertTrue(myItem.isUsable());
		
	}

	@Test
	void testUseItem() {
		Item i = f.createItem("vision");
		assertTrue(t.useItem(i));
		i = f.createItem("heal");
		assertFalse(t.useItem(i));

	}

	@Test
	void testAttack() {
		t.setHitPoints(100);
		assertEquals(true, d.attack(t));
		assertFalse(t.isAlive());
		
	}

	@Test
	void testDisplayDungeonNearHero() {
		
	}
	
	@Test
	void testHasPillars() {
		t.addItemToInventory(new Item('A', "Pillar of Abstraction", true));
		t.addItemToInventory(new Item('E', "Pillar of Encapsulation", true));
		t.addItemToInventory(new Item('I', "Pillar of Inheritance", true));
		t.addItemToInventory(new Item('P', "Pillar of Polymorphism", true));
		assertTrue(t.hasPillars());
		
		
	}
	
}
	
