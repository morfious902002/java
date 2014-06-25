import java.util.*;

/** Lord class to define nobles who hire protectors to fight.
*@author Anubhav Agarwal <aa3329@students.poly.edu>
*@version 1.0 Feb 24, 2013
*/
public class Lord extends Noble {

	/**
    *Arraylist containing all the protectors hired by the Lord that are currenly
    *working for him/her.
    */
    private List<Protector> hiredProtectors = new ArrayList<Protector>();

    /**
    *Class constructor with the name of the Lord.
    *@param name A String containing the name of the Lord.
    */
    public Lord (String name) {
        super(name);
    }

    /**
    *Calls the battle function for each of the Protector in Lords Army.
    *Different protectors do different action during a batlle.
    */
    public void protectorsBattle () {
        for (Protector p : hiredProtectors) {
            if (p.getStrength() > 0)
                p.battle();
        }
    }

	/**
    *Used to hire a protector by a Lord. Checks if the protector is alive and can be hired before
    *adding her/him to the list of hired protectors. It also increases the total strength of the Lord.
    *@param pName the name of the protector that is being hired by the Lord.
    */
    public void hire (Protector pName) {
        if (pName.setMaster(this) && totalStrength >= 0.0) {
            hiredProtectors.add(pName);
            totalStrength = totalStrength + pName.getStrength();
        }
        else {
            System.out.println(this.getName()+" could not hire "+ pName.getName());
        }
    }

    /**
    *Used to removes the warrior that ran away because of fear.
    *It also reduces the total strength of the Lord depending upon the strength of the
    *protector that ran away.
    *@param deserter name of the protector that ran away
    */
    public void removeProtector (Protector deserter) {
        totalStrength = totalStrength - deserter.getStrength();
        hiredProtectors.remove(deserter);
    }

    /**
    *Marks the total strength of defeated Lord and its protectors to -1. Which means they are dead.
    *@param deadNoble name of the Noble who died in the battle.
    */
    public void deadNoble(Lord deadNoble) {
        deadNoble.setStrength(-1.0);
        if (deadNoble instanceof Lord) {
            for (Protector p : deadNoble.hiredProtectors) {
                    p.setStrength(0.0);
               }
        }
    }

    /**
    *Overrides the toString method and return a String containing the name of the Noble and its protectors.
    *It also prints the strength of the protectors and the total no. of protectors in the Noble's army.
    *@return      String containing Noble and protectors name. protectors Strength and Army Count.
    */
    @Override
    public String toString () {
        String protectors = new String();
        for (Protector w : this.hiredProtectors) {
                    protectors = protectors + "    " +(w.getName() + ": "+
                        Double.toString(w.getStrength())) + "\n";
            }
        return this.getName() + " has an army of " + this.hiredProtectors.size() + "\n" + protectors ;          
    }

    /**
    *Adjusts the strength of Noble and its warrior depending upon the strength of the total
    *strength of loosing Noble.
    *@param winNoble       name of the noble that won the battle.
    *@param defeatedNoble  name of the noble that lost the battle.
    *@param ratio          temporary variable to store the strength that needs to be decreased
    *                      from each of the warrior in winning Noble's army.
    */
    public void adjustStrength (Lord winNoble, Noble defeatedNoble) {        
        winNoble.setStrength(winNoble.getStrength() - defeatedNoble.getStrength());
        if (winNoble instanceof Lord) {
            double ratio = defeatedNoble.getStrength() / (double)winNoble.hiredProtectors.size();
            for (Protector w : winNoble.hiredProtectors) {
                    double tempStrength = w.getStrength();
                    w.setStrength(tempStrength-ratio);
            }
        }
    }

    /**
    *Decides who won a battle depending upon the strength of fighting Nobles.
    *It also looks if the Nobles are dead or alive.
    *@param enemyName Name of the Noble that is being waged against.
    */
    public void battle (Noble enemyName) {
        System.out.println(this.getName() + " battles " + enemyName.getName());
        if (this.getStrength() > enemyName.getStrength() && this.getStrength() >= 0.0 && enemyName.getStrength() >= 0.0) {
            protectorsBattle();
            this.adjustStrength(this, enemyName);
            deadNoble(enemyName);            
            System.out.println(this.getName()+" defeats "+ enemyName.getName());
        }
        else if (this.getStrength() < enemyName.getStrength() && this.getStrength() >= 0.0 && enemyName.getStrength() >= 0.0) {
            protectorsBattle();
            enemyName.adjustStrength(enemyName, this);           
            deadNoble(this);            
            System.out.println(enemyName.getName()+" defeats "+ this.getName());
        }
        else if (this.getStrength() == enemyName.getStrength() && this.getStrength() >= 0.0 && enemyName.getStrength() >= 0.0) {  
            protectorsBattle();                   
            this.deadNoble(this);
            enemyName.deadNoble(enemyName);
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