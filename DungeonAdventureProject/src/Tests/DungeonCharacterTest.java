package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.DungeonCharacter;
import Model.HeroFactory;

/**
 * Test class for the DungeonCharacter class
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */

class DungeonCharacterTest {
	private DungeonCharacter myCharacterTest;
	private HeroFactory h = new HeroFactory();
	
	@BeforeEach 
	void setUp() {
	myCharacterTest = h.createHero("dev", "chartest");

	
	}
	
	@Test
	void testGetHitPoints() {
		assertEquals(200, myCharacterTest.getHitPoints());
		
	}
	
	@Test
	void testGetMaxHitPoints() {
		assertEquals(200, myCharacterTest.getMaxHitPoints());
		
		}
	
	@Test
	void testSetHitPoints() {
		myCharacterTest.setHitPoints(150);
		assertEquals(150, myCharacterTest.getHitPoints());
		
	}
	
	@Test
	void testGetChanceToHit() {
		assertEquals(1.0, myCharacterTest.getChanceToHit());
		
	}
	
	@Test
	void testGetMaxDamage() {
		assertEquals(150, myCharacterTest.getMaxDamage());
	}

	@Test
	void testGetMinDamage() {
		assertEquals(150, myCharacterTest.getMinDamage());
	}
	
	@Test
	void testGetAttackSpeed() {
		assertEquals(6, myCharacterTest.getAttackSpeed());
	}
	
	@Test
	void testIsAlive() {
		assertTrue(myCharacterTest.isAlive());
		myCharacterTest.setHitPoints(0);
		assertFalse(myCharacterTest.isAlive());
	}
	
	@Test
	void testtakeDamage() {
		myCharacterTest.setHitPoints(150);
		myCharacterTest.takeDamage(60);
		assertEquals(90, myCharacterTest.getHitPoints());
	}
	
	
	
	
	
	
}
