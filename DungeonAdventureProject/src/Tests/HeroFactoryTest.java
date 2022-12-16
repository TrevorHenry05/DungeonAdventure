package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Hero;
import Model.HeroFactory;
import Model.Warrior;

public class HeroFactoryTest {
	
	private HeroFactory myHeroFactory;
	
	@BeforeEach
	void setUp() {
		myHeroFactory = new HeroFactory();
	}
	
	@Test
	void testCreateHero() {
		Hero h = myHeroFactory.createHero("warrior", "test");
		assertEquals("Warrior", h.getClassName());
		assertEquals("test", h.getCharacterName());
		assertTrue(h instanceof Warrior);
	}
}
