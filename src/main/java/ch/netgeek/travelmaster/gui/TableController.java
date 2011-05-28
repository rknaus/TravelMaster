package ch.netgeek.travelmaster.gui;
import ch.netgeek.travelmaster.algorithm.Stopover;

import java.util.ArrayList;

public class TableController {
	
    private ArrayList<Stopover> connectionResult;
    private TableModel tableModel;
    private TableView tableView;
    
    public TableController(ArrayList<Stopover> connectionResult){
    	this.connectionResult = connectionResult;
    	tableModel = new TableModel(connectionResult);
    	tableView = new TableView(tableModel);
    }
    
    public TableView getView(){
    	return tableView;
    }
}
