package ch.netgeek.travelmaster.io;

/**
 * a setter and getter class which is required to add the information <br>
 * as an object to the ArrayList
 * 
 * @author 		Ruben Knaus, Dieu P. Van
 * @version 	1.0, 19.04.2011
 */
public class StationData {

	//variable declaration
	private String name;
	private int xPos;
	private int yPos;
	
	/**
	 * @param station     the name of the station
	 */
	public StationData(String name, int xPos, int yPos) {
	    setName(name);
	    setXPos(xPos);
	    setYPos(yPos);
	}

    /**
     * Sets the name of the sation.
     * 
	 * @param name         The name of the station
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the xPos of the station.
	 * 
	 * @param xPos         The xPos as integer
	 */
    private void setXPos(int xPos) {
        this.xPos = xPos;
    }
    
    /**
     * Sets the yPos of the station.
     * 
     * @param yPos          The yPos as integer
     */
    private void setYPos(int yPos) {
        this.yPos = yPos;
    }
	
	/**
	 * Returns the name of the Station.
	 * 
	 * @return             The name of the station
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the xPos of the station.
	 * 
	 * @return             The xPos as integer
	 */
	public int getXPos() {
	    return xPos;
	}
	
	/**
     * Returns the yPos of the station.
     * 
     * @return             The yPos as integer
     */
    public int getYPos() {
        return yPos;
    }
}
