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
	private JTable connectionTable;
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
    	connectionTable = new JTable(tableModel);
    	TableColumn cSource = 
    		connectionTable.getColumnModel().getColumn(0);
    	TableColumn cDestination = 
    		connectionTable.getColumnModel().getColumn(1);
    	TableColumn cLines = 
    		connectionTable.getColumnModel().getColumn(2);
    	TableColumn cDeparture = 
    		connectionTable.getColumnModel().getColumn(3);
    	TableColumn cArrival = 
    		connectionTable.getColumnModel().getColumn(4);
    	TableColumn cDuration = 
    		connectionTable.getColumnModel().getColumn(5);
    
    	// sets the titles of the columns
    	cSource.setHeaderValue("Start");
    	cDestination.setHeaderValue("Destination");
    	cLines.setHeaderValue("Line");
    	cDeparture.setHeaderValue("Dep.");
    	cArrival.setHeaderValue("Arr.");
    	cDuration.setHeaderValue("Duration");
    	
    	// table design (font, border, size etc.)
    	connectionTable.setOpaque(false);
    	connectionTable.setBackground(Color.WHITE);
    	connectionTable.getTableHeader().setReorderingAllowed(false);
    	connectionTable.getTableHeader().setResizingAllowed(false);
    	connectionTable.getTableHeader().setFont(new Font("arial", 1, 12));
        connectionTable.setFont(new Font("arial", 0, 12));
        connectionTable.setRowHeight(25);
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
    	return connectionTable;
    }
}
