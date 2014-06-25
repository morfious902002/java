import java.util.*;
/** Wizard class
*@author Anubhav Agarwal <aa3329@students.poly.edu>
*@version 1.0 Feb 24, 2013
*/
public class Wizard extends Protector{
	/**
    *Class constructor with the name and strength of the Wizard.
    *@param name      A String containing the name of the Wizard.
    *@param strength  A Double conatining the strength of the Wizard.
    */
	public Wizard (String name, double strength) {
		super(name,strength);
	}

	/**
	*Overrides the battle method in Protector class.
	*Prints POOF when fighting for its master.
	*/
	@Override
	public void battle () {
		System.out.println("POOF");
	}
   
}