package Model;

public class ItemFactory {
	public Item createItem(final String theItem) {
		if(theItem.equalsIgnoreCase("heal")) {
			return new Item('H', "Heal", true);
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
		
		throw new IllegalArgumentException("Illegal Argument Entered");
	}
}
