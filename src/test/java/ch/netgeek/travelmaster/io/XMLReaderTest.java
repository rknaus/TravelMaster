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

import ch.netgeek.travelmaster.route.Station;
import ch.netgeek.travelmaster.route.TransportNetwork;

/**
 * JUnit test cases for the XMLReader class.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class XMLReaderTest {

    // variable declaration
    private TransportNetwork transportNetwork;
    private XMLReader xmlReader;
    private XMLReader emptyXMLReader;
    private String stationsFileName;
    private String connectionsFileName;
    private String linesFileName;
    private File stationsFile;
    private File connectionsFile;
    private File linesFile;
    private ArrayList<StationData> stationDataList;
    private ArrayList<ConnectionData> connectionDataList;
    private ArrayList<LineData> lineDataList;
    
    /**
     * Sets up a standard xml reader object which can be used for the following
     * tests.
     */
    @Before
    public void setUpXMLReader() {
        transportNetwork = new TransportNetwork();
        stationsFileName = "testStations.xml";
        connectionsFileName = "testConnections.xml";
        linesFileName = "testLines.xml";
        stationsFile = new File(stationsFileName);
        connectionsFile = new File(connectionsFileName);
        linesFile = new File(linesFileName);
        stationDataList = new ArrayList<StationData>();
        connectionDataList = new ArrayList<ConnectionData>();
        lineDataList = new ArrayList<LineData>();
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
        xmlReader = new XMLReader(stationsFileName, connectionsFileName, 
                linesFileName, transportNetwork);
        emptyXMLReader = new XMLReader("test1.xml", "test2.xml", "test3.xml", 
                transportNetwork);
        stationDataList.add(new StationData("North"));
        stationDataList.add(new StationData("South"));
        stationDataList.add(new StationData("West"));
        stationDataList.add(new StationData("East"));
        stationDataList.add(new StationData("Center"));
        connectionDataList.add(new ConnectionData("North", "Center", 5));
        connectionDataList.add(new ConnectionData("Center", "South", 10));
        connectionDataList.add(new ConnectionData("West", "Center", 15));
        connectionDataList.add(new ConnectionData("Center", "East", 20));
        ArrayList<String> stations = new ArrayList<String>();
        stations.add("North");
        stations.add("Center");
        stations.add("South");
        ArrayList<String> departuresFirstStation = new ArrayList<String>();
        departuresFirstStation.add("08:40");
        departuresFirstStation.add("16:40");
        ArrayList<String> departuresLastStation = new ArrayList<String>();
        departuresLastStation.add("09:40");
        departuresLastStation.add("17:40");
        lineDataList.add(new LineData(1, "Metro", stations, departuresFirstStation, 
                departuresLastStation));
        stations.clear();
        departuresFirstStation.clear();
        departuresLastStation.clear();
        stations.add("West");
        stations.add("Center");
        stations.add("East");
        departuresFirstStation.add("10:00");
        departuresLastStation.add("11:00");
        lineDataList.add(new LineData(2, "Bus", stations, departuresFirstStation, 
                departuresLastStation));
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
            new XMLReader(stationsFileName, connectionsFileName, linesFileName, 
                    transportNetwork);
        assertEquals(XMLReader.class, testXMLReader.getClass());
    }

    /**
     * Reading the station xml file.
     */
    @Test
    public void testReadStation() {
        
        // Existing XML file 
        ArrayList<StationData> stationDataList = xmlReader.readStation();
        for (int i = 0; i < stationDataList.size(); i++) {
            assertEquals(stationDataList.get(i), stationDataList.get(i).getStation());
        }
        
        // Not existing XML file
        ArrayList<StationData> emptyStationDataList = emptyXMLReader.readStation();
        assertTrue(0 == emptyStationDataList.size());
    }

    /**
     * Reading the connection xml file.
     */
    @Test
    public void testReadConnection() {
        
        // Existing XML file 
        ArrayList<ConnectionData> connectionDataList = xmlReader.readConnection();
        for (int i = 0; i < connectionDataList.size(); i++) {
            assertEquals(connectionDataList.get(i).getStationA(), 
                    connectionDataList.get(i).getStationA());
            assertEquals(connectionDataList.get(i).getStationB(), 
                    connectionDataList.get(i).getStationB());
            assertTrue(connectionDataList.get(i).getDuration() 
                    == connectionDataList.get(i).getDuration());
        }
        
        // Not existing XML file
        ArrayList<ConnectionData> emptyConnectionDataList = 
            emptyXMLReader.readConnection();
        assertTrue(0 == emptyConnectionDataList.size());
    }

    /**
     * Reading the line xml file.
     */
    @Test
    public void testReadLine() {
        
        // Existing XML file 
        ArrayList<LineData> lineDataList = xmlReader.readLine();
        
        for (int i = 0; i < lineDataList.size(); i++) {
            assertTrue(lineDataList.get(i).getNumber() 
                    == lineDataList.get(i).getNumber());
            assertEquals(lineDataList.get(i).getType(), 
                    lineDataList.get(i).getType());
            ArrayList<String> tmpStationsList = lineDataList.get(i).getStations();
            for(int j = 0; j < tmpStationsList.size(); i++) {
                assertEquals(lineDataList.get(i).getStations().get(j), 
                        tmpStationsList.get(j));
            }
            ArrayList<String> tmpDeparturesFirstStationList = 
                lineDataList.get(i).getDeparturesFirstStation();
            for(int j = 0; j < tmpDeparturesFirstStationList.size(); i++) {
                assertEquals(lineDataList.get(i).getDeparturesFirstStation().get(j), 
                        tmpDeparturesFirstStationList.get(j));
            }
            ArrayList<String> tmpDeparturesLastStationList = 
                lineDataList.get(i).getDeparturesLastStation();
            for(int j = 0; j < tmpDeparturesLastStationList.size(); i++) {
                assertEquals(lineDataList.get(i).getDeparturesLastStation().get(j), 
                        tmpDeparturesLastStationList.get(j));
            }
        }
        
        // Not existing XML file
        ArrayList<LineData> emptyLineDataList = emptyXMLReader.readLine();
        assertTrue(0 == emptyLineDataList.size());
    }
    
    /**
     * Adding the stations to the TransportNetwork
     */
    @Test
    public void testAddStations() {
        xmlReader.addStations(stationDataList);
        for (int i = 0; i < stationDataList.size(); i++) {
            String expected = stationDataList.get(i).getStation();
            String actual = transportNetwork.getStation(expected).getName();
            assertEquals(expected, actual);
        }
    }
    
    /**
     * Adding the connections to the TransportNetwork
     */
    @Test
    public void testAddConnections() {
        
        // Without having any stations defined!
        xmlReader.addConnections(connectionDataList);
        assertTrue(0 == transportNetwork.getConnectionList().size());
        
        // Having Stations defined already
        xmlReader.addStations(stationDataList);
        xmlReader.addConnections(connectionDataList);
        for (int i = 0; i < connectionDataList.size(); i++) {
            Station stationA = transportNetwork.getStation(
                    connectionDataList.get(i).getStationA());
            Station stationB = transportNetwork.getStation(
                    connectionDataList.get(i).getStationB());
            assertTrue(connectionDataList.get(i).getDuration() == 
                transportNetwork.getConnection(stationA, stationB).getDuration());
        }
        
    }
    
    /**
     * Adding the lines to the TransportNetwork
     */
    @Test
    public void testAddLines() {
        
        // Without having stations and connections defined!
        xmlReader.addLines(lineDataList);
        assertTrue(0 == transportNetwork.getLines().size());
        
        // Without having connections defined!
        xmlReader.addStations(stationDataList);
        xmlReader.addLines(lineDataList);
        assertTrue(0 == transportNetwork.getLines().size());
        xmlReader.addConnections(connectionDataList);
        
        // Having illegal departure times
        ArrayList<LineData> faultyLineDataList = new ArrayList<LineData>();
        ArrayList<String> stations0 = new ArrayList<String>();
        stations0.add("North");
        stations0.add("Center");
        stations0.add("South");
        ArrayList<String> departuresFirstStation0 = new ArrayList<String>();
        departuresFirstStation0.add("0::40");
        departuresFirstStation0.add("16:40");
        ArrayList<String> departuresLastStation0 = new ArrayList<String>();
        departuresLastStation0.add("0::40");
        departuresLastStation0.add("17:40");
        faultyLineDataList.add(new LineData(1, "Metro", stations0, 
                departuresFirstStation0, departuresLastStation0));
        ArrayList<String> stations1 = new ArrayList<String>();
        stations1.add("North");
        stations1.add("Center");
        stations1.add("South");
        ArrayList<String> departuresFirstStation1 = new ArrayList<String>();
        departuresFirstStation1.add("08:400");
        departuresFirstStation1.add("16:40");
        ArrayList<String> departuresLastStation1 = new ArrayList<String>();
        departuresLastStation1.add("109340");
        departuresLastStation1.add("17:40");
        faultyLineDataList.add(new LineData(1, "Metro", stations1, 
                departuresFirstStation1, departuresLastStation1));
        xmlReader.addLines(faultyLineDataList);
        assertTrue(0 == transportNetwork.getLines().size());
        
        // Having correct departure times defined
        xmlReader.addLines(lineDataList);
        for (int i = 0; i < lineDataList.size(); i++) {
            assertTrue(lineDataList.get(i).getNumber() == 
                transportNetwork.getLines().get(i).getNumber());
        }
    }
}
