package Model;

public class ItemFactory {
	public Item createItem(final String theItem) {
		if(theItem.equalsIgnoreCase("heal")) {
			return new Item('H', "Healing Potion", true, true);
		}
		
		if(theItem.equalsIgnoreCase("vision")) {
			return new Item('V', "Vision", true);
		}
		
		if(theItem.equalsIgnoreCase("abstraction")) {
			return new Item('A', "Pillar of Abstraction", false, true);
		}
		
		if(theItem.equalsIgnoreCase("encapsulation")) {
			return new Item('E', "Pillar of Encapsulation", false, true);
		}
		
		if(theItem.equalsIgnoreCase("inheritance")) {
			return new Item('I', "Pillar of Inheritance", false, true);
		}
		
		if(theItem.equalsIgnoreCase("polymorphism")) {
			return new Item('P', "Pillar of Polymorphism", false, true);
		}
		
		if(theItem.equalsIgnoreCase("trap")) {
			return new Item('X', "Trap", true, false);
		} else if (theItem.equalsIgnoreCase("vision")) {
			return new Item('V', "Vision Potion", true, true);
		}
		throw new IllegalArgumentException("Illegal Argument Entered");
	}
}
