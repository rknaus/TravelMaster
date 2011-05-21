package ch.netgeek.travelmaster.route;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for the Station class
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class StationTest {

    // variables declaration
    private String name;
    private int xPos;
    private int yPos;
    private Station station;
    private String otherName;
    private int otherXPos;
    private int otherYPos;
    private Station otherStation;
    private Connection connection;
    
    /**
     * Sets up a standard station which can be used for the following tests.
     */
    @Before
    public void setUpStation() {
        name = "This Station";
        xPos = 10;
        yPos = 20;
        station = new Station(name, xPos, yPos);
        otherName = "Other Station";
        otherXPos = 30;
        otherYPos = 40;
        otherStation = new Station(otherName, otherXPos, otherYPos);
        connection = new Connection(station, otherStation, 5);
        station.addConnection(connection);
    }
    
    /**
     * Basic test which initializes a station object.
     */
    @Test
    public void testStation() {
        String testName = "test";
        int testXPos = 50;
        int testYPos = 60;
        Station test = new Station(testName, testXPos, testYPos);
        assertEquals(Station.class, test.getClass());
    }

    /**
     * Setting the x-pos of the station.
     */
    @Test
    public void testSetXPos() {
        station.setXPos(1);
        assertTrue(1 == station.getXPos());
    }

    /**
     * Setting the y-pos of the station.
     */
    @Test
    public void testSetYPos() {
        station.setYPos(1);
        assertTrue(1 == station.getYPos());
    }

    /**
     * Getting the name of the station.
     */
    @Test
    public void testGetName() {
        assertEquals("This Station", station.getName());
    }

    /**
     * Getting the x-pos of the station.
     */
    @Test
    public void testGetXPos() {
        assertTrue(xPos == station.getXPos());
    }

    /**
     * Getting the y-pos of the station.
     */
    @Test
    public void testGetYPos() {
        assertTrue(yPos == station.getYPos());
    }

    /**
     * Getting the connections of the station.
     */
    @Test
    public void testGetConncections() {
        assertEquals(connection.getDuration(), 
                station.getConncections().get(0).getDuration());
        assertTrue(1 == station.getConncections().size());
    }

    /**
     * Adding a new connection to the station.
     */
    @Test
    public void testAddConnection() {
        Connection testConnection = new Connection(station, new Station("B", 0, 0), 12);
        station.addConnection(testConnection);
        assertEquals(testConnection.getDuration(), 
                station.getConncections().get(1).getDuration());
        assertTrue(2 == station.getConncections().size());
    }

    /**
     * Testing the equals function
     */
    @Test
    public void testEqualsObject() {
        assertTrue(station.equals(new Station(name, xPos, yPos)));
        assertTrue(station.equals(station));
        assertFalse(station.equals(otherStation));
        assertFalse(station.equals(null));
    }

    /**
     * Testing the compareTo function
     */
    @Test
    public void testCompareTo() {
        assertTrue(0 == station.compareTo(new Station(name, xPos, yPos)));
        assertTrue(0 != station.compareTo(new Station("that station", xPos, yPos)));
        assertTrue(0 != station.compareTo(new Station(name, 9, yPos)));
        assertTrue(0 != station.compareTo(new Station(name, xPos, 19)));
    }

}
