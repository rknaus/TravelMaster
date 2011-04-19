package ch.netgeek.travelmaster.route;

public class ConnectionData {

	private String stationA;
	private String stationB;
	
	public ConnectionData(String stationA, String stationB){
		this.stationA = stationA;
		this.stationB = stationB;
	}
	
	public void setStationA(String stationA){
		this.stationA = stationA;
	}
	
	public String getStationA(){
		return stationA;
	}
	
	public void setStationB(String stationB){
		this.stationB = stationB;
	}
	
	public String getStationB(){
		return stationB;
	}
}
