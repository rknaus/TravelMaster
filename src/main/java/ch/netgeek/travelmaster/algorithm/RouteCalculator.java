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

    // class variable declaration
    
    /**
     * Represents the travel time for all unvisited or unreachable stations.
     */
    public static final int INFINITE_TRAVEL_TIME = Integer.MAX_VALUE;

    // object variable declaration
    
    /**
     * The transport network with all the stations, connection, lines and time
     * tables for each line and connection
     */
    private TransportNetwork transportNetwork;

    /**
     * A chronological list with the calculated time table from the source 
     * station to the destination station
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
     * All unvisited stations
     */
    private PriorityQueue<Station> unsettledStations;

    /**
     * A map of the shortest travel time from the source station to each station
     */
    private Map<Station, Integer> shortestTravelTime;

    /**
     * A map of the shortest arrival time from the source station at each station
     */
    private Map<List<Station>, Calendar> shortestArrivalTime;

    /**
     * A map of the shortest departure time after the arrival time at each station
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
     * All instance variables get initialized
     * 
     * @param transportNetwork      The instance of the transport network with
     *                              all stations.
     */
    public RouteCalculator(TransportNetwork transportNetwork) {
        this.transportNetwork = transportNetwork;
        timeTable = new ArrayList<Stopover>();

        /*
         * This comparator compares a station with another one.
         * If the the travel time to station B is longer than the travel time to 
         * the station A, it returns -1, otherwise it returns 1.
         * If the travel time is equal, then it compares the name of the
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

        // avoids exceptions if there are no stations
        if (transportNetwork.getStationList().size() != 0) {
            unsettledStations = new PriorityQueue<Station>(
                    transportNetwork.getStationList().size(), 
                    shortestTravelTimeComparator);
        }
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
     * @param source                the source station
     * @param destination           the destination station
     * @param departure             the departure time
     * @return                      the time table as a list of stopovers
     */
    public ArrayList<Stopover> calculateRoute(Station source, Station destination,
            Calendar departure) {

        // initializing the calculator
        initCalculator(source);

        // the current station
        Station station;

        // iterating over all stations to calculate the best time table
        while ((station = getNextStation()) != null) {

            // if the destination station is reached, stop here
            if (station.equals(destination)) {
                break;
            }

            // adds the station to the settled stations list
            settledStations.add(station);

            /*
             * somputes the new shortest travel time for the neighbor stations 
             * of the current station and updates it if a shorter travel time is 
             * found.
             */
            updateNeighbors(station, departure);
        }

        // returns the calculated time table as list of stopovers
        return generateStopoverList(source, destination);
    }

    /**
     * Generates an array list of stopover objects of the calculated time table
     * to get handed over to the GUI. 
     * 
     * @param source                the source station
     * @param destination           the destination station
     * @return                      an array list of stopover objects
     */
    private ArrayList<Stopover> generateStopoverList(Station source, Station destination) {

        // checks if there is a path from the destination to the source station
        boolean validPath = false;
        Station tmpStation = destination;
        while ((tmpStation = predecessors.get(tmpStation)) != null) {
            if (tmpStation.equals(source)) {
                validPath = true;
                break;
            }
        }

        // returns null if the stations aren't connected (directly or indirect)
        if (validPath == false) {
            return null;
        }

        // puts a list with all stopovers together
        ArrayList<Stopover> stopoverList = new ArrayList<Stopover>();
        
        // iterates from the destination to the source station
        Station tmpNeighbor = destination;
        while (predecessors.get(tmpNeighbor) != null) {

            // the predecessor of the previous station
            tmpStation = predecessors.get(tmpNeighbor);

            // the connection between the two stations
            Connection connection = transportNetwork.getConnection(tmpStation, 
                    tmpNeighbor);

            // the line with the best time table between the two stations
            Line line = bestLine.get(Arrays.asList(tmpStation, tmpNeighbor));

            /*
             * the departure-, arrival time and the travel duration for the two 
             * stations
             */
            Calendar departureTime = shortestDepartureTime.get(
                    Arrays.asList(tmpStation, tmpNeighbor));
            Calendar arrivalTime = shortestArrivalTime.get(
                    Arrays.asList(tmpStation, tmpNeighbor));
            int travelDuration = getShortestTravelTime(tmpNeighbor);

            /* 
             * creates the stopover object and adds it to the beginning of the
             * stopover list
             */
            Stopover stopover = new Stopover(tmpStation, tmpNeighbor, 
                    connection, line, departureTime, arrivalTime, travelDuration);
            stopoverList.add(0, stopover);

            // saves the current station as the neighbor for the next iteration
            tmpNeighbor = tmpStation;

        }

        // returns the array list of stopover objects
        return stopoverList;
    }

    /**
     * Updates the neighbor stations of a station with the current shortest 
     * travel time.
     * 
     * @param station
     * @param departure
     */
    private void updateNeighbors(Station station, Calendar departure) {

        // shortest travel time to the station
        int stationTravelTime = shortestTravelTime.get(station);

        /*
         * iterates over all neighbors of the station and updates the shortest 
         * travel time
         */
        for (Station neighbor : transportNetwork.getNeighborStationList(station)) {

            // skips if the station got already settled
            if (isSettled(neighbor)) {
                continue;
            }

            // connection to the neighbor station
            Connection connection = 
                transportNetwork.getConnection(station, neighbor);

            /*
             * if there is no connection between the stations, go to the next
             * iteration
             */
            if (connection == null) {
                continue;
            }

            // earliest departure time at the current station
            Calendar earliestDeparture = Calendar.getInstance();
            earliestDeparture.set(Calendar.HOUR_OF_DAY, departure.get(Calendar.HOUR_OF_DAY));
            earliestDeparture.set(Calendar.MINUTE, departure.get(Calendar.MINUTE));
            earliestDeparture.add(Calendar.MINUTE, stationTravelTime);

            /*
             * travel duration from the departure time to the arrival time from 
             * the current station to it's neighbor.
             */
            int duration = connection.getDuration();

            /*
             * the shortest departure time from the current station to it's 
             * neighbor
             */
            Calendar stationDepartureTime = null;

            /*
             * travel duration from the arrival time at the current station
             * until the arrival time of it's neighbor
             * initially it is infinitely
             */
            int neighborTravelTime = Integer.MAX_VALUE;

            // the arrival time at the neighbor station
            Calendar neighborArrivalTime = null;

            /*
             * the line with the best connection to the neighbor is initially
             * null
             */
            Line line = null;

            /*
             * iterates over all lines between the two stations to find the
             * next departure and the line belonging to the next departure.
             */
            for (Line tmpLine : connection.getLines()) {
                TimeTable timeTable = tmpLine.getTimeTable(station, neighbor);

                // the departure time at the current station
                Calendar tmpStationDepartureTime = 
                    timeTable.getNextDeparture(earliestDeparture);

                // the arrival time at the neighbor station
                Calendar tmpNeighborArrivalTime = Calendar.getInstance();
                tmpNeighborArrivalTime.set(0, 0, 0, 
                        tmpStationDepartureTime.get(Calendar.HOUR_OF_DAY), 
                        tmpStationDepartureTime.get(Calendar.MINUTE));
                tmpNeighborArrivalTime.add(Calendar.MINUTE, duration);

                /*
                 * computes the travel duration of the current station arrival 
                 * time until the neighbor station arrival time
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
                int tmpNeighborTravelTime = 
                    stationTravelTime + hours * 60 + minutes;

                /*
                 * if a shorter travel time gets found the time variables get
                 * updated (current station departure time, neighbor departure
                 * time, neighbor arrival time and the line between the two 
                 * stations)
                 */
                if (tmpNeighborTravelTime < neighborTravelTime) {
                    stationDepartureTime = tmpStationDepartureTime;
                    neighborTravelTime = tmpNeighborTravelTime;
                    neighborArrivalTime = tmpNeighborArrivalTime;
                    line = tmpLine;
                }
            }

            /*
             * if a line with a departure to the neighbor station got found
             * (not infinite travel time), various values must get updated
             */
            if (neighborTravelTime != Integer.MAX_VALUE) {
                int shortestTravelTime = getShortestTravelTime(neighbor);

                /*
                 * if the travel time to the neighbor station got shorter, the 
                 * following values must get updated
                 * - shortest travel time for the neighbor station
                 * - shortest arrival time at the neighbor station
                 * - shortest departure time at the current station
                 * - the best line between the two stations
                 * - the new predecessor of the neighbor station
                 */
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
     * This function initializes the dijkstra calculator. It basically resets
     * the needed lists and hash maps and sets a new source station.
     * 
     * @param source                the source station
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
     * @param station               the station to check
     * @return                      true if already settled
     */
    private boolean isSettled(Station station) {
        return settledStations.contains(station);
    }

    /**
     * Sets the shortest travel time in minutes for a given station
     * 
     * @param station               the station
     * @param travelTime            the travel time (minutes)
     */
    private void setShortestTravelTime(Station station, int travelTime) {

        /*
         * removes the station from the unsettled stations list, because the 
         * order of the unsettled stations list could be different after setting 
         * the travel time 
         */
        unsettledStations.remove(station);

        // sets the shortest travel time
        shortestTravelTime.put(station, travelTime);

        // adds the station again into the unsettled stations list
        unsettledStations.add(station);
    }

    /**
     * Returns the shortest travel time for a given station.
     * 
     * @param station               the station
     * @return                      the time in minutes
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
     * Returns the next station to visit (with the next lowest travel time)
     * 
     * @return                      the next station
     */
    private Station getNextStation() {
        return unsettledStations.poll();
    }
}
