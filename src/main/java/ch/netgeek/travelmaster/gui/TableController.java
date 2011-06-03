package ch.netgeek.travelmaster.gui;
import ch.netgeek.travelmaster.algorithm.Stopover;

import java.util.ArrayList;

public class TableController {
	
    private ArrayList<Stopover> stopoverList;
    private TableModel tableModel;
    private TableView tableView;
    
    /**
     * Initializes the Table Controller
     */
    public TableController() {
        stopoverList = new ArrayList<Stopover>();
    	tableModel = new TableModel(stopoverList);
    	tableView = new TableView(tableModel);
    }
    
    /**
     * Returns the current calculated route list
     * 
     * @return                  The route as Stopover list
     */
    public ArrayList<Stopover> getStopoverList() {
        return stopoverList;
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
