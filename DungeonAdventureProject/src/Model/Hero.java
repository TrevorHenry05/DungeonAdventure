package Model;

import java.util.ArrayList;
import java.util.List;

import Utility.Utility;

public abstract class Hero extends DungeonCharacter {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String myCharacterName;
	private final double myBlockChance;
	private final String myClassName;
	private List<Item> myInventory;
	private int myCurrX;
	private int myCurrY;
	private DungeonRoom myCurrRoom;
	private int myAttacks;
	
	public Hero(final int theHitPoints, final int theMaxHitPoints, final int theMinDamage, final int theMaxDamage, final double theChanceToHit, final int theAttackSpeed, final double theBlockChance, final String theClassName, final String theCharacterName, final int theAttacks) {
		super(theHitPoints, theMaxHitPoints, theMinDamage, theMaxDamage, theChanceToHit, theAttackSpeed);
		myCharacterName = theCharacterName;
		myBlockChance = theBlockChance;
		myClassName = theClassName;
		myCurrX = 0;
		myCurrY = 0;
		myAttacks = theAttacks;
		myInventory = new ArrayList<Item>();
		myCurrRoom = null;	
	}
	
	public int getAttacks() {
		return myAttacks;
	}
	
	public List<Item> getInventory() {
		return myInventory;
	}
	
	public String getCharacterName() {

		return myCharacterName;
}
	
	public double getBlockChance() {		
		return myBlockChance;
	}
	
	public String getClassName() {
		return myClassName;
	}
	
	public int getCurrX() {
		return myCurrX;
	}
	
	public int getCurrY() {
		return myCurrY;
	}
	
	public DungeonRoom getCurrRoom() {
		return myCurrRoom;
	}
	
	public void setCurrX(final int theCurrX) {
		myCurrX = theCurrX;
	}
	
	public void setCurrY(final int theCurrY) {
		myCurrY = theCurrY;
	}
	
	public void setCurrRoom(final DungeonRoom theCurrRoom) {
		myCurrRoom = theCurrRoom;
	}
	
	public void setAttacks(final int theAttacks) {
		myAttacks = theAttacks;
	}
	
	public void addItemToInventory(final Item theItem) {
		getInventory().add(theItem);
	}
	
	public String showInventory() {
		StringBuilder sb = new StringBuilder();
		for(Item item: getInventory()) {
			sb.append(item.getDescription());
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
	
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
	
	public boolean attack(final DungeonCharacter theMonster) {
		
		int chanceHit = Utility.randomNumberGen(0,100);
			
		if(chanceHit < (getChanceToHit() * 100)) {
				theMonster.takeDamage(Utility.randomNumberGen(getMinDamage(), getMaxDamage()));
				return true;
		}
			
		return false;	
	}
	
	public abstract boolean special(final DungeonCharacter theMonster);
	
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