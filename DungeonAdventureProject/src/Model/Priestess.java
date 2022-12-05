package Model;

import Utility.Utility;

public class Priestess extends Hero {
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the Priestess class that stores, the max health, maximum and minimum damage the character can deal, the chance to land an attack, the chance 
	 * to block an attack and the names of both the hero and the user.
	 * @param theCharacterName
	 */
	public Priestess(final String theCharacterName) {
		super(75, 75, 25, 45, 0.7, 5, 0.3, "Priestess", theCharacterName, 0);
	}
	
	/**
	 * The chance of the priestess's special ability being successful and the textual representation of the process of the special heal
	 * @param theCharacter
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
