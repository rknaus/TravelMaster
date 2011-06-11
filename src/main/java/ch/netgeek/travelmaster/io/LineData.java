package ch.netgeek.travelmaster.io;

import java.util.ArrayList;

/**
 * A getter and setter class which is required by the XMLReader to be used
 * as return value of Line objects in an ArrayList.
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     1.0
 */
public class LineData {

    // variable declaration
    private int number;
    private String type;
    private ArrayList<String> stations;
    private ArrayList<String> departuresFirstStation;
    private ArrayList<String> departuresLastStation;

    /**
     * Initializes the LineData object.
     * 
     * @param number                    The line number
     * @param type                      The line type
     * @param stations                  The list of stations the Line serves
     * @param departuresFirstStation    The departure times of the first station
     * @param departuresLastStation     The departure times of the last station
     */
    public LineData(int number, String type, ArrayList<String> stations,
            ArrayList<String> departuresFirstStation, 
            ArrayList<String> departuresLastStation) {
        setNumber(number);
        setType(type);
        setStations(stations);
        setDeparturesFirstStation(departuresFirstStation);
        setDeparturesLastStation(departuresLastStation);
    }

    /**
     * Returns the line number.
     * 
     * @return                          the line number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the line number.
     * 
     * @param number                    the line number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Returns the line type.
     * 
     * @return                          the line type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the line type.
     * 
     * @param type                      the line type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the stations the line serves.
     * 
     * @return                          the station list
     */
    public ArrayList<String> getStations() {
        return stations;
    }

    /**
     * Sets the stations the line serves.
     * 
     * @param stations                  the station list
     */
    public void setStations(ArrayList<String> stations) {
        this.stations = stations;
    }

    /**
     * Returns the departure times of the first station.
     * 
     * @return                          the departure list of the first station
     */
    public ArrayList<String> getDeparturesFirstStation() {
        return departuresFirstStation;
    }

    /**
     * Sets the departure times of the first station.
     * 
     * @param departuresFirstStation    the departure list of the first station
     */
    public void setDeparturesFirstStation(ArrayList<String> departuresFirstStation) {
        this.departuresFirstStation = departuresFirstStation;
    }

    /**
     * Returns the depature times of the last station.
     * 
     * @return                          the departure list of the last station
     */
    public ArrayList<String> getDeparturesLastStation() {
        return departuresLastStation;
    }

    /**
     * Sets the departure times of the last station.
     * 
     * @param departuresLastStation     the departure list of the last station
     */
    public void setDeparturesLastStation(ArrayList<String> departuresLastStation) {
        this.departuresLastStation = departuresLastStation;
    }
}
