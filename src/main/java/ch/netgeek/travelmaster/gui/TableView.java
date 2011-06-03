package ch.netgeek.travelmaster.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 * This class represents the connection result as a table.
 *
 * @author      Ruben Knaus, Dieu P. Van
 * @version     0.1
 *
 */

public class TableView {
	
	// variable declaration
	private JTable stopoverTable;
	private TableModel tableModel;
	
    /**
     * Initializes the table view.
     * 
     * @param tableModel      The table model
     */
    public TableView(TableModel tableModel) {
        this.tableModel = tableModel;
        createTable();
    }
    
    /**
     * creates a new table for the connection result
     */    
    public void createTable(){ 
        stopoverTable = new JTable(tableModel);
    	TableColumn cSource = 
    	    stopoverTable.getColumnModel().getColumn(0);
    	TableColumn cDestination = 
    	    stopoverTable.getColumnModel().getColumn(1);
    	TableColumn cLines = 
    	    stopoverTable.getColumnModel().getColumn(2);
    	TableColumn cDeparture = 
    	    stopoverTable.getColumnModel().getColumn(3);
    	TableColumn cArrival = 
    	    stopoverTable.getColumnModel().getColumn(4);
    	TableColumn cDuration = 
    	    stopoverTable.getColumnModel().getColumn(5);
    
    	// sets the titles of the columns
    	cSource.setHeaderValue("Start");
    	cDestination.setHeaderValue("Destination");
    	cLines.setHeaderValue("Line");
    	cDeparture.setHeaderValue("Dep.");
    	cArrival.setHeaderValue("Arr.");
    	cDuration.setHeaderValue("Duration");
    	
    	// table design (font, border, size etc.)
    	stopoverTable.setOpaque(false);
    	stopoverTable.setRowSelectionAllowed(false);
    	stopoverTable.setBackground(Color.WHITE);
    	stopoverTable.getTableHeader().setReorderingAllowed(false);
    	stopoverTable.getTableHeader().setResizingAllowed(false);
    	stopoverTable.getTableHeader().setFont(new Font("arial", 1, 12));
    	stopoverTable.setFont(new Font("arial", 0, 12));
    	stopoverTable.setRowHeight(25);
        cSource.setPreferredWidth(110);
        cDestination.setPreferredWidth(110);
        cLines.setPreferredWidth(40);
        cDeparture.setPreferredWidth(50);
        cArrival.setPreferredWidth(50);
        cDuration.setPreferredWidth(50);
    }
    
    /**
     * Returns the connection table. Needed for the GUI
     *
     * @return      instance of the JTable object
     */
    public JTable getTable(){
    	return stopoverTable;
    }
}
