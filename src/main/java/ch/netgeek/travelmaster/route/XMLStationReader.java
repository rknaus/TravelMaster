package ch.netgeek.travelmaster.route;

import ch.netgeek.travelmaster.route.StationData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
public class XMLStationReader {
	
	// variables
	private File file = new File("stations.xml");
	private Document doc;
	private Element root;
	
	// Elements in XML File
	private final String childStationTag = "station";
	private final String childStationATag = "stationA";
	private final String childStationBTag = "stationB";
	
    /**
     * This method opens the xml file and puts all stations to the <br>
     * list
     * 
     * @return stations     returns a list of all stations
     */
	public ArrayList<StationData> readStation(){
		ArrayList<StationData> stations = new ArrayList<StationData>();
		try{
		//create Document from file and get the root element
		SAXBuilder builder = new SAXBuilder(true);
		doc = builder.build(file);
		root = doc.getRootElement();
		} catch (Exception e) {
            return new ArrayList<StationData>();
        }
		
		List<?> children = root.getChildren();
		
		for(int i = 0; i < children.size(); i++){
			Element stationdata = ((Element) children.get(i));
			String station = stationdata.getChildText(childStationTag);
			stations.add(new StationData(station));			
		}
		
		return stations;
	}
	
    /**
     * This method opens the xml file and puts all connections to the <br>
     * list
     * 
     * @return connections     returns a list of all connections
     */
	public ArrayList<ConnectionData> readConnection(){
		ArrayList<ConnectionData> connections = new ArrayList<ConnectionData>();
		try{
		//create Document from file and get the root element
		SAXBuilder builder = new SAXBuilder(true);
		doc = builder.build(file);
		root = doc.getRootElement();
		} catch (Exception e) {
            return new ArrayList<ConnectionData>();
        }
		
		List<?> children = root.getChildren();
		
		for(int i = 0; i < children.size(); i++){
			Element connectiondata = ((Element) children.get(i));
			String stationA = connectiondata.getChildText(childStationATag);
			String stationB = connectiondata.getChildText(childStationBTag);
			connections.add(new ConnectionData(stationA, stationB));			
		}
		
		return connections;
	}
}
