package ch.netgeek.travelmaster.algorithm;

import java.util.Calendar;

import ch.netgeek.travelmaster.route.Connection;
import ch.netgeek.travelmaster.route.Line;
import ch.netgeek.travelmaster.route.Station;

/**
 * A setter and getter class which is required to add the time table information 
 * as a list of objects to an ArrayList in the RouteCalculator
 * 
 * @author 		Ruben Knaus, Dieu P. Van
 * @version 	1.0
 */
public class Stopover {

    //variable declaration
    private Station source;
    private Station destination;
    private Connection connection;
    private Line line;
    private Calendar arrivalTime;
    private Calendar departureTime;
    private int travelDuration;

    /**
     * Initializes the Stopover object
     * 
     * @param source                the source station
     * @param destination           the destination station
     * @param connection            the connection between source and destination
     * @param line                  the line on the connection
     * @param departureTime         the departure time at the source
     * @param arrivalTime           the arrival time at the destination
     * @param travelDuration        the total travel duration
     */
    public Stopover(Station source, Station destination, Connection connection,
            Line line, Calendar departureTime, Calendar arrivalTime, 
            int travelDuration) {
        setSource(source);
        setDestination(destination);
        setConnection(connection);
        setLine(line);
        setDepartureTime(departureTime);
        setArrivalTime(arrivalTime);
        setTravelDuration(travelDuration);
    }

    /**
     * Sets the source station.
     * 
     * @param source                the source station
     */
    public void setSource(Station source) {
        this.source = source;
    }

    /**
     * Sets the destination station.
     * 
     * @param destination           the destination station
     */
    public void setDestination(Station destination) {
        this.destination = destination;
    }

    /**
     * Sets the connection between the two stations.
     * 
     * @param connection            the connection between source and destination
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Sets the line on the connection.
     * 
     * @param line                  the line on the connection
     */
    public void setLine(Line line) {
        this.line = line;
    }

    /**
     * Sets the departure time at the source.
     * 
     * @param departureTime         the departure time at the source
     */
    public void setDepartureTime(Calendar departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Sets the arrival time at the destination.
     * 
     * @param arrivalTime           the arrival time at the destination
     */
    public void setArrivalTime(Calendar arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Sets the total travel duration in minutes.
     * 
     * @param travelDuration        the total travel duration
     */
    public void setTravelDuration(int travelDuration) {
        this.travelDuration = travelDuration;
    }

    /**
     * Returns the source station.
     * 
     * @return                      the source station
     */
    public Station getSource() {
        return source;
    }

    /**
     * Returns the destination station.
     * 
     * @return                      the destination station
     */
    public Station getDestinatio() {
        return destination;
    }

    /**
     * Returns the connection between the source and the destination.
     * 
     * @return                      the connection between source and destination
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Returns the line on the connection.
     * 
     * @return                      the line on the connection
     */
    public Line getLine() {
        return line;
    }

    /**
     * Returns the departure time at the source.
     * 
     * @return                      the departure time at the source
     */
    public Calendar getDepartureTime() {
        return departureTime;
    }

    /**
     * Returns the arrival time at the destination.
     * 
     * @return                      the arrival time at the destination
     */
    public Calendar getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Returns the total travel duration.
     * @return                      the total travel duration
     */
    public int getTravelDuration() {
        return travelDuration;
    }
}
