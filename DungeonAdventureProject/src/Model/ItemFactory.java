package Model;

public class ItemFactory {
	
	/**
	 * Constructor for item class and what item will be created
	 * @param theItem
	 * @return Item
	 */
	public Item createItem(final String theItem) {
		if(theItem.equalsIgnoreCase("heal")) {
			return new Item('H', "Heal", true);
		}
		
		if(theItem.equalsIgnoreCase("vision")) {
			return new Item('V', "Vision", true);
		}
		
		if(theItem.equalsIgnoreCase("abstraction")) {
			return new Item('A', "Pillar of Abstraction", false);
		}
		
		if(theItem.equalsIgnoreCase("encapsulation")) {
			return new Item('E', "Pillar of Encapsulation", false);
		}
		
		if(theItem.equalsIgnoreCase("inheritance")) {
			return new Item('I', "Pillar of Inheritance", false);
		}
		
		if(theItem.equalsIgnoreCase("polymorphism")) {
			return new Item('P', "Pillar of Polymorphism", false);
		}
		
		if(theItem.equalsIgnoreCase("trap")) {
			return new Item('X', "Trap", true);
		}
		throw new IllegalArgumentException("Illegal Argument Entered");
	}
}
