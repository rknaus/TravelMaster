package ch.netgeek.travelmaster.algorithm;
import java.util.ArrayList;
import java.util.Calendar;

import ch.netgeek.travelmaster.route.Station;
import ch.netgeek.travelmaster.route.Connection;

public class RouteCalculator {
	
	private int shortDur;
	private int[] u;
	private int numberOfConnections;
	
	public ArrayList<Stopover> calculateRoute(ArrayList<Station> stations,
			Station source, Station destination, Calendar departure){
		
		u = new int[4];
		for(int i = 0; i < stations.size(); i++){
			ArrayList<Connection> connections = stations.get(i).getConncections();
			numberOfConnections = 
				connections.size();
			u = new int[numberOfConnections];
			for(int i2 = 0; i2 < u.length; i2++){

			}
			
			
			
			
		}
		
		return null;
	}
}
