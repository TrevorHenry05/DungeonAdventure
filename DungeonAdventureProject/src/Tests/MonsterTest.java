package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Monster;
import Model.MonsterFactory;

class MonsterTest {
	private Monster m;
	private MonsterFactory f = new MonsterFactory();
	@BeforeEach
	void setUp() {
		m = f.createMonster(("Ogre"));
	}
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
