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
public class DataNGTest {
    
    public DataNGTest() {
    }

    @Test
    public void testGetORderSeries_trueCase() {      
        assertEquals(3, Data.getOrderSeries(1).length);
        assertEquals(1, Data.getOrderSeries(3).length);
        assertEquals(5, Data.getOrderSeries(2)[0][3]);
    }
     @Test
    public void testGetORderSeries_invalidIndex() {      
        assertEquals(null, Data.getOrderSeries(-1));
       assertEquals(null, Data.getOrderSeries(4));
    }
      @Test
    public void testGetMap_Index() {      
        assertEquals(3, Data.getMap()[0][0][3]);
       assertEquals(4, Data.getMap()[7][3][1]);
       assertEquals(10, Data.getMap().length);
    }
    @Test
    public void testGetMapDimension(){
        assertEquals(10, Data.getMapDimensions()[0]);
        assertEquals(7, Data.getMapDimensions()[1]);
    }
    
}
