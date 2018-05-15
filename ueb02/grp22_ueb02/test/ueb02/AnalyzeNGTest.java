/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ueb02;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Reyhan
 */
public class AnalyzeNGTest {
    
    public AnalyzeNGTest() {
    }

    @Test
    public void testCalcDistanceBetween_TrueCase() {
        int [] pos1 = {2, 2};
        int [] pos2 = {3, 5};
        assertEquals(4, Analyze.distance(pos1, pos2)); 
        
    }
     @Test
    public void testCalcDistanceBetween_invalidValue() {
        int [] pos1 = {1, 2};
        int [] pos2 = {3, 11};
        assertEquals(Integer.MAX_VALUE, Analyze.distance(pos1, pos2)); 
    }
    @Test
    public void testCalcDistanceBetween_invalidValueNegative() {
        int [] pos1 = {-1, 2};
        int [] pos2 = {3, 5};
        assertEquals(Integer.MAX_VALUE, Analyze.distance(pos1, pos2)); 
    }
    @Test
    public void testCalcDistanceBetween_invalidAllValue() {
        int [] pos1 = {-1, 13};
        int [] pos2 = {12, -3};
        assertEquals(Integer.MAX_VALUE, Analyze.distance(pos1, pos2)); 
    }
    /*****************************************/
    @Test
    public void testFlyDroneTo_trueCase(){
        //flownDrone is a helper method
        int [] pos1 = {5, 5}; 
        assertEquals(8, Analyze.flownDrone(pos1)); 
        int [] pos2 = {2, 2}; 
        assertEquals(13, Analyze.flownDrone(pos2)); 
        int [] pos3 = {0, 0};
        assertEquals(16, Analyze.flownDrone(pos3)); 
    }
    @Test
    public void testFlyDroneTo_invalidIndex(){
        //the dimension of the map is 10x7
        //flownDrone is a helper method
        int [] pos1 = {10, 8}; 
        assertEquals(-1, Analyze.flownDrone(pos1)); 
        int [] pos2 = {11, 7}; 
        assertEquals(-1, Analyze.flownDrone(pos2)); 
        int [] pos3 = {-4, -4}; 
        assertEquals(-1, Analyze.flownDrone(pos3)); 
        
    }
   @Test
   public void testTransportSameProducts_true(){
       int [] from = {0, 0};
       int[] to = {3, 4};
       assertEquals(1, Analyze.transportSameProduct(from, to, 3, 3));
       assertEquals(6, Analyze.getMap()[0][0].length);
   }
}
