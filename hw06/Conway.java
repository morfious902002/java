import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** Conway class to start Conway's Game of life with GUI.
*@author Anubhav Agarwal <aa3329@students.poly.edu>
*@version 1.0 March 12, 2013
*/
public class Conway {
	public static void main(String args[]) {
		/*
		*Will contain the no. of rows in the game.
		*/
		int row;
		/*
		*Will contain the no. of columns in the game.
		*/
		int col;
		//If no. of rows and cols are given then they are used otherwise
		//a default value is given.
		if (args.length == 2){
			row=Integer.parseInt(args[0]);
			col=Integer.parseInt(args[1]);
		}
		else {
			row = 15;
			col = 15;
		}
		//Start of Conways game.
		ConwayGUI game = new ConwayGUI(row,col);
	}
}