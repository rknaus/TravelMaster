package ch.netgeek.travelmaster.route;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents a timetable belonging to a line for a source and a destination
 * station. The departure times are stored in a LinkedList (sorted ascending
 * by departure time).
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     1.0
 * 
 */
public class TimeTable {

	// variable declaration
	ArrayList<Calendar> departures;

	/**
	 * A new Array List of Calendar elements will be created when the constructor
	 * gets called.
	 */
	public TimeTable() {
		departures = new ArrayList<Calendar>();
	}

	/**
	 * Adds a departure (Calendar object) to the departure list. It stores the
	 * departure sorted by time (ascending)
	 * 
	 * @param departure             The departure time
	 */
	public void addDeparture(Calendar departure) {
		if (departures.isEmpty()) {
			departures.add(departure);
		} else {
			int departureValue = departure.get(Calendar.HOUR_OF_DAY) * 60 
			+ departure.get(Calendar.MINUTE);
			boolean added = false;
			for (int i = 0; i < departures.size(); i++) {
				Calendar indexCalendar = departures.get(i);
				int indexValue = indexCalendar.get(Calendar.HOUR_OF_DAY) * 60
				+ indexCalendar.get(Calendar.MINUTE);
				if (indexValue >= departureValue) {
					departures.add(i, departure);
					added = true;
					break;
				}
			}
			if (added == false) {
				departures.add(departure);
			}
		}
	}

	/**
	 * Returns the next departure time according to the given time. If the
	 * departure list is empty, null will be returned. If the given time is later
	 * than any departure times, the earliest departure time will be returned.
	 * 
	 * @param calendar              The wished departure time
	 * @return                      The next departure time
	 */
	public Calendar getNextDeparture(Calendar departure) {
		if (departures.isEmpty()) {
			return null;
		} else {
			int departureValue = departure.get(Calendar.HOUR_OF_DAY) * 60 
			+ departure.get(Calendar.MINUTE);
			for (int i = 0; i < departures.size(); i++) {
				Calendar indexCalendar = departures.get(i);
				int indexValue = indexCalendar.get(Calendar.HOUR_OF_DAY) * 60
				+ indexCalendar.get(Calendar.MINUTE);
				if (indexValue >= departureValue) {
					return departures.get(i);
				}
			}
			return departures.get(0);
		}
	}

	/**
	 * Returns the departures.
	 * 
	 * @return                      The ArrayList of departures
	 */
	public ArrayList<Calendar> getDepartures() {
		return departures;
	}
}
