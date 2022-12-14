package Model;

import java.io.Serializable;

public abstract class DungeonCharacter implements Serializable {
	 


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int myHitPoints;
	private final double myChanceToHit;
	private final int myMinDamage;
	private final int myMaxDamage;
	private final int myAttackSpeed;
	private final int myMaxHitPoints;
	
	public DungeonCharacter(final int theHitPoints, final int theMaxHitPoints, final int theMinDamage, final int theMaxDamage, final double theChanceToHit, final int theAttackSpeed) {
		myHitPoints = theMaxHitPoints;
		myMaxHitPoints = theMaxHitPoints;
		myMinDamage = theMinDamage;
		myMaxDamage = theMaxDamage;
		myChanceToHit = theChanceToHit;
		myAttackSpeed = theAttackSpeed;
	}
	
	public int getHitPoints() {
		return myHitPoints;
	}
	
	public int getMaxHitPoints() {
		return myMaxHitPoints;
	}
	
	public void setHitPoints(final int theHitPoints) {
		if(theHitPoints < 0) {
			myHitPoints = 0;
		} else {
			myHitPoints = theHitPoints;
		}
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
			return false;
		}
		
		return true;
	}
	
	public void takeDamage(final int theDamage) {
		this.setHitPoints(this.getHitPoints() - theDamage);
	}
	
		
	
	
}
