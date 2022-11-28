package Utility;

import java.util.Random;

public class Utility {
	/**
	 * Universal random number generator
	 * @param theMin
	 * @param theMax
	 * @return rand
	 */
	public static int randomNumberGen(final int theMin, final int theMax) {
		Random rand = new Random();
		return rand.nextInt(theMax - theMin + 1) + theMin;	
	}
}
