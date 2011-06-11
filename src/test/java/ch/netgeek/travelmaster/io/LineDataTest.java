package ch.netgeek.travelmaster.io;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for the LineData class.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     1.0
 */
public class LineDataTest {

    // variable declaration
    private LineData lineData;
    private int number;
    private String type;
    private ArrayList<String> stations;
    private ArrayList<String> departuresFirstStation;
    private ArrayList<String> departuresLastStation;
    private String station1;
    private String station2;
    private String station3;
    private String departure1FirstStation;
    private String departure2FirstStation;
    private String departure3FirstStation;
    private String departure4FirstStation;
    private String departure1LastStation;
    private String departure2LastStation;
    private String departure3LastStation;
    private String departure4LastStation;

    /**
     * Sets up a standard line data object which can be used for the following
     * tests.
     */
    @Before
    public void setUpLineData() {
        number = 12;
        type = "Train";
        stations = new ArrayList<String>();
        station1 = "Bern";
        station2 = "Zurich";
        station3 = "St. Gallen";
        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        departuresFirstStation = new ArrayList<String>();
        departure1FirstStation = "06:00";
        departure2FirstStation = "08:12";
        departure3FirstStation = "22:45";
        departure4FirstStation = "23:05";
        departuresFirstStation.add(departure1FirstStation);
        departuresFirstStation.add(departure2FirstStation);
        departuresFirstStation.add(departure3FirstStation);
        departuresFirstStation.add(departure4FirstStation);
        departuresLastStation = new ArrayList<String>();
        departure1FirstStation = "07:07";
        departure2FirstStation = "08:08";
        departure3FirstStation = "22:22";
        departure4FirstStation = "23:23";
        departuresLastStation.add(departure1LastStation);
        departuresLastStation.add(departure2LastStation);
        departuresLastStation.add(departure3LastStation);
        departuresLastStation.add(departure4LastStation);
        lineData = new LineData(number, type, stations, departuresFirstStation, 
                departuresLastStation);
    }

    /**
     * Basic test which initializes a line data object.
     */
    @Test
    public void testLineData() {
        int number = 1;
        String type = "Bus";
        ArrayList<String> stations = new ArrayList<String>();
        stations.add("Luzern");
        stations.add("Basel");
        ArrayList<String> departuresFirstStation = new ArrayList<String>();
        departuresFirstStation.add("12:00");
        departuresFirstStation.add("14:00");
        ArrayList<String> departuresLastStation = new ArrayList<String>();
        departuresLastStation.add("16:00");
        departuresLastStation.add("18:00");
        LineData lineData = new LineData(number, type, stations, 
                departuresFirstStation, departuresLastStation);
        assertTrue(1 == lineData.getNumber());
        assertEquals("Bus", lineData.getType());
        assertEquals("Luzern", lineData.getStations().get(0));
        assertEquals("Basel", lineData.getStations().get(1));
        assertEquals("12:00", lineData.getDeparturesFirstStation().get(0));
        assertEquals("14:00", lineData.getDeparturesFirstStation().get(1));
        assertEquals("16:00", lineData.getDeparturesLastStation().get(0));
        assertEquals("18:00", lineData.getDeparturesLastStation().get(1));
    }

    /**
     * Getting the line number.
     */
    @Test
    public void testGetNumber() {
        assertTrue(number == lineData.getNumber());
    }

    /**
     * Setting the line number.
     */
    @Test
    public void testSetNumber() {
        lineData.setNumber(24);
        assertTrue(24 == lineData.getNumber());
    }

    /**
     * Getting the line type.
     */
    @Test
    public void testGetType() {
        assertEquals(type, lineData.getType());
    }

    /**
     * Setting the line type.
     */
    @Test
    public void testSetType() {
        lineData.setType("Test");
        assertEquals("Test", lineData.getType());
    }

    /**
     * Getting the stations list.
     */
    @Test
    public void testGetStations() {
        assertEquals(stations, lineData.getStations());
    }

    /**
     * Setting the stations list.
     */
    @Test
    public void testSetStations() {
        ArrayList<String> stations = new ArrayList<String>();
        stations.add("Zuoz");
        stations.add("Zernez");
        lineData.setStations(stations);
        assertEquals(stations, lineData.getStations());
    }

    /**
     * Getting the departures list of the first station.
     */
    @Test
    public void testGetDeparturesFirstStation() {
        assertEquals(departuresFirstStation, lineData.getDeparturesFirstStation());
    }

    /**
     * Setting the departures list of the first station.
     */
    @Test
    public void testSetDeparturesFirstStation() {
        ArrayList<String> departuresFirstStation = new ArrayList<String>();
        departuresFirstStation.add("01:01");
        departuresFirstStation.add("23:59");
        lineData.setDeparturesFirstStation(departuresFirstStation);
        assertEquals(departuresFirstStation, lineData.getDeparturesFirstStation());
    }

    /**
     * Getting the departures list of the last station.
     */
    @Test
    public void testGetDeparturesLastStation() {
        assertEquals(departuresLastStation, lineData.getDeparturesLastStation());
    }

    /**
     * Setting the departures list of the last station.
     */
    @Test
    public void testSetDeparturesLastStation() {
        ArrayList<String> departuresLastStation = new ArrayList<String>();
        departuresLastStation.add("01:01");
        departuresLastStation.add("23:59");
        lineData.setDeparturesLastStation(departuresLastStation);
        assertEquals(departuresLastStation, lineData.getDeparturesLastStation());
    }
}