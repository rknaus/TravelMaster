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
     * @param source           The source station
     * @param destination      The destination station
     * @param connection       The connection between source and destination
     * @param line             The line on the connection
     * @param departureTime    The departure time at the source
     * @param arrivalTime      The arrival time at the destination
     * @param travelDuration   The total travel duration
     */
    public Stopover(Station source, Station destination, Connection connection,
            Line line, Calendar departureTime, Calendar arrivalTime, int travelDuration) {
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
     * @param source            The source station
     */
    public void setSource(Station source) {
        this.source = source;
    }

    /**
     * Sets the destination station.
     * 
     * @param destination       The destination station
     */
    public void setDestination(Station destination) {
        this.destination = destination;
    }

    /**
     * Sets the connection between the two stations.
     * 
     * @param connection        The connection between source and destination
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Sets the line on the connection.
     * 
     * @param line              The line on the connection
     */
    public void setLine(Line line) {
        this.line = line;
    }

    /**
     * Sets the departure time at the source.
     * 
     * @param departureTime    The departure time at the source
     */
    public void setDepartureTime(Calendar departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Sets the arrival time at the destination.
     * 
     * @param arrivalTime     The arrival time at the destination
     */
    public void setArrivalTime(Calendar arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Sets the total travel duration.
     * 
     * @param travelDuration   The total travel duration
     */
    public void setTravelDuration(int travelDuration) {
        this.travelDuration = travelDuration;
    }

    /**
     * Returns the source station.
     * 
     * @return                  The source station
     */
    public Station getSource() {
        return source;
    }

    /**
     * Returns the destination station.
     * 
     * @return                  The destination station
     */
    public Station getDestinatio() {
        return destination;
    }
    
    /**
     * Returns the connection between the source and the destination.
     * 
     * @return                  The connection between source and destination
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Returns the line on the connection.
     * 
     * @return                  The line on the connection
     */
    public Line getLine() {
        return line;
    }

    /**
     * Returns the departure time at the source.
     * 
     * @return                  The departure time at the source
     */
    public Calendar getDepartureTime() {
        return departureTime;
    }

    /**
     * Returns the arrival time at the destination.
     * 
     * @return                  The arrival time at the destination
     */
    public Calendar getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Returns the total travel duration.
     * @return                  The total travel duration
     */
    public int getTravelDuration() {
        return travelDuration;
    }
}
