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
        ArrayList<Connection> connections = new ArrayList<Connection>();
        ArrayList<Calendar> departuresFirstStation = new ArrayList<Calendar>();
        ArrayList<Calendar> departuresLastStation = new ArrayList<Calendar>();
        
        Station station1 = new Station("1. Station");
        Station station2 = new Station("2. Station");
        Station station3 = new Station("3. Station");
        Station station4 = new Station("4. Station");
        Station station5 = new Station("5. Station");
        Station station6 = new Station("6. Station");
        Connection connection1 = new Connection(station6, station5, 10);
        Connection connection2 = new Connection(station1, station2, 4);
        Connection connection3 = new Connection(station5, station4, 12);
        Connection connection4 = new Connection(station3, station4, 7);
        Connection connection5 = new Connection(station2, station3, 9);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        Calendar c4 = Calendar.getInstance();

        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        stations.add(station4);
        stations.add(station5);
        stations.add(station6);
        connections.add(connection1);
        connections.add(connection2);
        connections.add(connection3);
        connections.add(connection4);
        connections.add(connection5);        
        c1.set(0, 0, 0, 15, 03);
        c2.set(0, 0, 0, 15, 33);
        c3.set(0, 0, 0, 15, 18);
        c4.set(0, 0, 0, 15, 48);
        departuresFirstStation.add(c1);
        departuresFirstStation.add(c2);
        departuresLastStation.add(c3);
        departuresLastStation.add(c4);
        
        test.addLine(3, "Bus", stations, departuresFirstStation, 
        		departuresLastStation);
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
