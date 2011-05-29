package ch.netgeek.travelmaster;

import java.util.ArrayList;

import ch.netgeek.travelmaster.algorithm.RouteCalculator;
import ch.netgeek.travelmaster.gui.GUI;
import ch.netgeek.travelmaster.io.XMLReader;
import ch.netgeek.travelmaster.route.TransportNetwork;
import ch.netgeek.travelmaster.algorithm.Stopover;
import ch.netgeek.travelmaster.gui.TableController;

/**
 * This is the TravelMaster main program.
 * TODO: Add general description about the program function here.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class TravelMaster {

    /**
     * This is the main function of the main program.
     * 
     * @param args              Java default arg value
     */
    public static void main(String[] args) {
    	
        // builds the transport network out of XML files
        TransportNetwork transportNetwork = new TransportNetwork();
        String stationsFileName = "stations.xml";
        String connectionsFileName = "connections.xml";
        String linesFileName = "lines.xml";
    	XMLReader xmlReader = new XMLReader(stationsFileName, 
    	        connectionsFileName, linesFileName, transportNetwork);
    	xmlReader.addStations(xmlReader.readStation());
    	xmlReader.addConnections(xmlReader.readConnection());
    	xmlReader.addLines(xmlReader.readLine());
    	
    	// initializes the route calculator
    	RouteCalculator routeCalculator = new RouteCalculator(transportNetwork);
    	
    	ArrayList<Stopover> connectionResult = new ArrayList<Stopover>();
    	TableController controller = new TableController(connectionResult);
    	
    	// initializes the GUI
    	new GUI(transportNetwork, routeCalculator, controller);
    }

}
