package Utility;

import java.util.Random;

public class Utility {
	
	public static int randomNumberGen(final int theMin, final int theMax) {
		Random rand = new Random();
		return rand.nextInt(theMax - theMin + 1) + theMin;	
	}
}
