package ch.netgeek.travelmaster.algorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import ch.netgeek.travelmaster.route.Line;
import ch.netgeek.travelmaster.route.Station;
import ch.netgeek.travelmaster.route.Connection;
import ch.netgeek.travelmaster.route.TimeTable;
import ch.netgeek.travelmaster.route.TransportNetwork;

/**
 * This class implements the Dijkstra algorithm to calculate the best time table
 * between two stations.
 *
 * @author      Ruben Knaus, Dieu P. Van
 * @version     0.1
 *
 */
public class RouteCalculator {
    
    // class variables declaration
    
    /**
     * Represents the traveling time for all unvisited or unreachable stations.
     */
    public static final int INFINITE_TRAVEL_TIME = Integer.MAX_VALUE;
    
    // object variables declaration
    /**
     * The transport network with all the stations, connection, lines and time
     * tables for each line and connection.
     */
    private TransportNetwork transportNetwork;
    
    /**
     * A chronological list from the source station to the destination station
     * with all time table information to get displayed.
     */
    private ArrayList<Stopover> timeTable;
    
    /**
     * Compares the travel time between two stations
     */
    private Comparator<Station> shortestTravelTimeComparator;
    
    /**
     * All visited stations
     */
    private Set<Station> settledStations;
    
    /**
     * All not visited stations
     */
    private PriorityQueue<Station> unsettledStations;
    
    /**
     * A map of the shortest travel time to each station
     */
    private Map<Station, Integer> shortestTravelTime;
    
    /**
     * A map of the shortest arrival time of each station
     */
    private Map<List<Station>, Calendar> shortestArrivalTime;
    
    /**
     * A map of the shortest departure time of each station
     */
    private Map<List<Station>, Calendar> shortestDepartureTime;
    
    /**
     * The best line of a station to the next station
     */
    private Map<List<Station>, Line> bestLine;
    
    /**
     * A map of the predecessor of each station
     */
    private Map<Station, Station> predecessors;
    
    /**
     * Initializes the RouteCalculator object.<br>
     * All instance objects get initialized
     * 
     * @param transportNetwork      The instance of the transport network with
     *                              all stations.
     */
    public RouteCalculator(TransportNetwork transportNetwork) {
        this.transportNetwork = transportNetwork;
        timeTable = new ArrayList<Stopover>();
        
        /*
         * This comparator compares a station with another one.
         * If the the travelling time to station b is longer than the travelling
         * time to the station a it returns -1, other wise it returns 1.
         * If the travelling time is equal, then it compares the name of the
         * stations and returns either -1 or 1 too.
         * Both stations can not have the same name.
         */
        shortestTravelTimeComparator = new Comparator<Station>() {

            @Override
            public int compare(Station stationA, Station stationB) {
                int shortestTravelTimeA = getShortestTravelTime(stationA);
                int shortestTravelTimeB = getShortestTravelTime(stationB);
                if (shortestTravelTimeA > shortestTravelTimeB) {
                    return 1;
                } else if (shortestTravelTimeA < shortestTravelTimeB) {
                    return -1;
                } else {
                    return stationA.compareTo(stationB);
                }
            }
            
        };
        settledStations = new HashSet<Station>();
        unsettledStations = new PriorityQueue<Station>(
                transportNetwork.getStationList().size(), shortestTravelTimeComparator);
        shortestTravelTime = new HashMap<Station, Integer>();
        shortestArrivalTime = new HashMap<List<Station>, Calendar>();
        shortestDepartureTime = new HashMap<List<Station>, Calendar>();
        bestLine = new HashMap<List<Station>, Line>();
        predecessors = new HashMap<Station, Station>();
    }

