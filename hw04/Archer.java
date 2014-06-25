import java.util.*;
/** Archer class
*@author Anubhav Agarwal <aa3329@students.poly.edu>
*@version 1.0 Feb 24, 2013
*/
public class Archer extends Warrior {
	/**
    *Class constructor with the name and strength of the Archer.
    *@param name      A String containing the name of the Archer.
    *@param strength  A Double conatining the strength of the Archer.
    */
	public Archer (String name, double strength) {
		super(name,strength);
	}

	/**
	*Overrides the battle method in Warrior class.
	*Prints TWAG when fighting for its master.
	*/
	@Override
	public void battle () {
		System.out.println("TWAG. " + this.getName() + " says: Take that in the name of my lord," + super.masterName());
	}

}