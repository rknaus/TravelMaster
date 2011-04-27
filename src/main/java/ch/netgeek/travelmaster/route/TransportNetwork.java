package ch.netgeek.travelmaster.route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the transport network which includes stations, connections between
 * stations, lines which go from one station over several stations to an end
 * station and the corresponding timetables for each line, station and
 * direction.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class TransportNetwork {

    // variables declaration
    private HashMap<String, Station> stations;
    private HashMap<List<Station>, Connection> connections;
    private ArrayList<Line> lines;

    /**
     * Initializes the Transport Network
     */
    public TransportNetwork() {
        stations = new HashMap<String, Station>();
        connections = new HashMap<List<Station>, Connection>();
        lines = new ArrayList<Line>();
    }

    /**
     * Adds a new Station to the transport network.
     * 
     * @param name              The station name
     */
    public void addStation(String name) {
        if (!(stations.containsKey(name))) {
            Station station = new Station(name);
            stations.put(name, station);
        }
    }

    /**
     * Adds a new Connection to the transport network. To add a new connections,
     * both stations A and B must be initialized already.
     * 
     * @param stationA          The station A as Station
     * @param stationB          The station B as Station
     * @param duration          The travel duration between the two stations
     */
    public void addConnection(Station stationA, Station stationB, int duration) {
        if (!(connections.containsKey(Arrays.asList(stationA, stationB))) && 
                !(connections.containsKey(Arrays.asList(stationB, stationA)))) {
            Connection connection = new Connection(stationA, stationB, duration);
            connections.put(Arrays.asList(stationA, stationB), connection);
        }
    }

    /**
     * Adds a new Line to the transport network. The containing stations and
     * connections must already be initialized. The departures of the first and
     * the last station are required to generate the timetable for the line.
     * 
     * @param number                    The line number as int
     * @param type                      The line type as String
     * @param stations                  The stations list in an ArrayList
     * @param departuresFirstStation    The departures as Calendar objects for 
     *                                  the first station in an ArrayList
     * @param departuresLastStation     The departures as Calendar objects for 
     *                                  the last station in an ArrayList
     */
    public void addLine(int number, String type, ArrayList<Station> stations, 
            ArrayList<Calendar> departuresFirstStation, 
            ArrayList<Calendar> departuresLastStation) {
        
    	Line line = new Line(number, type);
    	TransportNetwork tn = new TransportNetwork();
     	
    	for(Station station : stations){
    		for(Station neighbor : tn.getNeighborStationList(station)){   			
    			if(neighbor != null){
    				continue;
    			}
    			else{
    				break;
    			}
    		}
    	}
    	
    	for(Calendar departure : departuresFirstStation){
    		for(int i = 0; i < (stations.size()-1); i++){
    			Station stationA = tn.getConnection(stations.get(i), 
    					stations.get(i+1)).getStationA();
    			Station stationB = tn.getConnection(stations.get(i), 
    					stations.get(i+1)).getStationB();
    			int duration = connections.get(
    					Arrays.asList(stationA, stationB)).getDuration();
    			
    			line.addDeparture(stations.get(i), stations.get(i+1), departure);
    			lines.add(line);
    			departure.add(Calendar.MINUTE, duration);  
 		
//    			for(Connection connection : connections.values()){
//    				if(connection.getStationA().equals(stations.get(i))
//    						&& connection.getStationB().equals(stations.get(i+1))
//    						|| connection.getStationA().equals(stations.get(i+1)) 
//    						&& connection.getStationB().equals(stations.get(i))){
//        	    		line.addDeparture(stations.get(i), stations.get(i++), 
//        	    				departure);
//    					departure.add(Calendar.MINUTE, connection.getDuration());
//    				}
//    				else{
//    					continue;
//    				}
//    	    		lines.add(line);
//    			}	
    		}
    	}
    	
    	for(Calendar departure : departuresLastStation){
    		for(int i = (stations.size()-1); i > 0; i--){
    			Station stationA = tn.getConnection(stations.get(i), 
    					stations.get(i-1)).getStationA();
    			Station stationB = tn.getConnection(stations.get(i), 
    					stations.get(i-1)).getStationB();
    			int duration = connections.get(
    					Arrays.asList(stationA, stationB)).getDuration();
    			
    			line.addDeparture(stations.get(i), stations.get(i-1), departure);
    			lines.add(line);
    			departure.add(Calendar.MINUTE, duration);
    			
//    			for(Connection connection : connections.values()){
//    				if(connection.getStationA().equals(stations.get(i)) 
//    						&& connection.getStationB().equals(stations.get(i-1))
//    						|| connection.getStationA().equals(stations.get(i-1)) 
//    						&& connection.getStationB().equals(stations.get(i))){
//        	    		line.addDeparture(stations.get(i), stations.get(i--), 
//        	    				departure);
//    					departure.add(Calendar.MINUTE, connection.getDuration());
//    				}
//    				else{
//    					continue;
//    				}
//    	    		lines.add(line);
//    			}	
    		}
    	}
    }
    
    /**
     * Returns a Station with a given name. Returns null, if the station doesn't
     * exist.
     * 
     * @param name              The station name
     * @return                  The station object
     */
    public Station getStation(String name) {
        return stations.get(name);
    }

    /**
     * Returns a list of all stations
     * 
     * @return                  The stations in an ArrayList
     */
    public ArrayList<Station> getStationList() {
        ArrayList<Station> stationList = new ArrayList<Station>();
        for (Station station : stations.values()) {
            stationList.add(station);
        }
        return stationList;
    }

    /**
     * Returns a list of all neighbor stations at a given station.
     * 
     * @param station           The given Station
     * @return                  The list of neighbor stations
     */
    public ArrayList<Station> getNeighborStationList(Station station) {
        ArrayList<Station> neighborStationList = new ArrayList<Station>();
        ArrayList<Connection> connectionList = station.getConncections();
        for (Connection connection : connectionList) {
            neighborStationList.add(connection.getNeighborStation(station));
        }
        return neighborStationList;
    }
    
    /**
     * Returns a Connection with given stations A and B. Returns null, if the 
     * connection doesn't exist.
     * 
     * @param stationA          The stationA
     * @param stationB          The stationB
     * @return                  The connection object
     */
    public Connection getConnection(Station stationA, Station stationB) {
        Connection connection = connections.get(Arrays.asList(stationA, stationB));
        
        if(connection == null){
        	connection = connections.get(Arrays.asList(stationB, stationA));
        }
    	return connection;
    }
    
    /**
     * Returns a list of all connections
     * 
     * @return                  The connections in an ArrayList
     */
    public ArrayList<Connection> getConnectionList() {
        ArrayList<Connection> connectionList = new ArrayList<Connection>();
        for (Connection connection : connections.values()) {
            connectionList.add(connection);
        }
        return connectionList;
    }
    
    /**
     * Returns a list of all lines.
     * 
     * @return                  The list of lines
     */
    public ArrayList<Line> getLines() {
        return lines;
    }
}
