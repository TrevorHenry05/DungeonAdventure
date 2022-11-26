package Model;

import Utility.Utility;
import View.View;

public class Thief extends Hero {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Thief(final String theCharacterName) {
		super(75, 75, 20, 40, 0.8, 6, 0.4, "Thief", theCharacterName, 0);
	}
	
	@Override
	public boolean special(final DungeonCharacter  theMonster) {
		int chance = Utility.randomNumberGen(0,100);
		View v = new View();
		
		if(chance < 40) {
			theMonster.takeDamage(Utility.randomNumberGen(getMinDamage(), getMaxDamage()));
			return true;
		} else if(chance >= 40 && chance < 80) {
			theMonster.takeDamage(Utility.randomNumberGen(getMinDamage(), getMaxDamage()));
			setAttacks(getAttacks() + 1);
			v.displayText("Your special has rewarded you an extra attack in the turn!");
			return true;
		}
		
		return false;
	}
}
