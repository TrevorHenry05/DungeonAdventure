package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Berserker;
import Model.DungeonRoom;
import Model.Item;
import Model.Priestess;
import Model.Thief;
import Model.Warrior;

class HeroTest {
	private Warrior w;
	private Priestess p;
	private Thief t;
	private Berserker b;
	@BeforeEach
	void setUp() {
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
	void testShowInventory() {
		t.addItemToInventory(new Item('H', "Heal", true));
		assertEquals("Heal\r\n", t.showInventory());
	}
	@Test
	void testRemoveUseItems() {
		t.addItemToInventory(new Item('H', "Heal", true));
		t.addItemToInventory(new Item('A', "Abstraction", false));
		Item i = t.removeItemFromInventory("Heal");
		t.setHitPoints(t.getHitPoints() - 20);
		int h = t.getHitPoints();
		assertEquals(1, t.getInventory().size());
		assertTrue(t.useItem(i));
		assertTrue(h < t.getHitPoints());
		assertFalse(t.useItem(t.getInventory().get(0)));
		
	}

}
