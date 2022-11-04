package Model;

import Utility.Utility;

public class Thief extends Hero {
	
	public Thief(final String theCharacterName) {
		super(75, 20, 40, 0.8, 6, 0.4, "Thief", theCharacterName);
	}
	
	public boolean special(final Monster theMonster) {
		int chance = Utility.randomNumberGen(0,100);
		
		if(chance < 40) {
			theMonster.takeDamage(Utility.randomNumberGen(getMinDamage(),getMaxDamage()));
			System.out.println("Rewarded Extra attack");
			if(this.attack(theMonster)) {
				System.out.println("Extra attack succesful");
			} else {
				System.out.println("Extra attack unsuccesful");
			}
			return true;
		} 
		if ( chance < 80 && chance >= 40) {
			this.attack(theMonster);
			return true;
		}
		
		return false;
		
	}
}
