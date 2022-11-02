package Model;

import Utility.Utility;

public abstract class DungeonCharacter {
	 
	private int myHitPoints;
	private final double myChanceToHit;
	private final int myMinDamage;
	private final int myMaxDamage;
	private final int myAttackSpeed;
	//private final int myDamageRange;
	
	public DungeonCharacter(final int theHitPoints, final int theMinDamage, final int theMaxDamage, final double theChanceToHit, final int theAttackSpeed) {
		myHitPoints = theHitPoints;
		myMinDamage = theMinDamage;
		myMaxDamage = theMaxDamage;
		myChanceToHit = theChanceToHit;
		myAttackSpeed = theAttackSpeed;
	}
	
	public int getHitPoints() {
		return myHitPoints;
	}
	
	public void setHitPoints(final int theHitPoints) {
		myHitPoints = theHitPoints;
	}
	
	public double getChanceToHit() {
		return myChanceToHit;
	}
	
	public int getMaxDamage() {
		return myMaxDamage;
	}
	
	public int getMinDamage() {
		return myMinDamage;
	}
	
	public int getAttackSpeed() {
		return myAttackSpeed;
	}
	
	public boolean isAlive() {
		if(myHitPoints <= 0) {
			return true;
		}
		
		return false;
	}
	
	public void takeDamage(final int theDamage) {
		this.setHitPoints(this.getHitPoints() - theDamage);
	}
	
	public boolean attack(final DungeonCharacter theEnemy) {
		int chance = Utility.randomNumberGen(0,100);
		
		if(chance < (myChanceToHit * 100)) {
			theEnemy.takeDamage(Utility.randomNumberGen(myMinDamage, myMaxDamage));
			return true;
		}
		
		return false;
	}
	
}
