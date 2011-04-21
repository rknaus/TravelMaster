package ch.netgeek.travelmaster.route;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for the TimeTable class
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class TimeTableTest {
    
    // variables declaration
    private TimeTable timeTable;
    
    /**
     * Sets up a standard time table which can be used for the following tests.
     */
    @Before
    public void setUpTimeTable() {
        timeTable = new TimeTable();
    }
    
    public void addDepartures() {
        Calendar dep1 = Calendar.getInstance();
        Calendar dep2 = Calendar.getInstance();
        Calendar dep3 = Calendar.getInstance();
        Calendar dep4 = Calendar.getInstance();
        dep1.set(0, 0, 0, 1, 10);
        dep2.set(0, 0, 0, 20, 45);
        dep3.set(0, 0, 0, 12, 0);
        dep4.set(0, 0, 0, 0, 0);
        timeTable.addDeparture(dep1);
        timeTable.addDeparture(dep2);
        timeTable.addDeparture(dep3);
        timeTable.addDeparture(dep4);
    }

    /**
     * Basic test which initializes a time table object.
     */
    @Test
    public void testTimeTable() {
        TimeTable testTimeTable = new TimeTable();
        assertEquals(TimeTable.class, testTimeTable.getClass());
    }

    /**
     * Adding departures to the time table.
     */
    @Test
    public void testAddDeparture() {
        addDepartures();
        assertTrue(4 == timeTable.getDepartures().size());
    }

    /**
     * Testing if the next departure of a given time gets returned.
     */
    @Test
    public void testGetNextDeparture() {
        addDepartures();
        Calendar wishedDep1 = Calendar.getInstance();
        Calendar wishedDep2 = Calendar.getInstance();
        Calendar wishedDep3 = Calendar.getInstance();
        wishedDep1.set(0, 0, 0, 15, 30);
        wishedDep2.set(0, 0, 0, 12, 0);
        wishedDep3.set(0, 0, 0, 23, 59);
        Calendar dep1 = timeTable.getNextDeparture(wishedDep1);
        Calendar dep2 = timeTable.getNextDeparture(wishedDep2);
        Calendar dep3 = timeTable.getNextDeparture(wishedDep3);
        assertTrue(20 == dep1.get(Calendar.HOUR_OF_DAY));
        assertTrue(45 == dep1.get(Calendar.MINUTE));
        assertTrue(12 == dep2.get(Calendar.HOUR_OF_DAY));
        assertTrue(0 == dep2.get(Calendar.MINUTE));
        assertTrue(0 == dep3.get(Calendar.HOUR_OF_DAY));
        assertTrue(0 == dep3.get(Calendar.MINUTE));
    }

    @Test
    public void testGetDepartures() {
        addDepartures();
        ArrayList<Calendar> departures = timeTable.getDepartures();
        Calendar dep1 = departures.get(0);
        Calendar dep2 = departures.get(1);
        Calendar dep3 = departures.get(2);
        Calendar dep4 = departures.get(3);
        assertTrue(0 == dep1.get(Calendar.HOUR_OF_DAY));
        assertTrue(0 == dep1.get(Calendar.MINUTE));
        assertTrue(1 == dep2.get(Calendar.HOUR_OF_DAY));
        assertTrue(10 == dep2.get(Calendar.MINUTE));
        assertTrue(12 == dep3.get(Calendar.HOUR_OF_DAY));
        assertTrue(0 == dep3.get(Calendar.MINUTE));
        assertTrue(20 == dep4.get(Calendar.HOUR_OF_DAY));
        assertTrue(45 == dep4.get(Calendar.MINUTE));
    }

}
