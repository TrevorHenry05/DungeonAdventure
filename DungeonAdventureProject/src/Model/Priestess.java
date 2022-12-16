package Model;

import Utility.Utility;

public class Priestess extends Hero {
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the Priestess class
	 * @param theCharacterName
	 */
	public Priestess(final String theCharacterName) {
		//Health, MinDMG, MaxDMG, Hit%, AtkSpd, Block%
		super(75, 75, 25, 45, 0.7, 5, 0.3, "Priestess", theCharacterName, 0);
	}
	
	/**
	 * The chance for the priestess to heal
	 * 
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
