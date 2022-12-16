package Model;

import java.io.Serializable;

public class Item implements Serializable {
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	
	private final char  myType;
	private final String myDescription;
	private final boolean myItemUsable;
  
	/**
	 * The constructor for the item
	 * @param theType
	 * @param theDescription
	 * @param theItemUsable
	 */
	public Item(final char theType, final String theDescription, final boolean theItemUsable) {
		myType = theType;
		myDescription = theDescription;
		myItemUsable = theItemUsable;
		myIsCollectable = true;
	}
	
	/**
	 * The type of item
	 * @return myType
	 */
	public char getType() {
		return myType;
	}
	
	/**
	 * The item's description
	 * @return myDescription
	 */
	public String getDescription() {
		return myDescription;
	}
	
	/**
	 * Determines if the item is usable
	 * @return myItemUsable
	 */
	public boolean isUsable() {
		return myItemUsable;
	}
}
