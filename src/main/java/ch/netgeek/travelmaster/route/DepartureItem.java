package ch.netgeek.travelmaster.route;

/**
 * Represents a departure table item which can get displayed in the gui together
 * with other departure items as a departure list of a station.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class DepartureItem implements Comparable<DepartureItem> {

    private int hour;
    private int minute;
    private int line;
    
    /**
     * Initializes the departure item.
     * 
     * @param hour                      The departure hour
     * @param minute                    The departure minute
     * @param station                   The neighbor station
     */
    public DepartureItem(int hour, int minute, int line) {
        setHour(hour);
        setMinute(minute);
        setLine(line);
    }
    
    /**
     * Returns the departure hour.
     * 
     * @return                          The departure hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Sets the departure hour.
     * 
     * @param hour                      The departure hour
     */
    public void setHour(int hour) {
        this.hour = hour;
    }

    /**
     * Returns the departure minute.
     * 
     * @return                          The departure minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Sets the departure minute
     * 
     * @param minute                    The departure minute
     */
    public void setMinute(int minute) {
        this.minute = minute;
    }

    /**
     * Returns the line to the neighbor station
     * 
     * @return                          The line number
     */
    public int getLine() {
        return line;
    }

    /**
     * Sets the line to the neighbor station
     * 
     * @param line                      The line number
     */
    public void setLine(int line) {
        this.line = line;
    }
    
    /**
     * Compares the departure item with another departure item.
     */
    @Override
    public int compareTo(DepartureItem departureItem) {
        if (hour < departureItem.getHour()) return -1;
        if (hour > departureItem.getHour()) return +1;
        if (minute < departureItem.getMinute()) return -1;
        if (minute > departureItem.getMinute()) return +1;
        if (line < departureItem.getLine()) return -1;
        if (line > departureItem.getLine()) return +1;
        return 0;
    }
}
