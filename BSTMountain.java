package project5;

/**
 * This class uses a BST in order to represent a mountain. Each node represents a rest stop, with the connections between
 * them representing paths. A childless node whose depth is less than the max depth, while a childless node at max depth
 * is an exit.
 *
 * The bones of this class are taken from Professor Klukowska's BST<E> however I made it so it functions only with the
 * RestStop class. Additionally, the traversal functions are entirely my own work.
 *
 * @author Finn Mefford
 * @version 5/2/2021
 */
public class BSTMountain {

    private BSTNode root;

    private boolean added;
    public int depth;
    private int tempDepth;

    /**
     * Default constructor. Creates an empty tree.
     */
    public BSTMountain(){
        root = null;
    }

    /**
     * Wrapper function for the recursive add
     *
     * @param data The RestStop object to be added to the tree
     * @return True if data is successfully added, otherwise false
     */
    public boolean add(RestStop data){
        if (data == null){
            return false;
        }
        //replace root with reference to new tree after data is added
        root = recAdd(data, root);
        tempDepth = 0;
        return added;
    }

    /**
     * Recursive implementation of the add function
     *
     * @param data RestStop object to be added
     * @param node Node at which the recursive call is made
     * @return The root of the tree containing the new value
     */
    private BSTNode recAdd(RestStop data, BSTNode node){
        //if the current node is null sets the current node to be the new node
        if (node == null){
            added = true;
            if (tempDepth > depth){
                depth = tempDepth;
            }
            return new BSTNode(data);
        }

        int comp = node.compareTo(data);

        //traverses to the right or left depending on the comparison between current node and new node
        //if they are equal the recursive call is ended
        if (comp > 0){
            tempDepth++;
            node.left = recAdd(data, node.left);
        }
        else if (comp < 0){
            tempDepth++;
            node.right = recAdd(data, node.right);
        }
        else {
            added = false;
            return node;
        }
        return node;
    }

    /**
     * Wrapper function for the recursive traversal function
     */
    public void traverse(){
        Hiker hiker = new Hiker();
        BSTNode current = root;
        int hikerDepth = 0;
        recTraverse(hiker, current, hikerDepth, "");
    }

    /**
     * Recursive implementation of the traversal function
     *
     * This function prints all of the paths that a hiker can take down the mountain. This is based on the Hiker object's
     * processRestStop function which determines whether the hiker is able to continue through a rest stop.
     *
     * @param hiker The hiker to traverse the mountain
     * @param current The node being called recursively
     * @param hikerDepth This keeps track of the depth of the current node. The hiker cannot exit the mountian unless
     *                   they are at the max depth of the tree
     * @param solutions A string containing possible paths down the mountain
     */
    public void recTraverse(Hiker hiker, BSTNode current, int hikerDepth, String solutions){
        if (current == null){
            return;
        }
        //if the current node is childless and the hiker is at the max depth, prints the path to the current node
        if (current.left == null && current.right == null){
            if (hikerDepth == depth){
                solutions = solutions + current.data.getLabel();
                System.out.println(solutions);
            }
        }
        //recursively calls the two child nodes if the hiker has enough supplies to continue
        else if (hiker.processRestStop(current.data)){
            solutions = solutions + current.data.getLabel() + " ";
            recTraverse(new Hiker(hiker), current.left, hikerDepth + 1, solutions);
            recTraverse(new Hiker(hiker), current.right, hikerDepth + 1, solutions);
        }
    }

    /**
     * This class represents an individual node of the tree
     */
    private class BSTNode implements Comparable<RestStop>{
        private RestStop data;

        BSTNode left;
        BSTNode right;

        /**
         * Creates a new node using the given rest stop
         *
         * @param data Must be a RestStop object
         */
        public BSTNode(RestStop data){
            this.data = data;
            this.left = null;
            this.right = null;
        }

        /**
         * The nodes are compared using the compareTo() of RestStop
         *
         * @param o The RestStop object to be compared to
         * @return A positive int if this is greater than o, a negative int if this is less than o, and 0 if they are
         * the same
         */
        @Override
        public int compareTo(RestStop o) {
            return this.data.compareTo(o);
        }
    }
}
