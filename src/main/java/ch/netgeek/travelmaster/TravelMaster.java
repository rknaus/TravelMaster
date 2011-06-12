package ch.netgeek.travelmaster;

import ch.netgeek.travelmaster.algorithm.RouteCalculator;
import ch.netgeek.travelmaster.gui.GUI;
import ch.netgeek.travelmaster.io.XMLReader;
import ch.netgeek.travelmaster.route.TransportNetwork;
import ch.netgeek.travelmaster.gui.TableController;

/**
 * This is the TravelMaster main program.<br>
 * It simulates a public transport network and can calculate the best time table
 * between stations.<br>
 * For more information, please consult the project documentation delivered with
 * the software.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     1.0
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

        /*
         * initializes the table controller which displays the time table in the 
         * GUI
         */
        TableController controller = new TableController();

        // initializes the GUI
        new GUI(transportNetwork, routeCalculator, controller);
    }
}