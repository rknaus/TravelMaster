package ch.netgeek.travelmaster.route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the transport network which includes stations and connections 
 * between the stations, lines which go from one station over several stations 
 * to an end station and the corresponding timetables for each line, station and
 * direction.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     1.0
 * 
 */
public class TransportNetwork {

	// variable declaration
	private HashMap<String, Station> stations;
	private HashMap<List<Station>, Connection> connections;
	private ArrayList<Line> lines;

	/**
	 * Initializes the transport network
	 */
	public TransportNetwork() {
		stations = new HashMap<String, Station>();
		connections = new HashMap<List<Station>, Connection>();
		lines = new ArrayList<Line>();
	}

	/**
	 * Adds a new station to the transport network.
	 * 
	 * @param name              The station name
	 */
	public void addStation(String name, int xPos, int yPos) {
		if (!(stations.containsKey(name))) {
			Station station = new Station(name, xPos, yPos);
			stations.put(name, station);
		}
	}

	/**
	 * Adds a new connection to the transport network. To add a new connection,
	 * both stations A and B must be initialized before. The connection also
	 * must be added to the station A and B.
	 * 
	 * @param stationA          The station A as Station
	 * @param stationB          The station B as Station
	 * @param duration          The travel duration between the two stations
	 */
	public void addConnection(Station stationA, Station stationB, int duration) {
		if (!(connections.containsKey(Arrays.asList(stationA, stationB))) && 
				!(connections.containsKey(Arrays.asList(stationB, stationA)))) {
			Connection connection = new Connection(stationA, stationB, duration);
			connections.put(Arrays.asList(stationA, stationB), connection);
			Station tmpStationA = getStation(stationA.getName());
			tmpStationA.addConnection(connection);
			Station tmpStationB = getStation(stationB.getName());
			tmpStationB.addConnection(connection);
		}
	}

	/**
	 * Adds a new line to the transport network. The containing stations and
	 * connections must already be initialized. The departures of the first and
	 * the last station are required to generate the timetable for the line.
	 * 
	 * @param number                    The line number as int
	 * @param type                      The line type as String
	 * @param stations                  The stations list in an ArrayList
	 * @param departuresFirstStation    The departures as Calendar objects for 
	 *                                  the first station in an ArrayList
	 * @param departuresLastStation     The departures as Calendar objects for 
	 *                                  the last station in an ArrayList
	 */
	public void addLine(int number, String type, ArrayList<Station> stations, 
			ArrayList<Calendar> departuresFirstStation, 
			ArrayList<Calendar> departuresLastStation) {        
		Line line = new Line(number, type);

		/*
		 * Checking if the Stations list of the line is valid according to the
		 * Stations and Connections already initialized.
		 */
		boolean stationsCorrect = true;

		// stations list must not be empty
		if (stations.size() == 0) {
			stationsCorrect = false;
		}  	
		Station lineStation = stations.get(0);
		Station station = getStation(stations.get(0).getName());

		// first Station must exist
		if ((lineStation.equals(station))) {

			/*
			 * Check if the rest of the stations exist and if they are connected 
			 * together like expected.
			 */
			for (int i = 1; i < (stations.size() - 1); i++) {
				Station lineNeighborStation = stations.get(i + 1);
				station = getStation(stations.get(i).getName());

				/* 
				 * all stations expect the last station must have the correct
				 * neighbor station.
				 */
				ArrayList<Station> neighborStationList = 
					getNeighborStationList(station);

				// Neighbor Stations must exist
				if (neighborStationList.size() == 0) {
					stationsCorrect = false;
					break;
				}
				boolean foundNeighbor = false;
				for (Station neighborStation : neighborStationList) {
					if (lineNeighborStation.equals(neighborStation)) {
						foundNeighbor = true;
					}
				}
				if (foundNeighbor == false) {
					stationsCorrect = false;
					break;
				}
			}
		} else {
			stationsCorrect = false;
		}

		/*
		 * If the stations of the line are all valid, the line can get added to
		 * the TransportNetwork.
		 */
		if (stationsCorrect) {

			// Adding the line to the lines list of TransportNetwork
			lines.add(line);

			/*
			 * Adding the line to the connections and calculating the timetable
			 * for each direction and connection
			 */
			ArrayList<Calendar> departuresD1 = departuresFirstStation;
			ArrayList<Calendar> departuresD2 = departuresLastStation;
			for (int i = 0; i < (stations.size() - 1); i++) {
				int j = stations.size() - 1 - i;

				// Defining stations and connection in direction one (D1)
				Station stationD1 = getStation(stations.get(i).getName());
				Station neighborStationD1 = getStation(stations.get(i + 1).getName());
				Connection connectionD1 = getConnection(stationD1, neighborStationD1);

				// Adding the timeTable for each connection in direction one (D1)
				for (int k = 0; k < departuresD1.size(); k++) {
					Calendar departure = Calendar.getInstance();
					int hoursOfDay = departuresD1.get(k).get(Calendar.HOUR_OF_DAY);
					int minutes = departuresD1.get(k).get(Calendar.MINUTE);
					departure.set(0, 0, 0, hoursOfDay, minutes);
					line.addDeparture(stationD1, neighborStationD1, 
							departure);

					/*
					 * adding the travel time to the departure which is the
					 * correct departure time for the next iteration
					 */
					departuresD1.get(k).add(Calendar.MINUTE, 
							connectionD1.getDuration());
				}

				// Defining stations and connection in direction two (D2)
				Station stationD2 = getStation(stations.get(j).getName());
				Station neighborStationD2 = 
					getStation(stations.get(j - 1).getName());
				Connection connectionD2 = 
					getConnection(stationD2, neighborStationD2);

				// Adding the timeTable for each connection in direction two (D2)
				for (int k = 0; k < departuresD2.size(); k++) {                   
					Calendar departure = Calendar.getInstance();
					int hoursOfDay = 
						departuresD2.get(k).get(Calendar.HOUR_OF_DAY);
					int minutes = departuresD2.get(k).get(Calendar.MINUTE);
					departure.set(0, 0, 0, hoursOfDay, minutes);
					line.addDeparture(stationD2, neighborStationD2, departure);

					/*
					 * adding the travel time to the departure which is the
					 * correct departure time for the next iteration
					 */
					departuresD2.get(k).add(Calendar.MINUTE, 
							connectionD2.getDuration());
				}

				// Adding line to the connection (only in one direction!)
				connectionD1.addLine(line);                
			}
		}
	}

