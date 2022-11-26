package Model;

import Utility.Utility;
import View.View;

public class Berserker extends Hero{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Berserker(final String theCharacterName) {
		super(110, 110, 40, 70, 0.8, 5, 0.1, "Berserker", theCharacterName, 0);
	}
	
	@Override
	public boolean special(final DungeonCharacter  theMonster) {
		int chance = Utility.randomNumberGen(0,100);
		View v = new View();
		
		if(chance < 20) {
			theMonster.takeDamage(Utility.randomNumberGen(50, 90));
			return true;
		} else if(chance >= 20 && chance < 40) {
			int heroHealth = getHitPoints();
			theMonster.takeDamage(Utility.randomNumberGen(60, 100));
			setAttacks(getAttacks() + 1);
			v.displayText("Your special has rewarded you an extra attack in the turn! But you have taken damage because of it.");
			takeDamage(Utility.randomNumberGen(10, 20));
			v.displayText("You lost " + (heroHealth - getHitPoints()) + " health points from your special. Your current health " + getHitPoints() + "\n");
			return true;
		}
		
		return false;
	}
}
