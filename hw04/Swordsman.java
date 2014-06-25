import java.util.*;

/** Swordsman class
*@author Anubhav Agarwal <aa3329@students.poly.edu>
*@version 1.0 Feb 24, 2013
*/
public class Swordsman extends Warrior {
	/**
    *Class constructor with the name and strength of the Swordsman.
    *@param name      A String containing the name of the Swordsman.
    *@param strength  A Double conatining the strength of the Swordsman.
    */
	public Swordsman (String name, double strength) {
		super(name,strength);
	}

	/**
	*Overrides the battle method in Warrior class.
	*Prints CLANG when fighting for its master.
	*/
	@Override
	public void battle () {
		System.out.println("CLANG. " + this.getName() + " says: Take that in the name of my lord," + super.masterName());
	}
}