package Model;

public class HeroFactory {
	/**
	 * Creates the hero based off of the selected class
	 * @param theHero the class name the User picked to create
	 * @param theName the User wants his Hero to be called
	 * @return Hero object the User created
	 */
	public Hero createHero(final String theHero, final String theName) {
		if(theHero.equalsIgnoreCase("warrior")) {
			return new Warrior(theName);
		}
		if(theHero.equalsIgnoreCase("priestess")) {
			return new Priestess(theName);
		}
		if(theHero.equalsIgnoreCase("thief")) {
			return new Thief(theName);
		}
		if(theHero.equalsIgnoreCase("berserker")) {
			return new Berserker(theName);
		}
		if(theHero.equalsIgnoreCase("dev")) {
			return new Dev(theName);
		}
		
		throw new IllegalArgumentException("Illegal Argument Entered in CreateHero method");
	}
}
