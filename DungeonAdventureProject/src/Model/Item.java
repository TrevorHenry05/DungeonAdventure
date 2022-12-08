package Model;

import java.io.Serializable;
/**
 * This is the item class in which the type of item, description and what the item does is stored in a method
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
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
