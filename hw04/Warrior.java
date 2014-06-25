import java.util.*;

/** Warrior class to define different types of Warriors.
*@author Anubhav Agarwal <aa3329@students.poly.edu>
*@version 2.0 Feb 24, 2013
*/

public abstract class Warrior extends Protector{
    /**
    *Class constructor with the name and strength of the Warrior.
    *@param name      A String containing the name of the Warrior.
    *@param strength  A Double conatining the strength of the Warrior.
    */
    public Warrior (String name, double strength) {
        super(name,strength);
    }
}