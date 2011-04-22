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
	private Station station;
	private Calendar travelTime;
	private Stopover test;

    /**
     * Sets up definitions, which can be used for the following tests.
     */
	@Before
	public void setUpStoverTest(){
		station = new Station("station");
		travelTime = Calendar.getInstance();
		travelTime.set(0, 0, 0, 9, 50);
		test = new Stopover(station,travelTime);
	}
	
    /**
     * Basic test which initializes a line object.
     */
	@Test
	public void testStopover(){
		Calendar travelTime2 = Calendar.getInstance();
		travelTime2.set(0, 0, 0, 8, 30);
		Station station2 = new Station("station2");
		Stopover stopover = new Stopover(station2, travelTime2);
		assertEquals(stopover.getTravelTime(), travelTime2);
		assertEquals(stopover.getStation(), station2);
	}
	
    /**
     * Sets a new connection
     */	
	@Test
	public void testSetConnection(){
		Station stationA = new Station("stationA");
		Station stationB = new Station("stationB");
		Connection connection = new Connection(stationA, stationB, 4);
		test.setConnection(connection);
		assertTrue(test.getConnection() != null);
	}
	
    /**
     * Sets a new line
     */	
	@Test
	public void testSetLine(){
		Line line = new Line(3, "line");
		test.setLine(line);
		assertTrue(test.getLine() != null);
	}
	
    /**
     * Sets a arrival time
     */	
	@Test
	public void testSetArrivalTime(){
		Calendar arrivalTime = Calendar.getInstance();
		arrivalTime.set(0, 0, 0, 4, 40);
		test.setArrivalTime(arrivalTime);
		assertTrue(test.getArrivalTime()!= null);
	}
	
    /**
     * Sets a departure time
     */	
	@Test
	public void testSetDepartureTime(){
		Calendar departureTime = Calendar.getInstance();
		departureTime.set(0, 0, 0, 6, 35);
		test.setDepartureTime(departureTime);
		assertTrue(test.getDepartureTime()!= null);
	}
	
    /**
     * Gets the station
     */	
	@Test
	public void testGetStation(){
		assertEquals(test.getStation(), station);
	}
	
    /**
     * Gets the connection
     */	
	@Test
	public void testGetConnection(){
		Station stationA = new Station("stationA");
		Station stationB = new Station("stationB");
		Connection connection = new Connection(stationA, stationB, 4);
		test.setConnection(connection);
		assertEquals(connection, test.getConnection());
	}
	
    /**
     * Sets the line
     */	
	@Test
	public void testGetLine(){
		Line line = new Line(3, "line");
		test.setLine(line);
		assertEquals(line, test.getLine());
	}
	
    /**
     * Gets the arrival time
     */	
	@Test
	public void testGetArrivalTime(){
		Calendar arrivalTime = Calendar.getInstance();
		arrivalTime.set(0, 0, 0, 4, 40);
		test.setArrivalTime(arrivalTime);
		assertEquals(arrivalTime, test.getArrivalTime());
	}
	
    /**
     * Gets the departure time
     */	
	@Test
	public void testGetDepartureTime(){
		Calendar departureTime = Calendar.getInstance();
		departureTime.set(0, 0, 0, 6, 35);
		test.setDepartureTime(departureTime);
		assertEquals(departureTime, test.getDepartureTime());
	}
	
    /**
     * Gets the travel time
     */	
	@Test
	public void testGetTravelTime(){
		assertEquals(travelTime, test.getTravelTime());
	}
}
