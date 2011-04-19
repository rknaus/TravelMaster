package ch.netgeek.travelmaster.route;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Represents a timetable belonging to a line for a source and destination
 * station. The departure times are stored in a LinkedList (sorted ascending
 * by departure time).
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class TimeTable {

    // variables declaration
    LinkedList<Calendar> departures;

    /**
     * A new Linked List of Calendar elements gets created when the constructor
     * gets called.
     */
    public TimeTable() {
        departures = new LinkedList<Calendar>();
    }

    /**
     * Adds a departure (Calendar object) to the departures list. It stores the
     * departure sorted by time (ascending)
     * 
     * @param departure             The departure time
     */
    public void addDeparture(Calendar departure) {
        if (departures.isEmpty()) {
            departures.add(departure);
        } else {
            Calendar index = departures.getFirst();
            int indexValue = index.get(Calendar.HOUR) * 60 + index.get(Calendar.MINUTE);
            int departureValue = departure.get(Calendar.HOUR) * 60 + departure.get(Calendar.MINUTE);
            ListIterator<Calendar> iterator = departures.listIterator();
            while (iterator.hasNext() && indexValue <= departureValue) {
                index = iterator.next();
                indexValue = index.get(Calendar.HOUR) * 60 + index.get(Calendar.MINUTE);
            }
            departures.add(iterator.nextIndex(), departure);
        }
    }

    /**
     * Returns the next departure time according to the given time. If the
     * departures list is empty, null gets returned. If the given time is later
     * than any departure times, the earliest departure time gets returned.
     * 
     * @param calendar              The wished departure time
     * @return                      The next departure time
     */
    public Calendar getNextDeparture(Calendar departure) {
        if (departures.isEmpty()) {
            return null;
        } else {
            Calendar index = departures.getFirst();
            int indexValue = index.get(Calendar.HOUR) * 60 + index.get(Calendar.MINUTE);
            int departureValue = departure.get(Calendar.HOUR) * 60 + departure.get(Calendar.MINUTE);
            ListIterator<Calendar> iterator = departures.listIterator();
            while (iterator.hasNext() && indexValue <= departureValue) {
                index = iterator.next();
                indexValue = index.get(Calendar.HOUR) * 60 + index.get(Calendar.MINUTE);
            }
            if (indexValue < departureValue) {
                return departures.getFirst();
            } else {
                return departures.get(iterator.nextIndex());
            }
        }
    }

    public ArrayList<Calendar> getDepartures() {
        return null;
    }

}
