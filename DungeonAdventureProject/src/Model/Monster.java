package Model;

import Utility.Utility;

public class Monster extends DungeonCharacter {
	
	/**
	 * Serialization ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Generates the chance for character to heal
	 * @serial
	 */
	private final double myHealChance;
	/**
	 * The minimum amount the character to heal for
	 * @serial
	 */
	private final int myHealMin;
	/**
	 * The maximum amount the character to heal for
	 * @serial
	 */
	private final int myHealMax;
	/**
	 * The type of monster
	 * @serial
	 */
	private final String myMonsterType;
	
	/**
	 * Constructor for the monster class
	 * @param theHitPoints
	 * @param theMaxHitPoints
	 * @param theMinDamage
	 * @param theMaxDamage
	 * @param theChanceToHit
	 * @param theAttackSpeed
	 * @param theHealChance
	 * @param theHealMin
	 * @param theHealMax
	 * @param theMonsterType
	 */
	public Monster(final int theHitPoints, final int theMaxHitPoints, final int theMinDamage, final int theMaxDamage, final double theChanceToHit, final int theAttackSpeed, final double theHealChance, final int theHealMin, final int theHealMax, final String theMonsterType) {
		super(theHitPoints, theMaxHitPoints, theMinDamage, theMaxDamage, theChanceToHit, theAttackSpeed);
		myHealChance = theHealChance;
		myHealMin = theHealMin;
		myHealMax = theHealMax;
		myMonsterType = theMonsterType;
	}
	/**
	 * Getter for the heal chance
	 * @return myHealChance
	 */
	public double getHealChance() {
		return myHealChance;
	}
	/**
	 * Getter for the minimum heal amount
	 * @return myHealMin
	 */
	public int getHealMin() {
		return myHealMin;
	}	
	/**
	 * Getter for the maximum heal amount
	 * @return myHealax
	 */
	
	public int getHealMax() {
		return myHealMax;
	}
	/**
	 * Getter for the type of monster
	 * @return myMonsterType
	 */
	public String getMonsterType() {
		return myMonsterType;
	}
	/**
	 * Chance for monster to heal. Returns true if the heal is successful and false if the heal fails
	 * @return true or false
	 */
	public boolean heal() {
		int chance = Utility.randomNumberGen(0,100);
		int newHealth = getHitPoints() + Utility.randomNumberGen(getHealMin(), getHealMax());
		
		if(chance < (100 * getHealChance())) {
			if(newHealth >= getMaxHitPoints()) {
				setHitPoints(getMaxHitPoints());
			} else {
				setHitPoints(newHealth);
			}
			return true;
		}
		
		return false;
	}
	/**
	 * The percentage chance for the attack to land
	 * @param theHero
	 * @return true or false
	 */
	public boolean attack(final Hero theHero) {
		
		int chanceHit = Utility.randomNumberGen(0,100);
		int chanceHeroBlock = Utility.randomNumberGen(0,100);
			
		if(chanceHit < (getChanceToHit() * 100)) {
			if(chanceHeroBlock >= (theHero.getBlockChance() * 100)) {
				theHero.takeDamage(Utility.randomNumberGen(getMinDamage(), getMaxDamage()));
				return true;
			}
		}
			
		return false;	
	}


}
