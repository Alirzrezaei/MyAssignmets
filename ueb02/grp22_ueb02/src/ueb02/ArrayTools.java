package ueb02;

/**
 * Helping methods for analyzing an array.
 * @author klk
 */
public class ArrayTools {
    /**
     * this method determines if a certain {@code value} is containing in a
     * given {@code array} and returns the position of the first occurrence.
     *
     * @param array the given array
     * @param value the value to look for
     * @return the position of the first occurrence of the value, if not then -1
     */
    public static int containsAt(int[] array, int value) {
        //TODO insert code that makes sense
       
        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return i;
                }

            }
        }
        return -1;
    }

    /**
     * this method deletes in a given array the element at position {@code idx}
     *
     * @param array the given array
     * @param idx the position to delete at
     * @return a new array not containing the {@code idx}-th value, null if
     * invalid params
     */
    public static int[] deleteElementAt(int[] array, int idx) {
        if (array.length > 0) {
            int[] newArray = new int[array.length - 1];
            int j = 0;
            if (array.length > 0 && idx < array.length) {
                for (int i = 0; i < array.length; i++) {
                    if (i == idx) {
                        i++;
                    }
                    newArray[j] = array[i];
                    j++;
                }
                 return newArray;
            }
           
        }
        return null;
    }

    /**
     * this method inserts in a given array the element at position {@code idx}
     *
     * @param array the given array
     * @param idx the position to insert at
     * @param value the inserting value
     * @return a new array containing at {@code idx} the given {@code value},
     * null if invalid params
     */
    public static int[] insertElementAt(int[] array, int idx, int value) {
        int[] newArray = new int[array.length + 1];
        int j =0;
        if ( idx <= array.length) {
            for (int i = 0; i < array.length; i++) {
                if (i == idx) {
                   newArray[j] = value;
                   j++;
                }
                newArray[j] = array[i];
                j++;
            }
            return newArray;
        }
        return null;
    }
    
    /**
     * Gets the length of the longest array in given array.
     * @param array 2-dimensional array to look in
     * @return length of longest array in given array, 
     *         -1 if array is null or length of array is 0
     */
    public static int getLengthOfLongestArray(int[][] array) {
        int length = 0;
        if (array.length > 0) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].length > length) {
                    length = array[i].length;
                }
            }
            return length;
        }
        return -1;
    }
}
