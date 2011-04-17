package ch.netgeek.travelmaster.route;

import java.util.ArrayList;
import java.util.Date;

public class TimeTable {

    ArrayList<Date> departures;


    public ArrayList<Date> getNextDeparture(){
        return departures;
    }

    public void setDepartures(ArrayList<Date> departures){
        departures.addAll(departures);  
    }

    public ArrayList<Date> getDepartures(){
        return departures;
    }

    public void setDeparture(Date departure){
        departures.add(departure);
    }

}
