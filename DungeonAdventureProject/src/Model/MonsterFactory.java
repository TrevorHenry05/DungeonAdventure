package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

public class MonsterFactory {
	
	
	public static void main(String[] args) {
		MonsterFactory mf = new MonsterFactory();
		Monster m = mf.createMonster("gremlin");
		
		System.out.println(m.getHitPoints());
		System.out.println(m.getAttackSpeed());
		System.out.println(m.getChanceToHit());
		System.out.println(m.getMinDamage());
		System.out.println(m.getMaxDamage());
		System.out.println(m.getHealChance());
		System.out.println(m.getHealMin());
		System.out.println(m.getHealMax());
	}
	
	
	public Monster createMonster(final String theMonster) {
		
		SQLiteDataSource ds = null;
		
		try {
			ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:monster.db");
		} catch(Exception e) {
			e.printStackTrace();
            System.exit(0);
		}
		
		
		if(theMonster.equalsIgnoreCase("ogre")) {
			String query = "SELECT * FROM monsters WHERE monstertype='Ogre'";
			try ( Connection conn = ds.getConnection();
		            Statement stmt = conn.createStatement(); ) {
		            
		            ResultSet rs = stmt.executeQuery(query);
		            String type = rs.getString("monstertype");
		            return new Monster(rs.getInt("hitpoints"), rs.getInt("mindamage"), rs.getInt("maxdamage"), rs.getDouble("hitchance"), rs.getInt("attackspeed"),rs.getDouble("healchance"), rs.getInt("healmin"), rs.getInt("healmax"), type );
		        } catch ( SQLException e ) {
		            e.printStackTrace();
		            System.exit( 0 );
		        }
		}
		
		if(theMonster.equalsIgnoreCase("gremlin")) {
			String query = "SELECT * FROM monsters WHERE monstertype='Gremlin'";
			try ( Connection conn = ds.getConnection();
		            Statement stmt = conn.createStatement(); ) {
		            
		            ResultSet rs = stmt.executeQuery(query);
		            String type = rs.getString("monstertype");
		            return new Monster(rs.getInt("hitpoints"), rs.getInt("mindamage"), rs.getInt("maxdamage"), rs.getDouble("hitchance"), rs.getInt("attackspeed"),rs.getDouble("healchance"), rs.getInt("healmin"), rs.getInt("healmax"), type );
		        } catch ( SQLException e ) {
		            e.printStackTrace();
		            System.exit( 0 );
		        }
		}
		
		if(theMonster.equalsIgnoreCase("skeleton")) {
			String query = "SELECT * FROM monsters WHERE monstertype='Skeleton'";
			try ( Connection conn = ds.getConnection();
		            Statement stmt = conn.createStatement(); ) {
		            
		            ResultSet rs = stmt.executeQuery(query);
		            String type = rs.getString("monstertype");
		            return new Monster(rs.getInt("hitpoints"), rs.getInt("mindamage"), rs.getInt("maxdamage"), rs.getDouble("hitchance"), rs.getInt("attackspeed"),rs.getDouble("healchance"), rs.getInt("healmin"), rs.getInt("healmax"), type );
		        } catch ( SQLException e ) {
		            e.printStackTrace();
		            System.exit( 0 );
		        }
		}
		
		throw new IllegalArgumentException("Illegal Argument Entered");
	}
}
