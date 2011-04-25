package ch.netgeek.travelmaster.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;


/**
 * This class reads an existed xml-file to add all stations and connections<br>
 * to the list.
 *
 * @author      Ruben Knaus, Dieu P. Van
 * @version     1.0, 19.04.2011
 *
 */
public class XMLReader {

    // variables
    private File stationsFile;
    private File connectionsFile;
    private File linesFile;

    // Elements in XML files
    private final String stationTag;
    private final String connectionTag;
    private final String stationATag;
    private final String stationBTag;
    private final String durationTag;
    private final String lineTag;
    private final String numberTag;
    private final String typeTag;
    private final String stationsTag;
    private final String departuresFirstStationTag;
    private final String departuresLastStationTag;
    private final String departureTag;

    /**
     * Initializes the XMLReader object. It requires the filename (and path) of 
     * the three following XML files:
     * 
     * @param stationsFileName          the filename (and path) for the stations
     * @param connectionsFileName       the filename (and path) for the connections
     * @param linesFileName             the filename (and path) for the lines
     */
    public XMLReader(String stationsFileName, String connectionsFileName,
            String linesFileName) {
        stationsFile = new File(stationsFileName);
        connectionsFile = new File(connectionsFileName);
        linesFile = new File(linesFileName);
        
        stationTag = "station";
        connectionTag = "connection";
        stationATag = "stationA";
        stationBTag = "stationB";
        durationTag = "duration";
        lineTag = "line";
        numberTag = "number";
        typeTag = "type";
        stationsTag = "stations";
        departuresFirstStationTag = "departuresFirstStation";
        departuresLastStationTag = "departuresLastStation";
        departureTag = "departure";
    }
    
    /**
     * This method opens the xml file and puts all stations to the <br>
     * list
     * 
     * @return stations     returns a list of all stations
     */
    public ArrayList<StationData> readStation() {
        ArrayList<StationData> stations = new ArrayList<StationData>();
        Element root;
        try {
            
            // Create document from file and get the root element
            SAXBuilder builder = new SAXBuilder(true);
            Document doc = builder.build(stationsFile);
            root = doc.getRootElement();
        } catch (Exception e) {
            
            // Return an empty array list in case of an exception
            return new ArrayList<StationData>();
        }
        
        // Iterating over the xml <station> elements
        Iterator<?> lineIterator = root.getChildren(stationTag).iterator();
        while (lineIterator.hasNext()) {
            Element lineElement = (Element) lineIterator.next();
            
            // Getting the station name
            String station = lineElement.getText();
            
            // Adding the values as StationData object to the array list
            stations.add(new StationData(station));		
        }
        return stations;
    }

    /**
     * This method opens the xml file and puts all connections to the <br>
     * list
     * 
     * @return connections  returns a list of all connections
     */
    public ArrayList<ConnectionData> readConnection() {
        ArrayList<ConnectionData> connections = new ArrayList<ConnectionData>();
        Element root;
        try {
            
            // Create document from file and get the root element
            SAXBuilder builder = new SAXBuilder(true);
            Document doc = builder.build(connectionsFile);
            root = doc.getRootElement();
        } catch (Exception e) {
            
            // Return an empty array list in case of an exception
            return connections;
        }
        
        // Iterating over the xml <line> elements
        Iterator<?> lineIterator = root.getChildren(connectionTag).iterator();
        while (lineIterator.hasNext()) {
            Element lineElement = (Element) lineIterator.next();
            
            // Getting the station A name
            String stationA = lineElement.getChildText(stationATag);
            
            // Getting the station B name
            String stationB = lineElement.getChildText(stationBTag);
            
            // Getting the duration between station A and B
            int duration = Integer.parseInt(lineElement.getChildText(durationTag));
            
            // Adding the values as ConnectionData object to the array list
            connections.add(new ConnectionData(stationA, stationB, duration));
        }
        return connections;
    }
    
    /**
     * This method opens the xml file and puts all lines to the <br>
     * list
     * 
     * @return lines        returns a list of all lines
     */
    public ArrayList<LineData> readLine() {
        ArrayList<LineData> lines = new ArrayList<LineData>();
        Element root;
        try {
            
            // Create document from file and get the root element
            SAXBuilder builder = new SAXBuilder(true);
            Document doc = builder.build(linesFile);
            root = doc.getRootElement();
        } catch (Exception e) {
            
            // Return an empty array list in case of an exception
            return lines;
        }
        
        // Iterating over the xml <line> elements
        Iterator<?> lineIterator = root.getChildren(lineTag).iterator();
        while (lineIterator.hasNext()) {
            Element lineElement = (Element) lineIterator.next();
            
            // Getting the line number
            int number = Integer.parseInt(lineElement.getChildText(numberTag));
            
            // Getting the line type
            String type = lineElement.getChildText(typeTag);
            
            /*
             * Getting the stations the line serves with an interation over the
             * xml <stations> tag
             */
            ArrayList<String> stations = new ArrayList<String>();
            Iterator<?> subLineIterator = 
                lineElement.getChildren(stationsTag).iterator();
            while (subLineIterator.hasNext()) {
                Element subLineElement = (Element) subLineIterator.next();
                String station = subLineElement.getChildText(stationTag);
                stations.add(station);
            }
            
            /*
             * Getting the departure times of the first station with an 
             * iteration over the xml <departuresFirstStation> tag
             */
            ArrayList<String> departuresFirstStation = new ArrayList<String>();
            subLineIterator = 
                lineElement.getChildren(departuresFirstStationTag).iterator();
            while (subLineIterator.hasNext()) {
                Element subLineElement = (Element) subLineIterator.next();
                String station = subLineElement.getChildText(departureTag);
                departuresFirstStation.add(station);
            }
            
            /*
             * Getting the departure times of the last station with an
             * iteration over the xml <departuresLastStation> tag
             */
            ArrayList<String> departuresLastStation = new ArrayList<String>();
            subLineIterator = 
                lineElement.getChildren(departuresLastStationTag).iterator();
            while (subLineIterator.hasNext()) {
                Element subLineElement = (Element) subLineIterator.next();
                String station = subLineElement.getChildText(departureTag);
                departuresLastStation.add(station);
            }
            
            // Adding the values as LineData object to the array list
            lines.add(new LineData(number, type, stations, 
                    departuresFirstStation, departuresLastStation));
        }
        return lines;
    }
}
