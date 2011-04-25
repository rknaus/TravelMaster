package ch.netgeek.travelmaster.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import ch.netgeek.travelmaster.route.ConnectionTest;


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
    private File stationsFile = new File("stations.xml");
    private File connectionsFile = new File("connections.xml");
    private File linesFile = new File("lines.xml");

    // Elements in XML files
    private final String stationTag = "station";
    private final String stationATag = "stationA";
    private final String stationBTag = "stationB";
    private final String durationTag = "duration";
    private final String lineTag = "line";
    private final String numberTag = "number";
    private final String typeTag = "type";
    private final String stationsTag = "stations";
    private final String departuresFirstStationTag = "departuresFirstStation";
    private final String departuresLastStationTag = "departuresLastStation";
    private final String departureTag = "departure";

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
            //create Document from file and get the root element
            SAXBuilder builder = new SAXBuilder(true);
            Document doc = builder.build(stationsFile);
            root = doc.getRootElement();
        } catch (Exception e) {
            return new ArrayList<StationData>();
        }
        List<?> children = root.getChildren();
        for(int i = 0; i < children.size(); i++) {
            Element stationdata = ((Element) children.get(i));
            String station = stationdata.getChildText(stationTag);
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
            //create Document from file and get the root element
            SAXBuilder builder = new SAXBuilder(true);
            Document doc = builder.build(connectionsFile);
            root = doc.getRootElement();
        } catch (Exception e) {
            return new ArrayList<ConnectionData>();
        }
        List<?> children = root.getChildren();
        for (int i = 0; i < children.size(); i++) {
            Element connectiondata = ((Element) children.get(i));
            String stationA = connectiondata.getChildText(stationATag);
            String stationB = connectiondata.getChildText(stationBTag);
            int duration = Integer.parseInt(connectiondata.getChildText(durationTag));
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
        Iterator<?> lineIterator = root.getChildren(lineTag).iterator();
        
        // Iterating over the xml <line> elements
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
