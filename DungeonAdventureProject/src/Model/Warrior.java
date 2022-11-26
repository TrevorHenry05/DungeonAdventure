package Model;

import Utility.Utility;

public class Warrior extends Hero{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Warrior(final String theCharacterName) {
		super(125,125,35,60, 0.8, 4, 0.2, "Warrior", theCharacterName, 0);
	}
	
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
