package ch.netgeek.travelmaster.gui;
import ch.netgeek.travelmaster.algorithm.Stopover;

import java.util.ArrayList;

public class TableController {
	
    private ArrayList<Stopover> route;
    private TableModel tableModel;
    private TableView tableView;
    
    /**
     * Initializes the Table Controller
     */
    public TableController() {
    	route = new ArrayList<Stopover>();
    	tableModel = new TableModel(route);
    	tableView = new TableView(tableModel);
    }
    
    /**
     * Returns the current calculated route
     * 
     * @return                  The route as Stopover list
     */
    public ArrayList<Stopover> getRoute() {
        return route;
    }

    /**
     * Sets the current calculated route
     * 
     * @param route             The route as Stopover list
     */
    public void setRoute(ArrayList<Stopover> route) {
        this.route = route;
    }

    /**
     * Returns the table view object
     * 
     * @return                  The table view
     */
    public TableView getView(){
    	return tableView;
    }
}
