package ch.netgeek.travelmaster.io;

/**
 * A setter and getter class which is required to add the information as an 
 * object to the ArrayList.
 * 
 * @author 		Ruben Knaus, Dieu P. Van
 * @version 	1.0
 */
public class StationData {

    // variable declaration
    private String name;
    private int xPos;
    private int yPos;

    /**
     * Initializes the StationData object
     * 
     * @param name          the name of the station
     * @param xPos          the x coordinate
     * @param yPos          the y coordinate
     */
    public StationData(String name, int xPos, int yPos) {
        setName(name);
        setXPos(xPos);
        setYPos(yPos);
    }

    /**
     * Sets the name of the sation.
     * 
     * @param name          the name of the station
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the x coordinate of the station.
     * 
     * @param xPos         the x coordinate
     */
    private void setXPos(int xPos) {
        this.xPos = xPos;
    }

    /**
     * Sets the y coordinate of the station.
     * 
     * @param yPos          the y coordinate
     */
    private void setYPos(int yPos) {
        this.yPos = yPos;
    }

    /**
     * Returns the name of the Station.
     * 
     * @return              the name of the station
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the x coordinate of the station.
     * 
     * @return              the x coordinate
     */
    public int getXPos() {
        return xPos;
    }

    /**
     * Returns the y coordinate of the station.
     * 
     * @return             the y coordinate
     */
    public int getYPos() {
        return yPos;
    }
}