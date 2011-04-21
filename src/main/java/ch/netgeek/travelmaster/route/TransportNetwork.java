package ch.netgeek.travelmaster.route;

import java.util.ArrayList;
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
        
    }
    
    /**
     * Returns a Station with a given name. Returns null, if the station doesn't
     * exist.
     * 
     * @param name              The station name
     * @return                  The station object
     */
    public Station getStation(String name) {
        return null;
    }
    
    /**
     * Returns a list of all stations
     * 
     * @return                  The stations in an ArrayList
     */
    public ArrayList<Station> getStationList() {
        return null;
    }
    
    /**
     * Returns a list of all neighbor stations at a given station.
     * 
     * @param station           The given Station
     * @return                  The list of neighbor station
     */
    public ArrayList<Station> getNeighborStationList(Station station) {
        return null;
    }
}
