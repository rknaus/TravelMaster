package ch.netgeek.travelmaster.route;

/**
 * a setter and getter class which is required to add the information <br>
 * as an object to the ArrayList
 * 
 * @author 		Ruben Knaus, Dieu P. Van
 * @version 	1.0, 19.04.2011
 */
public class ConnectionData {

	//variable declaration
	private String stationA;
	private String stationB;
	
	/**
	 * @param stationA        the name of the current station
	 * @param stationB		  the name of the destination
	 */
	public ConnectionData(String stationA, String stationB){
		this.stationA = stationA;
		this.stationB = stationB;
	}
	
	/**
	 * @param stationA     sets the name of the current station
	 */
	public void setStationA(String stationA){
		this.stationA = stationA;
	}
	
	/**
	 * @return stationA     gets the name of the current station
	 */
	public String getStationA(){
		return stationA;
	}

	/**
	 * @param stationB     sets the name of the destination
	 */
	public void setStationB(String stationB){
		this.stationB = stationB;
	}

	/**
	 * @return stationB     gets the name of the destination
	 */
	public String getStationB(){
		return stationB;
	}
}
