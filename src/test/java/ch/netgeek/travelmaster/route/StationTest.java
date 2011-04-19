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
    private String stationName;
    private Station station;
    private String otherStationName;
    private Station otherStation;
    private Connection connection;
    
    /**
     * Sets up a standard station which can be used for the following tests.
     */
    @Before
    public void setUpStation() {
        stationName = "This Station";
        station = new Station(stationName);
        otherStationName = "Other Station";
        otherStation = new Station(otherStationName);
        connection = new Connection(station, otherStation, 5);
        station.addConnection(connection);
    }
    
    /**
     * Basic test which initializes a station object.
     */
    @Test
    public void testStation() {
        String testName = "test";
        Station test = new Station(testName);
        assertEquals("test", test.getName());
    }

    /**
     * Getting the name of the station.
     */
    @Test
    public void testGetName() {
        assertEquals("This Station", station.getName());
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
        Connection testConnection = new Connection(station, new Station("B"), 12);
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
        assertTrue(station.equals(new Station("This Station")));
        assertTrue(station.equals(station));
        assertFalse(station.equals(otherStation));
        assertFalse(station.equals("This Station"));
        assertFalse(station.equals(null));
    }

}
