package ch.netgeek.travelmaster.route;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a line which runs between several statiomns over connections. 
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class Line {
    
    // variables declaration
    private int number;
    private String type;
    private HashMap<List<Station>, TimeTable> timeTables;

    /**
     * Requires a line number (int) and a type (String).
     * 
     * @param number            The line number
     * @param type              The line type (Bus, Train, ...)
     */
    public Line(int number, String type) {
        setNumber(number);
        setType(type);
    }

    /**
     * Sets the number of the line.
     * 
     * @param number            The line number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Returns the number of the line.
     * 
     * @return                  The line number as integer
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the type of the line.
     * 
     * @param type              The line type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the type of the line.
     * 
     * @return                  The line type as String
     */
    public String getType() {
        return type;
    }
    
    /**
     * Adds a departure Time to the Line. The departure time requires a source
     * Station and a destination Station. The departure time is also required.
     * The function stores the departure time in a hash map where the source and
     * destination stations are the keys and the departure time gets added to a
     * TimeTable object which is the value of the hash map.
     * 
     * @param source            The source station
     * @param destination       The destination station
     * @param departure         The departure time
     */
    public void addDeparture(Station source, Station destination, Calendar departure) {
        TimeTable timeTable = timeTables.get(Arrays.asList(source, destination));
        if (timeTable != null) {
            timeTable.addDeparture(departure);
        } else {
            timeTable = new TimeTable();
            timeTable.addDeparture(departure);
            timeTables.put(Arrays.asList(source, destination), timeTable);
        }
    }
    
    /**
     * Returns the timetable for a source to a destination station. If there is
     * no existent timetable, the function returns null.
     * 
     * @param source            The source station
     * @param destination       The destination station
     * @return                  The timetable for the source to the destination
     *                          station.
     */
    public TimeTable getTimeTable(Station source, Station destination) {
        return timeTables.get(Arrays.asList(source, destination));
    }
    
}
