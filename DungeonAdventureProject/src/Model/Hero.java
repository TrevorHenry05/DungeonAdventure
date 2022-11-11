package Model;

import java.util.ArrayList;
import java.util.List;

import Utility.Utility;

public abstract class Hero extends DungeonCharacter {
	
	private final String myCharacterName;
	private final double myBlockChance;
	private final String myClassName;
	private List<Item> myInventory;
	private int myCurrX;
	private int myCurrY;
	private DungeonRoom myCurrRoom;
	
	public Hero(final int theHitPoints,final int theMaxHitPoints, final int theMinDamage, final int theMaxDamage, final double theChanceToHit, final int theAttackSpeed, final double theBlockChance, final String theClassName, final String theCharacterName) {
		super(theHitPoints,theMaxHitPoints, theMinDamage, theMaxDamage, theChanceToHit, theAttackSpeed);
		myCharacterName = theCharacterName;
		myBlockChance = theBlockChance;
		myClassName = theClassName;
		myCurrX = 0;
		myCurrY = 0;
		myInventory = new ArrayList<Item>();
		myCurrRoom = null;	
	}
	
	public List<Item> getInventory() {
		return myInventory;
	}
	
	public String getCharacterName() {

		return myCharacterName;
}
	
	public double getBlockChance() {
		
		int chanceBlock = Utility.randomNumberGen(0,100);
		
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
		
		for(Item item: getInventory()) {
			if(item.getDescription().equalsIgnoreCase(theItem)) {
				break;
			}
			i++;
		}
		
		Item item = getInventory().get(i);
		if(item.isUsable()) {
			getInventory().remove(i);
		}
		return item;
	}
	
	public void useItem(final Item theItem) {
		
		
		if(theItem.isUsable()) {
			if(theItem.getType() == 'H') {
				int newHealth = getHitPoints() + Utility.randomNumberGen(5,15);
				if(newHealth >= getMaxHitPoints()) {
					setHitPoints(getMaxHitPoints());
				} else {
					setHitPoints(newHealth);
				}
			} else if(theItem.getType() == 'X') {
				setHitPoints(getHitPoints() - Utility.randomNumberGen(1,20));
			}
		} else {
			System.out.println("Item not usable");
		}
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
	
	public boolean attack(final Monster theMonster) {
		
		int chanceHit = Utility.randomNumberGen(0,100);
			
		if(chanceHit < (getChanceToHit() * 100)) {
				theMonster.takeDamage(Utility.randomNumberGen(getMinDamage(), getMaxDamage()));
				return true;
		}
			
		return false;	
	}
	
}
