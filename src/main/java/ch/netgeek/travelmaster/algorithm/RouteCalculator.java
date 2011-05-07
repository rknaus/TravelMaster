package ch.netgeek.travelmaster.algorithm;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import ch.netgeek.travelmaster.route.Station;
import ch.netgeek.travelmaster.route.Connection;
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
    private Map<Station, Calendar> shortestArrivalTime;
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
    public void RouceCalculator(TransportNetwork transportNetwork) {
        this.transportNetwork = transportNetwork;
        
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
        shortestArrivalTime = new HashMap<Station, Calendar>();
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
        initCalculator(source, departure);
        
        // the current station
        Station station;
        
        while ((station = unsettledStations.poll()) != null) {
            
            // station must not be settled already
            assert !isSettled(station);
            
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
            updateNeighbors(station);
            
        }
        
        //TODO Return correct ArrayList of Stopovers here!!
		return null;
	}
    
    private void updateNeighbors(Station station) {
        
        // iterating over all neighbors of the station
        for (Station neighbor : transportNetwork.getNeighborStationList(station)) {
            
            // skip if the station got already settled
            if (isSettled(neighbor)) {
                continue;
            }
            
            /*
             * Add travelling time for each neighbor station to the shortest
             * travel time map.
             */
            
            
            //TODO CONTINUE HERE - This function is not finished yet!
            // int travelTime = getShortestTravelTime(station) + 
        }
        
    }

    /**
     * Initializes the dijkstra calculator.
     * 
     * @param source                The source station
     */
    private void initCalculator(Station source, Calendar departure) {
        settledStations.clear();
        unsettledStations.clear();
        shortestTravelTime.clear();
        predecessors.clear();
        
        //TODO CONTINUE HERE - This function is not finished yet!
        // Adding the source station
        //setShortestTravelTime(source, 0);
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
        unsettledStations.remove(station);
        shortestTravelTime.put(station, travelTime);
        unsettledStations.add(station);
    }

    /**
     * Returns the shortest travel time for a given station.
     * 
     * @param station               The station
     * @return                      The time in minutes
     */
    public int getShortestTravelTime(Station station) {
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
    public Station getNextStation() {
        return unsettledStations.poll();
    }
}
