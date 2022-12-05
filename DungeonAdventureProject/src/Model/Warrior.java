package Model;

import Utility.Utility;

public class Warrior extends Hero{

	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the Warrior class that stores, the max health, maximum and minimum damage the character can deal, the chance to land an attack, the chance 
	 * to block an attack and the names of both the hero and the user.
	 * @param theCharacterName
	 */
	public Warrior(final String theCharacterName) {
		super(125,125,35,60, 0.8, 4, 0.2, "Warrior", theCharacterName, 0);
	}
	
	/**
	 * The chance of the warrior's special ability being successful and the textual representation of the process of the special attack
	 * @param theMonster
	 */
	@Override
	public boolean special(final DungeonCharacter  theMonster) {
		int chanceHit = Utility.randomNumberGen(0,100);
		
		if(chanceHit < 40) {
			theMonster.takeDamage(Utility.randomNumberGen(75, 175));
			return true;
		}
		return false;
	}
}
