package Model;

public class Item {
	private final char  myType;
	private final String myDescription;
	private final boolean myItemUsable;
	private final boolean myIsCollectable;
	
	public Item(final char theType, final String theDescription, final boolean theItemUsable) {
		myType = theType;
		myDescription = theDescription;
		myItemUsable = theItemUsable;
		myIsCollectable = true;
	}
	public Item(final char theType, final String theD, final boolean theUsable, final boolean theCollectable) {
		myType = theType;
		myDescription = theD;
		myItemUsable = theUsable;
		myIsCollectable = theCollectable;
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
	public boolean isCollectable() {
		return myIsCollectable;
	}
}
