package ch.netgeek.travelmaster.io;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;

/**
 * This test class shows you all executed tests which have been done within the
 * class DataBuffer.
 * 
 * @author      Ruben Knaus, Dieu P. Van
 * @version     1.0, 19.04.2011
 */
public class XMLStationReaderTest {
	
    private XMLStationReader xmlStationReader;
    
    @Test
    /**
     * reads the station from the file
     */
    public void testReadStation() {
        try {
            ArrayList<StationData> c = xmlStationReader.readStation();

            assertNotNull(c);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    /**
     * reads the connection from the file
     */
    public void testReadConnection() {
        try {
            ArrayList<ConnectionData> c = xmlStationReader.readConnection();

            assertNotNull(c);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
