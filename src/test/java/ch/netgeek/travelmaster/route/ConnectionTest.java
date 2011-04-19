package ch.netgeek.travelmaster.route;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for the Connection class
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class ConnectionTest {

    // variables declaration
    private String stationNameA;
    private String stationNameB;
    private int duration;
    private Station stationA;
    private Station stationB;
    private Connection connection;
    private Line line1;
    private Line line2;
    
    /**
     * Sets up a standard connection which can be used for the following tests.
     */
    @Before
    public void setUpConnection() {
        stationNameA = "NameA";
        stationNameB = "NameB";
        duration = 5;
        stationA = new Station(stationNameA);
        stationB = new Station(stationNameB);
        connection = new Connection(stationA, stationB, duration);
        line1 = new Line(1, "Bus");
        line2 = new Line(2, "Bus");
        connection.addLine(line1);
        connection.addLine(line2);
    }
    
    /**
     * Basic test which initializes a connection object.
     */
    @Test
    public void testConnection() {
        Station testA = new Station("TestA");
        Station testB = new Station("TestB");
        Connection connection = new Connection(testA, testB, 12);
        assertEquals(testA, connection.getStationA());
        assertEquals(testB, connection.getStationB());
        assertTrue(12 == connection.getDuration());
    }

    /**
     * Setting a new station as "stationA".
     */
    @Test
    public void testSetStationA() {
        Station newStationA = new Station("newA");
        connection.setStationA(newStationA);
        assertEquals(newStationA, connection.getStationA());
    }

    /**
     * Setting a new station as "stationB".
     */
    @Test
    public void testSetStationB() {
        Station newStationB = new Station("newB");
        connection.setStationB(newStationB);
        assertEquals(newStationB, connection.getStationB());
    }

    /**
     * Getting the station A.
     */
    @Test
    public void testGetStationA() {
        assertEquals(stationA, connection.getStationA());
    }

    /**
     * Getting the station B.
     */
    @Test
    public void testGetStationB() {
        assertEquals(stationB, connection.getStationB());
    }

    /**
     * Getting the neighbor station of a given station. Unknown stations should
     * return null as neighbor station.
     */
    @Test
    public void testGetNeighborStation() {
        assertEquals(stationB, connection.getNeighborStation(stationA));
        assertEquals(stationA, connection.getNeighborStation(stationB));
        assertNull(connection.getNeighborStation(new Station("test")));
    }

    /**
     * Setting a new duration.
     */
    @Test
    public void testSetDuration() {
        connection.setDuration(20);
        assertTrue(20 == connection.getDuration());
    }

    /**
     * Getting the travel duration of the connection.
     */
    @Test
    public void testGetDuration() {
        assertTrue(5 == connection.getDuration());
    }

    /**
     * Getting the lines going via the connection.
     */
    @Test
    public void testGetLines() {
        assertTrue(2 == connection.getLines().size());
    }

    @Test
    public void testAddLine() {
        Line testLine = new Line(3, "Train");
        connection.addLine(testLine);
        assertEquals(testLine.getType(), connection.getLines().get(2).getType());
        assertTrue(testLine.getNumber() == connection.getLines().get(2).getNumber());
    }

}
