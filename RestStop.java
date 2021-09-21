package project5;

import java.util.ArrayList;

/**
 * This class represents a rest stop that a hiker will come across on the mountain. A rest stop will have a label which
 * is a String of the rest stop's name. The ArrayList supplies will contain food, axes, and rafts that the hiker can use
 * on their journey. The obstacles could contain rivers and fallen trees that can only be passed using the appropriate
 * supplies.
 *
 * @author Finn Mefford
 * @version 5/2/2021
 */
public class RestStop implements Comparable<RestStop>{
    private String label;
    private ArrayList<String> supplies;
    private ArrayList<String> obstacles;

    /**
     * Creates a rest stop with the given label, supplies, and obstacles.
     *
     * @param label String of the rest stop's name
     * @param supplies ArrayList containing the supplies at the rest stop
     * @param obstacles ArrayList containing the obstacles to be overcome at the rest stop
     */
    public RestStop (String label, ArrayList<String> supplies, ArrayList<String> obstacles){
        this.label = label;
        this.supplies = supplies;
        this.obstacles = obstacles;
    }

    /**
     * Getter for label
     * @return String representation of the rest stop's name
     */
    public String getLabel(){
        return label;
    }

    /**
     * Getter for supplies
     *
     * @return An ArrayList of strings representing supplies
     */
    public ArrayList<String> getSupplies(){
        return supplies;
    }

    /**
     * Getter for obstacles
     *
     * @return An ArrayList of strings representing obstacles
     */
    public ArrayList<String> getObstacles(){
        return obstacles;
    }

    /**
     * Creates a string where the label is the first word, followed by all the supplies, followed by all the obstacles
     *
     * @return String representation of the RestStop
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(this.label);
        //appends supplies to string
        for (String supply: supplies){
            sb.append(" ").append(supply);
        }
        //appends obstacles to string
        for (String obstacle: obstacles){
            sb.append(" ").append(obstacle);
        }
        return sb.toString();
    }

    /**
     * Performs alphanumeric comparison between the labels of two rest stops
     *
     * @param o The RestStop object to be compared to
     * @return A positive integer when this is greater than o, a negative integer when this is less than o, and a 0 when
     * they are equal
     */
    @Override
    public int compareTo(RestStop o) {
        return this.label.compareTo(o.getLabel());
    }
}
