package Model;

import Utility.Utility;

public class Thief extends Hero {
	
	public Thief(final String theCharacterName) {
		super(75, 75, 20, 40, 0.8, 6, 0.4, "Thief", theCharacterName);
	}
	
	@Override
	public boolean special(final DungeonCharacter  theMonster) {
		int chance = Utility.randomNumberGen(0,100);
		
		if(chance < 80) {
			attack(theMonster);
			return true;
		} 
		
		return false;
	}
}
