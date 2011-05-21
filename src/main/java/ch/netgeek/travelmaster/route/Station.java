package ch.netgeek.travelmaster.route;

import java.util.ArrayList;

/**
 * Represents a station which is interconnected with other stations over 
 * connections.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class Station implements Comparable<Station> {

    // variables declaration
    private String name;
    private int xPos;
    private int yPos;
    private ArrayList<Connection> connections;

    /**
     * Requires a name, an x-position and an y-position coordinate
     * 
     * @param name          The name of the station
     */
    public Station(String name, int xPos, int yPos) {
        setName(name);
        setXPos(xPos);
        setYPos(yPos);
        connections = new ArrayList<Connection>();
    }
    
    /**
     * Sets the name of the station.
     * 
     * @param name          The name of the station
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the x-position of the station.
     * 
     * @param xPos          The x-position
     */
    public void setXPos(int xPos) {
        this.xPos = xPos;
    }
    
    /**
     * Sets the y-position of the station.
     * 
     * @param yPos          The y-position
     */
    public void setYPos(int yPos) {
        this.yPos = yPos;
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
     * Returns the x-position of the station.
     * 
     * @return              The x-position
     */
    public int getXPos() {
        return xPos;
    }
    
    /**
     * Returns the y-position of the station.
     * 
     * @return              The y-position
     */
    public int getYPos() {
        return yPos;
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
        if (xPos != other.getXPos()) {
            return false;
        }
        if (yPos != other.getYPos()) {
            return false;
        }
        return true;
    }

    /**
     * Compares the station with another station by name.
     * 
     * @param station       the other station
     * @return              
     */
    @Override
    public int compareTo(Station station) {
        int difference = 0;
        if (name == null) {
            difference = (station.getName() == null ? 0 : +1);
        } else {
            difference = (station.getName() == null ? -1
                    : name.compareTo(station.getName()));
        }
        if (difference != 0) return difference;
        if (xPos < station.getXPos()) return -1;
        if (xPos > station.getXPos()) return +1;
        if (yPos < station.getYPos()) return -1;
        if (yPos > station.getYPos()) return +1;
        return 0;
    }
}
