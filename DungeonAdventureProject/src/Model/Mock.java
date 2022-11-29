package Model;

import Utility.Utility;

public class Mock extends Hero{
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the Warrior class
	 * @param theCharacterName
	 */
	public Mock(final String theCharacterName) {
		super(200,200,80,80, 1.0, 4, 0.2, "Mock", theCharacterName, 0);
	}
	
	/**
	 * The chance the warrior's special attack lands
	 * @param theMonster
	 */
	@Override
	public boolean special(final DungeonCharacter  theMonster) {
		int chanceHit = Utility.randomNumberGen(0,100);
		
		if(chanceHit < 90) {
			theMonster.takeDamage(Utility.randomNumberGen(75, 175));
			return true;
		}
		return false;
	}
}
