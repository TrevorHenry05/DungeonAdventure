package Model;


public class mapgenerator {

	public static void main(String[] args) {
			
		String[] Dungeon = new String[25];
		String room1 = " ";
		for (int i = 0; i < 25; i++){
			double random = Math.random();
			if (random <= .04){
				room1 = ("monster");
            }
            else if (random > .04 && random <= .25){
               room1 = ("item");
            }
            else{
                room1 =("pillar");
            }
		Dungeon[i]= room1;
	
		}

		for(int i=0; i<25; i++ ) {
			System.out.println(Dungeon[i]);
			
			
		}
		
	}
}
