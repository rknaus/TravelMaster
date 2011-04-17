package ch.netgeek.travelmaster.route;

import java.util.ArrayList;
import java.util.Date;

public class TimeTable {

    ArrayList<Date> departures;


    public ArrayList<Date> getNextDeparture(Date date) {
        //TODO  Should return the next departure
        return null;
    }

    public void setDepartures(ArrayList<Date> departures) {
        this.departures = departures;
    }

    public ArrayList<Date> getDepartures(){
        return departures;
    }

    public void addDeparture(Date departure){
        departures.add(departure);
    }

}
