import java.util.*;

/** Protector class
*@author Anubhav Agarwal <aa3329@students.poly.edu>
*@version 1.0 Feb 24, 2013
*/
public abstract class Protector {
	/**
    *Contains the Strength of the Protector.
    */
    private double strength = 0.0;
    /**
    *Conatins the name of the Protector.
    */
    private String name = null;
    /**
    *Contains the Noble if the Protector has been hired.
    */
    private Lord master = null;

    /**
    *Class constructor with the name and strength of the Protector.
    *@param gname      A String containing the name of the Protector.
    *@param gstrength  A Double conatining the strength of the Protector.
    */
    public Protector (String gname, double gstrength) {
        strength = gstrength;
        name     = gname;
    }

    /**
    *Checks to see if the Protector already has a master. If not then sets it to the given Lord's Name.
    *@param noblesName Name of the lord is to be the Protector master.
    *@return           A boolean to say if it was successful or not in setting the Protector's master.
    */
    public boolean setMaster (Lord lordsName) {
        if (master == null && strength > 0.0) {
            master = lordsName;
            return true;
        }
        else {
            return false;
        }
    }

    /**
    *Checks if a Protector can runway if he/she was hired by a noble.
    *If he/she can runaway then decreases the strength of the Noble/Lord
    *and makes the master feild null. Also prints a message saying
    *that the Protector is running away.
    */
    public void runaway () {
        if (master != null && strength > 0.0){
            master.removeProtector(this);    
            System.out.println("So long "+ master.getName() +". I'm out'a here! -- "+ name);
            master = null;  
        }   
    }

    /**
    *Returns the string containing the Protector's name.
    *@return  A String containing the name of the Protector.
    */
    public String getName () {
        return this.name;
    }

    /**
    *Returns a double containing the Protector's strength.
    *@return A double containing the Protector's strength.
    */
    public double getStrength () {
        return this.strength;
    }

    /**
    *Sets the Protector's strength to the given double value.
    *@param adjStrength A double that is assigned as the strength of the Protector.
    */
    public void setStrength (double adjStrength) {
        this.strength = adjStrength;
    }

    /**
    *Each child Protector does something special and has to override it.
    */
    public abstract void battle ();

    /**
    *Return a string containing the name of the master.
    *@return A String conatining the name of the master.
    */
    public String masterName () {
        if (master != null) {
    	   return master.getName();
        }
        else
            return null;
    }

}