package Model;

import java.io.Serializable;

public abstract class DungeonCharacter implements Serializable {
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Amount of health points the user has at any given time
	 * @serial
	 */
	private int myHitPoints;
	/**
	 * The percentage chance a normal attack has to land
	 * @serial
	 */
	private final double myChanceToHit;
	/**
	 * The lowest amount of damage a normal attack can do
	 * @serial
	 */
	private final int myMinDamage;
	/**
	 * The highest amount of damage a normal attack can do
	 * @serial
	 */
	private final int myMaxDamage;
	/**
	 * The speed at which a user or monster can attack in a single turn
	 * @serial
	 */
	private final int myAttackSpeed;
	/**
	 * The most amount of health the user or monster can have
	 * @serial
	 */
	private final int myMaxHitPoints;

	/**
	 * The constructor for the DungeonCharacter class
	 * @param theHitPoints
	 * @param theMaxHitPoints
	 * @param theMinDamage
	 * @param theMaxDamage
	 * @param theChanceToHit
	 * @param theAttackSpeed
	 */
	public DungeonCharacter(final int theHitPoints,final int theMaxHitPoints, final int theMinDamage, final int theMaxDamage, final double theChanceToHit, final int theAttackSpeed) {
		myHitPoints = theHitPoints;
		myMaxHitPoints = theMaxHitPoints;
		myMinDamage = theMinDamage;
		myMaxDamage = theMaxDamage;
		myChanceToHit = theChanceToHit;
		myAttackSpeed = theAttackSpeed;
	}
	
	/**
	 * returns the specific character's current hit points
	 * @return myHitPoints
	 */
	public int getHitPoints() {
		return myHitPoints;
	}
	
	/**
	 * Gets the max amount of hit points the specific character can have
	 * @return MaxHitPoints
	 */
	public int getMaxHitPoints() {
		return myMaxHitPoints;
	}
	
	/**
	 * Sets the character's current hit points but does not allow hit points to go below zero
	 * @param theHitPoints
	 */
	public void setHitPoints(final int theHitPoints) {
		if(theHitPoints < 0) {
			myHitPoints = 0;
		} else {
			myHitPoints = theHitPoints;
		}
	}
	
	/**
	 * Returns the chance to land a normal attack
	 * @return myChancetoHit
	 */
	public double getChanceToHit() {
		return myChanceToHit;
	}
	
	/**
	 * Returns the max amount of damage a character can deal
	 * @return myMaxDamage
	 */
	public int getMaxDamage() {
		return myMaxDamage;
	}
	
	/**
	 * Returns the minimum amount of damage a character can deal
	 * @return myMinDamage
	 */
	public int getMinDamage() {
		return myMinDamage;
	}
	
	/**
	 * Returns the characters attack speed for a turn
	 * @return myAttackSpeed
	 */
	public int getAttackSpeed() {
		return myAttackSpeed;
	}
	
	/**
	 * 
	 * @return true or false if the character is alive
	 */
	public boolean isAlive() {
		if(myHitPoints <= 0) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * The amount of damage taken in the turn
	 * @param theDamage
	 */
	public void takeDamage(final int theDamage) {
		setHitPoints(getHitPoints() - theDamage);
	}
	
		
	
	
}
