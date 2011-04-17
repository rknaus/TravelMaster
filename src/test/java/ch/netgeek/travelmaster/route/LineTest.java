package ch.netgeek.travelmaster.route;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LineTest {

    private Line line;
    
    @Before
    public void setUpLine() {
        line = new Line(2, "Bus");
    }
    
    @Test
    public void testLine() {
        Line line = new Line(5, "TestLine");
        assertEquals(5, line.getNumber());
        assertEquals("TestLine", line.getType());
    }
    
    @Test
    public void testSetNumber() {
        line.setNumber(3);
        assertEquals(3, line.getNumber());
    }

    @Test
    public void testGetNumber() {
        assertEquals(2, line.getNumber());
    }

    @Test
    public void testSetType() {
        line.setType("Train");
        assertEquals("Train", line.getType());
    }

    @Test
    public void testGetType() {
        assertEquals("Bus", line.getType());
    }

}
