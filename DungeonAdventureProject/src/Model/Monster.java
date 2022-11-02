package Model;

public class Monster extends DungeonCharacter {
	
	private final double myHealChance;
	private final int myHealMin;
	private final int myHealMax;
	private final String myMonsterType;
	
	public Monster(final int theHitPoints, final int theMinDamage, final int theMaxDamage, final double theChanceToHit, final int theAttackSpeed, final double theHealChance, final int theHealMin, final int theHealMax, final String theMonsterType) {
		super(theHitPoints, theMinDamage, theMaxDamage, theChanceToHit, theAttackSpeed);
		myHealChance = theHealChance;
		myHealMin = theHealMin;
		myHealMax = theHealMax;
		myMonsterType = theMonsterType;
	}
	
	public double getHealChance() {
		return myHealChance;
	}
	
	public int getHealMin() {
		return myHealMin;
	}
	
	public int getHealMax() {
		return myHealMax;
	}
	
	public String getMonsterType() {
		return myMonsterType;
	}
	
}
