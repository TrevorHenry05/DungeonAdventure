package Model;

import Utility.Utility;

public class Warrior extends Hero{

	
	public Warrior(final String theCharacterName) {
		super(125,125,35,60, 0.8, 4, 0.2, "Warrior", theCharacterName);
	}
	
	public boolean special(final Monster theMonster) {
		int chanceHit = Utility.randomNumberGen(0,100);
		
		if(chanceHit < (0.4 * 100)) {
			theMonster.takeDamage(Utility.randomNumberGen(75, 175));
			return true;
		}
		return false;
	}
}
