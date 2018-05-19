package ueb02;

//import static ueb02.ArrayTools.*;
import java.lang.Math;
//import jdk.management.resource.internal.ApproverGroup;

/**
 * Methods to manage the transport from products from warehouses to customers by drone.
 * 
 * @author Capt'n Mo, klk
 */
public class Analyze {
    
    
    
//<editor-fold defaultstate="collapsed" desc="constants">
    /** signs to show for printing the map. */
    //TODO insert code that makes sense
    private final char WAREHOUSE = 'W';
    private static final char EMPTY = 'E'; 
    private static final char CUSTOMER = 'C';
    /** position of service-station of the drone {@code POS_SERVICE}*/
    //TODO insert code that makes sense
    private static final int [] POS_SERVICE = {0, 0};
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="attributes">
    /**
     * the amount of units the drone flew. Default is 0. {@code units}
     */
    //TODO insert code that makes sense
    private static  int unites = 0; 
    /**
     * the current map working on. Default is the Map from Data. {@code map}
     * 
     */ 
    //TODO insert code that makes sense
    private static int [][][] map = Data.getMap();
    /**
     * the current position of the Drone. Default is POS_SERVICE. {@code posDrone}
     */
    //TODO insert code that makes sense
    private static int [] posDrone = POS_SERVICE.clone();
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="helping methods">
    /**
     * Calculate the Euclidean distance between two points.
     *
     * @param pos1 the first point
     * @param pos2 the second point
     * @return the Euclidean distance between those two points,
     *         Integer.MAX_VALUE if invalid params
     */
    private static int calcDistanceBetween(int[] pos1, int[] pos2) {
        //Math.ceil(), Math.sqrt(), Math.pow() may be used
        //TODO insert code that makes sense
        
        if( isValidPosition(pos1) && isValidPosition(pos2))
        {
            int distance = (int) Math.ceil(Math.sqrt(Math.pow((pos2[0] - pos1[0]), 2) +
                    Math.pow((pos2[1] - pos1[1]), 2)));
            return distance ;
        } else {
            return Integer.MAX_VALUE;
        }      
    }
    
