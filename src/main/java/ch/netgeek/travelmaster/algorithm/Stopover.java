package ch.netgeek.travelmaster.algorithm;

import java.util.Calendar;

import ch.netgeek.travelmaster.route.Connection;
import ch.netgeek.travelmaster.route.Line;
import ch.netgeek.travelmaster.route.Station;

/**
 * a setter and getter class which is required to add the information <br>
 * as an object to the ArrayList in RouteCalculator as well as information <br>
 * for the calculation of the journey
 * 
 * @author 		Ruben Knaus, Dieu P. Van
 * @version 	1.0, 22.04.2011
 */
public class Stopover {

	//variable declaration
	private Station station;
	private Connection connection;
	private Line line;
	private Calendar arrivalTime;
	private Calendar departureTime;
	private Calendar travelTime;
	
	/**
	 * @param station        the name of the station
	 * @param travelTime		  the duration of the journey
	 */
	public Stopover(Station station, Calendar travelTime){
		this.station = station;
		this.travelTime = travelTime;
	}
	
	/**
	 * @param connection     sets the connection from A to B
	 */
	public void setConnection(Connection connection){
		this.connection = connection;
	}
	
	/**
	 * @param line     sets the line
	 */
	public void setLine(Line line){
		this.line = line;
	}
	
	/**
	 * @param arrivalTime     sets the arrival time of the journey
	 */
	public void setArrivalTime(Calendar arrivalTime){
		this.arrivalTime = arrivalTime;
	}
	
	/**
	 * @param departureTime     sets the departure time of the journey
	 */
	public void setDepartureTime(Calendar departureTime){
		this.departureTime = departureTime;
	}
	
	/**
	 * @return station     get the name of the station
	 */
	public Station getStation(){
		return station;
	}
	
	/**
	 * @return connection     get the connection from A to B
	 */
	public Connection getConnection(){
		return connection;
	}
	
	/**
	 * @return line     get the line
	 */
	public Line getLine(){
		return line;
	}
	
	/**
	 * @return arrivalTime     get the arrival time of the journey
	 */
	public Calendar getArrivalTime(){
		return arrivalTime;
	}
	
	/**
	 * @return departureTime     get the departure time of the journey
	 */
	public Calendar getDepartureTime(){
		return departureTime;
	}
	
	/**
	 * @return travelTime     get the duration of the journey
	 */
	public Calendar getTravelTime(){
		return travelTime;
	}
}
