package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.MockMonster;
import Model.Monster;
import Model.MonsterFactory;

public class MonsterFactoryTest {
	
	private MonsterFactory myMonsterFactory;
	
	@BeforeEach
	void setUp() {
		myMonsterFactory = new MonsterFactory();
	}
	
	@Test
	void testCreateHero() {
		Monster h = myMonsterFactory.createMonster("mock");
		assertEquals("MockMonster", h.getMonsterType());
		assertTrue(h instanceof MockMonster);
	}
}
