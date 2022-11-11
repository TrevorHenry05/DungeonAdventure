package Model;

import Utility.Utility;

public class Priestess extends Hero {
	
	public Priestess(final String theCharacterName) {
		super(75, 75, 25, 45, 0.7, 5, 0.3, "Priestess", theCharacterName);
	}
	
	public boolean special() {
		setHitPoints(getHitPoints() + Utility.randomNumberGen(20,40));
		return true;
	}
}
