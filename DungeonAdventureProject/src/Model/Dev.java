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
		super(200,200,150,150, 1.0, 6, 0.0, "Warrior", theCharacterName, 0);
	}
	
	/**
	 * The special for the Dev class
	 * 
	 * @param theMonster the special should be performed on
	 */
	@Override
	public boolean special(final DungeonCharacter  theMonster) {
		theMonster.takeDamage(Utility.randomNumberGen(200, 200));
		return true;	
	}
}
