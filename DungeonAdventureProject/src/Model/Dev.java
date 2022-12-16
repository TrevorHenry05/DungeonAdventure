package Model;

import Utility.Utility;

public class Dev extends Hero{
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the Warrior class
	 * @param theCharacterName
	 */
	public Dev(final String theCharacterName) {
		super(200,200,100,150, 1.0, 6, 0.8, "Warrior", theCharacterName, 0);
	}
	
	/**
	 * The special for the Dev class
	 * 
	 * @param theMonster the special should be performed on
	 */
	@Override
	public boolean special(final DungeonCharacter  theMonster) {
		theMonster.takeDamage(Utility.randomNumberGen(100, 200));
		return true;	
	}
}
