package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Item;
import Model.ItemFactory;

public class ItemTest {

	private ItemFactory myItemFactory;
	private Item myItem1;
	private Item myItem2;
	
	@BeforeEach
	void setUp() {
		myItemFactory = new ItemFactory();
		myItem1 = myItemFactory.createItem("heal");
		myItem2 = myItemFactory.createItem("inheritance");
	}
	
	@Test
	void testGetType() {
		assertEquals('H', myItem1.getType());
		assertEquals('I', myItem2.getType());
	}
	
	@Test
	void testGetDescription() {
		assertEquals("Heal", myItem1.getDescription());
		assertEquals("Pillar of Inheritance", myItem2.getDescription());
	}
	
	@Test
	void testIsUable() {
		assertTrue(myItem1.isUsable());
		assertFalse(myItem2.isUsable());
	}
}
