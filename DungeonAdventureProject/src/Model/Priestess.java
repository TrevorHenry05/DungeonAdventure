package Model;

import Utility.Utility;

public class Priestess extends Hero {
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the Priestess class
	 * @param theCharacterName the User picked for his Hero
	 */
	public Priestess(final String theCharacterName) {
		//Health, MinDMG, MaxDMG, Hit%, AtkSpd, Block%
		super(75, 75, 25, 45, 0.7, 5, 0.3, "Priestess", theCharacterName, 0);
	}
	
	/**
	 * The special for the Priestess class
	 * 
	 * @param theCharacter the special should be performed on
	 */
	@Override
	public boolean special(final DungeonCharacter theCharacter) {
		int newHealth = getHitPoints() + Utility.randomNumberGen(20,40);
		if(newHealth >= getMaxHitPoints()) {
			theCharacter.setHitPoints(getMaxHitPoints());
		} else {
			theCharacter.setHitPoints(newHealth);
		}

		return true;
	}
}
