package Tests;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Dev;
import Model.Hero;
import Model.MockMonster;
import Model.Monster;
/**
 * Test class for the Dev class
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
public class DevTest {
	
	private Hero myHero;
	private Monster myMonster;
	
	@BeforeEach
	void setup() {
		myHero = new Dev("test");
		myMonster = new MockMonster();
	}
	
	@Test
	void testSpecial() {
		myHero.special(myMonster);
		assertFalse(myMonster.isAlive());
	}
}
