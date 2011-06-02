package ch.netgeek.travelmaster.route;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for the DepartureItem class
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class DepartureItemTest {
    
    // variables declaration
    private int hour;
    private int minute;
    private int line;
    private DepartureItem departureItem;

    /**
     * Sets up a standard departure item which can be used for the following
     * tests.
     */
    @Before
    public void setUpDepartureItem() {
        hour = 10;
        minute = 30;
        line = 2;
        departureItem = new DepartureItem(hour, minute, line);
    }

    /**
     * Basic test which initializes a departure item object.
     */
    @Test
    public void testDepartureItem() {
        hour = 10;
        minute = 30;
        line = 2;
        DepartureItem departureItem = new DepartureItem(hour, minute, line);
        assertTrue(DepartureItem.class == departureItem.getClass());
    }

    /**
     * Getting the departure hour
     */
    @Test
    public void testGetHour() {
        assertTrue(hour == departureItem.getHour());
    }

    /**
     * Setting the departure hour
     */
    @Test
    public void testSetHour() {
        int hour = 5;
        departureItem.setHour(hour);
        assertTrue(hour == departureItem.getHour());
    }

    /**
     * Getting the departure minute
     */
    @Test
    public void testGetMinute() {
        assertTrue(minute == departureItem.getMinute());
    }

    /**
     * Setting the departure minute
     */
    @Test
    public void testSetMinute() {
        int minute = 5;
        departureItem.setMinute(minute);
        assertTrue(minute == departureItem.getMinute());
    }
    
    /**
     * Getting the line number
     */
    @Test
    public void testGetLine() {
        assertTrue(line == departureItem.getLine());
    }

    /**
     * Setting the line number
     */
    @Test
    public void testSetLine() {
        int line = 5;
        departureItem.setLine(line);
        assertTrue(line == departureItem.getLine());
    }
}
