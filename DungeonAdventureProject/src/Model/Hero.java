package Model;

import java.util.ArrayList;
import java.util.List;

import Utility.Utility;

public abstract class Hero extends DungeonCharacter {
	

	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @serial
	 * Stores character name inputed by user
	 */

	private final String myCharacterName;
	/**
	 * Contains the chance for the user to block an attack
	 * @serial
	 */
	private final double myBlockChance;
	/**
	 * Stores the name of the class that is selected by the user
	 * @serial
	 */
	private final String myClassName;
	/**
	 * Stores the user's collected items and updates when new items are collected or collected items are used
	 * @serial
	 */
	private List<Item> myInventory;
	/**
	 * Stores the current x value of the user in the 2D dungeon array and is updated when the user moves
	 * @serial
	 */
	private int myCurrX;
	/**
	 * Stores the current y value of the user in the 2D dungeon array and is updated when the user moves
	 * @serial
	 */
	private int myCurrY;
	/**
	 * Keeps track of the room represented by the 2d array that the user is currently in
	 * @serial
	 */
	private DungeonRoom myCurrRoom;
	/**
	 * @serial
	 */
	private int myAttacks;
	
	/**
	 * Constructor for Hero class
	 * 
	 * @param theHitPoints
	 * @param theMaxHitPoints
	 * @param theMinDamage
	 * @param theMaxDamage
	 * @param theChanceToHit
	 * @param theAttackSpeed
	 * @param theBlockChance
	 * @param theClassName
	 * @param theCharacterName
	 * @param theAttacks
	 */
	public Hero(final int theHitPoints,final int theMaxHitPoints, final int theMinDamage, final int theMaxDamage, final double theChanceToHit, final int theAttackSpeed, final double theBlockChance, final String theClassName, final String theCharacterName, final int theAttacks) {
		super(theHitPoints,theMaxHitPoints, theMinDamage, theMaxDamage, theChanceToHit, theAttackSpeed);
		myCharacterName = theCharacterName;
		myBlockChance = theBlockChance;
		myClassName = theClassName;
		myCurrX = 0;
		myCurrY = 0;
		myAttacks = theAttacks;
		myInventory = new ArrayList<Item>();
		myCurrRoom = null;	
	}
	
	/**
	 * getter for the user's attacks
	 * @return How many attacks the user has left per turn
	 */
	public int getAttacks() {
		return myAttacks;
	}
	
	/**
	 * getter for the user's current inventory
	 * @return The list of items the user currently has
	 */
	public List<Item> getInventory() {
		return myInventory;
	}
	
	/**
	 * getter for the user's inputed name
	 * @return The name the user had
	 */
	public String getCharacterName() {
		return myCharacterName;
	}
	
	/**
	 * getter for the percentage chance the character has to block an attack
	 * 
	 * @return Obtains the chance the user has to block the attack
	 */
	public double getBlockChance() {		
		return myBlockChance;
	}
	
	/**
	 * getter for the class name that the user selected
	 * 
	 * @return The name of the class the user has selected
	 */
	public String getClassName() {
		return myClassName;
	}
	
	/**
	 * getter for the user's current X value in the array
	 * 
	 * @return the current X position of the user
	 */
	public int getCurrX() {
		return myCurrX;
	}
	
	/**
	 * getter for the user's current Y value in the array
	 * 
	 * @return the current Y position of the user
	 */
	public int getCurrY() {
		return myCurrY;
	}
	
	/**
	 * getter for the user's current room in the dungeon array
	 * 
	 * @return the current room the user is in
	 */
	public DungeonRoom getCurrRoom() {
		return myCurrRoom;
	}
	
	/**
	 * sets the user's X value and stores it in the current X
	 * 
	 * @param theCurrX
	 */
	public void setCurrX(final int theCurrX) {
		myCurrX = theCurrX;
	}
	
	/**
	 * Sets the user's Y value and stores it in the current Y
	 * 
	 * @param theCurrY
	 */
	public void setCurrY(final int theCurrY) {
		myCurrY = theCurrY;
	}
	
	/**
	 * Sets the current room the user is in
	 * 
	 * @param theCurrRoom
	 */
	public void setCurrRoom(final DungeonRoom theCurrRoom) {
		myCurrRoom = theCurrRoom;
	}
	
	/**
	 * Sets the attacks the character has to use in the turn
	 * 
	 * @param theAttacks
	 */
	public void setAttacks(final int theAttacks) {
		myAttacks = theAttacks;
	}
	
	/**
	 * adds the collected item to the inventory array
	 * 
	 * @param theItem
	 */
	public void addItemToInventory(final Item theItem) {
		getInventory().add(theItem);
	}
	
