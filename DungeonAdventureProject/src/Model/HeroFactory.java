package Model;

public class HeroFactory {
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
		
		throw new IllegalArgumentException("Illegal Argument Entered"); 
	}
}
