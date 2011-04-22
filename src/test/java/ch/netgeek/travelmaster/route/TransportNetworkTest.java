package ch.netgeek.travelmaster.route;

import static org.junit.Assert.*;

import org.junit.Test;

public class TransportNetworkTest {

    private TransportNetwork test;
    private Station stationA = new Station("stationA");
    private Station stationB = new Station("stationB");
    
    @Test
    public void testTransportNetwork() {
        fail("Not yet implemented");
    }

    @Test
    public void testAddStation() {
        test.addStation("Hollywood");
        assertTrue(test.getStation("Hollywood")!= null);
    }

    @Test
    public void testAddConnection() {
        test.addConnection(stationA, stationB, 4);
        Connection connection = new Connection(stationA, stationB, 4);
    }

    @Test
    public void testAddLine() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetStation() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetStationList() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetNeighborStationList() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetLines() {
        fail("Not yet implemented");
    }

}