    /**
     * Calculates the best time table for a given source and destination and a 
     * given departure time.
     * 
     * @param source                The source station
     * @param destination           The destination station
     * @param departure             The departure time
     * @return                      A the time table as a list of stop overs
     */
    public ArrayList<Stopover> calculateRoute(Station source, Station destination,
            Calendar departure) {
        
        // initializing the calculator
        initCalculator(source);
        
        // the current station
        Station station;
        
        while ((station = getNextStation()) != null) {
            
            // if the destination is reached, stop here
            if (station.equals(destination)) {
                break;
            }
            
            // add station to the settled stations list
            settledStations.add(station);
            
            /*
             * Compute new shortest travel time for neighbor stations and update
             * if a shorter travel time is found.
             */
            updateNeighbors(station, departure);
            
        }
        
		return generateStopoverList(source, destination);
	}
    
    private ArrayList<Stopover> generateStopoverList(Station source, Station destination) {
        
        // Checking if there is a path from the destination to the start
        boolean validPath = false;
        Station tmpStation = destination;
        while ((tmpStation = predecessors.get(tmpStation)) != null) {
            if (tmpStation.equals(source)) {
                validPath = true;
                break;
            }
        }
        
        // Return null if the stations aren't connected (directly or indirect)
        if (validPath == false) {
            return null;
        }
        
        // Puts a list with all stopovers together
        ArrayList<Stopover> stopoverList = new ArrayList<Stopover>();
        Station tmpNeighbor = destination;
        while (predecessors.get(tmpNeighbor) != null) {
            tmpStation = predecessors.get(tmpNeighbor);
            Connection connection = transportNetwork.getConnection(tmpStation, 
                    tmpNeighbor);
            Line line = bestLine.get(Arrays.asList(tmpStation, tmpNeighbor));
            Calendar departureTime = shortestDepartureTime.get(Arrays.asList(tmpStation, tmpNeighbor));
            Calendar arrivalTime = shortestArrivalTime.get(Arrays.asList(tmpStation, tmpNeighbor));
            int travelDuration = getShortestTravelTime(tmpNeighbor);
            Stopover stopover = new Stopover(tmpStation, tmpNeighbor, 
                    connection, line, departureTime, arrivalTime, travelDuration);
            stopoverList.add(0, stopover);
            tmpNeighbor = tmpStation;
            
        }
        return stopoverList;
    }

