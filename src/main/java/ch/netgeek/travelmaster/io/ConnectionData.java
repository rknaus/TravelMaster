package ch.netgeek.travelmaster.io;

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
	private int duration;
	
	/**
	 * @param stationA        the name of the current station
	 * @param stationB		  the name of the destination
	 */
	public ConnectionData(String stationA, String stationB, int duration){
		this.stationA = stationA;
		this.stationB = stationB;
		this.duration = duration;
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
	
	/**
	 * @param duration     sets the journey duration between the stations
	 */
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	/**
	 * @return duration     gets the the journey duration between the stations
	 */
	public int getDuration(){
		return duration;
	}
}
