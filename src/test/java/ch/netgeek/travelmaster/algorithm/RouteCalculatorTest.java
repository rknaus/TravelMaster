package ch.netgeek.travelmaster.algorithm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import ch.netgeek.travelmaster.route.Connection;
import ch.netgeek.travelmaster.route.Line;
import ch.netgeek.travelmaster.route.Station;
import ch.netgeek.travelmaster.route.TimeTable;
import ch.netgeek.travelmaster.route.TransportNetwork;

/**
 * JUnit test cases for the TransportNetwork class
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class RouteCalculatorTest {

    private RouteCalculator routeCalculator;
    private TransportNetwork transportNetwork;
    private Station sNorth;
    private Station sSouth;
    private Station sWest;
    private Station sEast;
    private Station sCenter;
    private Connection cNorthCenter;
    private Connection cCenterSouth;
    private Connection cWestCenter;
    private Connection cCenterEast;
    private Line line1;
    private Line line2;
    
    /**
     * Sets up a standard Transport Network for the route calculator
     */
    @Before
    public void setUpRouteCalculator() {
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
        cNorthCenter = transportNetwork.getConnection(sNorth, sCenter);
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
        line1 = transportNetwork.getLines().get(0);
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
        line2 = transportNetwork.getLines().get(1);
        
        // Initializing the route calculator
        routeCalculator = new RouteCalculator(transportNetwork);
    }

    /**
     * Basic test which initializes a route calculator object.
     */
    @Test
    public void testRouteCalculator() {
        routeCalculator = new RouteCalculator(transportNetwork);
        assertEquals(RouteCalculator.class, routeCalculator.getClass());
    }

    @Test
    public void testCalculateRoute() {
        Calendar departure = Calendar.getInstance();
        ArrayList<Stopover> stopoverList;
        Stopover stopover;
        
        // Test time table from North to West at 07:10
        departure.set(0, 0, 0, 07, 10);
        stopoverList = routeCalculator.calculateRoute(sNorth, sWest, departure);
        
        // First stopover
        stopover = stopoverList.get(0);
        assertEquals(sNorth, stopover.getSource());
        assertTrue(8 == stopover.getDepartureTime().get(Calendar.HOUR_OF_DAY));
        assertTrue(40 == stopover.getDepartureTime().get(Calendar.MINUTE));
        assertEquals(sCenter, stopover.getDestinatio());
        assertTrue(8 == stopover.getArrivalTime().get(Calendar.HOUR_OF_DAY));
        assertTrue(45 == stopover.getArrivalTime().get(Calendar.MINUTE));
        assertEquals("Bus", stopover.getLine().getType());
        assertTrue(1 == stopover.getLine().getNumber());
        assertTrue(5 == stopover.getConnection().getDuration());
        assertTrue(95 == stopover.getTravelDuration());
        
        // Second stopover
        stopover = stopoverList.get(1);
        assertEquals(sCenter, stopover.getSource());
        assertTrue(11 == stopover.getDepartureTime().get(Calendar.HOUR_OF_DAY));
        assertTrue(20 == stopover.getDepartureTime().get(Calendar.MINUTE));
        assertEquals(sWest, stopover.getDestinatio());
        assertTrue(11 == stopover.getArrivalTime().get(Calendar.HOUR_OF_DAY));
        assertTrue(35 == stopover.getArrivalTime().get(Calendar.MINUTE));
        assertEquals("Train", stopover.getLine().getType());
        assertTrue(2 == stopover.getLine().getNumber());
        assertTrue(15 == stopover.getConnection().getDuration());
        assertTrue(325 == stopover.getTravelDuration());
        
        // Test time table from South to East at 09:40
        departure.set(0, 0, 0, 9, 40);
        stopoverList = routeCalculator.calculateRoute(sSouth, sEast, departure);
        
        // First stopover
        stopover = stopoverList.get(0);
        assertEquals(sSouth, stopover.getSource());
        System.out.println(stopover.getDepartureTime().get(Calendar.HOUR_OF_DAY));
        assertTrue(9 == stopover.getDepartureTime().get(Calendar.HOUR_OF_DAY));
        assertTrue(40 == stopover.getDepartureTime().get(Calendar.MINUTE));
        assertEquals(sCenter, stopover.getDestinatio());
        assertTrue(9 == stopover.getArrivalTime().get(Calendar.HOUR_OF_DAY));
        assertTrue(50 == stopover.getArrivalTime().get(Calendar.MINUTE));
        assertEquals("Bus", stopover.getLine().getType());
        assertTrue(1 == stopover.getLine().getNumber());
        assertTrue(10 == stopover.getConnection().getDuration());
        assertTrue(10 == stopover.getTravelDuration());
        
        // Second stopover
        stopover = stopoverList.get(1);
        assertEquals(sCenter, stopover.getSource());
        assertTrue(10 == stopover.getDepartureTime().get(Calendar.HOUR_OF_DAY));
        assertTrue(15 == stopover.getDepartureTime().get(Calendar.MINUTE));
        assertEquals(sEast, stopover.getDestinatio());
        assertTrue(10 == stopover.getArrivalTime().get(Calendar.HOUR_OF_DAY));
        assertTrue(35 == stopover.getArrivalTime().get(Calendar.MINUTE));
        assertEquals("Train", stopover.getLine().getType());
        assertTrue(2 == stopover.getLine().getNumber());
        assertTrue(20 == stopover.getConnection().getDuration());
        assertTrue(115 == stopover.getTravelDuration());
        
        // Test time table from Center to Center
        stopoverList = routeCalculator.calculateRoute(sCenter, sCenter, departure);
        assertNull(stopoverList);
        
        // Test time table from Center to North when there is no connection
        Connection connection = transportNetwork.getConnection(sCenter, sNorth);
        connection.setStationA(sWest);
        connection.setStationB(sEast);
        stopoverList = routeCalculator.calculateRoute(sCenter, sNorth, departure);
        assertNull(stopoverList);
        
//        for (Stopover stopoverx : stopoverList) {
//            System.out.println("------------------------------");
//            System.out.println("Departure Station: " + stopoverx.getSource().getName());
//            System.out.println("Departure time: " + stopoverx.getDepartureTime().getTime());
//            System.out.println("Arrival Station: " + stopoverx.getDestinatio().getName());
//            System.out.println("Arrival time: " + stopoverx.getArrivalTime().getTime());
//            System.out.println("Line: " + stopoverx.getLine().getNumber() + ", Line type: " + stopoverx.getLine().getType());
//            System.out.println("Duration: " + stopoverx.getConnection().getDuration());
//            System.out.println("Travel Duration: " + stopoverx.getTravelDuration());
//            System.out.println("------------------------------");
//        }
    }

}
