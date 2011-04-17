package ch.netgeek.travelmaster.route;

import java.util.ArrayList;

public class Connection {
    
    private Station stationA;
    private Station stationB;
    private int duration;
    private ArrayList<Line> lines;
    private ArrayList<TimeTable> timeTableA;
    private ArrayList<TimeTable> timeTableB;

    public Connection(Station stationA, Station stationB, int duration) {
        setStationA(stationA);
        setStationB(stationB);
        setDuration(duration);
        lines = new ArrayList<Line>();
        timeTableA = new ArrayList<TimeTable>();
        timeTableB = new ArrayList<TimeTable>();
    }

    public void setStationA(Station station) {
        stationA = station;
    }

    public void setStationB(Station station) {
        stationB = station;
    }

    public Station getStationA() {
        return stationA;
    }

    public Station getStationB() {
        return stationB;
    }

    public Station getNeighborStation(Station station) {
        //TODO  Check if given station is stationA or stationB
        //TODO  Return neighbor station
        return null;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void addTimeTableA(Line line, TimeTable timeTable) {
        //TODO  Add timetable for particular line to timetableA
        //      Maybe like that: timeTableA.add(line, timeTable);
    }

    public void addTimeTableB(Line line, TimeTable timeTable) {
        //TODO  Add timetable for particular line to timetableB
        //      Maybe like that: timeTableB.add(line, timeTable);
    }

}
