package project5;

/**
 * This class represents a hiker. The hiker can carry food, rafts, and axes, which are used to deal with obstacles.
 *
 * @author Finn Mefford
 * @version 5/2/2021
 */
public class Hiker {
    private int numOfFood;
    private int numOfRafts;
    private int numOfAxes;

    /**
     * Default constructor. Sets all field to 0.
     */
    public Hiker(){
        this.numOfFood = 0;
        this.numOfRafts = 0;
        this.numOfAxes = 0;
    }

    /**
     * Creates a copy of the given Hiker object
     *
     * @param hikerModel Hiker to be copied
     */
    public Hiker(Hiker hikerModel){
        this.numOfFood = hikerModel.numOfFood;
        this.numOfAxes = hikerModel.numOfAxes;
        this.numOfRafts = hikerModel.numOfRafts;
    }

    /**
     * Determines if the hiker can continue through the rest stop
     *
     * @param restStop The current rest stop that the hiker is at
     * @return True if at least one food, one axe per fallen tree, and one raft per river, otherwise returns false.
     */
    public boolean processRestStop(RestStop restStop){
        //adds supplies to the hiker
        for (String supply: restStop.getSupplies()){
            if (supply.equals("food"))
                this.numOfFood++;
            else if (supply.equals("axe"))
                this.numOfAxes++;
            else if (supply.equals("raft"))
                this.numOfRafts++;
        }
        //removes supplies to deal with obstacles
        //if hiker is missing supplies returns false
        for (String obstacle: restStop.getObstacles()){
            if (obstacle.equals("river"))
                if (numOfRafts > 0)
                    this.numOfRafts--;
                else
                    return false;

            else if (obstacle.equals("fallen tree"))
                if (numOfAxes > 0)
                    this.numOfAxes--;
                else
                    return false;
        }
        this.numOfFood--;
        if (numOfFood < 0)
            return false;

        return true;
    }
}
