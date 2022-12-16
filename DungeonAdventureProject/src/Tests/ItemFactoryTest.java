package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Item;
import Model.ItemFactory;
/**
 * Test class for the ItemFactory class
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
public class ItemFactoryTest {
	
	private ItemFactory myItemFactory;
	
	@BeforeEach
	void setUp() {
		myItemFactory = new ItemFactory();
	}
	
	@Test
	void testCreateHero() {
		Item h = myItemFactory.createItem("heal");
		assertEquals('H', h.getType());
		assertEquals("Heal", h.getDescription());
		assertTrue(h instanceof Item);
	}
}
