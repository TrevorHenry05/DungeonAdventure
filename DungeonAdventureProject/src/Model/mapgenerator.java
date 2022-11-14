package Model;


public class mapgenerator {

	public static void main(String[] args) {
			
		mapgenerator obj[] = new mapgenerator[25];
		Room[] roomObjects = new Room[];
		for (int i = 0; i < 25; i++){
			double random = Math.random();
			if (random <= .04){
				roomObjects = new room();
            }
            else if (random > .04 && random <= .25){
               roomObjects = ("item");
            }
            else{
            	roomObjects =("pillar");
            }
		Dungeon[i]= room1;
	
		}

		for(int i=0; i<25; i++ ) {
			System.out.println(Dungeon[i]);
			
			
		}
		
	}
}