	/**
	 * Uses string builder to generate a string to represent what items the user is currently holding
	 * 
	 * @return a text representation of the items the user is currently holding
	 */
	public String showInventory() {
		StringBuilder sb = new StringBuilder();
		for(Item item: getInventory()) {
			sb.append(item.getDescription());
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
	
	/**
	 * Uses a StringBuilder to build and Return a text representation of the rooms that are surrounding the room the hero is currently in.
	 * 
	 * @param theDungeon
	 * @return text representation of the rooms surrounding the room the hero is currently in
	 */
	public String displayDungeonNearHero(final Dungeon theDungeon) {
		StringBuilder sb = new StringBuilder();
		int x, y;
		if((getCurrX() - 1) >= 0) {
			x = getCurrX() - 1;
		} else {
			x = 0;
		}
		
		if((getCurrY() - 1) >= 0) {
			y = getCurrY() - 1;
		} else {
			y = 0;
		}
		
		for(int i = x; ((i < theDungeon.getDungeon().length) && (i < getCurrX() + 2)); i++) {
			StringBuilder row1 = new StringBuilder();
			StringBuilder row2 = new StringBuilder();
			StringBuilder row3 = new StringBuilder();
			for(int j = y; ((j < theDungeon.getDungeon()[i].length) && (j < getCurrY() + 2)); j++) {
				row1.append(theDungeon.getDungeon()[i][j].getRoom()[0][0]);
				row1.append(theDungeon.getDungeon()[i][j].getRoom()[0][1]);
				row1.append(theDungeon.getDungeon()[i][j].getRoom()[0][2]);
				if((i == getCurrX()) && (j == getCurrY())) {
					row2.append(theDungeon.getDungeon()[i][j].getRoom()[1][0]);
					row2.append(theDungeon.getDungeon()[i][j].getRoom()[1][1]);
					row2.append(theDungeon.getDungeon()[i][j].getRoom()[1][2]);
				} else {
					row2.append(theDungeon.getDungeon()[i][j].getRoom()[1][0]);
					row2.append(theDungeon.getDungeon()[i][j].getRoom()[1][1]);
					row2.append(theDungeon.getDungeon()[i][j].getRoom()[1][2]);
				}
				
				row3.append(theDungeon.getDungeon()[i][j].getRoom()[2][0]);
				row3.append(theDungeon.getDungeon()[i][j].getRoom()[2][1]);
				row3.append(theDungeon.getDungeon()[i][j].getRoom()[2][2]);				
			}
			
			sb.append(row1.toString());
			sb.append(System.lineSeparator());
			sb.append(row2.toString());
			sb.append(System.lineSeparator());
			sb.append(row3.toString());
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
	
	/**
	 * Updates inventory by removing items that are used
	 * 
	 * @param theItem
	 * @return the used item
	 */
	public Item removeItemFromInventory(final String theItem) {
		int i = 0;
		
		if(getInventory().size() == 0) {
			return null;
		}
		
		for(Item item: getInventory()) {
			if(item.getDescription().equalsIgnoreCase(theItem)) {
				break;
			}
			i++;
		}
		
		if(i >= (getInventory().size())) {
			return null;
		}
		
		Item item = getInventory().get(i);
		if(item.isUsable()) {
			getInventory().remove(i);
		}
		return item;
	}
	
	/**
	 * Performs the action of the item the user is currently using
	 * 
	 * @param theItem
	 * @return the used item
	 */
	public boolean useItem(final Item theItem) {
		if(theItem == null) {
			return false;
		}
		
		if(theItem.isUsable()) {
			if(theItem.getType() == 'H') {
				int newHealth = getHitPoints() + Utility.randomNumberGen(5,15);
				if(newHealth >= getMaxHitPoints()) {
					setHitPoints(getMaxHitPoints());
				} else {
					setHitPoints(newHealth);
				}
				return true;
			} else if(theItem.getType() == 'V') {
				return true;
			} else if(theItem.getType() == 'X') {
				setHitPoints(getHitPoints() - Utility.randomNumberGen(1,20));
			}
		} else {
			System.out.println("Item not usable");
		}
		
		return false;
	}
	
	/**
	 * Checks if the room has a pillar in it
	 * 
	 * @return true or false
	 */
	public boolean hasPillars() {
		int total = 0;
		
		for(Item item: getInventory()) {
			if(item.getType() == 'I' || item.getType() == 'A' || item.getType() == 'E' || item.getType() == 'P') {
				total++;
			}
		}
		
		if(total >= 4) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param theMonster
	 * @return if the attack was successful
	 */
	public boolean attack(final DungeonCharacter theMonster) {
		
		int chanceHit = Utility.randomNumberGen(0,100);
			
		if(chanceHit < (getChanceToHit() * 100)) {
				theMonster.takeDamage(Utility.randomNumberGen(getMinDamage(), getMaxDamage()));
				return true;
		}
			
		return false;	
	}
	
	/**
	 * 
	 * @param theMonster
	 * @return
	 */
	public abstract boolean special(final DungeonCharacter theMonster);
	
	/**
	 * Creates a string that represents the items in the room
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		StringBuilder pillars = new StringBuilder();
		int healCount = 0, visionCount = 0;
		
		sb.append("Name: " + getCharacterName());
		sb.append(System.lineSeparator());
		sb.append("Hit Points: " + getHitPoints());
		sb.append(System.lineSeparator());
		
		
		for(Item item: getInventory()) {
			if(item.getType() == 'H') {
				healCount++;
			} 
			if(item.getType() == 'V') {
				visionCount++;	
			}
			
			if(item.getType() == 'A' || item.getType() == 'I' || item.getType() == 'E' || item.getType() == 'P') {
				pillars.append(item.getDescription());
				pillars.append(System.lineSeparator());
			}
		}
		
		sb.append("Total Heal Potions: " + healCount);
		sb.append(System.lineSeparator());
		sb.append("Total Vision Pottions: "+ visionCount);
		sb.append(System.lineSeparator());
		sb.append("Pillars Found:\n" + pillars.toString());
		
		return sb.toString();
	}
	
}