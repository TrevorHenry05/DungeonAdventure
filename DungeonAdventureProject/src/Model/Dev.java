package Model;

import Utility.Utility;

/**
 * A class that creates a new dev hero and stores the special ability of the dev class. The dev class is used for the developers to quickly navigate through the game
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
public class Dev extends Hero {
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the Warrior class that stores, the max health, maximum and minimum damage the character can deal, the chance to land an attack, the chance 
	 * to block an attack and the names of both the hero and the user.
	 * @param theCharacterName
	 */
	public Dev(final String theCharacterName) {
		super(200,200,100,150, 1.0, 6, 0.7, "Warrior", theCharacterName, 0);
	}
	
	/**
	 * The chance of the warrior's special ability being successful and the textual representation of the process of the special attack
	 * @param theMonster
	 */
	@Override
	public boolean special(final DungeonCharacter  theMonster) {
		
		theMonster.takeDamage(Utility.randomNumberGen(200, 200));
		return true;
		
	}
}
