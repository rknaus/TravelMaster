package ch.netgeek.travelmaster.gui;

import ch.netgeek.travelmaster.algorithm.Stopover;

import java.util.ArrayList;

/**
 * This class coordinates the communication between the model and the view and 
 * itself. It requests the calculated route from the model class and launches the table 
 * view.
 * 
 * @author        Ruben Knaus, Dieu P. Van
 * @version       1.0
 *
 */
public class TableController {

	// class variable declaration
	private ArrayList<Stopover> stopoverList;
	private TableModel tableModel;
	private TableView tableView;

	/**
	 * Initializes the table controller
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
