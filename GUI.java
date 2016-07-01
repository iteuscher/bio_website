import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * @author Brian Sea
 * @date 08/01/2015
 * editor: Isaac Teuscher
 * nickname: "Iron Code"
 */


public class GUI {
	private JButton[] board;
	private GameLogic logic;
	private JButton reset;

	/**
	 * Creates the graphical frontend of TicTacToe with the default logic
	 */
	public GUI() {
		int n = 0;
		boolean valid = false;
		while (!valid)
		{
			String nn = JOptionPane.showInputDialog("Enter the dimensions you want to have (ie: 3 for a 3 by 3 board)");
			if(nn == null ) {
				System.exit(-1);
			}
			
			try 
			{
				valid=true;
				n = Integer.parseInt(nn);
				if (n<=1)
				{
					valid=false;
					JOptionPane.showMessageDialog(null, "Error: Input positive dimensions greater than 1", "Error please enter a positive Integer greater than 1", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			catch (NumberFormatException e)
			{
				valid=false;
				JOptionPane.showMessageDialog(null,"Error: Input legit dimensions", "Error please enter an Integer", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		

		logic = new GameLogic(n);

		/*
		 * Create the number of spaces needs for the board
		 */
		board = new JButton[logic.numSpaces()];

		// Create the actual buttons and attach
		// ActionListeners to each one
		for( int i = 0; i < board.length; i++ ) {
			// TODO: Create the buttons and attach them to the array
			board[i] = new JButton();

			board[i].addActionListener( new BtnListener() );
		}

		reset = new JButton("Reset");
		// TODO: Add a ResetListener reset
		reset.addActionListener( new ResetListener() );
		// TODO: Complete the ResetListener
	}

	// This method is called each time we want to update
	// the board
	private void updateBoard() {

		// Go through the board and place the correct
		// text in each button
		for( int i = 0; i < board.length; i++ ) {
			String filled = logic.getPlayer(i);
			board[i].setText( filled );
			// TODO: If the button is filled, then disable it
			if (board[i].getText()==""){
				board[i].setEnabled(true);
			}
			else
			{
				board[i].setEnabled(false);
			}
		}
	}

	/**
	 * Creates the graphical pieces of the front-end
	 * @return the frame to show on the screen
	 */
	public JFrame createGUI(){
		JFrame f = new JFrame("Tic Tac Toe");
		f.setLayout(new BorderLayout());


		JPanel panel = new JPanel();
		// TODO: fix the board layout
		for( int i = 0; i < board.length; i++ ) {
			panel.add(board[i]);
		}
		f.add( panel, BorderLayout.CENTER);
		f.add( reset, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout((int)Math.sqrt(board.length), (int)Math.sqrt(board.length)));

		return f;
	}

	private class BtnListener implements ActionListener{
		public void actionPerformed( ActionEvent e )
		{
			// Get the button that was pressed
			JButton b = (JButton)e.getSource();
			for( int i = 0; i < board.length; i++ )
			{
				// if the button is in the board
				if( b == board[i] ){
					// Button i was pressed!

					// TODO: Make the move in the logic
					logic.makeMove(i);

					// TODO: Update the board
					updateBoard();

					// TODO: Check for a winner
					String winner = logic.checkWin();
					if (winner.equals(""))
					{

					}
					else
					{
						//display the string given from Check win
						for( int k = 0; k < board.length; k++ )
							{
								//logic.reset();
							
								//updateBoard();
								board[k].setEnabled(false);
							}
						JOptionPane.showMessageDialog(null, winner + " Wins!!!!!","YAY!!!",JOptionPane.PLAIN_MESSAGE);
							
						
						}

						// TODO: Handle a Tie
						//NEED TO DO!

					}
				}
			}
		}

		// This listener 'fires' when the rest button is pressed and
		// it should reset the logic and board
		private class ResetListener implements ActionListener{
			public void actionPerformed( ActionEvent e ) {
				// TODO: Complete Me!
				logic.reset();
				//for( int i = 0; i < board.length; i++ ) {
				//board[i].setText("");
				updateBoard();
			}

		}



		public static void main(String[] args) {
			GUI app = new GUI();
			JFrame f = app.createGUI();
			f.setSize(500,500);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
			
			JOptionPane.showMessageDialog(null, "This code is dedicated to Mr Steve Gregg, for his wisdom and teachings of coding", "Dedication" ,JOptionPane.PLAIN_MESSAGE);
			JOptionPane.showMessageDialog(null, "Simply click on a square to take it. Use the 'Reset' button to start over. X starts first. Enjoy your game!","Welcome to Tic Tac Toe by Isaac Teuscher!",JOptionPane.PLAIN_MESSAGE);
			
		}
	}
