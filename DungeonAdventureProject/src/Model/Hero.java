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
	
	public Hero(final int theHitPoints, final int theMinDamage, final int theMaxDamage, final double theChanceToHit, final int theAttackSpeed, final double theBlockChance, final String theClassName, final String theCharacterName) {
		super(theHitPoints, theMinDamage, theMaxDamage, theChanceToHit, theAttackSpeed);
		myCharacterName = theCharacterName;
		myBlockChance = theBlockChance;
		myClassName = theClassName;
		myCurrX = 0;
		myCurrY = 0;
		myInventory = new ArrayList<Item>();
		myCurrRoom = null;	
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
	
	public void addItemToInvetory(final Item theItem) {
		myInventory.add(theItem);
	}
	
	public void removeItemFromInventory(final Item theItem) {
		myInventory.remove(theItem);
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
