import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** MyFrame class to define the JFrame.
*@author Anubhav Agarwal <aa3329@students.poly.edu>
*@version 1.0 March 12, 2013
*/
class MyFrame extends JFrame {
	MyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Conways Game Of Life");
	}
}

/** GameGUI class to define Conway's Game of life with GUI.
*@author Anubhav Agarwal <aa3329@students.poly.edu>
*@version 1.0 March 12, 2013
*/
public class ConwayGUI {
	/**
    *Two dimensional array of JButtons.
    */
	private final JButton[][] button;
	/**
    *Two dimensional array of JButtons.
    */
	private static JButton[][] buttonArr;
	/**
    *The MyFrame tis the GUI for the game.
    */
	private MyFrame myFrame = new MyFrame();
	/**
    *JPanel that will conatin the menus i.e Next Gen. and Clear button.
    */
	private JPanel menus = new JPanel();
	/**
    *JButton for the menus Jpanel. When player clicks on this it, next generation of the 
    *game will be calculated.
    */
	private JButton genButton = new JButton("Next Gen.");
	/**
    *JButton for the menus Jpanel. When player clicks on this it, the board will be cleared.
    */
	private JButton clearButton = new JButton("Clear");
	/**
    *JPanel that will conatin the button for the grid.
    */
	private JPanel gameGrid = new JPanel();

	/**
	*Class constructor. No. of rows and col are its parameters.
	*Its adds buttons and panels to the frame. It also calls appropriate 
	*functions for particular event.
	*@param row  Final int containing the number of rows in the game.
	*@param col  Final int containing the numbee of cols in the game.
	*/
	public ConwayGUI(final int row,final int col) {		
		myFrame.setVisible(true);		
		menus.add(genButton);
		menus.add(clearButton);
		myFrame.add(menus,BorderLayout.SOUTH);		
		gameGrid.setLayout(new GridLayout(row,col));
		myFrame.add(gameGrid,BorderLayout.CENTER);
		button = new JButton[row+2][col+2];
		setGamePanel(row,col);
		clearAction(row, col);
		generateAction(row, col);		
		myFrame.pack();
	}

	/**
	*Creates the buttons that are added to the panel conatining the games grid.
	*@param row  Final int containing the number of rows in the game.
	*@param col  Final int containing the numbee of cols in the game.
	*/
	private void setGamePanel (final int row,final int col){
		for(int i = 0; i < row+2; i++)
			for(int j = 0;j < col+2; j++) {
				button[i][j] = new JButton();
				button[i][j].setBackground(Color.blue);
				button[i][j].setPreferredSize(new Dimension(30,30));
			}

		for(int x = 1;x < row+1;x++) 
			for(int y = 1;y < col+1;y++) {
				final int i=x,j=y;
				gameGrid.add(button[x][y]);
				button[x][y].addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if(button[i][j].getBackground() == Color.blue)
							button[i][j].setBackground(Color.yellow);
						else
							button[i][j].setBackground(Color.blue);
					}
				});
			}
	}

	/**
	*Calls to calculates the next generation of Conways Game of Life
	*when the player clicks on the Next Gen button
	*@param row  Final int containing the number of rows in the game.
	*@param col  Final int containing the numbee of cols in the game.
	*/
	private void generateAction (final int row, final int col){
		genButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePlay(row,col);
			}
		});
	}

	/**
	*Clears the game grid if the player clicks on the clear button.
	*@param row  Final int containing the number of rows in the game.
	*@param col  Final int containing the numbee of cols in the game.
	*/
	private void clearAction(final int row, final int col){
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i  < row; i++) 
					for(int j = 0;j < col; j++)
						button[i][j].setBackground(Color.blue);		
			}
		});
	}

	/**
	*Calculates the next generation of the game depending upon the number
	*of live neighnours.
	*@param row  Final int containing the number of rows in the game.
	*@param col  Final int containing the numbee of cols in the game.
	*/
	private void gamePlay(int row,int col){
		buttonArr = new JButton[row+2][col+2];
		for(int i = 0;i < row+2;i++){
			for(int j = 0;j < col+2;j++){
				buttonArr[i][j] = new JButton();
				buttonArr[i][j].setBackground(Color.blue);
			}
		}
		for(int i = 1;i < row+1;i++){
			for(int j = 1;j < col+1;j++){
				int neigbour = liveNeighbours(i,j);
				if ((neigbour == 2 || neigbour == 3) && button[i][j].getBackground() == Color.yellow){
					buttonArr[i][j] = button[i][j];
				}
				else if (neigbour < 2 || neigbour > 3){
					buttonArr[i][j].setBackground(Color.blue);
				}
				else if (neigbour == 3 && button[i][j].getBackground() == Color.blue){
					buttonArr[i][j].setBackground(Color.yellow);
				}
			}
		}
		for(int i = 0;i < row+2;i++){
			for(int j = 0;j < col+2;j++){
				if(buttonArr[i][j].getBackground() == Color.yellow){
					button[i][j].setBackground(Color.yellow);
				}
				else
					button[i][j].setBackground(Color.blue);
			}
		}
	}

	/**
	*Returns the number of neighbours that are alive.
	*@param i  Row of the cell whose live neighbours are being calculated. 
	*@param j  Column of the cell whose live neighbours are being calculated.
	*@param count  Contains the total number of live neighbours.
	*@return Returns an int containing the number of living neighbours.
	*/
	private int liveNeighbours(int i,int j) {
		int count = 0;
		if (button[i - 1][j - 1].getBackground() == Color.yellow) {
			count++;
		}
		if (button[i - 1][j].getBackground() == Color.yellow) {
			count++;
		}
		if (button[i - 1][j + 1].getBackground() == Color.yellow) {
			count++;
		}
		if (button[i][j - 1].getBackground() == Color.yellow) {
			count++;
		}
		if (button[i][j + 1].getBackground() == Color.yellow){
			count++;
		}
		if (button[i + 1][ j - 1].getBackground() == Color.yellow){
			count++;
		}
		if (button[i + 1][j].getBackground() == Color.yellow) {
			count++;
		}
		if (button[i + 1][j + 1].getBackground() == Color.yellow) {
			count++;
		}
		return count;
	}
}