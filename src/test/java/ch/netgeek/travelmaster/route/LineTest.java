package ch.netgeek.travelmaster.route;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for the Line class
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class LineTest {

    // variables declaration
    private int number;
    private String type;
    private Line line;
    private Calendar departure;
    private Station source;
    private Station destination;
    private String sourceName;
    private String destinationName;
    
    /**
     * Sets up a standard line which can be used for the following tests.
     */
    @Before
    public void setUpLine() {
        number = 1;
        type = "Bus";
        line = new Line(number, type);
        departure = Calendar.getInstance();
        departure.set(0, 0, 0, 9, 50);
        sourceName = "src";
        destinationName = "dst";
        source = new Station(sourceName);
        destination = new Station(destinationName);
        line.addDeparture(source, destination, departure);
    }
    
    /**
     * Basic test which initializes a line object.
     */
    @Test
    public void testLine() {
        int number = 2;
        String type = "Train";
        Line line = new Line(number, type);
        assertTrue(number == line.getNumber());
        assertEquals(type, line.getType());
    }

    /**
     * Setting a new line number.
     */
    @Test
    public void testSetNumber() {
        line.setNumber(12);
        assertTrue(12 == line.getNumber());
    }

    /**
     * Getting the line number.
     */
    @Test
    public void testGetNumber() {
        assertTrue(number == line.getNumber());
    }

    /**
     * Setting a new line type.
     */
    @Test
    public void testSetType() {
        line.setType("Ship");
        assertEquals("Ship", line.getType());
    }

    /**
     * Getting the line type.
     */
    @Test
    public void testGetType() {
        assertEquals(type, line.getType());
    }

    /**
     * Adding a new Departure to the line which then gets added to a TimeTable
     * object.
     */
    @Test
    public void testAddDeparture() {
        Station testSrc = new Station("foo");
        Station testDst = new Station("bar");
        Calendar testDep = Calendar.getInstance();
        line.addDeparture(testSrc, testDst, testDep);
        assertEquals(testDep, 
                line.getTimeTable(testSrc, testDst).getDepartures().get(0));
    }

    /**
     * Getting the TimeTable for a specific source and destination.
     */
    @Test
    public void testGetTimeTable() {
        assertEquals(departure, 
                line.getTimeTable(source, destination).getDepartures().get(0));
    }

}
