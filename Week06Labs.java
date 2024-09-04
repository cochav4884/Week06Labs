package labs;

import java.util.Scanner;

public class Week06Labs {
	
	// This Lab requires you to program a simple game of Tic Tac Toe
		//
		// 		 Note:  there are many ways to implement this game.  Use what 
		//		  		you have already learned.  You have the tools to implement
		//		  		this game.  
		//
		// Rules:  
		//		- Tic Tac Toe is a 2-player game played on a 3x3 grid.  
		//
		//		- One player usually plays "X"s and the other player plays "O"s.
		//
		// 		- In our version of the game, "X"s will always start.
		//
		//		- Players will alternate turns.
		//
		// 		- The game will end when one of the conditions is true:
		//				1. A player gets three squares in a row 
		//				2. A player gets three squares in a column
		//				3. A player gets three squares in either diagonal
		//				4. Every square is filled in, but no player has achieved 
		//					conditions 1,2, or 3 as described above --> A Draw!
		//
		//		- A player can win on turn 5, 6, 7, 8, or 9 (start checking for winners on or before turn 5)
		//
		//		- Error checking of input is not necessary here, but will make this game much more
		//				robust.  If you choose to implement this, get the game working first, and then
		//				add a check of the input to make sure that it's an Integer from 1 to 9.
		//
		//		- Use the Scanner to input the users choice of square -- one suggestion is  
		//				to create numbers within the square, and then have the user choose 
		//				that integer (1-9) to choose the location.
		//
		//		- One way to display the game board would be as follows:
		//				+---+---+---+       to		+---+---+---+		+---+---+---+		+---+---+---+
		//				| 1 | 2 | 3 |	   find		|---|---|---|		| | | | | | |		| \ |   | / |
		//				+---+---+---+	   a win	+---+---+---+		+---+---+---+		+---+---+---+
		//				| 4 | 5 | 6 |	-- look-->	|---|---|---|	&	| | | | | | |   & 	|   | X |   |
		//				+---+---+---+	 at these	+---+---+---+		+---+---+---+		+---+---+---+
		//				| 7 | 8 | 9 |	8 patterns	|---|---|---|		| | | | | | |		| / |   | \ |
		//				+---+---+---+		        +---+---+---+		+---+---+---+		+---+---+---+
			
			
		// Start with these steps:
		//
		//		1. Create a class with a main method -- this Lab can be implemented in just one class.
		//				
		//				Alternatively, you could create an object with it's own methods. 
		//				(e.g. GameBoard.java)
		//
		//		2. You will need to declare a gameBoard.  
		//
		//				As shown above, Tic Tac Toe is played on a 3x3 grid that has 9 spaces. 
		//
		//					a. One suggestion is to create an array of length 9 to represent the board:
		//
		//						For example:  String[] gameBoard = new String[9]; 
		//
		//							i. Just remember that arrays are 0 based, so to access the grid, 
		//							the index would go from 0-8.  
		//
		//							ii. Wins can occur in 8 ways (see above).
		//								For example: A horizontal win would be found by 
		//								looking at the contents of: 
		//									gameBoard[0] & gameBoard[1] & gameBoard[2] 
		//									gameBoard[3] & gameBoard[4] & gameBoard[5] 
		//									gameBoard[6] & gameBoard[7] & gameBoard[8] 
		//
		//					b. Another option for the gameBoard would be a 2d array -- This option might be
		//						is a bit more challenging!  
		//
		//						For example:  String[][] gameBoard = new String[3][3];
		//					
		//							i. Just remember that arrays are 0 based, so to access the grid, 
		//							the index would go from 0-2 in two directions.  
		//
		//							ii. Wins can occur in 8 ways.  A horizontal win would be comparing:  
		//									gameBoard[0][0] & gameBoard[0][1] & gameBoard[0][2] 
		// 
		//		3.  Implement the rules as listed above.  
		//
		//		4.  You might want to have the following methods:
		//					a. display() -- to print the gameBoard
		//					b. checkWinStatus() -- to check if a player has won
		//					c. initialize() -- to set up your new gameBoard
		//			
		//		5.  Reminder:  There is no one way to implement this game.
		//		
		//	
		
		// START YOUR IMPLEMENTATION BELOW THIS LINE
	

	    // Constants for the game
	    private static final int SIZE = 9;
	    private static final String PLAYER_X = "X";
	    private static final String PLAYER_O = "O";
	    private static final String EMPTY = " ";
	    
	    private static String[] gameBoard = new String[SIZE];
	    
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        boolean gameOngoing = true;
	        String currentPlayer = PLAYER_X;
	        
	        initialize();
	        
	        while (gameOngoing) {
	            display();
	            System.out.println("Player " + currentPlayer + ", choose a position (1-9):");
	            
	            int position = scanner.nextInt() - 1;
	            
	            if (position < 0 || position >= SIZE || !gameBoard[position].equals(EMPTY)) {
	                System.out.println("Invalid move. Try again.");
	                continue;
	            }
	            
	            gameBoard[position] = currentPlayer;
	            
	            if (checkWinStatus(currentPlayer)) {
	                display();
	                System.out.println("Player " + currentPlayer + " wins!");
	                gameOngoing = false;
	            } else if (isDraw()) {
	                display();
	                System.out.println("The game is a draw!");
	                gameOngoing = false;
	            } else {
	                // Switch player
	                currentPlayer = currentPlayer.equals(PLAYER_X) ? PLAYER_O : PLAYER_X;
	            }
	        }
	        
	        scanner.close();
	    }
	    
	    private static void initialize() {
	        for (int i = 0; i < SIZE; i++) {
	            gameBoard[i] = EMPTY;
	        }
	    }
	    
	    private static void display() {
	        System.out.println(" " + gameBoard[0] + " | " + gameBoard[1] + " | " + gameBoard[2] + " ");
	        System.out.println("---+---+---");
	        System.out.println(" " + gameBoard[3] + " | " + gameBoard[4] + " | " + gameBoard[5] + " ");
	        System.out.println("---+---+---");
	        System.out.println(" " + gameBoard[6] + " | " + gameBoard[7] + " | " + gameBoard[8] + " ");
	    }
	    
	    private static boolean checkWinStatus(String player) {
	        // Check rows
	        for (int i = 0; i < 3; i++) {
	            if (gameBoard[i * 3].equals(player) && gameBoard[i * 3 + 1].equals(player) && gameBoard[i * 3 + 2].equals(player)) {
	                return true;
	            }
	        }
	        // Check columns
	        for (int i = 0; i < 3; i++) {
	            if (gameBoard[i].equals(player) && gameBoard[i + 3].equals(player) && gameBoard[i + 6].equals(player)) {
	                return true;
	            }
	        }
	        // Check diagonals
	        if (gameBoard[0].equals(player) && gameBoard[4].equals(player) && gameBoard[8].equals(player)) {
	            return true;
	        }
	        if (gameBoard[2].equals(player) && gameBoard[4].equals(player) && gameBoard[6].equals(player)) {
	            return true;
	        }
	        return false;
	    }
	    
	    private static boolean isDraw() {
	        for (String s : gameBoard) {
	            if (s.equals(EMPTY)) {
	                return false;
	            }
	        }
	        return true;
	    }
	}
