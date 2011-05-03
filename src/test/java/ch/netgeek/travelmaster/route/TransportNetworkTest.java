package ch.netgeek.travelmaster.route;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TransportNetworkTest {

	private TransportNetwork transportNetwork;
    private Station sNorth;
    private Station sSouth;
    private Station sWest;
    private Station sEast;
    private Station sCenter;
    private Connection cNorthSouth;
    private Connection cCenterSouth;
    private Connection cWestCenter;
    private Connection cCenterEast;
    private Line line1;
    private Line line2;
    
    /**
     * Sets up a standard transport network object which can be used for the 
     * following tests.
     */
    @Before
    public void setUpTransportNetwork(){
        /*
         * The example route:
         *                      Line1 08:40, 16:40
         *                        |
         *                      North
         *                        |
         *                        5
         * 10:00                  |                   11:00
         * Line2--West---15-----Center-----20---East--Line2
         *                        |
         *                       10
         *                        |
         *                      South
         *                        |
         *                      Line1 09:40, 17:40
         */
        transportNetwork = new TransportNetwork();
        
        
        
        line1 = new Line(1, "Bus");
        line2 = new Line(2, "Train");
        
        // Adding Stations to transport network
        transportNetwork.addStation("North");
        transportNetwork.addStation("South");
        transportNetwork.addStation("West");
        transportNetwork.addStation("East");
        transportNetwork.addStation("Center");
        sNorth = transportNetwork.getStation("North");
        sSouth = transportNetwork.getStation("South");
        sWest = transportNetwork.getStation("West");
        sEast = transportNetwork.getStation("East");
        sCenter = transportNetwork.getStation("Center");
        
        // Adding Connections to transport network
        transportNetwork.addConnection(sNorth, sCenter, 5);
        transportNetwork.addConnection(sCenter, sSouth, 10);
        transportNetwork.addConnection(sWest, sCenter, 15);
        transportNetwork.addConnection(sCenter, sEast, 20);
        cNorthSouth = transportNetwork.getConnection(sNorth, sCenter);
        cCenterSouth = transportNetwork.getConnection(sCenter, sSouth);
        cWestCenter = transportNetwork.getConnection(sWest, sCenter);
        cCenterEast = transportNetwork.getConnection(sCenter, sEast);
        
        // Adding Lines to transport network
        int line1Number = 1;
        String line1Type = "Bus";
        ArrayList<Station> line1Stations = new ArrayList<Station>();
        line1Stations.add(sNorth);
        line1Stations.add(sCenter);
        line1Stations.add(sSouth);
        ArrayList<Calendar> line1DeparturesFirstStation = new ArrayList<Calendar>();
        Calendar line1DepFirst1 = Calendar.getInstance();
        line1DepFirst1.set(0, 0, 0, 8, 40);
        line1DeparturesFirstStation.add(line1DepFirst1);
        Calendar line1DepFirst2 = Calendar.getInstance();
        line1DepFirst2.set(0, 0, 0, 16, 40);
        line1DeparturesFirstStation.add(line1DepFirst2);
        ArrayList<Calendar> line1DeparturesLastStation = new ArrayList<Calendar>();
        Calendar line1DepLast1 = Calendar.getInstance();
        line1DepLast1.set(0, 0, 0, 9, 40);
        line1DeparturesLastStation.add(line1DepLast1);
        Calendar line1DepLast2 = Calendar.getInstance();
        line1DepLast2.set(0, 0, 0, 17, 40);
        line1DeparturesLastStation.add(line1DepLast2);
        transportNetwork.addLine(line1Number, line1Type, line1Stations, 
                line1DeparturesFirstStation, line1DeparturesLastStation);
    	int line2Number = 2;
    	String line2Type = "Train";
    	ArrayList<Station> line2Stations = new ArrayList<Station>();
    	line2Stations.add(sWest);
    	line2Stations.add(sCenter);
    	line2Stations.add(sEast);
    	ArrayList<Calendar> line2DeparturesFirstStation = new ArrayList<Calendar>();
        Calendar line2DepFirst1 = Calendar.getInstance();
        line2DepFirst1.set(0, 0, 0, 10, 0);
        line2DeparturesFirstStation.add(line2DepFirst1);
        ArrayList<Calendar> line2DeparturesLastStation = new ArrayList<Calendar>();
        Calendar line2DepLast1 = Calendar.getInstance();
        line2DepLast1.set(0, 0, 0, 11, 0);
        line2DeparturesLastStation.add(line2DepLast1);
    	transportNetwork.addLine(line2Number, line2Type, line2Stations, 
    	        line2DeparturesFirstStation, line2DeparturesLastStation);
    }
    
    /**
     * Basic test which initializes a transport network object.
     */
    @Test
    public void testTransportNetwork() {
        TransportNetwork test = new TransportNetwork();
        assertTrue(test.getStation("test") == null);
        assertTrue(test.getLines().isEmpty() == true);
    }

    /**
     * Adds a station to the transport network.
     */
    @Test
    public void testAddStation() {
        String station = "Hollywood";
        transportNetwork.addStation(station);
        assertEquals(station, transportNetwork.getStation(station).getName());    
    }

    /**
     * Adds a connection to the transport network.
     */
    @Test
    public void testAddConnection() {
        Station s1 = transportNetwork.getStation("North");
        Station s2 = transportNetwork.getStation("West");
        transportNetwork.addConnection(s1, s2, 4);
        Connection connection = transportNetwork.getConnection(s1, s2);
        assertTrue(4 == connection.getDuration());
        assertEquals(s1, connection.getStationA());
        assertEquals(s2, connection.getStationB());
    }

    /**
     * Adding a line to the transport network
     */
    @Test
    public void testAddLine() {
        fail("Not yet implemented");
    }

    /**
     * Getting a station from the transport network
     */
    @Test
    public void testGetStation() {
        String wrongName = "Test";
        String north = "North";
        Station wrongStation = transportNetwork.getStation(wrongName);
        Station northStation = transportNetwork.getStation(north);
        assertNull(wrongStation);
        assertEquals(north, northStation.getName());
    }

    /**
     * Getting a list of all stations
     */
    @Test
    public void testGetStationList() {
    	ArrayList<Station> stations = transportNetwork.getStationList();
    	assertTrue(5 == stations.size());
    	for(Station station : stations) {
    	    Station expected;
    	    if (station.equals(new Station("North"))) {
    	        expected = new Station("North");
    	        assertEquals(expected, station);
    	    } else if (station.equals(new Station("South"))) {
    	        expected = new Station("South");
    	        assertEquals(expected, station);
    	    } else if (station.equals(new Station("West"))) {
    	        expected = new Station("West");
    	        assertEquals(expected, station);
            } else if (station.equals(new Station("East"))) {
                expected = new Station("East");
                assertEquals(expected, station);
            } else if (station.equals(new Station("Center"))) {
                expected = new Station("Center");
                assertEquals(expected, station);
            } else {
                fail("Station List not correct!");
            }
    	}
    }

    /**
     * Getting the neighbor list of a station
     */
    @Test
    public void testGetNeighborStationList() {
        ArrayList<Station> northNeighbors = transportNetwork.getNeighborStationList(sNorth);
        
        System.out.println("Neighborstation List Size: " + northNeighbors.size());
        
        assertTrue(1 == northNeighbors.size());
        
    }
    
    @Test
    public void testGetConnection() {
        Connection c1 = transportNetwork.getConnection(sNorth, sCenter);
        Connection c2 = transportNetwork.getConnection(sCenter, sSouth);
        Connection c3 = transportNetwork.getConnection(sWest, sCenter);
        Connection c4 = transportNetwork.getConnection(sCenter, sEast);
        assertTrue(5 == c1.getDuration());
        assertTrue(10 == c2.getDuration());
        assertTrue(15 == c3.getDuration());
        assertTrue(20 == c4.getDuration());
    }
    
    /**
     * Getting the connection list of the transport network.
     */
    @Test
    public void testGetConnectionList() {
        ArrayList<Connection> connections = transportNetwork.getConnectionList();
        assertTrue(4 == connections.size());
        for (Connection connection : connections) {
            if (connection.getDuration() == 5) {
                assertEquals(sNorth, connection.getStationA());
                assertEquals(sCenter, connection.getStationB());
            } else if (connection.getDuration() == 10) {
                assertEquals(sCenter, connection.getStationA());
                assertEquals(sSouth, connection.getStationB());
            } else if (connection.getDuration() == 15) {
                assertEquals(sWest, connection.getStationA());
                assertEquals(sCenter, connection.getStationB());
            } else if (connection.getDuration() == 20) {
                assertEquals(sCenter, connection.getStationA());
                assertEquals(sEast, connection.getStationB());
            } else {
                fail("Connection list is not correct");
            }
        }
    }

    /**
     * Getting the lines of the transport network.
     */
    @Test
    public void testGetLines() {
        ArrayList<Line> lines = transportNetwork.getLines();
        assertTrue(2 == lines.size());
        for (Line line : lines) {
            if (line.getNumber() == 1) {
                assertEquals(line1.getType(), line.getType());
                TimeTable tNorthCenter = line.getTimeTable(sNorth, sCenter);
                ArrayList<Calendar> dNorthCenter = tNorthCenter.getDepartures();
                for (Calendar departure : dNorthCenter) {
                    int hoursOfDay = departure.get(Calendar.HOUR_OF_DAY);
                    int minutes = departure.get(Calendar.MINUTE);
                    if (hoursOfDay == 8) {
                        assertTrue(40 == minutes);
                    } else if (hoursOfDay == 16) {
                        assertTrue(40 == minutes);
                    } else {
                        fail("Departures are wrong");
                    }
                }
                TimeTable tCenterSouth = line.getTimeTable(sCenter, sSouth);
                ArrayList<Calendar> dCenterSouth = tCenterSouth.getDepartures();
                for (Calendar departure : dCenterSouth) {
                    int hoursOfDay = departure.get(Calendar.HOUR_OF_DAY);
                    int minutes = departure.get(Calendar.MINUTE);
                    if (hoursOfDay == 8) {
                        assertTrue(45 == minutes);
                    } else if (hoursOfDay == 16) {
                        assertTrue(45 == minutes);
                    } else {
                        fail("Departures are wrong");
                    }
                }
                
                TimeTable tSouthCenter = line.getTimeTable(sSouth, sCenter);
                ArrayList<Calendar> dSouthCenter = tSouthCenter.getDepartures();
                for (Calendar departure : dSouthCenter) {
                    int hoursOfDay = departure.get(Calendar.HOUR_OF_DAY);
                    int minutes = departure.get(Calendar.MINUTE);
                    if (hoursOfDay == 9) {
                        assertTrue(40 == minutes);
                    } else if (hoursOfDay == 17) {
                        assertTrue(40 == minutes);
                    } else {
                        fail("Departures are wrong");
                    }
                }
                TimeTable tCenterNorth = line.getTimeTable(sCenter, sNorth);
                ArrayList<Calendar> dCenterNorth = tCenterNorth.getDepartures();
                for (Calendar departure : dCenterNorth) {
                    int hoursOfDay = departure.get(Calendar.HOUR_OF_DAY);
                    int minutes = departure.get(Calendar.MINUTE);
                    if (hoursOfDay == 9) {
                        assertTrue(50 == minutes);
                    } else if (hoursOfDay == 17) {
                        assertTrue(50 == minutes);
                    } else {
                        fail("Departures are wrong");
                    }
                }
            } else if (line.getNumber() == 2) {
                assertEquals(line2.getType(), line.getType());
                
                
                // TODO CONTINUE HERE!
                
                
            } else {
                fail("Line list is not correct");
            }
        }
    }
}
