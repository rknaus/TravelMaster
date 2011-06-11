package ch.netgeek.travelmaster.io;

/**
 * A setter and getter class which is required to add the connection information
 * as an object to an array list.
 * 
 * @author 		Ruben Knaus, Dieu P. Van
 * @version 	1.0
 */
public class ConnectionData {

    // variable declaration
    private String stationA;
    private String stationB;
    private int duration;

    /**
     * Initializes the Connection Data object.
     * 
     * @param stationA          the name of the current station
     * @param stationB          the name of the destination
     */
    public ConnectionData(String stationA, String stationB, int duration) {
        setStationA(stationA);
        setStationB(stationB);
        setDuration(duration);
    }

    /**
     * Sets the name of station a.
     * 
     * @param stationA          the name of station a
     */
    public void setStationA(String stationA) {
        this.stationA = stationA;
    }

    /**
     * Returns the name of station a.
     * 
     * @return                  the name of station a
     */
    public String getStationA() {
        return stationA;
    }

    /**
     * Sets the name of station b.
     * 
     * @param                   the name of station b
     */
    public void setStationB(String stationB) {
        this.stationB = stationB;
    }

    /**
     * Returns the name of station b.
     * 
     * @return                  the name of station b
     */
    public String getStationB() {
        return stationB;
    }

    /**
     * Sets the tavel duration of the connection. 
     * 
     * @param duration          the travel duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns the travel duration of the connection.
     * 
     * @return                  the travel duration
     */
    public int getDuration() {
        return duration;
    }
}