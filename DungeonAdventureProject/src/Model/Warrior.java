package Model;

import Utility.Utility;

public class Warrior extends Hero{

	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for the Warrior class
	 * @param theCharacterName
	 */
	public Warrior(final String theCharacterName) {
		super(125,125,35,60, 0.8, 4, 0.2, "Warrior", theCharacterName, 0);
	}
	
	/**
	 * The chance the warrior's special attack lands
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
