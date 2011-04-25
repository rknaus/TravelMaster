package ch.netgeek.travelmaster.io;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test cases for the XMLReader class.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class XMLReaderTest {

    // variable declaration
    private XMLReader xmlReader;
    private XMLReader faultyXMLReader;
    private String stationsFileName;
    private String connectionsFileName;
    private String linesFileName;
    private File stationsFile;
    private File connectionsFile;
    private File linesFile;
    
    /**
     * Sets up a standard xml reader object which can be used for the following
     * tests.
     */
    @Before
    public void setUpXMLReader() {
        stationsFileName = "testStations.xml";
        connectionsFileName = "testConnections.xml";
        linesFileName = "testLines.xml";
        stationsFile = new File(stationsFileName);
        connectionsFile = new File(connectionsFileName);
        linesFile = new File(linesFileName);
        try {
            FileWriter stationsWriter = new FileWriter(stationsFile);
            FileWriter connectionsWriter = new FileWriter(connectionsFile);
            FileWriter linewWriter = new FileWriter(linesFile);
            BufferedWriter bufferedStationsWriter = new BufferedWriter(stationsWriter);
            BufferedWriter bufferedConnectionsWriter = new BufferedWriter(connectionsWriter);
            BufferedWriter bufferedLinesWriter = new BufferedWriter(linewWriter);
            
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
            
            // Creating an example stations xml file
            bufferedStationsWriter.write("<stations>\n");
            bufferedStationsWriter.write("  <station>North</station>\n");
            bufferedStationsWriter.write("  <station>South</station>\n");
            bufferedStationsWriter.write("  <station>West</station>\n");
            bufferedStationsWriter.write("  <station>East</station>\n");
            bufferedStationsWriter.write("  <station>Center</station>\n");
            bufferedStationsWriter.write("</stations>\n");
            
            // Creating an example connections xml file
            bufferedConnectionsWriter.write("<connections>\n");
            bufferedConnectionsWriter.write("   <connection>\n");
            bufferedConnectionsWriter.write("       <stationA>North</stationA>\n");
            bufferedConnectionsWriter.write("       <stationB>Center</stationB>\n");
            bufferedConnectionsWriter.write("       <duration>5</duration>\n");
            bufferedConnectionsWriter.write("   </connection>\n");
            bufferedConnectionsWriter.write("   <connection>\n");
            bufferedConnectionsWriter.write("       <stationA>Center</stationA>\n");
            bufferedConnectionsWriter.write("       <stationB>South</stationB>\n");
            bufferedConnectionsWriter.write("       <duration>10</duration>\n");
            bufferedConnectionsWriter.write("   </connection>\n");
            bufferedConnectionsWriter.write("   <connection>\n");
            bufferedConnectionsWriter.write("       <stationA>West</stationA>\n");
            bufferedConnectionsWriter.write("       <stationB>Center</stationB>\n");
            bufferedConnectionsWriter.write("       <duration>15</duration>\n");
            bufferedConnectionsWriter.write("   </connection>\n");
            bufferedConnectionsWriter.write("   <connection>\n");
            bufferedConnectionsWriter.write("       <stationA>Center</stationA>\n");
            bufferedConnectionsWriter.write("       <stationB>East</stationB>\n");
            bufferedConnectionsWriter.write("       <duration>20</duration>\n");
            bufferedConnectionsWriter.write("   </connection>\n");
            bufferedConnectionsWriter.write("</connections>\n");
            
            // Creating an example lines xml file
            bufferedLinesWriter.write("<lines>\n");
            bufferedLinesWriter.write(" <line>\n");
            bufferedLinesWriter.write("     <number>1</number>\n");
            bufferedLinesWriter.write("     <type>Metro</type>\n");
            bufferedLinesWriter.write("     <stations>\n");
            bufferedLinesWriter.write("         <station>North</station>\n");
            bufferedLinesWriter.write("         <station>Center</station>\n");
            bufferedLinesWriter.write("         <station>South</station>\n");
            bufferedLinesWriter.write("     </stations>\n");
            bufferedLinesWriter.write("     <departuresFirstStation>\n");
            bufferedLinesWriter.write("         <departure>08:40</departure>\n");
            bufferedLinesWriter.write("         <departure>16:40</departure>\n");
            bufferedLinesWriter.write("     </departuresFirstStation>\n");
            bufferedLinesWriter.write("     <departuresLastStation>\n");
            bufferedLinesWriter.write("         <departure>09:40</departure>\n");
            bufferedLinesWriter.write("         <departure>17:40</departure>\n");
            bufferedLinesWriter.write("     </departuresLastStation>\n");
            bufferedLinesWriter.write(" </line>\n");
            bufferedLinesWriter.write(" <line>\n");
            bufferedLinesWriter.write("     <number>2</number>\n");
            bufferedLinesWriter.write("     <type>Bus</type>\n");
            bufferedLinesWriter.write("     <stations>\n");
            bufferedLinesWriter.write("         <station>West</station>\n");
            bufferedLinesWriter.write("         <station>Center</station>\n");
            bufferedLinesWriter.write("         <station>East</station>\n");
            bufferedLinesWriter.write("     </stations>\n");
            bufferedLinesWriter.write("     <departuresFirstStation>\n");
            bufferedLinesWriter.write("         <departure>10:00</departure>\n");
            bufferedLinesWriter.write("     </departuresFirstStation>\n");
            bufferedLinesWriter.write("     <departuresLastStation>\n");
            bufferedLinesWriter.write("         <departure>11:00</departure>\n");
            bufferedLinesWriter.write("     </departuresLastStation>\n");
            bufferedLinesWriter.write(" </line>\n");
            bufferedLinesWriter.write("</lines>\n");
            
            bufferedStationsWriter.close();
            bufferedConnectionsWriter.close();
            bufferedLinesWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        xmlReader = new XMLReader(stationsFileName, connectionsFileName, linesFileName);
        faultyXMLReader = new XMLReader("test1.xml", "test2.xml", "test3.xml");
    }
    
    /**
     * Deletes the files the standard xml reader created.
     */
    @After
    public void tearDownXMLReader() {
        stationsFile.delete();
        connectionsFile.delete();
        linesFile.delete();
    }
    
    /**
     * Basic test which initializes a XMLReader object.
     */
    @Test
    public void testXMLReader() {
        XMLReader testXMLReader = 
            new XMLReader(stationsFileName, connectionsFileName, linesFileName);
        assertEquals(XMLReader.class, testXMLReader.getClass());
    }

    /**
     * Reading the station xml file.
     */
    @Test
    public void testReadStation() {
        
        // Existing XML file 
        ArrayList<StationData> stationDataList = xmlReader.readStation();
        String[] stationNameList = {"North", "South", "West", "East", "Center"};
        for (int i = 0; i < stationDataList.size(); i++) {
            assertEquals(stationNameList[i], stationDataList.get(i).getStation());
        }
        
        // Not existing XML file
        ArrayList<StationData> faultyStationDataList = faultyXMLReader.readStation();
        assertTrue(0 == faultyStationDataList.size());
    }

    /**
     * Reading the connection xml file.
     */
    @Test
    public void testReadConnection() {
        
        // Existing XML file 
        ArrayList<ConnectionData> connectionDataList = xmlReader.readConnection();
        String[] connectionStationAList = {"North", "Center", "West", "Center"};
        String[] connectionStationBList = {"Center", "South", "Center", "East"};
        int[] connectionDurationList = {5, 10, 15, 20};
        for (int i = 0; i < connectionDataList.size(); i++) {
            assertEquals(connectionStationAList[i], connectionDataList.get(i).getStationA());
            assertEquals(connectionStationBList[i], connectionDataList.get(i).getStationB());
            assertTrue(connectionDurationList[i] == connectionDataList.get(i).getDuration());
        }
        
        // Not existing XML file
        ArrayList<ConnectionData> faultyConnectionDataList = faultyXMLReader.readConnection();
        assertTrue(0 == faultyConnectionDataList.size());
    }

    /**
     * Reading the line xml file.
     */
    @Test
    public void testReadLine() {
        
        // Existing XML file 
        ArrayList<LineData> lineDataList = xmlReader.readLine();
        int[] lineNumberList = {1, 2};
        String[] lineTypeList = {"Metro", "Bus"};
        String[][] lineStationsList = {{"North", "Center", "South"}, 
                {"West", "Center", "East"}};
        String[][] lineDeparturesFirstStationList = {{"08:40", "16:40"}, {"10:00"}};
        String[][] lineDeparturesLastStationList = {{"09:40", "17:40"}, {"11:00"}};
        
        for (int i = 0; i < lineDataList.size(); i++) {
            assertTrue(lineNumberList[i] == lineDataList.get(i).getNumber());
            assertEquals(lineTypeList[i], lineDataList.get(i).getType());
            ArrayList<String> tmpStationsList = lineDataList.get(i).getStations();
            for(int j = 0; j < tmpStationsList.size(); i++) {
                assertEquals(lineStationsList[i][j], tmpStationsList.get(j));
            }
            ArrayList<String> tmpDeparturesFirstStationList = 
                lineDataList.get(i).getDeparturesFirstStation();
            for(int j = 0; j < tmpDeparturesFirstStationList.size(); i++) {
                assertEquals(lineDeparturesFirstStationList[i][j], 
                        tmpDeparturesFirstStationList.get(j));
            }
            ArrayList<String> tmpDeparturesLastStationList = 
                lineDataList.get(i).getDeparturesLastStation();
            for(int j = 0; j < tmpDeparturesLastStationList.size(); i++) {
                assertEquals(lineDeparturesLastStationList[i][j], 
                        tmpDeparturesLastStationList.get(j));
            }
        }
        
        // Not existing XML file
        ArrayList<LineData> faultyLineDataList = faultyXMLReader.readLine();
        assertTrue(0 == faultyLineDataList.size());
    }
}
