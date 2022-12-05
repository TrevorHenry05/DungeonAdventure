package Model;

import Utility.Utility;
import View.ViewGUI;

public class Thief extends Hero {
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the Thief class that stores, the max health, maximum and minimum damage the character can deal, the chance to land an attack, the chance 
	 * to block an attack and the names of both the hero and the user.
	 * @param theCharacterName
	 */
	public Thief(final String theCharacterName) {
		super(75, 75, 20, 40, 0.8, 6, 0.4, "Thief", theCharacterName, 0);
	}
	
	/**
	 * The chance of the thief's special ability being successful and the textual representation of the process of the special attack
	 * @param theMonster
	 */
	@Override
	public boolean special(final DungeonCharacter  theMonster) {
		int chance = Utility.randomNumberGen(0,100);
		ViewGUI v = new ViewGUI();
		
		if(chance < 40) {
			theMonster.takeDamage(Utility.randomNumberGen(getMinDamage(), getMaxDamage()));
			return true;
		} else if(chance >= 40 && chance < 80) {
			theMonster.takeDamage(Utility.randomNumberGen(getMinDamage(), getMaxDamage()));
			setAttacks(getAttacks() + 1);
			v.addTexttoTextArea("Your special has rewarded you an extra attack in the turn!");
			return true;
		}
		
		return false;
	}
}
