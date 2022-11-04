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
	           System.out.println("This is your game board:");
	    System.out.println("-------------------------------");
	}

	//Game Board
	public static void Game_Board(){
	    //Declare new array, maze 10x10
	    char maze[][] = new char[10][10];

	    //Randomly print the obstacles in the maze.
	    for (int i = 0; i < maze.length; i++){
	        for (int j = 0; j < maze.length; j++){
	            double random = Math.random();
	            if (random <= .05){
	                maze[i][j] = '*';
	            }
	            else if (random > .06 && random <= .15){
	                maze[i][j] = 'X';
	            }
	            else{
	                maze[i][j] = '.';
	            }
	            maze[0][0] = 'P';
	            maze[9][9] = 'E';
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
