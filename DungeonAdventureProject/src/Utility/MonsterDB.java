package Utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

public class MonsterDB {
	
	/**
	 * The database for the monsters that can be called at any time throughout the game.
	 * @param args
	 */
	public static void main(String[] args) {

		SQLiteDataSource ds = null;
		
		try {
			ds = new SQLiteDataSource();
			ds.setUrl("jdbc:sqlite:monster.db");
		} catch(Exception e) {
			e.printStackTrace();
            System.exit(0);
		}
		
		System.out.println( "Opened database successfully" );
		
		String query = "CREATE TABLE IF NOT EXISTS monsters ( " + "monstertype VARCHAR(20) NOT NULL UNIQUE, " + "hitpoints INT NOT NULL, " + "attackspeed INT NOT NULL, " +
                            "hitchance REAL NOT NULL, " + "mindamage INT NOT NULL, " + "maxdamage INT NOT NULL, " + "healchance REAL NOT NULL, " +
                            "healmin INT NOT NULL, " + "healmax INT NOT NULL )";
		
		try ( Connection conn = ds.getConnection();
               Statement stmt = conn.createStatement(); ) {
              int rv = stmt.executeUpdate(query);
              System.out.println( "executeUpdate() returned " + rv );
          } catch ( SQLException e ) {
              e.printStackTrace();
              System.exit( 0 );
          }
          System.out.println( "Created monsters table successfully" );
          
          System.out.println( "Attempting to insert three rows into questions table" );

          String query2 = "INSERT INTO monsters ( monstertype, hitpoints, attackspeed, hitchance, mindamage, maxdamage, healchance, healmin, healmax )" + 
        		  				" VALUES ( 'Ogre', 200, 2, 0.6, 30, 60, 0.1, 30, 60)";
          
          String query3 = "INSERT INTO monsters ( monstertype, hitpoints, attackspeed, hitchance, mindamage, maxdamage, healchance, healmin, healmax )" + 
	  				" VALUES ( 'Gremlin', 70, 5, 0.8, 15, 30, 0.4, 20, 40)";
          
          String query4 = "INSERT INTO monsters ( monstertype, hitpoints, attackspeed, hitchance, mindamage, maxdamage, healchance, healmin, healmax )" + 
	  				" VALUES ( 'Skeleton', 100, 3, 0.8, 30, 50, 0.3, 30, 50)";
          
          try ( Connection conn = ds.getConnection();
                 Statement stmt = conn.createStatement(); ) {
                int rv = stmt.executeUpdate(query2);
                System.out.println( "1st executeUpdate() returned " + rv );

                rv = stmt.executeUpdate(query3);
                System.out.println( "2nd executeUpdate() returned " + rv );
                
                rv = stmt.executeUpdate(query4);
                System.out.println( "3rd executeUpdate() returned " + rv );
            } catch ( SQLException e ) {
                e.printStackTrace();
                System.exit( 0 );
            }
	}

}
