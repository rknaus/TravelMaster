package ch.netgeek.travelmaster.route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
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
    
    /**
     * Initializes the Transport Network
     */
    public TransportNetwork() {
        stations = new HashMap<String, Station>();
        connections = new HashMap<List<Station>, Connection>();
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
    	
    	Connection connection = connections.get(Arrays.asList(stationA, stationB));
        if (!(connections.containsKey(Arrays.asList(stationA, stationB)))) {
        	connection.setDuration(duration);
        }
        else{
            connection.setDuration(duration);
        	connections.put(Arrays.asList(stationA, stationB), connection);
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
        if(stations.get(name)==null){
        	return null;
        }
        else{
        	return stations.get(name);
        }
    }
    
    /**
     * Returns a list of all stations
     * 
     * @return                  The stations in an ArrayList
     */
    public ArrayList<Station> getStationList() {
    	
    	ArrayList<Station> stationList = new ArrayList<Station>();
    	
    	if(stations==null){
    		return null;
    	}
    	else{
    		for(Iterator<String> iter = stations.keySet().iterator(); iter.hasNext();){
    			String station = iter.next();
    			stationList.add(stations.get(station));
    		}
    		return stationList;
    	}
    }
    
    /**
     * Returns a list of all neighbor stations at a given station.
     * 
     * @param station           The given Station
     * @return                  The list of neighbor station
     */
    public ArrayList<Station> getNeighborStationList(Station station){
    	ArrayList<Station> neighborStationList = new ArrayList<Station>();
    	if(stations.get(station)==null){
    		return null;
    	}
    	else{
    		for(Iterator<String> iter = stations.keySet().iterator(); iter.hasNext();){
    			if(iter.equals(station)){
    				neighborStationList.add(stations.get(station));
    			}
    		}
    		return neighborStationList;
    	}
    }
}