	/**
	 * Returns a station with a given name. Returns null, if the station doesn't
	 * exist.
	 * 
	 * @param name              The station name
	 * @return                  The station object
	 */
	public Station getStation(String name) {
		return stations.get(name);
	}

	/**
	 * Returns a list of all stations
	 * 
	 * @return                  The stations in an ArrayList
	 */
	public ArrayList<Station> getStationList() {
		ArrayList<Station> stationList = new ArrayList<Station>();
		for (Station station : stations.values()) {
			stationList.add(station);
		}
		return stationList;
	}

	/**
	 * Returns a list of all neighbor stations at a given station.
	 * 
	 * @param station           The given Station
	 * @return                  The list of neighbor stations
	 */
	public ArrayList<Station> getNeighborStationList(Station station) {
		ArrayList<Station> neighborStationList = new ArrayList<Station>();
		ArrayList<Connection> connectionList = station.getConncections();
		for (Connection connection : connectionList) {
			neighborStationList.add(connection.getNeighborStation(station));
		}
		return neighborStationList;
	}

	/**
	 * Returns a Connection with given stations A and B. Returns null, if the 
	 * connection doesn't exist.
	 * 
	 * @param stationA          The stationA
	 * @param stationB          The stationB
	 * @return                  The connection object
	 */
	public Connection getConnection(Station stationA, Station stationB) {
		Connection connection = 
			connections.get(Arrays.asList(stationA, stationB));        
		if(connection == null) {
			connection = connections.get(Arrays.asList(stationB, stationA));
		}
		return connection;
	}

	/**
	 * Returns a list of all connections
	 * 
	 * @return                  The connections in an ArrayList
	 */
	public ArrayList<Connection> getConnectionList() {
		ArrayList<Connection> connectionList = new ArrayList<Connection>();
		for (Connection connection : connections.values()) {
			connectionList.add(connection);
		}
		return connectionList;
	}

	/**
	 * Returns a list of all lines.
	 * 
	 * @return                  The list of lines
	 */
	public ArrayList<Line> getLines() {
		return lines;
	}

	/**
	 * Returns a departure list of a station to be displayed in the gui.
	 * 
	 * @param sourceName        The source station name
	 * @param destinationName   The destination station name
	 * @return                  The list of departure items
	 */
	public ArrayList<DepartureItem> getDepartures(
			String sourceName, String destinationName) {
		Station source = getStation(sourceName);
		Station destination = getStation(destinationName);
		ArrayList<DepartureItem> departureItems = new ArrayList<DepartureItem>();

		// returns an empty departure item list if the station name doesn't exist
		if (source == null || destination == null) {
			return departureItems;
		}    
		Connection connection = getConnection(source, destination);
		ArrayList<Line> lines = connection.getLines();

		// iterates over all lines to get time table from each lines
		for (Line line : lines) {
			TimeTable timeTable = line.getTimeTable(source, destination);
			ArrayList<Calendar> departures = timeTable.getDepartures();

			/*
			 * iterates over all departure items to insert them sorted into the
			 * departure item list
			 */
			for (Calendar departure : departures) {
				int hour = departure.get(Calendar.HOUR_OF_DAY);
				int minute = departure.get(Calendar.MINUTE);
				DepartureItem departureItem = new DepartureItem(hour, minute, 
						line.getNumber());

				// if the departure item list is empty
				if (departureItems.size() == 0) {
					departureItems.add(departureItem);
					continue;
				}
				boolean added = false;

				// adds the departure item to the correct position
				for (int i = 0; i < departureItems.size(); i++) {
					if (departureItem.compareTo(departureItems.get(i)) <= 0) {
						departureItems.add(i, departureItem);
						added = true;
						break;
					}
				}

				// if not added, add to the end
				if (added == false) {
					departureItems.add(departureItem);
				}
			}
		}      
		return departureItems;
	}
}
