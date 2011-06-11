package ch.netgeek.travelmaster.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import ch.netgeek.travelmaster.route.Station;
import ch.netgeek.travelmaster.route.TransportNetwork;

/**
 * This class reads an existed xml-file to add all stations and connections<br>
 * to the list.
 *
 * @author      Ruben Knaus, Dieu P. Van
 * @version     1.0
 */
public class XMLReader {

    // variable declaration
    private File stationsFile;
    private File connectionsFile;
    private File linesFile;
    private TransportNetwork transportNetwork;

    // XML element variable declaration
    private final String stationTag;
    private final String nameTag;
    private final String xPosTag;
    private final String yPosTag;
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
     * the XML files with the information about the transport network and it
     * requires the transport network object.
     * 
     * @param stationsFileName          the filename (and path) for the stations
     * @param connectionsFileName       the filename (and path) for the connections
     * @param linesFileName             the filename (and path) for the lines
     * @param transportNetwork          the transport network object
     */
    public XMLReader(String stationsFileName, String connectionsFileName,
            String linesFileName, TransportNetwork transportNetwork) {
        
        // saving the parameters in local variables
        stationsFile = new File(stationsFileName);
        connectionsFile = new File(connectionsFileName);
        linesFile = new File(linesFileName);
        this.transportNetwork = transportNetwork;

        // setting the values for the XML elements
        stationTag = "station";
        nameTag = "name";
        xPosTag = "xpos";
        yPosTag = "ypos";
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
     * This method opens the stations XML file, puts all stations to a list and 
     * returns it.
     * 
     * @return stations                 a list of all stations
     */
    public ArrayList<StationData> readStation() {
        ArrayList<StationData> stations = new ArrayList<StationData>();
        Element root;
        try {

            // create a document of the file and gets the root element
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(stationsFile);
            root = doc.getRootElement();
        } catch (Exception e) {

            // returns an empty array list in case of an exception
            return new ArrayList<StationData>();
        }

        // iterating over the xml <station> elements
        Iterator<?> lineIterator = root.getChildren(stationTag).iterator();
        while (lineIterator.hasNext()) {
            Element lineElement = (Element) lineIterator.next();

            // getting the station name, x coordinate and y coordinate
            String name = lineElement.getChildText(nameTag);
            int xPos = Integer.parseInt(lineElement.getChildText(xPosTag));
            int yPos = Integer.parseInt(lineElement.getChildText(yPosTag));

            // adding the values as StationData object to the array list
            stations.add(new StationData(name, xPos, yPos));		
        }
        return stations;
    }

    /**
     * This method opens the connections XML file, puts all connections to a 
     * list and returns it.
     * 
     * @return                          a list of all connections
     */
    public ArrayList<ConnectionData> readConnection() {
        ArrayList<ConnectionData> connections = new ArrayList<ConnectionData>();
        Element root;
        try {

            // creates a document of the file and gets the root element
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(connectionsFile);
            root = doc.getRootElement();
        } catch (Exception e) {

            // returns an empty array list in case of an exception
            return connections;
        }

        // iterating over the xml <line> elements
        Iterator<?> lineIterator = root.getChildren(connectionTag).iterator();
        while (lineIterator.hasNext()) {
            Element lineElement = (Element) lineIterator.next();

            /*
             * getting the station A name, the station B name and the duration 
             * between station A and B
             */
            String stationA = lineElement.getChildText(stationATag);
            String stationB = lineElement.getChildText(stationBTag);
            int duration = Integer.parseInt(lineElement.getChildText(durationTag));

            // adding the values as ConnectionData object to the array list
            connections.add(new ConnectionData(stationA, stationB, duration));
        }
        return connections;
    }

    /**
     * This method opens the lines XML file, puts all lines to a list and 
     * returns it.
     * 
     * @return                          a list of all lines
     */
    public ArrayList<LineData> readLine() {
        ArrayList<LineData> lines = new ArrayList<LineData>();
        Element root;
        try {

            // Creates document of the file and gets the root element
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(linesFile);
            root = doc.getRootElement();
        } catch (Exception e) {

            // returns an empty array list in case of an exception
            return lines;
        }

        // iterating over the xml <line> elements
        Iterator<?> lineIterator = root.getChildren(lineTag).iterator();
        while (lineIterator.hasNext()) {
            Element lineElement = (Element) lineIterator.next();

            // getting the line number and the line type
            int number = Integer.parseInt(lineElement.getChildText(numberTag));
            String type = lineElement.getChildText(typeTag);

            /*
             * getting the stations the line serves with an interation over the
             * xml <stations> tag
             */
            ArrayList<String> stations = new ArrayList<String>();
            Element stationsElement = lineElement.getChild(stationsTag);
            Iterator<?> stationIterator = 
                stationsElement.getChildren(stationTag).iterator();
            while (stationIterator.hasNext()) {
                Element stationElement = (Element) stationIterator.next();
                String station = stationElement.getText();                
                stations.add(station);
            }

            /*
             * getting the departures of the first station with an iteration 
             * over the xml <departuresFirstStation> tag
             */
            ArrayList<String> departuresFirstStation = new ArrayList<String>();
            Element departuresFirstStationElement = 
                lineElement.getChild(departuresFirstStationTag);
            Iterator<?> depFirstStationIterator = 
                departuresFirstStationElement.getChildren(departureTag).iterator();
            while (depFirstStationIterator.hasNext()) {
                Element departureElement = 
                    (Element) depFirstStationIterator.next();
                String station = departureElement.getText();
                departuresFirstStation.add(station);
            }

            /*
             * getting the departures of the last station with an iteration over 
             * the xml <departuresLastStation> tag
             */
            ArrayList<String> departuresLastStation = new ArrayList<String>();
            Element departuresLastStationElement = 
                lineElement.getChild(departuresLastStationTag);
            Iterator<?> depLastStationIterator = 
                departuresLastStationElement.getChildren(departureTag).iterator();
            while (depLastStationIterator.hasNext()) {
                Element departureElement = 
                    (Element) depLastStationIterator.next();
                String station = departureElement.getText();
                departuresLastStation.add(station);
            }

            // adding the values as LineData object to the array list
            lines.add(new LineData(number, type, stations, 
                    departuresFirstStation, departuresLastStation));
        }
        return lines;
    }

    /**
     * Adds stations to the TransportNetwork object.
     * 
     * @param stations                  the list with StationData objects to add
     */
    public void addStations(ArrayList<StationData> stations) {
        for (StationData stationData : stations) {
            transportNetwork.addStation(stationData.getName(), 
                    stationData.getXPos(), stationData.getYPos());
        }
    }

    /**
     * Adds connections to the TransportNetwork object.
     * 
     * @param connections               the list with ConnectionData objects to add
     */
    public void addConnections(ArrayList<ConnectionData> connections) {
        for (ConnectionData connectionData : connections) {
            Station stationA = 
                transportNetwork.getStation(connectionData.getStationA());
            Station stationB = 
                transportNetwork.getStation(connectionData.getStationB());
            int duration = connectionData.getDuration();
            if (stationA != null && stationB != null) {
                transportNetwork.addConnection(stationA, stationB, duration);
            }
        }
    }

    /**
     * Adds lines to the TransportNetwork object.
     * 
     * @param lines                     the list with LineData objects to add
     */
    public void addLines(ArrayList<LineData> lines) {
        
        // iterating over all line data objects
        for (LineData lineData : lines) {
            int number = lineData.getNumber();
            String type = lineData.getType();
            ArrayList<Station> stations = new ArrayList<Station>();
            
            /*
             * Getting the stations of the line data object and checking if the
             * stations exist. If not, the line doesn't get added to the
             * transport network.
             */
            for (String stationString : lineData.getStations()) {
                Station station = transportNetwork.getStation(stationString);
                if (station != null) {
                    stations.add(station);
                } else {
                    break;
                }
            }

            // lineData.getLines and lines must have the same size
            if (lineData.getStations().size() != stations.size()) {
                continue;
            }
            ArrayList<Calendar> departuresFirstStation = new ArrayList<Calendar>();
            for (String departureString : lineData.getDeparturesFirstStation()) {

                // departure time must be ##:## -> 5 characters long
                if (departureString.length() != 5) {
                    break;
                }

                // departure hour and minute must be convertable to int
                int hour;
                int minute;
                try {
                    hour = Integer.parseInt(departureString.substring(0, 2));
                    minute = Integer.parseInt(departureString.substring(3));
                } catch (NumberFormatException e) {
                    break;
                }
                Calendar departure = Calendar.getInstance();
                departure.set(0, 0, 0, hour, minute);
                departuresFirstStation.add(departure);
            }

            /* 
             * lineData.getDeparturesFirstStation and departuresFirstStation 
             * must have the same size
             */
            if (lineData.getDeparturesFirstStation().size() 
                    != departuresFirstStation.size()) {
                continue;
            }
            ArrayList<Calendar> departuresLastStation = new ArrayList<Calendar>();
            for (String departureString : lineData.getDeparturesLastStation()) {

                // departure time must be ##:## -> 5 characters long
                if (departureString.length() != 5) {
                    break;
                }

                // departure hour and minute must be convertable to int
                int hour;
                int minute;
                try {
                    hour = Integer.parseInt(departureString.substring(0, 2));
                    minute = Integer.parseInt(departureString.substring(3));
                } catch (NumberFormatException e) {
                    break;
                }
                Calendar departure = Calendar.getInstance();
                departure.set(0, 0, 0, hour, minute);
                departuresLastStation.add(departure);
            }

            /* 
             * LineData.getDeparturesLastStation and departuresLastStation must
             * have the same size
             */
            if (lineData.getDeparturesLastStation().size() 
                    != departuresLastStation.size()) {
                continue;
            }
            
            /*
             * finally adding the line to the transport network, if all data is 
             * valid
             */
            transportNetwork.addLine(number, type, stations, 
                    departuresFirstStation, departuresLastStation);
        }
    }
}