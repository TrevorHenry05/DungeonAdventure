package Model;

import java.util.Random;
import java.util.Scanner;
public class MapGenerator {
	Scanner move = new Scanner(System.in);
	
	public static void main(String[] args) {

		 //Call methods
	    Game_Beginning();
	    Game_Board();     
	}

	//Intro to the game
	public static void Game_Beginning(){
	           System.out.println("Maze");
	    System.out.println("                      ");
	}

	//Game Board
	public static void Game_Board(){
	    //Declare new array, maze 10x10
	    String maze[][] = new String[5][5];

	    //Randomly print the obstacles in the maze.
	    for (int i = 0; i < maze.length; i++){
	        for (int j = 0; j < maze.length; j++){
	            double random = Math.random();
	            if (random <= .04){
	                maze[i][j] = Monster;
	            }
	            else if (random > .04 && random <= .25){
	                maze[i][j] = 'M';
	            }
	            else{
	                maze[i][j] = '.';
	            }
	            maze[0][0] = "S";
	            maze[4][4] = "E";
	            System.out.print(maze[i][j]);
	        }
	        System.out.println("");
	    }


	}

	/**
	 *  Add a method called "makePMove." Define char right, char left and so on
	 */
	public static void makeMove(){
	    int row;
	    int col;
	    System.out.print("Enter your move (Up-Down-Left-Right): ");

	}
	   
	   
	   
	   
	   
	   /**      Random rd = new Random(); // creating Random object
      int[] arr = new int[25];
      for (int i = 0; i < arr.length; i++) {
         arr[i] = rd.nextInt(); // storing random integers in an array
         System.out.println(arr[i]); // print array element
      } */
   }
