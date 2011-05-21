package ch.netgeek.travelmaster.algorithm;

import ch.netgeek.travelmaster.route.Connection;
import ch.netgeek.travelmaster.route.Line;
import ch.netgeek.travelmaster.route.Station;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for the Stopover class
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class StopoverTest {
	
	//variable declaration
	private Station source;
	private Station destination;
	private Connection connection;
	private Line line;
	private Calendar departureTime;
	private Calendar arrivalTime;
	private int travelDuration;
	private Stopover stopover;

    /**
     * Sets up definitions, which can be used for the following tests.
     */
	@Before
	public void setUpStoverTest() {
	    source = new Station("Source", 0, 0);
	    destination = new Station("Destination", 0, 0);
		connection = new Connection(source, destination, 10);
		line = new Line(2, "Bus");
		departureTime = Calendar.getInstance();
		departureTime.set(0, 0, 0, 9, 50);
		arrivalTime = Calendar.getInstance();
		arrivalTime.set(0, 0, 0, 10, 0);
		travelDuration = 15;
		stopover = new Stopover(source, destination, connection, line, 
		        departureTime, arrivalTime, travelDuration);
	}
	
    /**
     * Basic test which initializes a line object.
     */
	@Test
	public void testStopover() {
	    source = new Station("Source", 0, 0);
        destination = new Station("Destination", 0, 0);
        connection = new Connection(source, destination, 10);
        line = new Line(2, "Bus");
        departureTime = Calendar.getInstance();
        departureTime.set(0, 0, 0, 9, 50);
        arrivalTime = Calendar.getInstance();
        arrivalTime.set(0, 0, 0, 10, 0);
        travelDuration = 15;
        stopover = new Stopover(source, destination, connection, line, 
                departureTime, arrivalTime, travelDuration);
        assertEquals(Stopover.class, stopover.getClass());
	}
	
	/**
	 * Sets a new source station
	 */
	@Test
	public void testSetSource() {
	    Station source = new Station("Test", 0, 0);
	    stopover.setSource(source);
	    assertEquals(source, stopover.getSource());
	}
	
	/**
     * Sets a new destination station
     */
    @Test
    public void testSetDestination() {
        Station destination = new Station("Test", 0, 0);
        stopover.setDestination(destination);
        assertEquals(destination, stopover.getDestinatio());
    }
	
    /**
     * Sets a new connection
     */	
	@Test
	public void testSetConnection() {
		Station stationA = new Station("stationA", 0, 0);
		Station stationB = new Station("stationB", 0, 0);
		Connection connection = new Connection(stationA, stationB, 4);
		stopover.setConnection(connection);
		assertTrue(stopover.getConnection().getDuration() == 4);
	}
	
    /**
     * Sets a new line
     */	
	@Test
	public void testSetLine() {
		Line line = new Line(3, "line");
		stopover.setLine(line);
		assertTrue(stopover.getLine().getNumber() == 3);
		assertEquals("line", stopover.getLine().getType());
	}
    
    /**
     * Sets a departure time
     */ 
    @Test
    public void testSetDepartureTime() {
        Calendar departureTime = Calendar.getInstance();
        departureTime.set(0, 0, 0, 6, 35);
        stopover.setDepartureTime(departureTime);
        assertEquals(departureTime, stopover.getDepartureTime());
    }
	
    /**
     * Sets a arrival time
     */	
	@Test
	public void testSetArrivalTime() {
		Calendar arrivalTime = Calendar.getInstance();
		arrivalTime.set(0, 0, 0, 4, 40);
		stopover.setArrivalTime(arrivalTime);
		assertEquals(arrivalTime, stopover.getArrivalTime());
	}
	
	/**
	 * Sets the total travel duration
	 */
	@Test
	public void testSetTravelDuration() {
	    int duration = 80;
	    stopover.setTravelDuration(duration);
	    assertTrue(80 == stopover.getTravelDuration());
	}
	
    /**
     * Returns the source station
     */	
	@Test
	public void testGetSource() {
		assertEquals(source, stopover.getSource());
	}
	
	/**
     * Returns the destination station
     */ 
    @Test
    public void testGetDestination() {
        assertEquals(destination, stopover.getDestinatio());
    }
	
    /**
     * Gets the connection
     */	
	@Test
	public void testGetConnection() {
		assertEquals(source, stopover.getConnection().getStationA());
		assertEquals(destination, stopover.getConnection().getStationB());
		assertTrue(10 == stopover.getConnection().getDuration());
	}
	
	/**
	 * Returns the line
	 */
	@Test
	public void testGetLine() {
	    assertEquals("Bus", stopover.getLine().getType());
	    assertTrue(2 == stopover.getLine().getNumber());
	}
    
    /**
     * Gets the departure time
     */ 
    @Test
    public void testGetDepartureTime() {
        assertEquals(departureTime, stopover.getDepartureTime());
    }
	
    /**
     * Gets the arrival time
     */	
	@Test
	public void testGetArrivalTime() {
		assertEquals(arrivalTime, stopover.getArrivalTime());
	}
	
    /**
     * Gets the travel duration
     */	
	@Test
	public void testGetTravelDuration() {
		assertTrue(travelDuration == stopover.getTravelDuration());
	}
}
