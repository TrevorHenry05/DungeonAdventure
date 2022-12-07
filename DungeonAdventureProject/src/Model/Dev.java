package Model;

import Utility.Utility;

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
		
		theMonster.takeDamage(Utility.randomNumberGen(125, 200));
		return true;
		
	}
}
