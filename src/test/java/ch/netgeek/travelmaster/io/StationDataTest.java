package ch.netgeek.travelmaster.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for the StationData class.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     1.0
 */
public class StationDataTest {

    // variable declaration
    StationData stationData;
    String name;
    int xPos;
    int yPos;

    /**
     * Sets up an example station data object.
     */
    @Before
    public void setUpStationData() {
        name = "Station Name";
        xPos = 50;
        yPos = 100;
        stationData = new StationData(name, xPos, yPos);
    }

    /**
     * Basic test which instantiates the object.
     */
    @Test
    public void testStationData() {
        name = "Station Name";
        xPos = 50;
        yPos = 100;
        stationData = new StationData(name, xPos, yPos);
        assertEquals(StationData.class, stationData.getClass());
    }

    /**
     * Setting a new name.
     */
    @Test
    public void testSetName() {
        String otherName = "test";
        stationData.setName(otherName);
        assertEquals(otherName, stationData.getName());
    }

    /**
     * Getting the name. 
     */
    @Test
    public void testGetName() {
        assertEquals(name, stationData.getName());
    }

    /**
     * Getting the xPos value.
     */
    @Test
    public void testGetXPos() {
        assertTrue(xPos == stationData.getXPos());
    }

    /**
     * Getting the yPos value.
     */
    @Test
    public void testGetYPos() {
        assertTrue(yPos == stationData.getYPos());
    }
}
