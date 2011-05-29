package ch.netgeek.travelmaster.gui;
import ch.netgeek.travelmaster.algorithm.Stopover;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
	/**
	 * serialVersionUID is required by the AbstractTableModel class
	 */
	private static final long serialVersionUID = 6524119601232296864L;

	// ArrayList for the connection result
	private ArrayList<Stopover> connectionResult;
	    
	 /**
	  * The constructor of the TableModel class requires data to be returned by
	  * its functions.
	  * 
	  * @param connectionResult      list of connection to be stored in the <br>
	  * object instance
	  */
	 public TableModel(ArrayList<Stopover> connectionResult) {
		 this.connectionResult = connectionResult;
	 }
	    
	 /**
	  * Returns the number of columns and implements the needed columns.
	  * 
	  * @return              the number of columns
	  */
	 @Override
	 public int getColumnCount() {
		 return 6;
	 }

	 /**
	  * Returns the number of rows.
	  * 
	  * @return		the number of rows
	  */
	 @Override
	 public int getRowCount() {
		 if (connectionResult.isEmpty()) {
			 return 12;
		 }     
	     return connectionResult.size();
	 }

	 /**
	  * The getValue method returns the data of the fields of the JTable.
	  * 
	  * @param rowIndex      specifies the row number to get data from
	  * @param columnIndex   specifies the column number to get data from
	  * @return              Object-Array with two dimensions
	  */
	 @Override
	 public Object getValueAt(int rowIndex, int columnIndex) {
		 
	     /* 
	      * If the stopover list has no entries, give back a message which says 
	      * that there is no data.
	      */
	     if (connectionResult.isEmpty()) {
	         return " ";
	     }
	 	        
	     /*
	      * Depending on the rowIndex and the columnIndex, the correct data is
	      * given out
	      */
	     if (columnIndex == 0) {                 
	    	 // Source station column
	         return " " + connectionResult.get(rowIndex).getSource();
	     } else if (columnIndex == 1) {
	    	 // Destination column
	    	 return " " + connectionResult.get(rowIndex).getDestinatio();
	     } else if (columnIndex == 2) {          
	    	 // line column
	         return connectionResult.get(rowIndex).getLine();
	     } else if (columnIndex == 3) {          
	    	 // departure column
	         return connectionResult.get(rowIndex).getDepartureTime();
	     } else if (columnIndex == 4) {          
	    	 // arrival column
	         return connectionResult.get(rowIndex).getArrivalTime();
	     } else if (columnIndex == 5) {          
	    	 // duration column
	         return connectionResult.get(rowIndex).getTravelDuration();
	     }
	        
	     /* returns null if the columnIndex is too small or too big */
	     return null;
	 }
}

