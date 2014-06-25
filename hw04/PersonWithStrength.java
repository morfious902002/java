import java.util.*;
/** PersonWithStrength class to define nobles fight themself.
*@author Anubhav Agarwal <aa3329@students.poly.edu>
*@version 1.0 Feb 24, 2013
*/
public class PersonWithStrength extends Noble{
    /**
    *Class constructor with the name of the PersonWithStrength and his strength.
    *@param name A String containing the name of the PersonWithStrength.
    *@param strength double with the strength of the PersonWithStrength.
    */
	public PersonWithStrength (String name, double strength) {
		super(name);
		this.setStrength(strength);		
	}

	/**
    *Overrides the toString method and return a String containing the name of PersonWithStrength and their strength.
    *@return      String containing Noble's name.
    */
    @Override
    public String toString () {
        return getName() +" : " + getStrength();          
    }

    /**
    *Decides who won a battle depending upon the strength of fighting Nobles.
    *It also looks if the Nobles are dead or alive.
    *@param enemyName Name of the Noble that the PersonWithStrength is fighting.
    */
    public void battle (Noble enemyName) {
        System.out.println(this.getName() + " battles " + enemyName.getName());
        if (this.getStrength() > enemyName.getStrength() && this.getStrength() >= 0.0 && enemyName.getStrength() >= 0.0) {
            if (enemyName instanceof Lord) {
                enemyName.protectorsBattle();
            }
            this.adjustStrength(this, enemyName);
            enemyName.deadNoble(enemyName);            
            System.out.println(this.getName()+" defeats "+ enemyName.getName());
        }
        else if (this.getStrength() < enemyName.getStrength() && this.getStrength() >= 0.0 && enemyName.getStrength() >= 0.0) {
            if (enemyName instanceof Lord) {
                enemyName.protectorsBattle();
            }
            enemyName.adjustStrength(enemyName, this);           
            this.deadNoble(this);
            System.out.println(enemyName.getName()+" defeats "+ this.getName());
        }
        else if (this.getStrength() == enemyName.getStrength() && this.getStrength() >= 0.0 && enemyName.getStrength() >= 0.0) { 
            if (enemyName instanceof Lord) {
                enemyName.protectorsBattle();
            }        
            deadNoble(this);
            deadNoble(enemyName);
            System.out.println("Mutual Annihilation: "+this.getName()+" and "+enemyName.getName()+" die at each other's hands");
        }
        else if (this.getStrength() >= 0.0 && enemyName.getStrength() < 0.0) {
            System.out.println("He's dead, " + this.getName());
        }
        else if (this.getStrength() < 0.0 && enemyName.getStrength() >= 0.0){
        	System.out.println("He's dead, " + enemyName.getName());
        }
        else if (this.getStrength() < 0.0 && enemyName.getStrength() < 0.0) {
            System.out.println("Oh, NO! They're both dead!  Yuck!");
        }
    }
}