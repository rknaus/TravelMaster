package ch.netgeek.travelmaster.route;

import java.util.ArrayList;

/**
 * Represents a connection between two stations.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     1.0
 * 
 */
public class Connection {

	// variable declaration
	private Station stationA;
	private Station stationB;
	private int duration;
	private ArrayList<Line> lines;

	/**
	 * Requires two stations which should be interconnected (nodes) and a 
	 * duration (weight of the connection in minutes).
	 * 
	 * @param stationA          The instance of station a
	 * @param stationB          The instance of station b
	 * @param duration          The travel duration between station a and b
	 */
	public Connection(Station stationA, Station stationB, int duration) {
		setStationA(stationA);
		setStationB(stationB);
		setDuration(duration);
		lines = new ArrayList<Line>();
	}

	/**
	 * Sets the station a to another value.
	 * 
	 * @param station           The instance of station a
	 */
	public void setStationA(Station station) {
		stationA = station;
	}

	/**
	 * Sets the station b to another value.
	 * 
	 * @param station           The instance of station b
	 */
	public void setStationB(Station station) {
		stationB = station;
	}

	/**
	 * Returns the station a.
	 * 
	 * @return                  The instance of station a
	 */
	public Station getStationA() {
		return stationA;
	}

	/**
	 * Returns the station b.
	 * 
	 * @return                  The instance of station b
	 */
	public Station getStationB() {
		return stationB;
	}

	/**
	 * Returns the neighbor station of the given station. If the given station
	 * doesn't exist in the connection, null gets returned.
	 * 
	 * @param station           The given station
	 * @return                  The instance of the neighbor station
	 */
	public Station getNeighborStation(Station station) {
		if (station.equals(stationA)) {
			return stationB;
		} else if (station.equals(stationB)) {
			return stationA;
		}
		return null;
	}

	/**
	 * Sets the travel duration (Minutes) to another value.
	 * 
	 * @param duration          The travel duration between two stations
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Returns the travel duration (Minutes).
	 * @return                  The travel duration between two stations
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Returns all lines going via the current connection.
	 * 
	 * @return                  The lines as ArrayList
	 */
	public ArrayList<Line> getLines() {
		return lines;
	}

	/**
	 * Adds a new line to the connection.
	 * 
	 * @param line              The line instance getting added to the connection
	 */
	public void addLine(Line line) {
		lines.add(line);
	}
}
