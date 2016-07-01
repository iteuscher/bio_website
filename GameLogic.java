/**
 * @author Brian Sea
 * @date 08/01/2015
 */
public class GameLogic {

	// The board spaces
	private int[] board;

	// Whose turn it is; -1 = 0, 1 = X
	private int turn;

	/**
	 * Constructs a Tic-Tac-Toe logic with nine spaces
	 */
	public GameLogic(int n) {
		// Create the array of nine spaces 
		/*
		 * TODO: Create the array of nine spaces
		 */
		board = new int[n*n];
		reset();
	}

	/**
	 * Get the number of spaces in the game.  The board will always
	 * be square.
	 * @return the total number of space in the game.
	 */
	public int numSpaces() {
		return board.length;
	}

	/**
	 * Resets the board to starting positions.
	 * X always goes first
	 */
	public void reset(){
		for( int i = 0; i < board.length; i++ ) {
			board[i] = 0;
			turn = 1;
		}
	}

	/**
	 * @param i the space to make the next move [0, numSpaces-1]
	 * @return true if the move was made, false for an invalid move
	 */
	public boolean makeMove( int i )
	{
		boolean rtn = false;

		// Make sure that i is within the board
		if( i >= 0 && i < board.length ) {
			/*
			 * TODO: Make the move and change the turn
			 */
			board[i] = turn;
			turn = turn * -1;
			rtn = true;

		}
		return rtn;
	}


	/** 
	 * @return The player that has won as a String ("X", "O", "Tie", "")
	 * @return "X" if X wins
	 * @return "O" if O wins
	 * @return "Tie" if it is a tie
	 * @return "" if the game is not over
	 */
	public String checkWin(){
		String rtn = "";

		/*  The board is represented as follows:
		 *     -------------
		 *     | 0 | 1 | 2 |
		 *     -------------
		 *     | 3 | 4 | 5 |
		 *     -------------
		 *     | 6 | 7 | 8 |
		 *     -------------
		 *     
		 *     Since O is -1 and X is 1, if the 
		 *     sum of any row or column or diagonal
		 *     adds to 3 or -3, we know that we have 
		 *     a winner
		 */

		int sum = 0;


		// Check for a Tie

		for (int i = 0; i< board.length; i++)
		{
			if (board[i] == -1 || board[i] == 1)
			{
				sum++;
			}
		}
		if (sum == board.length)
		{
			rtn = "Everyone";
		}

		/*
		 * TODO:  Check for a tie. You may add a field for this to work
		 */


		// Check Rows
		//nested loop checking num at the end of each row
		/*
		 * TODO: Check Rows Here
		 */
		int num = 0;

		for (int k = 0; k<board.length; k += Math.sqrt(board.length))
		{
			num = 0;

			for (int i = k; i<Math.sqrt(board.length)+k; i++)
			{
				num = num + board[i];
				if (num == Math.sqrt(board.length))
				{
					rtn = "X";
				}


				else if (num == -1*(Math.sqrt(board.length)))
				{
					rtn = "0";
				}

			}
		}


		// Check Columns
		//use dimensions from row check and do a nested loop
		/*
		 * TODO: Check Columns Here
		 */
		num = 0;

		for (int i =0; i<Math.sqrt(board.length); i++)
		{
			num = 0; 
			for (int k = i; k<board.length; k += (Math.sqrt(board.length)))
			{
				num = num + board[k];
				if (num == Math.sqrt(board.length))
				{
					rtn = "X";
				}


				else if (num == -1*(Math.sqrt(board.length)))
				{
					rtn = "0";
				}

			}
		}



		//add the length +1 to get to the next diagonal
		// Check Diagonals
		/*
		 * TODO: Check Diagonals here
		 */
		num = 0;

		for (int i =0; i<board.length; i+= Math.sqrt(board.length)+1)
		{
			num = num + board[i];
			if (num == Math.sqrt(board.length))
			{
				rtn = "X";
			}


			else if (num == -1*(Math.sqrt(board.length)))
			{
				rtn = "0";
			}

		}

		
		//check other diagonals
		num = 0;

		for (int i = (int) Math.sqrt(board.length)-1; i<board.length-1; i+= ((Math.sqrt(board.length))-1))
		{
			num = num + board[i];
			if (num == Math.sqrt(board.length))
			{
				rtn = "X";
			}


			else if (num == -1*(Math.sqrt(board.length)))
			{
				rtn = "0";
			}

		}



		return rtn;
	}

	/**
	 * @param space the index of the space to check [0, numSpace() - 1]
	 * @return the player in the space; An empty string is returned if the space is blank.
	 */
	public String getPlayer( int space ) {
		String rtn = "";

		// Check that the space is in the board
		if( space >= 0 && space < board.length )
		{
			// It is, so translate it the player number
			// into a human readable string
			if( board[space ] == 1 ) {
				rtn = "X";
			}
			else if( board[space] == -1 )
			{
				rtn = "O";
			}
		}
		return rtn;
	}
}