    /**
     * Checks if the given position is valid in the map.
     *
     * @param pos
     * @return true, if pos is a valid position in the map
     */
    private static boolean isValidPosition(int[] pos) {
        //TODO insert code that makes sense
        return ((pos[1] <= Data.getMapDimensions()[1] && pos[0] <= Data.getMapDimensions()[0]) &&
                (pos[0] >= 0 && pos[1] >= 0));
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="working methods">
    /**
     * Flies the drone from current position to a given position via the
     * Euclidean distance. Prints the destination and flewn distance.
     * Changes the attributes {@code units} and {@code posDrone}.
     * If the given position isn_t valid, a message on serr is shown.
     *
     * @param pos the position to fly to
     * @return the distance flown by the drone; if the drone cannot fly -1 is returned
     */
    private static int flyDroneTo(int[] pos) {
        //TODO insert code that makes sense
        if(isValidPosition(pos)){
            unites += calcDistanceBetween(posDrone, pos); 
            posDrone = pos.clone(); 
            return unites; 
        }else {
            System.err.print("wrong position is given. ");
            return -1;  
         } 
    }
    
    /**
     * Transports one product of an order to a specified position with the drone.
     * Flies drone to from, collects count of ordered products at from and
     * flies drone to to. If there aren't enough products at from, the
     * remaining count of the order is given as result.
     *
     * @param from the position of the warehouse to get the product at
     * @param to the position to transport to
     * @param product product to transport
     * @param count count of products to transport
     * @return count of products still to transport
     */
    private static int transportSameProducts(int[] from, int[] to, int product, int count) {
        int [] warehouse = map[from[0]][from[1]].clone(); 
        int [] customer = new int[count];
        int counter = 0;
      
        if(isValidPosition(to) && isValidPosition(from) && Data.isWarehouse(from[0], from[1])){
           int idx = 0;
            while(ArrayTools.containsAt(warehouse, product)!= -1 && count != 0){
                int index = ArrayTools.containsAt(warehouse, product);
                customer[idx] = product;
                idx++;
                System.out.print(warehouse.length+ "*");
                System.out.print(index+ "*");
                warehouse = ArrayTools.deleteElementAt(warehouse, index).clone();
                counter++;
                count--;
            }
            
            map[from[0]][from[1]] = warehouse.clone();
        
            if (isEmpty(to)) {
                map[to[0]][to[1]] = customer.clone();
            } else {  
                int [] customer2 = new int [counter + map[to[0]][to[1]].length];
                int j = 0;
                for (int i = 0; i < customer2.length; i++) {
                    if(i < map[to[0]][to[1]].length){
                    customer2[i] = map[to[0]][to[1]][i];
                    }else{
                        customer2[i] = customer[j];
                        j++;
                    }    
                }
                map[to[0]][to[1]] = customer2.clone();
            }
            
            return count;
        }
        return 0;
    }
    
    /**
     * Determines the nearest warehouse for a specified position and product.
     *
     * @param pos the starting point
     * @param product the product
     * @return the nearest (Euclidean distance) warehouse position having the
     * {@code product}; null if there is no warehouse having the product
     */
    private static int[] findNearestWarehouse(int[] pos, int product) {
        //TODO insert code that makes sense
        boolean isWarehouse = false;
        int [] nearestWarehouse = new int [2];
        int distance = calcDistanceBetween(new int[] {0, 0}, Data.getMapDimensions());
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                if(Data.isWarehouse(i, j) && ArrayTools.containsAt(pos, product)!= -1){
                    
                    if (calcDistanceBetween(pos, new int[]{i, j}) < distance) {
                        isWarehouse = true; 
                        nearestWarehouse[0] = i;
                        nearestWarehouse[1] = j;
                        distance = calcDistanceBetween(pos, nearestWarehouse);
                    }
                }
            }
        }
        if(isWarehouse){
            return nearestWarehouse; 
        }else return null;
        
    }
    
    /**
     * Transports an order-series by the drone.
     * Process every order of the series. Prints the values of the order.
     * Searches for the nearest warehouse with the product, 
     * transports the ordered number of the product to the target adress.
     * If the first detected warehouse does not hold enough of the product,
     * the next warehouse with the product has to be used.
     * If there is no warehouse with the product, a message on serr is printed.
     * After all orders have been delivered, the drone flies to the service-station.
     *
     * @param orders the order-series working on
     * @return false if there is no warehouse with the product/not enough of the product; true otherwise
     */
    public static boolean transportOrdersOfOneSeries(int[][] orders) {
        //TODO insert code that makes sense
        
        return false; 
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Output">
    /**
     * Prints the current map to sout.
     * The signs declared in constants at begin of the class are used to 
     * visualize the cells.
     */
    public static void printCurrentState() {
        //TODO insert code that makes sense
    }

    /**
     * Determines the maximum length of a given
     * {@code column} in the map-array. Used for nice output only.
     *
     * @param column the given column
     * @return the maximum of the length of all cells in the given
     * {@code column} plus 1 (for the sign of one's cell)
     */
    private static int getPrintWidthPerColumn(int column) {
        //TODO insert code that makes sense
        return 0;
    }
    //</editor-fold>

    /**
     * resetting every value to its default
     */
    public static void resetToOrigState() {
        //TODO insert code that makes sense
        unites = 0; 
        map = Data.getMap();
        posDrone = POS_SERVICE;
    }
    
    /**
     * Helper method to test private methods.
     * @param pos1 first point that drone start from it
     * @param pos2 second point that drone finish there
     * @return integer number as distance that drone flew or max value of integer
     * if the positions are not valid
     */
    public static int distance(int[] pos1, int[] pos2){
        return calcDistanceBetween(pos1, pos2); 
    }
    /**
     * Helper method to give the distance of the the drone from current position to 
     * the given position.
     * @param pos the position that the drone should fly to. 
     * @return distance between to positions and -1 if the the position is not valid. 
     */
    public static int flownDrone(int [] pos){
        return flyDroneTo(pos); 
    }
    public static int transportSameProduct(int[] from, int[] to, int product, int count){
        return transportSameProducts(from, to, product, count);
    }
    private static boolean isEmpty(int [] pos){
        return map[pos[0]][pos[1]].length < 1;
    }
    public static void getMap(){
        System.out.println("");
         for(int i = 0; i < map[0][0].length; i++){
        System.out.print(map[0][0][i]);
        }
         System.out.println("");
         for(int i = 0; i < map[1][2].length; i++){
        System.out.print(map[1][2][i]);
        }
      //return map;
       
    }

}
