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
	private String station;
	
	/**
	 * @param station     the name of the station
	 */
	public StationData(String station){
		this.station = station;
	}
	
	/**
	 * @param station     sets the name of the sation
	 */
	public void setStation(String station){
		this.station = station;
	}
	
	/**
	 * @return station     the name of the station
	 */
	public String getStation(){
		return station;
	}
}
