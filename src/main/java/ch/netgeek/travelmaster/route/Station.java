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
        connections = new ArrayList<Connection>();
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

    /**
     * Overriding the equals method to compare stations with each other.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        Station other = (Station) object;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
