package ch.netgeek.travelmaster.route;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TransportNetworkTest {

	private TransportNetwork test;
    private Station stationA = new Station("stationA");
    private Station stationB = new Station("stationB");
    private Station station = new Station("station");
    
    @Before
    public void setUpTransportNetwork(){
    	test = new TransportNetwork();
    }
    
    @Test
    public void testTransportNetwork() {
        TransportNetwork test = new TransportNetwork();
        test.getStation("test");
        test.getLines();
        test.addConnection(stationA, stationB, 4);
        assertTrue(test.getStation("test") == null);
        assertTrue(test.getLines().isEmpty() == true);
    }

    @Test
    public void testAddStation() {
        test.addStation("Hollywood");
        assertTrue(test.getStation("Hollywood") != null);     
    }

    @Test
    public void testAddConnection() {
        test.addConnection(stationA, stationB, 4);
    }

    @Test
    public void testAddLine() {
        TransportNetwork test = new TransportNetwork();
        ArrayList<Station> stations = new ArrayList<Station>();
        ArrayList<Calendar> departuresFirstStation = new ArrayList<Calendar>();
        ArrayList<Calendar> departuresLastStation = new ArrayList<Calendar>();
        test.addLine(3, "Bus", stations, departuresFirstStation, departuresLastStation);
        assertTrue(test.getLines() != null);
    }

    @Test
    public void testGetStation() {
        test.getStation("station");
        HashMap<String, Station> stations = new HashMap<String, Station>();
        stations.put("station", station);
        assertEquals(test.getStation("station"), stations.get(station));
    }

    @Test
    public void testGetStationList() {
    	test.addStation("station");
        ArrayList<Station> stationList = new ArrayList<Station>();
        stationList.add(station);
        assertEquals(test.getStationList(), stationList);
    }

    @Test
    public void testGetNeighborStationList() {
        Connection connection = new Connection(stationA, stationB, 4);
        stationA.addConnection(connection);
        stationB.addConnection(connection);
    	ArrayList<Station> neighborStationList = new ArrayList<Station>();
    	neighborStationList.add(stationB);
        assertEquals(neighborStationList, test.getNeighborStationList(stationA));
    }

    @Test
    public void testGetLines() {
        fail("Not yet implemented");
    }

}
