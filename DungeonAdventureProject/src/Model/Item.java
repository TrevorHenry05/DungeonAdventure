package Model;

public class Item {
	private final String  myType;
	private final char myDescription;
	private final boolean myItemUsable;
	
	public Item(final String theType, final char theDescription, final boolean theItemUsable) {
		myType = theType;
		myDescription = theDescription;
		myItemUsable = theItemUsable;
	}
	
	public void  use(Hero theHero, Item theItem) {
		
	}
	
	public String getType() {
		return myType;
	}
	
	public char getDescription() {
		return myDescription;
	}
	
	public boolean getItemUsable() {
		return myItemUsable;
	}
}
