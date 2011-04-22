package ch.netgeek.travelmaster.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for the ConnectionData class
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class ConnectionDataTest {
	
	//variable declaration
	private String stationA;
	private String stationB;
	private int duration;
	private ConnectionData test;
	
    /**
     * Sets up definitions, which can be used for the following tests.
     */
	@Before	
	public void setUpConnectionData(){
		stationA = "stationA";
		stationB = "stationB";
		duration = 20;
		test = new ConnectionData(stationA, stationB, duration);
	}
	
    /**
     * Basic test which initializes a ConnectionData object.
     */
	@Test
	public void testConnectionData(){
		String stationC = "stationC";
		String stationD = "stationD";
		int duration2 = 50;
		ConnectionData connectionData = new ConnectionData(stationC, stationD, duration2);
		assertEquals(connectionData.getStationA(), stationC);
		assertEquals(connectionData.getStationB(), stationD);
		assertEquals(connectionData.getDuration(), duration2);
	}
	
    /**
     * Sets a source station
     */	
	@Test
	public void testSetStationA(){
		test.setStationA(stationA);
		assertTrue(test.getStationA() != null);
	}

    /**
     * Gets the source station
     */	
	@Test
	public void testGetStationA(){
		test.setStationA(stationA);
		assertEquals(test.getStationA(), stationA);
	}
	
    /**
     * Sets a destination
     */	
	@Test
	public void testSetStationB(){
		test.setStationB(stationB);
		assertTrue(test.getStationB() != null);
	}

    /**
     * Gets the destination
     */	
	@Test
	public void testGetStationB(){
		test.setStationA(stationB);
		assertEquals(test.getStationB(), stationB);
	}
	
    /**
     * Sets a duration
     */	
	@Test
	public void testSetDuration(){
		test.setDuration(duration);
		assertTrue(test.getDuration() != 0);
	}

    /**
     * Gets the destination
     */	
	@Test
	public void testGetDuration(){
		test.setDuration(duration);
		assertEquals(test.getDuration(), duration);
	}
}
