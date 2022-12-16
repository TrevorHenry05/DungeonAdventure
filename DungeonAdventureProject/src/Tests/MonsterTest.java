package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Hero;
import Model.HeroFactory;
import Model.Monster;
import Model.MonsterFactory;

/**
 * Test class for the Monster class
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */

class MonsterTest {
	private Monster m;
	private Hero a;
	private MonsterFactory f;
	private HeroFactory h;
	
	
	@BeforeEach
	void setUp() {
		h = new HeroFactory();
		f = new MonsterFactory();
		a = h.createHero("dev", "chartest");
		m = f.createMonster(("Mock"));
	}
	
	@Test
	void testGetHealChance() {
		assertEquals(1.0, m.getHealChance());
	}

	@Test
	void testGetHealMin() {
		assertEquals(20, m.getHealMin());
	}
	
	@Test
	void testGetHealMax() {
		assertEquals(20, m.getHealMax());
	}
	
	@Test
	void testGetMonsterType() {
		assertEquals("MockMonster", m.getMonsterType());
		
	}
	
	@Test
	void testHeal() {
		m.setHitPoints(100);
		assertEquals(true, m.heal());
		assertEquals(120, m.getHitPoints());
		
	}
	
	@Test
	void testAttack() {
		a.setHitPoints(100);
		assertEquals(true, m.attack(a));
		assertFalse(a.isAlive()); //Because MockMonster does so much damage
		
	}
	
	
	
	
	
	
	
	
}
