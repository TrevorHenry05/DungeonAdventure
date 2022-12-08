package Model;

import java.io.Serializable;
/**
 * Class for the constructor for all dugenon character classes. Stores the health, damage and attack speed
 * 
 * @author Trevor Henry, Riley Stevenson, and Colton Wickens
 * @version 1.0
 */
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
	 * @return The amount of health the hero has at any given moment 
	 */
	public int getHitPoints() {
		return myHitPoints;
	}
	
	/**
	 * Gets the max amount of hit points the specific character can have
	 * @return the max amount of health the hero can have 
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
	 * @return the percentage chance for the user to land a normal attack
	 */
	public double getChanceToHit() {
		return myChanceToHit;
	}
	
	/**
	 * Returns the max amount of damage a character can deal
	 * @return the maximum amount of damage the user could deal when they use a normal attack
	 */
	public int getMaxDamage() {
		return myMaxDamage;
	}
	
	/**
	 * Returns the minimum amount of damage a character can deal
	 * @return the minimum amount of damage the user could deal when they use a normal attack
	 */
	public int getMinDamage() {
		return myMinDamage;
	}
	
	/**
	 * Returns the characters attack speed for a turn
	 * @return the attack speed the user has for the turn. The amount of times the user can attack in a turn
	 */
	public int getAttackSpeed() {
		return myAttackSpeed;
	}
	
	/**
	 * Checks to see if the user is alive which is determined by if the hero's health is above 0
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
	 * @param the amount of damage taken from an attack
	 */
	public void takeDamage(final int theDamage) {
		setHitPoints(getHitPoints() - theDamage);
	}
	
		
	
	
}
