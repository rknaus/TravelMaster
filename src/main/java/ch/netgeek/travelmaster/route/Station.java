package ch.netgeek.travelmaster.route;

import java.util.ArrayList;

/**
 * Represents a station which is interconnected with other stations over 
 * connections.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class Station {

    // variables declaration
    private String name;
    private ArrayList<Connection> connections;

    /**
     * Requires a name which should be unique and should stay the permanently 
     * the same (HashCode).
     * 
     * @param name          The name of the station
     */
    public Station(String name) {
        this.name = name;
    }

    /**
     * Returns the station name.
     * 
     * @return              The name of the station
     */
    public String getName(){
        return name;
    }

    /**
     * Returns all containing connections.
     * 
     * @return              An ArrayList of all connections
     */
    public ArrayList<Connection> getConncections() {
        return connections;
    }

    /**
     * Adds a connection to the station.
     * 
     * @param connection    Connects the station with another one
     */
    public void addConnection(Connection connection) {
        connections.add(connection);
    }
}
