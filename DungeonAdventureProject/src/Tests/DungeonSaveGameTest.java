package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Dungeon;
import Model.DungeonSaveGame;
import Model.Hero;
import Model.Warrior;
/**
 * Test class for the DungeonSaveGame class
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
public class DungeonSaveGameTest {

	private DungeonSaveGame myDungeonSaveGame;
	private Hero myHero;
	private Dungeon myDungeon;
	
	@BeforeEach
	void setup() {
		myHero = new Warrior("test");
		myDungeon = new Dungeon(5, 5);
		myDungeonSaveGame = new DungeonSaveGame(myDungeon, myHero);
	}
	
	@Test
	void testGetDungeon() {
		assertEquals(myDungeonSaveGame.getDungeon(), myDungeon);
	}
	
	@Test
	void testGetHero() {
		assertEquals(myDungeonSaveGame.getHero(), myHero);
	}
}
