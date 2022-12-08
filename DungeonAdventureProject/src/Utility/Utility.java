package Utility;

import java.util.Random;
/**
 * Class that has the universal random number generator that is used in every class
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
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
