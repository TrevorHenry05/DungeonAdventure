package Model;

public class Item {
	private final char  myType;
	private final String myDescription;
	private final boolean myItemUsable;
	
	public Item(final char theType, final String theDescription, final boolean theItemUsable) {
		myType = theType;
		myDescription = theDescription;
		myItemUsable = theItemUsable;
	}
	
	
	public char getType() {
		return myType;
	}
	
	public String getDescription() {
		return myDescription;
	}
	
	public boolean isUsable() {
		return myItemUsable;
	}
}
