import java.util.*;

/** Noble class to define Nobles and their methods.
*@author Anubhav Agarwal <aa3329@students.poly.edu>
*@version 2.0 Feb 24, 2013
*/

public abstract class Noble {
    /**
    *The total strength of the Noble. If it is -ve then Noble is dead.
    */
    public double totalStrength = 0.0;
    /**
    *Name of the Noble.
    */
    private String nName = null;
    /**
    *Class constructor with the name of the Noble.
    *@param name A String containing the name of the Noble.
    */
    public Noble (String name) {
        nName = name;
    }

    /**
    *Returns the Noble's name.
    *@return   Returns a String containing the Name of the Noble.
    */
    public String getName() {
        return nName;
    }    

    /**
    * Returns the total strength of the Noble.
    *@return    Returns a double containing the total strength of the Noble.
    */
    public double getStrength () {
        return this.totalStrength;
    }

    /**
    *Assigns the given value of strength to the Noble's total strength.
    *@param givenStrength assigns the given strength to the Noble's total strength.
    */
    public void setStrength (double givenStrength) {
        totalStrength = givenStrength;
    }

    /**
    *Can be modified by a chiled class that uses Arraylist of hired protectors.
    */
    public void protectorsBattle (){        
    }

    /**
    *Adjusts the strength of Noble and its warrior depending upon the strength of the total
    *strength of loosing Noble.
    *@param winNoble       name of the noble that won the battle.
    *@param defeatedNoble  name of the noble that lost the battle.
    *                      from each of the warrior in winning Noble's army.
    */
    public void adjustStrength (Noble winNoble, Noble defeatedNoble) {
        totalStrength = winNoble.getStrength() - defeatedNoble.getStrength();
    }

    /**
    *Decides who won a battle depending upon the strength of fighting Nobles.
    *It has to be implemented in each child class.
    *@param enemyName Name of the Noble that is being waged against.
    */
    /**
    *Decides who won a battle depending upon the strength of fighting Nobles.
    *It also looks if the Nobles are dead or alive.
    *@param enemyName Name of the Noble that is being waged against.
    */
    public abstract void battle (Noble enemyName);

    /**
    *Overrides the toString method and return a String containing the name of the Noble.
    *@return      String containing Noble's name.
    */
    @Override
    public String toString () {
        return this.nName;          
    }

    /**
    *Marks the total strength of defeated Noble to -1. Which means they are dead.
    *@param deadNoble name of the Noble who died in the battle.
    */
    public void deadNoble(Noble deadNoble) {
        deadNoble.setStrength(-1.0);
    }    
}