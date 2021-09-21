package project5;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * This class takes a file name as a command line argument and then creates a BSTMountain using the file and determines
 * how many paths through the tree there are for a hiker.
 *
 * @author Finn Mefford
 * @version 5/2/2021
 */
public class BSTMountainAdventure {
    /**
     * This method handles the file i/o and creates a BSTMountain using the given file.
     * Each line of the file must be in the format LABEL SUPPLIES OBSTACLES. This method will create a RestStop using
     * the line and then add it to the BSTMountain from the top of the file downwards.
     *
     * @param args The first input will be the name of the file to be used
     */
    public static void main(String[] args){
        BSTMountain mountain = new BSTMountain();

        try {
            Scanner in = new Scanner(new File(args[0]));

            while(in.hasNext()){
                String line = in.nextLine();
                String[] inputs = line.split(" ");
                ArrayList<String> supplies = new ArrayList<>();
                ArrayList<String> obstacles = new ArrayList<>();

                if (inputs.length > 1) {
                    for (int i = 1; i < inputs.length; i++){
                        switch (inputs[i]) {
                            //if the word is a supply the word is added
                            case "food":
                            case "raft":
                            case "axe":
                                supplies.add(inputs[i]);
                                break;
                            //if the word is river the word is added
                            case "river":
                                obstacles.add(inputs[i]);
                                break;
                            //if the word is fallen and the next word is tree "fallen tree" is added
                            case "fallen":
                                if (i != inputs.length - 1){
                                    if (inputs[i + 1].equals("tree")){
                                        obstacles.add("fallen tree");
                                    }
                                }
                                break;
                        }
                    }
                }
                mountain.add(new RestStop(inputs[0], supplies, obstacles));
            }
        }
        //prints an error if the file name is invalid
        catch (FileNotFoundException e){
            System.err.println("Invalid file");
        }

        mountain.traverse();

    }
}