    /**
     * Updates the neighbor stations of station with the current shortest travel
     * time.
     * 
     * @param station
     */
    private void updateNeighbors(Station station, Calendar departure) {
        
        // Shortest travelling time to station
        int stationTravelTime = shortestTravelTime.get(station);
        
        // iterating over all neighbors of the station and update the values
        for (Station neighbor : transportNetwork.getNeighborStationList(station)) {
            
            // skip if the station got already settled
            if (isSettled(neighbor)) {
                continue;
            }

            // Connection to the neighbor station
            Connection connection = transportNetwork.getConnection(station, neighbor);
            
            // If there is no connection between the stations, go to next
            if (connection == null) {
                continue;
            }
            
            // Earliest departure time at station
            Calendar earliestDeparture = Calendar.getInstance();
            earliestDeparture.set(Calendar.HOUR_OF_DAY, departure.get(Calendar.HOUR_OF_DAY));
            earliestDeparture.set(Calendar.MINUTE, departure.get(Calendar.MINUTE));
            earliestDeparture.add(Calendar.MINUTE, stationTravelTime);

            /*
             * Travelling duration from departure to arrival from station to 
             * neighbor.
             */
            int duration = connection.getDuration();

            // Shortest departure time from station to neighbor
            Calendar stationDepartureTime = null;
            
            /*
             * Travelling duration from arrival time at station to arrival time 
             * of neighbor.
             */
            int neighborTravelTime = Integer.MAX_VALUE;
            
            // Arrival time at the neighbor station
            Calendar neighborArrivalTime = null;
            
            // Line with the best connection to the neighbor
            Line line = null;
            
            /*
             * Iterating over all lines between the two stations to find the
             * next departure and the line belonging to the next departure.
             */
            for (Line tmpLine : connection.getLines()) {
                TimeTable timeTable = tmpLine.getTimeTable(station, neighbor);
                
                // Departure at station
                Calendar tmpStationDepartureTime = timeTable.getNextDeparture(earliestDeparture);
                
                // Arrival at neighbor
                Calendar tmpNeighborArrivalTime = Calendar.getInstance();
                tmpNeighborArrivalTime.set(0, 0, 0, 
                        tmpStationDepartureTime.get(Calendar.HOUR_OF_DAY), 
                        tmpStationDepartureTime.get(Calendar.MINUTE));
                tmpNeighborArrivalTime.add(Calendar.MINUTE, duration);
                
                /*
                 * Computing travelling duration from station arrival until
                 * neighbor arrival
                 */
                int hours = 
                    (tmpNeighborArrivalTime.get(Calendar.HOUR_OF_DAY) 
                            - earliestDeparture.get(Calendar.HOUR_OF_DAY));
                int minutes = 
                    (tmpNeighborArrivalTime.get(Calendar.MINUTE)
                            - earliestDeparture.get(Calendar.MINUTE));
                if (hours < 0) {
                    hours = hours + 24;
                }
                if (minutes < 0) {
                    minutes = minutes + 60;
                }
                int tmpNeighborTravelTime = stationTravelTime + hours * 60 + minutes;
                
                // If a shorter travel time has been found
                if (tmpNeighborTravelTime < neighborTravelTime) {
                    stationDepartureTime = tmpStationDepartureTime;
                    neighborTravelTime = tmpNeighborTravelTime;
                    neighborArrivalTime = tmpNeighborArrivalTime;
                    line = tmpLine;
                }
            }
            
            // When found a line with a departure to the neighbor
            if (neighborTravelTime != Integer.MAX_VALUE) {
                int shortestTravelTime = getShortestTravelTime(neighbor);
                
                // If the travel time got shorter, update the values
                if (shortestTravelTime > neighborTravelTime) {
                    setShortestTravelTime(neighbor, neighborTravelTime);
                    shortestArrivalTime.put(Arrays.asList(station, neighbor), neighborArrivalTime);
                    shortestDepartureTime.put(Arrays.asList(station, neighbor), stationDepartureTime);
                    bestLine.put(Arrays.asList(station, neighbor), line);
                    predecessors.put(neighbor, station);
                }
            }
        }
        
    }

    /**
     * Initializes the dijkstra calculator.
     * 
     * @param source                The source station
     */
    private void initCalculator(Station source) {
        timeTable.clear();
        settledStations.clear();
        unsettledStations.clear();
        shortestTravelTime.clear();
        shortestArrivalTime.clear();
        shortestDepartureTime.clear();
        bestLine.clear();
        predecessors.clear();
        setShortestTravelTime(source, 0);
        unsettledStations.add(source);
    }
    
    /**
     * Checks if a station already got settled by the dijkstra algorithm
     * 
     * @param station               The station to check
     * @return                      true if already settled
     */
    private boolean isSettled(Station station) {
        return settledStations.contains(station);
    }
    
    /**
     * Sets the shortest travel time to the appropriate value
     * 
     * @param station               The station
     * @param travelTime            The travel time
     */
    private void setShortestTravelTime(Station station, int travelTime) {
        
        /*
         * Removing station from the unsettled Stations list, because the order
         * could be different after setting the travel time 
         */
        unsettledStations.remove(station);
        
        // setting the shortest travel time
        shortestTravelTime.put(station, travelTime);
        
        // Adding the station again into the unsettled stations list
        unsettledStations.add(station);
    }

    /**
     * Returns the shortest travel time for a given station.
     * 
     * @param station               The station
     * @return                      The time in minutes
     */
    private int getShortestTravelTime(Station station) {
        Integer duration = shortestTravelTime.get(station);
        if (duration == null) {
            return INFINITE_TRAVEL_TIME;
        } else {
            return duration;
        }
    }
    
    /**
     * Gets the next station to visit (with the next lowest travel time)
     * 
     * @return                      The next station
     */
    private Station getNextStation() {
        return unsettledStations.poll();
    }
}
