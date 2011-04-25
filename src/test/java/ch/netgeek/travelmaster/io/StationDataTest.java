package ch.netgeek.travelmaster.io;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for the StationData class
 * 
 * @author      Ruben Knaus, Dieu P. Van
 * @version     0.1
 */
public class StationDataTest {
	
	//variable declaration
	private String station;
	private StationData test;
	
    /**
     * Sets up definitions, which can be used for the following tests.
     */
	@Before
	public void setUpStationData(){
		station = "station";
		test = new StationData(station);
	}
	
    /**
     * Basic test which initializes a ConnectionData object.
     */
	@Test
	public void testStationData(){
		String station2 = "station2";
		StationData stationData2 = new StationData(station2);
		assertEquals(station2, stationData2.getStation());
	}
	
    /**
     * Sets a station
     */	
	@Test
	public void testSetStation(){
		test.setStation(station);
		assertTrue(test.getStation() != null);
	}
	
    /**
     * Gets a station
     */	
	@Test
	public void testGetStation(){
		test.setStation(station);
		assertEquals(test.getStation(), station);
	}
}
