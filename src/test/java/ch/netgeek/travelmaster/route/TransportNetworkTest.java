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
        ArrayList<Line> lines = new ArrayList<Line>();
        
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
        Line line01 = new Line(3, "Metro");
        Line line02 = new Line(3, "Metro");
        Line line03 = new Line(3, "Metro");
        Line line04 = new Line(3, "Metro");
        Line line05 = new Line(3, "Metro");
        Line line06 = new Line(3, "Metro");
        Line line07 = new Line(3, "Metro");
        Line line08 = new Line(3, "Metro");
        Line line09 = new Line(3, "Metro");
        Line line10 = new Line(3, "Metro");
        Line line11 = new Line(3, "Metro");
        Line line12 = new Line(3, "Metro");
        Line line13 = new Line(3, "Metro");
        Line line14 = new Line(3, "Metro");
        Line line15 = new Line(3, "Metro");
        Line line16 = new Line(3, "Metro");
        Line line17 = new Line(3, "Metro");
        Line line18 = new Line(3, "Metro");
        Line line19 = new Line(3, "Metro");
        Line line20 = new Line(3, "Metro");
        Line line21 = new Line(3, "Metro");
        Line line22 = new Line(3, "Metro");
        Line line23 = new Line(3, "Metro");
        Line line24 = new Line(3, "Metro");
        Calendar lineC01 = Calendar.getInstance();
        Calendar lineC02 = Calendar.getInstance();
        Calendar lineC03 = Calendar.getInstance();
        Calendar lineC04 = Calendar.getInstance();
        Calendar lineC05 = Calendar.getInstance();
        Calendar lineC06 = Calendar.getInstance();
        Calendar lineC07 = Calendar.getInstance();
        Calendar lineC08 = Calendar.getInstance();
        Calendar lineC09 = Calendar.getInstance();
        Calendar lineC10 = Calendar.getInstance();
        Calendar lineC11 = Calendar.getInstance();
        Calendar lineC12 = Calendar.getInstance();
        Calendar lineC13 = Calendar.getInstance();
        Calendar lineC14 = Calendar.getInstance();
        Calendar lineC15 = Calendar.getInstance();
        Calendar lineC16 = Calendar.getInstance();
        Calendar lineC17 = Calendar.getInstance();
        Calendar lineC18 = Calendar.getInstance();
        Calendar lineC19 = Calendar.getInstance();
        Calendar lineC20 = Calendar.getInstance();
        Calendar lineC21 = Calendar.getInstance();
        Calendar lineC22 = Calendar.getInstance();
        Calendar lineC23 = Calendar.getInstance();
        Calendar lineC24 = Calendar.getInstance();
        
        
        line1.addDeparture(station1, station2, departure)

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
        
        
        
        test.addLine(3, "Metro", stations, departuresFirstStation, 
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
    public void testGetConnectionList() {
    	Connection connection = new Connection(stationA, stationB, 10);
    	test.addConnection(stationA, stationB, 10);
        ArrayList<Connection> connectionList = new ArrayList<Connection>();
        connectionList.add(connection);
        assertEquals(test.getStationList(), connectionList);
    }

    @Test
    public void testGetLines() {
        fail("Not yet implemented");
    }

}
