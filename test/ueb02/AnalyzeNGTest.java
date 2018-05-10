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
    
    
}
